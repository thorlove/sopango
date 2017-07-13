package com.sopango.model.data;

import java.util.List;

public class Albumlist {
    private Integer count;
    private List<AlbumData> list;
    
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public List<AlbumData> getList() {
        return list;
    }
    public void setList(List<AlbumData> list) {
        this.list = list;
    }
    @Override
    public String toString() {
        return "Albumlist [count=" + count + ", list=" + list + "]";
    }
}
