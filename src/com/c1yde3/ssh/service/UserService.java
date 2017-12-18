package com.c1yde3.ssh.service;

import com.c1yde3.ssh.model.User;

/**
 * Created by c1yde3 on 2017/12/12.
 */
public interface UserService {

    User checkLogin(String name, String pass);

    boolean register(String name, String pass);
}