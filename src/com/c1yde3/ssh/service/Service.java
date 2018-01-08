package com.c1yde3.ssh.service;

import com.c1yde3.ssh.model.TrainPOJO;
import com.c1yde3.ssh.model.Trans;

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
    Map<String, Object> getTripsByStartEndDate(String start,String end,String date);

    /**
     * 指定出发站，到达站，返回所有可能列车
     * @param startStation 出发站
     * @param endStation   到达站
     * @return 封装好的数据map，包含列车信息
     */
    Map<String,Object> getTripsByTwoStation(String startStation,String endStation);


    /**
     * 用于前端获取所有车次信息，以便列车管理
     * @return 返回所有列车信息
     */
    Map<String, Object> getAllTrips();


    /**
     * 更新列车信息，存储到数据库
     * @param trainPOJO 列车车次
     * @return 返回成功失败
     */
    Map<String,Object> updateOneTrip(Trans tran);


    /**
     * 获取不能直达的两趟列车给客户
     * @param start 出发展
     * @param end   终点站
     * @param date  日期
     * @return  返回前端需要的数据，即两趟列车信息
     */
    Map<String,Object> getIndirectTrain(String start, String end, String date);
}
