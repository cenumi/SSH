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
     * @return 暂定返回list<String[]>,不用实现这个接口了，上面没有用到
     */
    List<String[]> getALLTrips(String start, String end, String date);

    /**
     * @param start 出发地
     * @param end   目的地
     * @param date  日期
     * @return 暂定返回map<String, Object>,map具体怎么内容参考简单实现
     */
    Map<String, Object> getAllTrips(String start,String end,String date);
}
