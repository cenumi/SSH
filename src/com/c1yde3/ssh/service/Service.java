package com.c1yde3.ssh.service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyonghao8 on 2017/12/12.
 */
public interface Service {

    /**
     * @param start 出发地
     * @param end   目的地
     * @param date  日期
     * @return 暂定返回map<String, Object>,map具体怎么内容参考简单实现
     */
    Map<String, Object> getAllTrips(String start,String end,String date);

    /**
     * 指定出发站，到达站，返回所有可能列车
     * @param startStation 出发站
     * @param endStation   到达站
     * @return 封装好的数据map，包含列车信息
     */
    Map<String,Object> getTripsByTwoStation(String startStation,String endStation);

}
