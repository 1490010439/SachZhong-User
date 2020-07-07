package com.cmpay.sachzhong.dto;

import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.entity.UserDO;

import java.util.List;

public class MenuPageRspDTO extends PageRspDTO{


    private List<MenuDO> menus;

    public List<MenuDO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDO> menus) {
        this.menus = menus;
    }
}
