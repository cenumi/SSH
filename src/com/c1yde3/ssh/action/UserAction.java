package com.c1yde3.ssh.action;

import com.c1yde3.ssh.model.User;
import com.c1yde3.ssh.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        HttpServletRequest request = ServletActionContext.getRequest();
        if (user != null){
            request.setAttribute("user",username);
            request.setAttribute("msg","登陆成功");
            return SUCCESS;
        }
        request.setAttribute("msg","登陆失败");
        return ERROR;
    }

    public String register() {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (userService.register(username, password)){
            request.setAttribute("user",username);
            request.setAttribute("msg","登陆成功");
            return SUCCESS;
        }
        request.setAttribute("msg","注册失败");
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