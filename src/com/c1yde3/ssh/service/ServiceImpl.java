package com.c1yde3.ssh.service;

import com.c1yde3.ssh.dao.BaseDAO;
import com.c1yde3.ssh.model.TrainPOJO;
import com.c1yde3.ssh.model.Trans;
import com.c1yde3.ssh.utils.FormatTrian;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyonghao8 on 2017/12/12.
 */
@org.springframework.stereotype.Service
@Scope("prototype")
public class ServiceImpl implements Service {

    @Autowired
    private BaseDAO baseDAO;

    @Autowired
    private FormatTrian formatTrian;

    @Autowired
    private Gson gson;

    @Autowired
    private ServiceImpl service;


    /**
     * 根据出发地，目的地，(出发时间)查询所有符合的列车信息
     * @param start 出发地
     * @param end   目的地
     * @param date  日期
     * @return 返回所有符合的列车信息
     */
    @Override
    public Map<String, Object> getTripsByStartEndDate(String start, String end, String date) {
        if (date.equals("") || date == null) {
            return service.getTripsByTwoStation(start, end);
        }else {
            Map<String,Object> map = new HashMap();
            List list = service.getTrips(start, end);
            //封装返回结果集
            if (list != null) {
                //把所有符合日期的车次找出来
                Trans trans;
                for (int i = 0; i < list.size(); i++) {
                    trans = (Trans) list.get(i);
                    if (trans.getDay().indexOf(date) == -1) {
                        list.remove(i);
                    }
                }
                //格式化列车信息
                list = formatTrian.getformatedTrian(list, start, end);
                map.put("code", 0);
                map.put("msg", "成功");
                map.put("count", list.size());
                map.put("data", list);
            } else {
                map.put("code", 0);
                map.put("msg", "失败");
                map.put("count", 0);
                map.put("data", "[]");
            }
            return map;
        }
    }


    /**
     * 根据出发地，目的地返回所有可能的列车,当没有日期被getTripsByStartEndDate调用
     * @param startStation  出发站
     * @param endStation  到达站
     * @return 返回所有符合的列车信息
     */
    public Map<String,Object> getTripsByTwoStation(String startStation,String endStation){
        Map<String, Object> map = new HashMap<>();

        List formatedTrian = formatTrian.getformatedTrian(service.getTrips(startStation,endStation),startStation,endStation);

        map.put("code",0);
        map.put("count", formatedTrian.size());
        map.put("data", formatedTrian);
        if (formatedTrian.size() != 0) {
            map.put("msg","成功");
        }else{
            map.put("msg","失败");
        }
        return map;
    }

    /**
     * 调用dao，完成数据获取
     * @param startStation 出发站台
     * @param endStation 终点站
     * @return 返回符合条件的所有列车信息
     */
    public List getTrips(String startStation,String endStation){
        List list = baseDAO.findByTwoStation(startStation,endStation);

        for (int i=0; i<list.size(); i++){
            //站点startStation在站点endStation先前到达，则添加到结果集
            if (((Trans) list.get(i)).getPassby().indexOf(startStation) >= ((Trans) list.get(i)).getPassby().indexOf(endStation)){
                list.remove(i);
            }
        }
        return list;
    }


    /**
     * 用于前端获取所有车次信息，以便列车管理
     * @return 返回所有列车信息
     */
    @Override
    public Map<String, Object> getAllTrips() {
        List list = formatTrian.getformatedTrian(baseDAO.getAllTrips());
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("count",list.size());
        map.put("data",list);
        if (list.size() != 0){
            map.put("msg","成功");
        }else{
            map.put("msg","失败");
        }
        return map;
    }

    /**
     * 更新列车信息，存储到数据库
     * @param trainPOJO 列车车次
     * @return 返回成功失败
     */
    @Override
    public Map<String,Object> updateOneTrip(TrainPOJO trainPOJO) {
        Trans trans = formatTrian.formatedToTrian(trainPOJO);
        boolean t =  baseDAO.update(trans);
        Map<String,Object> map = new HashMap<>();
        map.put("code",t);
        return  map;
    }
}
