package com.c1yde3.ssh.dao;

import java.util.List;

/**
 * Created by c1yde3 on 2017/12/12.
 */

public interface BaseDAO {
    boolean add(Object o);

    boolean delete(Object o);

    boolean update(Object o);

    List find(Object o);

    List findByTwoStation(String station1,String station2);

    List getAllTrips();

    List getTripByOneStation(String station);

}
