package com.abc.service;

import com.abc.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public Set<String> getPermissionRulesByUserId(Long userId){
        List<String> list = permissionDao.getPermissionRulesByUserId(userId);
        return new TreeSet<>(list);
    }
}
