package com.sopango.model;

import java.io.Serializable;

public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long uk;
    private String uname;
    private String avatar_url;
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
    
    
}
