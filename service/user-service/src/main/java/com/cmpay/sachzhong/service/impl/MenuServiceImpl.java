package com.cmpay.sachzhong.service.impl;

import com.cmpay.sachzhong.dao.IMenuDao;
import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.entity.MenuDOExample;
import com.cmpay.sachzhong.entity.MenuDOKey;
import com.cmpay.sachzhong.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

    @Override
    public List<MenuDO> getById(int id) {
        MenuDOExample menuDOExample =new MenuDOExample();
        MenuDOExample.Criteria criteria =menuDOExample.createCriteria();
        criteria.andMenuIdEqualTo(id);
        return iMenuDao.selectByExample(menuDOExample);
    }

    @Override
    public List<MenuDO> getByMenuNumber(String menuNumber) {
        MenuDOExample menuDOExample =new MenuDOExample();
        MenuDOExample.Criteria criteria =menuDOExample.createCriteria();
        criteria.andMenuNumberEqualTo(menuNumber);
        return iMenuDao.selectByExample(menuDOExample);
    }
}
