package com.c1yde3.ssh.service;

import com.c1yde3.ssh.dao.BaseDAO;
import com.c1yde3.ssh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 10543 on 2017/12/12.
 */
@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDAO baseDAO;

    @Override
    public User checkLogin(String name, String pass) {

        User u = new User();
        u.setUsername(name);
        u.setPassword(pass);
        List users = baseDAO.find(u);
        if (users.size() != 0) return (User)users.get(0);
        return null;
    }

    @Override
    public boolean register(String name, String pass) {

        User u = new User();
        u.setUsername(name);
        u.setPassword(pass);
        List users = baseDAO.find(u);
        return users.size() == 0 && baseDAO.add(u);
    }
}
