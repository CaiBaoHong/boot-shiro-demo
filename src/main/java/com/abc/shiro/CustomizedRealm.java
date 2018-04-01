package com.abc.shiro;

import com.abc.entity.User;
import com.abc.service.PermissionService;
import com.abc.service.RoleService;
import com.abc.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


public class CustomizedRealm extends AuthorizingRealm {

    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;




    {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(hashMatcher);
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        Long userId = (Long) getAvailablePrincipal(principals);

        Set<String> roleNames = roleService.getRolesByUserId(userId);
        Set<String> permissions = permissionService.getPermissionRulesByUserId(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        User userDB = userService.findByUname(username);
        if (userDB == null) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB.getUid(), userDB.getPwd(), getName());
        if (userDB.getPwdSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getPwdSalt()));
        }

        return info;

    }

}
