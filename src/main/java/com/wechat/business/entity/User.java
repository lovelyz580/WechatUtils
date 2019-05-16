package com.wechat.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class User {
        private String userId;

        private String nickName;

        private String avatarUrl;

        private Integer gender;

        private String city;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
        private Date learnTime;
    // 版本信息
    /**
     * 版本号
     */
    private String version;
    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public List<String> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<String> idlist) {
        this.idlist = idlist;
    }

    /**
     * 每页的数据量
     */
    private Integer pagesize;
    // 操作数据
    /**
     * 根据uuidlist删除信息时使用
     */
    private List<String> idlist;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId == null ? null : userId.trim();
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName == null ? null : nickName.trim();
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city == null ? null : city.trim();
        }

        public Date getLearnTime() {
            return learnTime;
        }

        public void setLearnTime(Date learnTime) {
            this.learnTime = learnTime;
        }
    }