package com.aishangrun.aishangrun.entity;

import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;



@Component
public class UserEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String id;

    private String name;

    private String password ;

    private Date updateTime;

    private Integer roleId;

    private Integer state;

    private String phoneNum;

    private Integer authortyId;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", updateTime=" + updateTime +
                ", roleId=" + roleId +
                ", state=" + state +
                ", phoneNum='" + phoneNum + '\'' +
                ", authortyId=" + authortyId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getAuthortyId() {
        return authortyId;
    }

    public void setAuthortyId(Integer authortyId) {
        this.authortyId = authortyId;
    }
}
