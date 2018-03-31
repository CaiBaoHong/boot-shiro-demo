package com.abc.service;

import com.abc.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 根据用户id查询用户的所有角色
     * @param userId
     * @return
     */
    public Set<String> getRolesByUserId(Long userId){
        List<String> list = roleDao.getRolesByUserId(userId);
        return new TreeSet<>(list);
    }

}
