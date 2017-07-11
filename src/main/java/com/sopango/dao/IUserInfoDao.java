package com.sopango.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sopango.model.UserInfo;

public interface IUserInfoDao {
    
    @Select("select * from pan_uinfo where id = #{pk}")
    public UserInfo getByPK(@Param("pk") Long pk);
}
