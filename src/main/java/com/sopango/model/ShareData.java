package com.sopango.model;

import java.io.Serializable;
import java.sql.Date;

public class ShareData implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private Long shareid;
    private Long uinfo_id;
    private String category;
    private Long album_id;
    private Long feed_time;
    private Integer filecount;
    private Long filesize;
    private Integer dir_cnt;
    private String filePath;
    
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
    public Long getShareid() {
        return shareid;
    }
    public void setShareid(Long shareid) {
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
    public Long getAlbum_id() {
        return album_id;
    }
    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }
    public Long getFeed_time() {
        return feed_time;
    }
    public void setFeed_time(Long feed_time) {
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
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String toString() {
        return "ShareData [id=" + id + ", title=" + title + ", shareid="
                + shareid + ", uinfo_id=" + uinfo_id + ", category=" + category
                + ", album_id=" + album_id + ", feed_time=" + feed_time
                + ", filecount=" + filecount + ", filesize=" + filesize
                + ", dir_cnt=" + dir_cnt + ", filePath=" + filePath + "]";
    }

}
