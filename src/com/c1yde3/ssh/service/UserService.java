package com.c1yde3.ssh.service;

import com.c1yde3.ssh.model.User;

public interface UserService {

    User checkLogin(String name, String pass);

    boolean register(String name, String pass);
}