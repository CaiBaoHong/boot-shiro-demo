package com.abc.controller;

import com.abc.entity.User;
import com.abc.service.UserService;
import com.abc.vo.BaseResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public BaseResponse add(@RequestBody User user){

        final String oper = "add user";

        if (StringUtils.isEmpty(user.getUname())){
            return BaseResponse.fail(oper,"用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPwd())){
            return BaseResponse.fail(oper,"密码不能为空");
        }

        User userDB = userService.findByUname(user.getUname());

        if (userDB!=null){
            return BaseResponse.fail(oper,"用户已注册");
        }

        //密码加密
        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
        String salt = saltGen.nextBytes().toBase64();
        String hashedPwd = new Sha256Hash(user.getPwd(), salt, 1024).toBase64();

        //保存新用户数据
        User newUser = new User();
        newUser.setUname(user.getUname());
        newUser.setNick(user.getNick());
        newUser.setPwd(hashedPwd);
        newUser.setPwdSalt(salt);
        newUser.setCreated(new Date());
        Long uid = userService.save(user);
        newUser.setUid(uid);

        return BaseResponse.succ(oper,"user",newUser);
    }

    @ResponseBody
    @PostMapping("/login")
    public BaseResponse login(@RequestBody String body){

        JSONObject json = JSON.parseObject(body);
        String uname = json.getString("uname");
        String pwd = json.getString("pwd");

        final String oper = "add user";
        if (StringUtils.isEmpty(uname)){
            return BaseResponse.fail(oper,"用户名不能为空");
        }
        if (StringUtils.isEmpty(pwd)){
            return BaseResponse.fail(oper,"密码不能为空");
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login( new UsernamePasswordToken(uname, pwd) );
            return BaseResponse.succ(oper,"userId",currentUser.getPrincipal());

        } catch ( UnknownAccountException uae ) {
            return BaseResponse.fail(oper,"用户帐号或密码不正确");

        } catch ( IncorrectCredentialsException ice ) {
            return BaseResponse.fail(oper,"用户帐号或密码不正确");

        } catch ( LockedAccountException lae ) {
            return BaseResponse.fail(oper,"用户帐号被锁定不可用");

        } catch ( AuthenticationException ae ) {
            return BaseResponse.fail(oper,"登录失败："+ae.getMessage());
        }

    }


}
