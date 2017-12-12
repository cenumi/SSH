package com.c1yde3.ssh.service;

import java.util.List;

/**
 * Created by wangyonghao8 on 2017/12/12.
 */
public interface Service {
    /**
     * @param start 出发地
     * @param end   目的地
     * @param date  日期
     * @return 暂定返回list<String[]>,后面讨论是否返回json
     */
    List<String[]> getALLTrips(String start, String end, String date);
}
