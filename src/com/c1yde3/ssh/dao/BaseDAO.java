package com.c1yde3.ssh.dao;

import java.util.List;

public interface BaseDAO {
    boolean add(Object o);

    boolean delete(Object o);

    boolean update(Object o);

    List find(Object o);
}
