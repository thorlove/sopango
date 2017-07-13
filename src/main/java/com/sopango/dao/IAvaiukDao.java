package com.sopango.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sopango.model.Avaiuk;

public interface IAvaiukDao {
    
    @Select("select * from pan_avaiuk where flag=0 limit 1")
    public Avaiuk findFirst();
    
    @Update("update pan_avaiuk set flag = #{flag} where uk=#{uk}")
    public int updateFlag(@Param("flag") Integer flag,@Param("uk") Long uk);
}
