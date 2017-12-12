package com.c1yde3.ssh.service;

import com.c1yde3.ssh.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyonghao8 on 2017/12/12.
 */
@org.springframework.stereotype.Service
@Scope("prototype")
public class ServiceImpl implements Service {

    @Autowired
    private BaseDAO baseDAO;

    @Override
    public List<String[]> getALLTrips(String start, String end, String date) {
        String[] station1 = {"K1580", "过上海站终" + end, "04:22_06:33", "02:07,当日到达", "3", "--", "--", "--", "--", "--", "560", "--", "无", "有"};
        String[] station2 = {"K1511", "过杭州终" + end, "04:22_06:33", "02:07,当日到达", "--", "--", "--", "--", "10", "--", "10", "--", "12", "无"};
        String[] station3 = {"K123", "过上海站终" + end, "04:22_06:33", "02:07,当日到达", "--", "--", "--", "--", "--", "--", "无", "--", "无", "无"};
        String[] station4 = {"D538", "过杭州终" + end, "04:22_06:33", "02:07,当日到达", "2", "--", "--", "--", "--", "--", "无", "--", "无", "有"};
        String[] station5 = {"G983", "过北京终" + end, "04:22_06:33", "02:07,当日到达", "--", "无", "78", "--", "无", "--", "无", "--", "123", "无"};
        List<String[]> lists = new ArrayList<String[]>();

        lists.add(station1);
        lists.add(station2);
        lists.add(station3);
        lists.add(station4);
        lists.add(station5);
        return lists;
    }
}
