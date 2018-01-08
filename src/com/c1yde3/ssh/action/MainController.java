package com.c1yde3.ssh.action;

import com.c1yde3.ssh.model.StationPOJO;
import com.c1yde3.ssh.model.Trans;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.T;


/**
 * Created by wangyonghao8 on 2017/12/12
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
     * 函数说明:  指定出发地，目的地(和时间)，返回所有可能的车次
     *
     * @return 返回所有匹配车次
     */
    public String getTripsByStartEndDate() {
        Map<String, Object>map = service.getTripsByStartEndDate(startStation,endStation,date);
        String result = gson.toJson(map);
        System.out.println(result);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("data",result);
        return "success";
    }

    /**
     * 获取全部列车信息，以便维护列车
     * @return  所有列车的信息
     */
    public String getNeedUpdateTrip(){
        Map<String, Object>map = service.getAllTrips();
        String result = gson.toJson(map);
        System.out.println(result);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("data",result);
        return "success";
    }

    /**
     * 更新一个列车
     * @return 做个样子
     */
    public String updateOneTrip(){
        //TODO：前端数据封装成TriaPOJO，执行下面语句就好了
        Trans newTrain = new Trans();
        HttpServletRequest request = ServletActionContext.getRequest();
        newTrain.setId( Integer.valueOf(request.getParameter("id")));
        newTrain.setArrival(request.getParameter("arrival"));
        newTrain.setDay(request.getParameter("date"));
        newTrain.setDepature(request.getParameter("depature"));
        newTrain.setNumber(request.getParameter("number"));
        newTrain.setPassby(request.getParameter("passby"));
        newTrain.setTicket(request.getParameter("ticket"));
        service.updateOneTrip(newTrain);
        Map<String, Object>map = service.getAllTrips();
        String result = gson.toJson(map);
        System.out.println(result);
        request.setAttribute("data",result);
        return "success";
//        Map<String, Object>map = service.updateOneTrip(pojo);
//        String result = gson.toJson(map);
//        System.out.println(result);
//        HttpServletRequest request = ServletActionContext.getRequest();
//        request.setAttribute("data",result);
    }

    /**
     * 根据出发站，终点站，时间查找两趟列车
     */
    public String getIndirectTrip(){
        Map<String, Object>map = service.getIndirectTrain(startStation,endStation,date);
        String result = gson.toJson(map);
        System.out.println(result);
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
