package com.sopango.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.sopango.model.ShareData;

public interface IShareDataDao {
    
    @Insert({"insert into pan_sharedata values(#{data.id},#{data.title},#{data.shareid},#{data.uinfo_id},#{data.category},#{data.album_id},#{data.feed_time},#{data.filecount},#{data.filesize},#{data.dir_cnt})"})
    public void save(@Param("data") ShareData data);
    
}
