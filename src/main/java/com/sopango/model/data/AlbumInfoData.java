package com.sopango.model.data;


/**
 * 专辑
 *
 * @author thb
 * @date 2017-7-11 下午4:15:12
 * @since  V1.0
 */
public class AlbumInfoData {
    private Long album_id;
    private String title;
    private String desc;
    private Integer filecount;
    private String cover_thumb;

    public Long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getFilecount() {
        return filecount;
    }

    public void setFilecount(Integer filecount) {
        this.filecount = filecount;
    }

    public String getCover_thumb() {
        return cover_thumb;
    }

    public void setCover_thumb(String cover_thumb) {
        this.cover_thumb = cover_thumb;
    }

    @Override
    public String toString() {
        return "AlbumInfoData [album_id=" + album_id + ", title=" + title
                + ", desc=" + desc + ", filecount=" + filecount
                + ", cover_thumb=" + cover_thumb + "]";
    }

}
