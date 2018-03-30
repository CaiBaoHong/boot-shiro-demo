package com.abc.service;

import com.abc.dao.PermissionDao;
import com.abc.dao.RolePermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

}
