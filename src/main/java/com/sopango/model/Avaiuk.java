package com.sopango.model;

import java.io.Serializable;

public class Avaiuk implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long uk;
    private int flag;

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
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
