package com.c1yde3.ssh.action;

import com.c1yde3.ssh.service.Service;
import com.c1yde3.ssh.utils.ResultUtils;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyonghao8 on 2017/12/12.
 */

@Controller
@Scope("prototype")
public class MainController {
    @Autowired
    private Service service;

    @Autowired
    private Gson gson;

    private String startStation;
    private String endStation;
    private String date;

    /**
     * 函数说明:  指定出发地，目的地和时间，返回所有可能的车次
     *
     * @return 返回所有匹配车次
     */
    public String getAllTrips() {
        Map<String, Object>map = service.getAllTrips(startStation,endStation,date);
//        Map<String, Object>map = service.getTripsByTwoStation(startStation,endStation);
        String result = gson.toJson(map);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("data",result);
        return "success";
    }

    public String getTripByTwoStation(){
        Map<String, Object> map = service.getTripsByTwoStation(startStation,endStation);
        String result = gson.toJson(map);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("data",result);
        return "success";
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
