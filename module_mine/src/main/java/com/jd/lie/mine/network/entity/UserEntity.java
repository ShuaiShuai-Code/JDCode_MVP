package com.jd.lie.mine.network.entity;

import com.jd.common.entity.BaseDataEntity;

import java.util.List;

/**
 * @author ：王文彬 on 2020/5/21 16：15
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class UserEntity extends BaseDataEntity<String> {
    private String phoneNumber;
    private String photoUrl;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
