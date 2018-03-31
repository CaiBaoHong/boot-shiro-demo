package com.abc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionDao {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    public List<String> getPermissionRulesByUserId(Long userId){
        String sql = "SELECT p.rule FROM permission p, role_permission rp, user_role ur " +
                "WHERE p.pid = rp.perm_id AND ur.role_id = rp.role_id " +
                "AND ur.user_id = ?";
        return jdbc.queryForList(sql,String.class,userId);
    }

}
