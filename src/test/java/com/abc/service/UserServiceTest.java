package com.abc.service;

import com.abc.entity.User;
import com.abc.shiro.CustomizedRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomizedRealm customizedRealm;

    @Test
    public void testSave(){
        User user = new User();
        user.setUname("cai");
        user.setNick("菜籽");
        user.setPwd("123456");
        user.setPwdSalt("445566");
        user.setCreated(new Date());
        Long uid = userService.save(user);
        System.out.println("save user, uid: "+uid);
    }

    @Test
    public void testSaveUserWithEncryptedPwd(){
        String plainPwd = "123456";

        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        String hashedPwd = new Sha256Hash(plainPwd, salt, 1024).toBase64();

        User user = new User();
        user.setUname("bao");
        user.setNick("宝宝");
        user.setPwd(hashedPwd);
        user.setPwdSalt(salt);
        user.setCreated(new Date());
        Long uid = userService.save(user);
        System.out.println("save user with encrypted pwd, uid: "+uid);

    }

    @Test
    public void testLogin(){
        DefaultSecurityManager sm = new DefaultSecurityManager();
        sm.setRealm(customizedRealm);
        SecurityUtils.setSecurityManager(sm);
        userService.login("bao","123456");
    }

    @Test
    public void testLoginAndHasRole(){
        DefaultSecurityManager sm = new DefaultSecurityManager();
        sm.setRealm(customizedRealm);
        SecurityUtils.setSecurityManager(sm);
        userService.login("bao","123456");
        Subject subject = SecurityUtils.getSubject();
        boolean hasRole = subject.hasRole("general_user");
        System.out.println("user 'bao' hasRole 'general_user' ? --> "+hasRole);
        boolean hasRoleRoot = subject.hasRole("root");
        System.out.println("user 'bao' hasRole 'root' ? --> "+hasRoleRoot);
    }

    @Test
    public void testLoginAndHasPermission(){
        DefaultSecurityManager sm = new DefaultSecurityManager();
        sm.setRealm(customizedRealm);
        SecurityUtils.setSecurityManager(sm);
        userService.login("bao","123456");
        Subject subject = SecurityUtils.getSubject();
        boolean permLog = subject.isPermitted("log:mgr");
        boolean permData = subject.isPermitted("data:mgr");
        System.out.println("user 'bao' isPermitted 'log:mgr' ? --> "+permLog);
        System.out.println("user 'bao' isPermitted 'data:mgr' ? --> "+permData);
    }


}
