package com.c1yde3.ssh.model;

import java.util.List;

/**
 * Created by wangyonghao8 on 2017/12/24.
 */
public class TrainPOJO {
    /*
                            {field: 'id', title: '车次', width:120, fixed: 'left'},
                            {field: 'departure_name', title: '出发站', width:140},
                            {field: 'arrive_name', title: '到达站', width:140},
                            {field: 'departure_time', title: '出发时间', width:120, sort: true},
                            {field: 'arrive_time', title: '到达时间', width:120,sort: true},
                            {field: 'spend', title: '历时', width: 140},
                            {field: 'class0', title: '商务座', width: 100},
                            {field: 'class1', title: '一等座', width: 100},
                            {field: 'class2', title: '二等座', width: 100},
                            {field: 'class3', title: '高级软卧', width: 100},
                            {field: 'class4', title: '软卧', width: 100},
                            {field: 'class5', title: '动卧', width: 100},
                            {field: 'class6', title: '硬卧', width: 100},
                            {field: 'class7', title: '软座', width: 100},
                            {field: 'class8', title: '硬座', width: 100},
                            {field: 'class9', title: '无座', width: 100}
     */
    private String id;//车次
    private String departure_name;//出发站
    private String arrive_name;//到达站
    private String departure_time;//出发时间
    private String arrive_time;//到达时间
    private String spend;//历时
    private String class0;//商务座
    private String class1;//一等座
    private String class2;//二等座
    private String class3;//高级软卧
    private String class4;//软卧
    private String class5;//动卧
    private String class6;//硬卧
    private String class7;//软座
    private String class8;//硬座
    private String class9;//无座
    private List passby;//经过的站名
    private String day;//日期

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List getPassby() {
        return passby;
    }

    public void setPassby(List passby) {
        this.passby = passby;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeparture_name() {
        return departure_name;
    }

    public void setDeparture_name(String departure_name) {
        this.departure_name = departure_name;
    }

    public String getArrive_name() {
        return arrive_name;
    }

    public void setArrive_name(String arrive_name) {
        this.arrive_name = arrive_name;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = spend;
    }

    public String getClass0() {
        return class0;
    }

    public void setClass0(String class0) {
        this.class0 = class0;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getClass2() {
        return class2;
    }

    public void setClass2(String class2) {
        this.class2 = class2;
    }

    public String getClass3() {
        return class3;
    }

    public void setClass3(String class3) {
        this.class3 = class3;
    }

    public String getClass4() {
        return class4;
    }

    public void setClass4(String class4) {
        this.class4 = class4;
    }

    public String getClass5() {
        return class5;
    }

    public void setClass5(String class5) {
        this.class5 = class5;
    }

    public String getClass6() {
        return class6;
    }

    public void setClass6(String class6) {
        this.class6 = class6;
    }

    public String getClass7() {
        return class7;
    }

    public void setClass7(String class7) {
        this.class7 = class7;
    }

    public String getClass8() {
        return class8;
    }

    public void setClass8(String class8) {
        this.class8 = class8;
    }

    public String getClass9() {
        return class9;
    }

    public void setClass9(String class9) {
        this.class9 = class9;
    }
}
