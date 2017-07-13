package com.sopango.model.data;

public class AlbumData {
    private Long add_time;
    private Long size;
    private String category;
    private String path;
    private String server_filename;
    private Integer isdir;
    
    public Long getAdd_time() {
        return add_time;
    }
    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getServer_filename() {
        return server_filename;
    }
    public void setServer_filename(String server_filename) {
        this.server_filename = server_filename;
    }
    public Integer getIsdir() {
        return isdir;
    }
    public void setIsdir(Integer isdir) {
        this.isdir = isdir;
    }
    @Override
    public String toString() {
        return "AlbumData [add_time=" + add_time + ", size=" + size
                + ", category=" + category + ", path=" + path
                + ", server_filename=" + server_filename + ", isdir=" + isdir
                + "]";
    }
}
