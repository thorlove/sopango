package com.sopango.model.data;

import java.util.List;

public class RecordsData {
    private String feed_type; //share  album
    private String category;
    private Long shareid;
    private String title;
    private Integer filecount;
    private Long uk;
    private Long feed_time;
    private String desc;
    private Long album_id;
    private String username;
    private List<FileData> filelist;
    
    private Integer dir_cnt;//dir_cnt
    private Integer category_1_cnt;//category_x_cnt
    private Integer category_2_cnt;
    private Integer category_3_cnt;
    private Integer category_4_cnt;
    private Integer category_5_cnt;
    private Integer category_6_cnt;
    private Integer category_7_cnt;
    private Integer category_8_cnt;
    private Integer category_9_cnt;
    
    public String getFeed_type() {
        return feed_type;
    }
    public void setFeed_type(String feed_type) {
        this.feed_type = feed_type;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Long getShareid() {
        return shareid;
    }
    public void setShareid(Long shareid) {
        this.shareid = shareid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getFilecount() {
        return filecount;
    }
    public void setFilecount(Integer filecount) {
        this.filecount = filecount;
    }
    public Long getUk() {
        return uk;
    }
    public void setUk(Long uk) {
        this.uk = uk;
    }
    public Long getFeed_time() {
        return feed_time;
    }
    public void setFeed_time(Long feed_time) {
        this.feed_time = feed_time;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public List<FileData> getFilelist() {
        return filelist;
    }
    public void setFilelist(List<FileData> filelist) {
        this.filelist = filelist;
    }
    public Long getAlbum_id() {
        return album_id;
    }
    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }
    public Integer getDir_cnt() {
        return dir_cnt;
    }
    public void setDir_cnt(Integer dir_cnt) {
        this.dir_cnt = dir_cnt;
    }
    public Integer getCategory_1_cnt() {
        return category_1_cnt;
    }
    public void setCategory_1_cnt(Integer category_1_cnt) {
        this.category_1_cnt = category_1_cnt;
    }
    public Integer getCategory_2_cnt() {
        return category_2_cnt;
    }
    public void setCategory_2_cnt(Integer category_2_cnt) {
        this.category_2_cnt = category_2_cnt;
    }
    public Integer getCategory_3_cnt() {
        return category_3_cnt;
    }
    public void setCategory_3_cnt(Integer category_3_cnt) {
        this.category_3_cnt = category_3_cnt;
    }
    public Integer getCategory_4_cnt() {
        return category_4_cnt;
    }
    public void setCategory_4_cnt(Integer category_4_cnt) {
        this.category_4_cnt = category_4_cnt;
    }
    public Integer getCategory_5_cnt() {
        return category_5_cnt;
    }
    public void setCategory_5_cnt(Integer category_5_cnt) {
        this.category_5_cnt = category_5_cnt;
    }
    public Integer getCategory_6_cnt() {
        return category_6_cnt;
    }
    public void setCategory_6_cnt(Integer category_6_cnt) {
        this.category_6_cnt = category_6_cnt;
    }
    public Integer getCategory_7_cnt() {
        return category_7_cnt;
    }
    public void setCategory_7_cnt(Integer category_7_cnt) {
        this.category_7_cnt = category_7_cnt;
    }
    public Integer getCategory_8_cnt() {
        return category_8_cnt;
    }
    public void setCategory_8_cnt(Integer category_8_cnt) {
        this.category_8_cnt = category_8_cnt;
    }
    public Integer getCategory_9_cnt() {
        return category_9_cnt;
    }
    public void setCategory_9_cnt(Integer category_9_cnt) {
        this.category_9_cnt = category_9_cnt;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "RecordsData [feed_type=" + feed_type + ", category=" + category
                + ", shareid=" + shareid + ", title=" + title + ", filecount="
                + filecount + ", uk=" + uk + ", feed_time=" + feed_time
                + ", desc=" + desc + ", album_id=" + album_id + ", filelist="
                + filelist + ", dir_cnt=" + dir_cnt + ", category_1_cnt="
                + category_1_cnt + ", category_2_cnt=" + category_2_cnt
                + ", category_3_cnt=" + category_3_cnt + ", category_4_cnt="
                + category_4_cnt + ", category_5_cnt=" + category_5_cnt
                + ", category_6_cnt=" + category_6_cnt + ", category_7_cnt="
                + category_7_cnt + ", category_8_cnt=" + category_8_cnt
                + ", category_9_cnt=" + category_9_cnt + "]";
    }
    
}
