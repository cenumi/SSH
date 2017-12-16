package com.c1yde3.ssh.dao;

import java.util.List;

/**
 * Created by 10543 on 2017/12/12.
 */

public interface BaseDAO {
    boolean add(Object o);

    boolean delete(Object o);

    boolean update(Object o);

    List find(Object o);
}
