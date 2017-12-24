package com.c1yde3.ssh.service;

import com.c1yde3.ssh.dao.BaseDAO;
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
     * 根据出发地，目的地，出发时间查询所有符合的列车信息
     * @param start 出发地
     * @param end   目的地
     * @param date  日期
     * @return 返回所有符合的列车信息
     */
    @Override
    public Map<String, Object> getAllTrips(String start, String end, String date) {
        Map map = service.getTripsByTwoStation(start,end);
        List list = service.getTripsByTwoStations(start,end);
        //把所有符合日期的车次找出来
        Trans trans;
        for (int i=0;i<list.size();i++){
            trans = (Trans) list.get(i);
            if(trans.getDay().indexOf(date) == -1){
                list.remove(i);
            }
        }
        list = formatTrian.getformatedTrian(list,start,end);

        map.put("code",0);
        map.put("msg","123");
        if(list != null){
            map.put("count", list.size());
            map.put("data", list);
        }else{
            map.put("count",list.size());
            map.put("data","[]");
        }
        System.out.print(gson.toJson(map));
        return map;
    }


    /**
     * 根据出发地，目的地返回所有可能的列车
     * @param station1  出发站
     * @param station2  到达站
     * @return 返回所有符合的列车信息
     */
    public Map<String,Object> getTripsByTwoStation(String station1,String station2){
        Map<String, Object> map = new HashMap<>();

        List formatedTrian = formatTrian.getformatedTrian(service.getTripsByTwoStations(station1,station2),station1,station2);

        map.put("code",0);
        map.put("msg","成功或失败或不写");
        if (formatedTrian!=null) {
            map.put("count", formatedTrian.size());
            map.put("data", formatedTrian);
        }else{
            map.put("count",0);
            map.put("data","[]");
        }
        System.out.print(gson.toJson(map));
        return map;
    }

    public List getTripsByTwoStations(String station1,String station2){
        List list = baseDAO.findByTwoStation(station1,station2);
        //符合条件的结果集
        List<Trans> trians = new ArrayList<>();
        if (list != null){
            for (int i=0; i<list.size(); i++){
                Trans trian = (Trans) list.get(i);
                //站点station1在站点station2先前到达，则添加到结果集
                if(trian.getPassby().indexOf(station1) < trian.getPassby().indexOf(station2)) {
                    trians.add(trian);
                    System.out.println(trian.toString());
                }
            }
            return trians;
        }else{
            return null;
        }
    }

}
