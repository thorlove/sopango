package com.sopango.model.data;

import java.util.List;

public class Feedata {
    
    private Integer total_count;
    private List<RecordsData> records;
    
    public Integer getTotal_count() {
        return total_count;
    }
    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }
    public List<RecordsData> getRecords() {
        return records;
    }
    public void setRecords(List<RecordsData> records) {
        this.records = records;
    }
    @Override
    public String toString() {
        return "Feedata [total_count=" + total_count.toString() + ", records=" + records.toString()
                + "]";
    }
    
}
