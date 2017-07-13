package com.sopango.model.data;

/**
 * 用户粉丝
 * http://pan.baidu.com/pcloud/friend/getfanslist?query_uk=%s&limit=24&start=%s&bdstoken=null&channel=chunlei&clienttype=0&web=1&logid=MTQ3NDAzNjQwNzg3OTAuNzM1MzMxMDUyMDczMjYxNA== (query_uk limit start是必须参数)
 * @author thb
 * @date 2017-7-11 下午3:51:14
 * @since  V1.0
 * 
 */
public class FansData {
    private String fans_uname;
    private String avatar_url;
    private Integer follow_count;
    private Integer fans_count;
    private Long follow_time;
    private Integer pubshare_count;
    private Long fans_uk;
    private Integer album_count;
    
    public String getFans_uname() {
        return fans_uname;
    }
    public void setFans_uname(String fans_uname) {
        this.fans_uname = fans_uname;
    }
    public String getAvatar_url() {
        return avatar_url;
    }
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
    public Integer getFollow_count() {
        return follow_count;
    }
    public void setFollow_count(Integer follow_count) {
        this.follow_count = follow_count;
    }
    public Integer getFans_count() {
        return fans_count;
    }
    public void setFans_count(Integer fans_count) {
        this.fans_count = fans_count;
    }
    public Long getFollow_time() {
        return follow_time;
    }
    public void setFollow_time(Long follow_time) {
        this.follow_time = follow_time;
    }
    public Integer getPubshare_count() {
        return pubshare_count;
    }
    public void setPubshare_count(Integer pubshare_count) {
        this.pubshare_count = pubshare_count;
    }
    public Long getFans_uk() {
        return fans_uk;
    }
    public void setFans_uk(Long fans_uk) {
        this.fans_uk = fans_uk;
    }
    public Integer getAlbum_count() {
        return album_count;
    }
    public void setAlbum_count(Integer album_count) {
        this.album_count = album_count;
    }
    @Override
    public String toString() {
        return "FansData [fans_uname=" + fans_uname + ", avatar_url="
                + avatar_url + ", follow_count=" + follow_count
                + ", fans_count=" + fans_count + ", follow_time=" + follow_time
                + ", pubshare_count=" + pubshare_count + ", fans_uk=" + fans_uk
                + ", album_count=" + album_count + "]";
    }
}
