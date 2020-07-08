package com.cmpay.sachzhong.dto;

import java.util.List;

public class MenuListDTO {

    List<Integer> menuIds;

    public List<Integer> getRoleIds() {
        return menuIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.menuIds = roleIds;
    }
}
