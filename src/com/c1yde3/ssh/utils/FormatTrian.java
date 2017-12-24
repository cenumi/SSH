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

    @Autowired
    private Gson gson;

    public List getformatedTrian(List trians,String start,String end){
        List formatList = new ArrayList<>();
        for (int i=0;i<trians.size();i++){
            Trans trian = (Trans) trians.get(i);
            System.out.println(trian.toString());
            TrainPOJO pojo = new TrainPOJO();
            String[] part;//用于解析字符串
            pojo.setId(trian.getNumber());//列车号
            pojo.setDeparture_name(start);//出发站
            pojo.setArrive_name(end);//到达站
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
            String[] temp;//用于保存单个站点信息
            //找到出发站于到达站的位置
            int startIndex=0;
            int endIndex=0;
            int baseMoney = 0;
            for (int j=0;j<part.length;j++){
                temp = part[j].split(",");
                if(temp[2].equals(start)){
                    startIndex = j;
                    pojo.setDeparture_time(temp[0]);//出发时间
                    baseMoney = Integer.valueOf(temp[1]);
                }
                if(temp[2].equals(end)){
                    endIndex = j;
                    pojo.setArrive_time(temp[0]);//到达时间
                }
            }
            //把出发站于到达站之间的站点信息解析出来并且封装
            List stations = new ArrayList();
            for(int j=startIndex;j<endIndex;j++){
                StationPOJO station = new StationPOJO();
                temp = part[j].split(",");
                station.setTime(temp[0]);
                station.setMoney(String.valueOf(Integer.valueOf(temp[1])-baseMoney));
                station.setName(temp[2]);
                stations.add(station);
            }
            pojo.setPassby(stations);//解析经过站完成
            //TODO: 解析耗时
            //pojo.setSpend(().toString);
            pojo.setSpend("6:30");

            formatList.add(pojo);
        }
        return  formatList;
    }

}
