package com.c1yde3.ssh.service;

import com.c1yde3.ssh.dao.BaseDAO;
import com.c1yde3.ssh.model.StationPOJO;
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
            System.out.println(list.get(i).toString());
            //站点startStation在站点endStation先前到达，则添加到结果集
            if (((Trans) list.get(i)).getPassby().indexOf(startStation) >= ((Trans) list.get(i)).getPassby().indexOf(endStation)){
                list.remove(i);
                i=-1;//important,删除list，i也要变
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
//        List list = formatTrian.getformatedTrian(baseDAO.getAllTrips());
        List list = baseDAO.getAllTrips();
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
     * @param tran 列车车次
     * @return 返回成功失败
     */
    @Override
    public Map<String,Object> updateOneTrip(Trans tran) {
        boolean t =  baseDAO.update(tran);
        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        return  map;
    }

    /**
     * 当前版本没有最短路径，最少花费，最少时间判断，只是给出第一个匹配到的中转站
     * @param start 出发展
     * @param end   终点站
     * @param date  日期
     * @return
     */
    @Override
    public Map<String,Object> getIndirectTrain(String start, String end, String date){
        Map<String,Object> map = new HashMap<>();
        //1.获取所有包含站点及对应时间的列车,并且格式化
        List startTrip = this.getListBystationDate(start,date);
        List endTrip = this.getListBystationDate(end,date);
        //2.找
        List wantTrips = new ArrayList();
        String hop=null;//中间站
        for (int i=0;i<startTrip.size();i++){
            //分析每一趟列车
            TrainPOJO train = (TrainPOJO) startTrip.get(i);
            List<StationPOJO> stations = train.getPassby();
            //找到当前列车查询的出发站后面的所有站点
            for (int j=0;j<stations.size();j++){
                if (stations.get(j).getName().equals(start) && j != stations.size()-1){
                    //获取当前列车起始站后面的所有站
                    List<StationPOJO> pojos = stations.subList(j,stations.size());
                    //查找后面的站
                    for (int t=0;t<pojos.size();t++){
                        hop = pojos.get(t).getName();
                        List list = formatTrian.getformatedTrian(service.getTrips(hop,end));
                        if (list.size() > 0){
                            //找到符合的,只选一个对列车
                            wantTrips.add(startTrip.get(i));
                            wantTrips.add(list.get(0));
                            break;
                        }
                    }
                }
                if (wantTrips.size() != 0){
                    break;
                }
            }
            if (wantTrips.size() != 0){
                break;
            }
        }
        if (wantTrips.size() != 0){
//            TrainPOJO one = (TrainPOJO) wantTrips.get(0);
//            List oneList = new ArrayList();
//            oneList.add(one);
//            oneList = formatTrian.getformatedTrian(oneList,start,hop);
//            Trans two = (Trans) wantTrips.get(1);
//            List twoList = new ArrayList();
//            twoList.add(two);
//            twoList = formatTrian.getformatedTrian(twoList,hop,end);
//            wantTrips.clear();
//            wantTrips.add(oneList.get(0));
//            wantTrips.add(twoList.get(0));
            map.put("msg","成功");
            map.put("count",wantTrips.size());
            map.put("data",wantTrips);
        }else{
            map.put("msg","失败");
            map.put("count",0);
            map.put("data",null);
        }

        return map;
    }
    //辅助getIndirectTrain方法
    public List getListBystationDate(String station,String date){
        List Trip = baseDAO.getTripByOneStation(station);
        if (Trip != null){
            //把所有符合日期的车次找出来
            Trans trans;
            for(int i=0;i<Trip.size();i++){
                trans = (Trans) Trip.get(i);
                if(trans.getDay().indexOf(date) == -1){
                    Trip.remove(i);
                }
            }
        }
        return formatTrian.getformatedTrian(Trip);
    }
}
