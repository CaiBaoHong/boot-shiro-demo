package com.abc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    public List<String> getRolesByUserId(Long userId) {
        String sql = "select r.rcode from role r, user_role ur " +
                "where r.rid = ur.role_id and ur.user_id = ?";
        return jdbc.queryForList(sql,String.class,userId);
    }
}
