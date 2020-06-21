package com.cmpay.sachzhong.service.impl;

import com.cmpay.sachzhong.dao.IMenuDao;
import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.entity.MenuDOKey;
import com.cmpay.sachzhong.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    @Autowired
    IMenuDao iMenuDao;

    @Override
    public MenuDO get(int id) {
        MenuDOKey menuDOKey =new MenuDOKey();
        menuDOKey.setMenuId(id);
        return iMenuDao.get(menuDOKey);
    }

    @Override
    public List<MenuDO> find(MenuDO entity) {
        return iMenuDao.find(entity);
    }

    @Override
    public int insert(MenuDO entity) {
        return iMenuDao.insert(entity);
    }

    @Override
    public int update(MenuDO entity) {
        return iMenuDao.update(entity);
    }

    @Override
    public int delete(int id) {
        MenuDOKey menuDOKey =new MenuDOKey();
        menuDOKey.setMenuId(id);
        return iMenuDao.delete(menuDOKey);
    }
}
