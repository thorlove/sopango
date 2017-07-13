package com.sopango.model.data;

public class FileData {
    private String server_filename;
    private Integer isdir; //1:是
    private Long size;
    private String path;
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
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    @Override
    public String toString() {
        return "FileData [server_filename=" + server_filename + ", isdir="
                + isdir + ", size=" + size + ", path=" + path + "]";
    }
    
}
