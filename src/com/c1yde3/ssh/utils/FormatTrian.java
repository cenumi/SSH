package com.c1yde3.ssh.utils;

import com.c1yde3.ssh.model.StationPOJO;
import com.c1yde3.ssh.model.TrainPOJO;
import com.c1yde3.ssh.model.Trans;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyonghao8 on 2017/12/24.
 */
public class FormatTrian {

    public List getformatedTrian(List trians,String start,String end){
        List list = this.getformatedTrian(trians);
        if(start.equals("") || start == null) {
            return list;
        }else {
            for (int i=0;i<list.size();i++) {
                List stations = ((TrainPOJO) list.get(i)).getPassby();
                List wantedStation = new ArrayList();
                int temp = stations.size();
                int baseMoney=0;
                for (int j=0;j<stations.size();j++){
                    //找到出发站
                    if (((StationPOJO)stations.get(j)).getName().equals(start)){
                        ((TrainPOJO) list.get(i)).setDeparture_time(((StationPOJO)stations.get(j)).getDepatureTime());//更新列车出发时间
                        ((TrainPOJO) list.get(i)).setDeparture_name(((StationPOJO)stations.get(j)).getName());//更新列车出发站
                        baseMoney = Integer.valueOf(((StationPOJO)stations.get(j)).getMoney());//获取相对车票钱
                        temp = j;
                        wantedStation.add(stations.get(j));
                    }
                    //更新出发站到目的站的票价
                    if(temp < j ){
                        ((StationPOJO)stations.get(j)).setMoney(
                                String.valueOf(
                                        Integer.valueOf(
                                                ((StationPOJO)stations.get(j)).getMoney())-baseMoney));
                        wantedStation.add(stations.get(j));
                    }
                    //找到终点站
                    if (((StationPOJO)stations.get(j)).getName().equals(end)){
                        ((TrainPOJO) list.get(i)).setArrive_time(((StationPOJO)stations.get(j)).getArrivalTime());//更新列车到达时间
                        ((TrainPOJO) list.get(i)).setArrive_name(((StationPOJO)stations.get(j)).getName());//更新列车终点站名
                        String time = calTime(((TrainPOJO) list.get(i)).getDeparture_time(),((TrainPOJO) list.get(i)).getArrive_time());
                        ((TrainPOJO) list.get(i)).setSpend(time);//更新列车耗时
                        //wantedStation.add(stations.get(j));
                        break;//列车解析完成，跳出
                    }
                }
                ((TrainPOJO) list.get(i)).setPassby(wantedStation);//更新列车站点图
            }

            return list;
        }
    }

    public List getformatedTrian(List trians){
        List formatList = new ArrayList<>();
        for (int i=0;i<trians.size();i++){
            Trans trian = (Trans) trians.get(i);
            TrainPOJO pojo = new TrainPOJO();
            String[] part;//用于解析字符串
            pojo.setId(trian.getNumber());//列车号
            pojo.setDeparture_time(trian.getDepature());//出发时间
            pojo.setArrive_time(trian.getArrival());//到达时间
            pojo.setDay(trian.getDay());//设置日期
            //解析车票
            part = trian.getTicket().split(";");
            pojo.setClass0(part[0]);
            pojo.setClass1(part[1]);
            pojo.setClass2(part[2]);
            pojo.setClass3(part[3]);
            pojo.setClass4(part[4]);
            pojo.setClass5(part[5]);
            pojo.setClass6(part[6]);
            pojo.setClass7(part[7]);
            pojo.setClass8(part[8]);
            pojo.setClass9(part[9]);
            //解析经过站
            part = trian.getPassby().split(";");
            pojo.setDeparture_name(part[0].split(",")[4]);//出发站
            pojo.setArrive_name(part[part.length-1].split(",")[4]);//到达站
            String[] temp;//用于保存单个站点信息
            //把出发站于到达站之间的站点信息解析出来并且封装
            List stations = new ArrayList();
            for(int j=0;j<part.length;j++){
                StationPOJO station = new StationPOJO();
                temp = part[j].split(",");
                station.setArrivalTime(temp[0]);
                station.setStayTime(temp[1]);
                station.setDepatureTime(temp[2]);
                station.setMoney(temp[3]);
                station.setName(temp[4]);
                stations.add(station);
            }
            pojo.setPassby(stations);//解析经过站完成
            //TODO: 解析耗时
            pojo.setSpend(calTime(pojo.getDeparture_time(),pojo.getArrive_time()));

            formatList.add(pojo);
        }
        return  formatList;
    }


    public Trans formatedToTrian(TrainPOJO pojo){
        Trans trans = new Trans();
        //TODO:逆向转换
        return trans;
    }

    /**
     * 计算列车的时间
     * @param startTime
     * @param endTime
     * @return
     */
    public static String calTime(String startTime,String endTime){
        char[] a = startTime.toCharArray();
        char[] b = endTime.toCharArray();
        int st = ((a[0]-'0')*10+(a[1]-'0'))*60+(a[3]-'0')*10+(a[4]-'0');//min
        int et = ((b[0]-'0')*10+(b[1]-'0'))*60+(b[3]-'0')*10+(b[4]-'0');//min
        int time = et - st;
        if(time<0){   //差一天
            time+=24*60;
        }
        int hour=time/60;
        int minute=time%60;
        return  String.valueOf(hour)+"h"+String.valueOf(minute)+"m";

    }
}
