package com.sopango.model.data;

import java.util.List;

import com.sopango.model.UserInfo;

public class YunData {
    private UserInfo uinfo;
    private Feedata feedata;
    private AlbumInfoData albuminfo;
    private Albumlist albumlist;
    private List<FansData> fans_list;
    private List<FollowData> follow_list;
    
    private Integer total_count;

    public UserInfo getUinfo() {
        return uinfo;
    }

    public void setUinfo(UserInfo uinfo) {
        this.uinfo = uinfo;
    }

    public Feedata getFeedata() {
        return feedata;
    }

    public void setFeedata(Feedata feedata) {
        this.feedata = feedata;
    }
    
    public AlbumInfoData getAlbuminfo() {
        return albuminfo;
    }

    public void setAlbuminfo(AlbumInfoData albuminfo) {
        this.albuminfo = albuminfo;
    }

    public Albumlist getAlbumlist() {
        return albumlist;
    }

    public void setAlbumlist(Albumlist albumlist) {
        this.albumlist = albumlist;
    }

    public List<FansData> getFans_list() {
        return fans_list;
    }

    public void setFans_list(List<FansData> fans_list) {
        this.fans_list = fans_list;
    }

    public List<FollowData> getFollow_list() {
        return follow_list;
    }

    public void setFollow_list(List<FollowData> follow_list) {
        this.follow_list = follow_list;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    @Override
    public String toString() {
        return "YunData [uinfo=" + uinfo + ", feedata=" + feedata
                + ", albuminfo=" + albuminfo + ", albumlist=" + albumlist
                + ", fans_list=" + fans_list + ", follow_list=" + follow_list
                + ", total_count=" + total_count + "]";
    }
}
