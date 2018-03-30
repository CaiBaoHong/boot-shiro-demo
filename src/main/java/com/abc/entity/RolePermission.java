package com.abc.entity;

import java.io.Serializable;

public class RolePermission implements Serializable{

    private Long roleId;
    private Long permId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }
}
