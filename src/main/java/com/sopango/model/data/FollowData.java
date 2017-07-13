package com.sopango.model.data;

/**
 * 用户关注（订阅）的人:
 * http://yun.baidu.com/pcloud/friend/getfollowlist?query_uk=800444770&limit=24&start=%s&bdstoken=e6f1efec456b92778e70c55ba5d81c3d&channel=chunlei&clienttype=0&web=1&logid=MTQ3NDA3NDg5NzU4NDAuMzQxNDQyMDY2MjA5NDA4NjU=
 * @author thb
 * @date 2017-7-11 下午3:52:41
 * @since  V1.0
 */
public class FollowData {
    
    private String follow_uname;
    private String avatar_url;
    private Integer follow_count;
    private Integer fans_count;
    private Long follow_time;
    private Integer pubshare_count;
    private Long follow_uk;
    private Integer album_count;
    
    public String getFollow_uname() {
        return follow_uname;
    }
    public void setFollow_uname(String follow_uname) {
        this.follow_uname = follow_uname;
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
    public Long getFollow_uk() {
        return follow_uk;
    }
    public void setFollow_uk(Long follow_uk) {
        this.follow_uk = follow_uk;
    }
    public Integer getAlbum_count() {
        return album_count;
    }
    public void setAlbum_count(Integer album_count) {
        this.album_count = album_count;
    }
    @Override
    public String toString() {
        return "FollowData [follow_uname=" + follow_uname + ", avatar_url="
                + avatar_url + ", follow_count=" + follow_count
                + ", fans_count=" + fans_count + ", follow_time=" + follow_time
                + ", pubshare_count=" + pubshare_count + ", follow_uk=" + follow_uk
                + ", album_count=" + album_count + "]";
    }
    
    
}
