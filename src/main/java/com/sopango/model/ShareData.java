package com.sopango.model;

import java.io.Serializable;
import java.sql.Date;

public class ShareData implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private String shareid;
    private Long uinfo_id;
    private String category;
    private String album_id;
    private Date feed_time;
    private Integer filecount;
    private Long filesize;
    private Integer dir_cnt;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getShareid() {
        return shareid;
    }
    public void setShareid(String shareid) {
        this.shareid = shareid;
    }
    public Long getUinfo_id() {
        return uinfo_id;
    }
    public void setUinfo_id(Long uinfo_id) {
        this.uinfo_id = uinfo_id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getAlbum_id() {
        return album_id;
    }
    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }
    public Date getFeed_time() {
        return feed_time;
    }
    public void setFeed_time(Date feed_time) {
        this.feed_time = feed_time;
    }
    public Integer getFilecount() {
        return filecount;
    }
    public void setFilecount(Integer filecount) {
        this.filecount = filecount;
    }
    public Long getFilesize() {
        return filesize;
    }
    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }
    public Integer getDir_cnt() {
        return dir_cnt;
    }
    public void setDir_cnt(Integer dir_cnt) {
        this.dir_cnt = dir_cnt;
    }

}
