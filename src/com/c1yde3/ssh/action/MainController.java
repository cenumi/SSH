package com.c1yde3.ssh.action;

import com.c1yde3.ssh.service.Service;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangyonghao8 on 2017/12/12.
 */

@Controller
@Scope("prototype")
public class MainController {
    @Autowired
    private Service service;

    private String startStation;
    private String endStation;
    private String date;

    /**
     * 函数说明:  指定出发地，目的地和时间，返回所有可能车次
     *
     * @return 返回所有匹配车次
     */
    public String getAllTrips() {
        List<String[]> lists = service.getALLTrips(startStation, endStation, date);
        ActionContext.getContext().put("tripLists", lists);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("tripLists", lists);
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
