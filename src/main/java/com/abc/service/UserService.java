package com.abc.service;

import com.abc.dao.UserDao;
import com.abc.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Long save(User user){
        return userDao.save(user);
    }

    public User findByUname(String uname){
        return userDao.findByUname(uname);
    }

    public void login(String uname,String pwd){
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uname, pwd);
        try {
            currentUser.login( token );
            //if no exception, that's it, we're done!
            System.out.println("login success...");
        } catch ( UnknownAccountException uae ) {
            //username wasn't in the system, show them an error message?
            System.err.println(uae.getMessage());
        } catch ( IncorrectCredentialsException ice ) {
            //password didn't match, try again?
            System.err.println(ice.getMessage());
        } catch ( LockedAccountException lae ) {
            //account for that username is locked - can't login.  Show them a message?
            System.err.println(lae.getMessage());
        } catch ( AuthenticationException ae ) {
            //unexpected condition - error?
            System.err.println(ae.getMessage());
        }
    }


}
