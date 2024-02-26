package com.jd.lie.mine.network.entity;

import com.jd.common.entity.BaseDataEntity;

/**
 * @author ：王文彬 on 2020/5/21 16：15
 * @describe ：
 * @email ：wangwenbin29@jd.com
 */
public class VersionEntity extends BaseDataEntity<VersionEntity> {
    private String versionNumber;
    private String versionInformation;
    private String versionUrl;

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionInformation() {
        return versionInformation;
    }

    public void setVersionInformation(String versionInformation) {
        this.versionInformation = versionInformation;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }
}
