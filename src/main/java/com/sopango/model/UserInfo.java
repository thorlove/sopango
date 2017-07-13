package com.sopango.model;

import java.io.Serializable;

public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long uk;
    private String uname;
    private String avatar_url;
    private Integer fans_count;
    private Integer follow_count;
    private Integer pubshare_count;
    private Integer album_count;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUk() {
        return uk;
    }
    public void setUk(Long uk) {
        this.uk = uk;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getAvatar_url() {
        return avatar_url;
    }
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
    
    
    public Integer getFans_count() {
        return fans_count;
    }
    public void setFans_count(Integer fans_count) {
        this.fans_count = fans_count;
    }
    public Integer getFollow_count() {
        return follow_count;
    }
    public void setFollow_count(Integer follow_count) {
        this.follow_count = follow_count;
    }
    public Integer getPubshare_count() {
        return pubshare_count;
    }
    public void setPubshare_count(Integer pubshare_count) {
        this.pubshare_count = pubshare_count;
    }
    public Integer getAlbum_count() {
        return album_count;
    }
    public void setAlbum_count(Integer album_count) {
        this.album_count = album_count;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", uk=" + uk + ", uname=" + uname
                + ", avatar_url=" + avatar_url + ", fans_count=" + fans_count
                + ", follow_count=" + follow_count + ", pubshare_count="
                + pubshare_count + ", album_count=" + album_count + "]";
    }
    
}
