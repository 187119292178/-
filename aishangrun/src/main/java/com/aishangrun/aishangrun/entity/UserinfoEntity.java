package com.aishangrun.aishangrun.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class UserinfoEntity implements Serializable {

    private  Integer id;

    private String name;

    private String idNum ;

    private Integer age;

    private Integer sex;

    private Float height;

    private Float weight;

    private String userId;

    private Float totalDistance;

    private Float totalCalorie;

    private Integer totalDay;

    private String phoneNum;
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Float getTotalCalorie() {
        return totalCalorie;
    }

    public void setTotalCalorie(Float totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    @Override
    public String toString() {
        return "UserinfoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNum='" + idNum + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight +
                ", userId=" + userId +
                ", totalDistance=" + totalDistance +
                ", totalCalorie=" + totalCalorie +
                ", totalDay=" + totalDay +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
