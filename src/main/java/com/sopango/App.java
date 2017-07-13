package com.sopango;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.sopango.common.DataUtil;
import com.sopango.common.PKGen;
import com.sopango.common.RedisUtil;
import com.sopango.dao.IAvaiukDao;
import com.sopango.dao.IShareDataDao;
import com.sopango.dao.IUserInfoDao;
import com.sopango.model.Avaiuk;
import com.sopango.model.ShareData;
import com.sopango.model.UserInfo;
import com.sopango.model.data.AlbumData;
import com.sopango.model.data.FansData;
import com.sopango.model.data.FileData;
import com.sopango.model.data.FollowData;
import com.sopango.model.data.RecordsData;
import com.sopango.model.data.YunData;

public class App {
    
    private static Logger log = Logger.getLogger(App.class);
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
    static String FOLLOW_URL = "http://yun.baidu.com/pcloud/friend/getfollowlist?query_uk=%s&limit=24&start=%s&bdstoken=e6f1efec456b92778e70c55ba5d81c3d&channel=chunlei&clienttype=0&web=1&logid=MTQ3NDA3NDg5NzU4NDAuMzQxNDQyMDY2MjA5NDA4NjU=";
    static String FANS_URL = "http://pan.baidu.com/pcloud/friend/getfanslist?query_uk=%s&limit=24&start=%s&bdstoken=null&channel=chunlei&clienttype=0&web=1&logid=MTQ3NDAzNjQwNzg3OTAuNzM1MzMxMDUyMDczMjYxNA==";
    static String SHARE_URL = "http://pan.baidu.com/wap/share/home?uk=%s&start=%s";
    static String ALBUM_URL = "https://pan.baidu.com/wap/album/info?uk=%s&album_id=%s";
    static String LINK_URL = "https://pan.baidu.com/wap/link?uk=%s&shareid=%s";
    
    static Map<String, String> HEARD_MAP = new HashMap<String, String>();
    static {
        HEARD_MAP.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
        HEARD_MAP.put("X-Requested-With", "XMLHttpRequest");
        HEARD_MAP.put("Accept", "application/json, text/javascript, */*; q=0.01");
        HEARD_MAP.put("Referer","https://yun.baidu.com/share/home?uk=325913312#category/type=0");
        HEARD_MAP.put("Accept-Language", "zh-CN");
    }

    public static void main(String[] args) {
        App app = new App();
        app.startIndex();
//        app.indexResource(Long.valueOf("2889076181").longValue());
    }

    public void startIndex() {
        SqlSession session = sqlSessionFactory.openSession();
        // 无限循环
        while (true) {
            // 从数据库获取可用uk,可用首先从某个粉丝超多的用户入手,获取他粉丝的uk,存入数据库
            IAvaiukDao avaiukDao = session.getMapper(IAvaiukDao.class);
            Avaiuk avaiuk = avaiukDao.findFirst();
            if(avaiuk!=null && avaiuk.getUk()!=null){
                // 更新数据库,标记该uk已经被用户爬过
                avaiukDao.updateFlag(1, avaiuk.getUk());
                session.commit();session.close();
                getFollow(avaiuk.getUk(), 0,true);
                getFans(avaiuk.getUk(), 1,true);
            }else{
                break;
            }
        }
    }
    
    public void getFollow(Long uk,int start, boolean index){
        log.info("进来getFllow,uk:{"+uk+"},start:{"+start+"}");
        boolean exitUK = false;
        try {
            String flag = RedisUtil.getStringFromJedis("pan:"+uk, "getFollow");
            exitUK = flag!=null;
        } catch (Exception e) {
            exitUK = true;
        }
        if (!exitUK) {
            RedisUtil.addStringToJedis("pan:"+uk, ""+uk, 0, "getFollow");
            if (index) {
                indexResource(uk);
            }
            recFollow(uk,start,true);
        } else {
            if (start > 0) {//分页订阅
                recFollow(uk,start,false);
            } else {
                log.warn("uk is index:{"+uk+"}");
            }
        }
    }
    public void recFollow(long uk,int start,boolean goPage){
        try {
            Thread.sleep(1000*30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String real_url = String.format(FOLLOW_URL, uk, start);
        YunData yunData = DataUtil.getFollowData(real_url,HEARD_MAP);
        List<FollowData> followList = yunData.getFollow_list();
        if (yunData!=null && yunData.getTotal_count() !=null && yunData.getTotal_count() > 0) {
            for (FollowData follow : followList) {
                int follow_count = follow.getFollow_count();
                int shareCount = follow.getPubshare_count();
                if (follow_count > 0 ) {
                    if (shareCount > 0) {
                        getFollow(follow.getFollow_uk(), 0, true);
                    } else {
                        getFollow(follow.getFollow_uk(), 0, false);
                    }
                }
            }
            if(goPage){
                int total_count = yunData.getTotal_count();
                int total_page = (total_count - 1) / 24 + 1;
                for (int i = 1; i < total_page; i++) {
                    getFollow(uk, i * 24,false);
                }
            }
        } else {
            log.info("为空:{"+uk+"}");
        }
    }
    
    
    public void getFans(Long uk,int start, boolean index){
        log.info("进来getFans,uk:{"+uk+"},start:{"+start+"}");
        boolean exitUK = false;
        try {
            String flag = RedisUtil.getStringFromJedis("pan:"+uk, "getFans");
            exitUK = flag!=null;
        } catch (Exception e) {
            exitUK = true;
        }
        if (!exitUK) {
            RedisUtil.addStringToJedis("pan:"+uk, ""+uk, 0, "getFans");
            if (index) {
                indexResource(uk);
            }
            recFans(uk,start,true);
        } else {
            if (start > 0) {//分页订阅
                recFans(uk,start,false);
            } else {
                log.warn("uk is index:{"+uk+"}");
            }
        }
    }
    public void recFans(long uk,int start,boolean goPage){
        try {
            Thread.sleep(1000*30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String real_url = String.format(FANS_URL, uk, start);
        YunData yunData = DataUtil.getFollowData(real_url,HEARD_MAP);
        List<FansData> fansList = yunData.getFans_list();
        if (yunData!=null && yunData.getTotal_count() !=null && yunData.getTotal_count() > 0) {
            for (FansData fans : fansList) {
                int fans_count = fans.getFans_count();
                int shareCount = fans.getPubshare_count();
                if (fans_count > 0 ) {
                    if (shareCount > 0) {
                        getFans(fans.getFans_uk(), 0, true);
                    } else {
                        getFans(fans.getFans_uk(), 0, false);
                    }
                }
            }
            if(goPage){
                int total_count = yunData.getTotal_count();
                int total_page = (total_count - 1) / 24 + 1;
                for (int i = 1; i < total_page; i++) {
                    getFans(uk, i * 24,false);
                }
            }
        } else {
            log.info("为空:{"+uk+"}");
        }
    }
    
    long uinfoId = 0;
    long nullStart = System.currentTimeMillis();
    
    public void indexResource(long uk) {
        while (true) {
            String real_url = String.format(SHARE_URL, uk, 0);
            YunData yunData = DataUtil.getYunData(real_url);
            if (yunData != null) {
//                log.info("{"+yunData.toString()+"}");
                UserInfo yunUinfo = yunData.getUinfo();
                int share_count = yunUinfo.getPubshare_count();
                int album_count = yunUinfo.getAlbum_count();
                if (share_count > 0 || album_count>0) {
                    UserInfo uinfo = new UserInfo();
                    uinfoId = PKGen.nextId();
                    uinfo.setUk(uk);
                    uinfo.setAvatar_url(yunUinfo.getAvatar_url());
                    uinfo.setId(uinfoId);
                    List<RecordsData> recordses = yunData.getFeedata().getRecords();
                    for (RecordsData record : recordses) {
                        ShareData share = new ShareData();
                        share.setId(PKGen.nextId());
                        share.setTitle(record.getTitle());
                        share.setShareid(record.getShareid());
                        share.setUinfo_id(uinfoId);
                        share.setFilecount(record.getFilecount());
                        share.setCategory(record.getCategory());
                        share.setFeed_time(record.getFeed_time());
                        if(record.getFilecount()!=null&&record.getFilecount()>=1 && record.getDir_cnt()==null){
                            FileData file = record.getFilelist().get(0);
                            share.setDir_cnt(0);
                            share.setFilesize(file.getSize());
                        }else{
                            share.setDir_cnt(record.getDir_cnt());
                        }
                        if(!"album".equals(record.getFeed_type())){
                            SqlSession session = sqlSessionFactory.openSession();
                            IShareDataDao shareDao = session.getMapper(IShareDataDao.class);
                            shareDao.save(share);
                            session.commit();session.close();
                        }else{
                            insertAlbum(uk, record.getAlbum_id());
                        }
                    }
                    uinfo.setUname(recordses.get(0).getUsername());
                    SqlSession session = sqlSessionFactory.openSession();
                    IUserInfoDao userDao = session.getMapper(IUserInfoDao.class);
                    userDao.save(uinfo);
                    session.commit();session.close();
                }
                int totalPage = (share_count - 1) / 20 + 1;
                int start = 0;
                if (totalPage > 1) {
                    for (int i = 1; i < totalPage; i++) {
                        start = i * 20;
                        real_url = String.format(SHARE_URL, uk, start);
                        yunData = DataUtil.getYunData(real_url);
                        if (yunData != null) {
//                            log.info("{"+yunData.toString()+"}");
                            List<RecordsData> recordses = yunData.getFeedata().getRecords();
                            for (RecordsData record : recordses) {
                                System.out.println("-->"+record.toString());
                                ShareData share = new ShareData();
                                share.setId(PKGen.nextId());
                                share.setTitle(record.getTitle());
                                share.setShareid(record.getShareid());
                                share.setUinfo_id(uinfoId);
                                share.setFilecount(record.getFilecount());
                                share.setCategory(record.getCategory());
                                if(record.getFilecount()!=null&&record.getFilecount()>=1 && record.getDir_cnt()==null){
                                    FileData file = record.getFilelist().get(0);
                                    share.setDir_cnt(0);
                                    share.setFilesize(file.getSize());
                                }else{
                                    share.setDir_cnt(record.getDir_cnt());
                                    share.setFilesize(1024L);
                                }
                                if(!"album".equals(record.getFeed_type())){
                                    SqlSession session = sqlSessionFactory.openSession();
                                    IShareDataDao shareDao = session.getMapper(IShareDataDao.class);
                                    shareDao.save(share);
                                    session.commit();session.close();
                                }else{
                                    insertAlbum(uk, record.getAlbum_id());
                                }
                            }
                        } else {
                            i--;
                            log.warn("uk:{"+uk+"},msg:{"+yunData+"}");
                            long temp = nullStart;
                            nullStart = System.currentTimeMillis();
                            if ((nullStart - temp) < 1500) {
                                try {
                                    Thread.sleep(60000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                break;
            } else {
                log.warn("uk:{"+uk+"},msg:{"+yunData+"}");
                long temp = nullStart;
                nullStart = System.currentTimeMillis();
                //在1500毫秒内2次请求到的数据都为null时,此时可能被百度限制了,休眠一段时间就可以恢复
                if ((nullStart - temp) < 1500) {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void insertAlbum(Long uk,Long ablum_id){
        String real_url = String.format(ALBUM_URL, uk,ablum_id);
        YunData yunData = DataUtil.getYunData(real_url);
        if (yunData != null) {
            List<AlbumData> albumDataList = yunData.getAlbumlist().getList();
            for (AlbumData album : albumDataList) {
                ShareData share = new ShareData();
                share.setId(PKGen.nextId());
                share.setTitle(album.getServer_filename());
                share.setUinfo_id(uinfoId);
                share.setCategory(album.getCategory());
                share.setAlbum_id(ablum_id);
                share.setFeed_time(album.getAdd_time());
                share.setFilecount(1);
                share.setDir_cnt(0);
                share.setFilesize(album.getSize());
                SqlSession session = sqlSessionFactory.openSession();
                IShareDataDao shareDao = session.getMapper(IShareDataDao.class);
                shareDao.save(share);
                session.commit();session.close();
            }        
        }
    }
}
