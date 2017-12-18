package com.c1yde3.ssh.action;

import com.c1yde3.ssh.model.User;
import com.c1yde3.ssh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
/**
 * Created by c1yde3 on 2017/12/12.
 */
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

    @Autowired
    private UserService userService;

    private String username;
    private String password;

    public String login() {
        User user = userService.checkLogin(username, password);
        if (user != null) return SUCCESS;
        return ERROR;
    }

    public String register() {
        if (userService.register(username, password)) return SUCCESS;
        return ERROR;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}