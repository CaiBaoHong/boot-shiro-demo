package com.abc.dao;

import com.abc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    /**
     * 新增用户
     * @param user
     * @return
     */
    public Long save(User user){
        String sql = "INSERT INTO `user` (`uid`,`uname`,`nick`,`pwd`,`pwd_salt`,`created`,`updated`) " +
                "VALUES (:uid,:uname,:nick,:pwd,:pwdSalt,:created,:updated)";
        KeyHolder key  = new GeneratedKeyHolder();
        namedJdbc.update(sql, new BeanPropertySqlParameterSource(user), key);
        return (Long) key.getKey();
    }

    /**
     * 根据用户登录名查询用户
     * @param uname
     * @return
     */
    public User findByUname(String uname) {
        String sql = "select `uid`,`uname`,`nick`,`pwd`,`pwd_salt`,`created`,`updated` " +
                "FROM `user` where `uname` = ?";
        List<User> list = jdbc.query(sql, new BeanPropertyRowMapper<>(User.class), uname);
        return list.isEmpty()?null:list.get(0);
    }
}
