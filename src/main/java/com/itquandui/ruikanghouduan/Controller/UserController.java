package com.itquandui.ruikanghouduan.Controller;

import com.itquandui.ruikanghouduan.Service.UserService;
import com.itquandui.ruikanghouduan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 注册接口
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean success = userService.register(user);
        return success ? "注册成功" : "用户名已存在";
    }

    // 登录接口
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean success = userService.login(user.getUsername(), user.getPassword());
        return success ? "登录成功" : "用户名或密码错误";
    }
}