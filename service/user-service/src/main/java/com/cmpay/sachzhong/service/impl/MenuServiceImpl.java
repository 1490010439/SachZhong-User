package com.cmpay.sachzhong.service.impl;

import com.cmpay.lemon.framework.utils.PageUtils;
import com.cmpay.sachzhong.dao.IMenuDao;
import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.entity.MenuDOExample;
import com.cmpay.sachzhong.entity.MenuDOKey;
import com.cmpay.sachzhong.service.MenuService;
import com.cmpay.sachzhong.utils.SqlValue;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    IMenuDao iMenuDao;

    @Override
    public List<MenuDO> selectList() {
        MenuDOExample menuDOExample =new MenuDOExample();
        MenuDOExample.Criteria criteria =menuDOExample.createCriteria();
        return iMenuDao.selectByExample(menuDOExample);
    }

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

        MenuDOExample menuDOExample=new MenuDOExample();
        MenuDOExample.Criteria criteria = menuDOExample.createCriteria();
        criteria.andMenuIdEqualTo(entity.getMenuId());
        return iMenuDao.updateByExample(entity,menuDOExample);
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

    @Override
    public PageInfo<MenuDO> getPage(int pageNum, int pageSize, MenuDO menuDO) {
        PageInfo<MenuDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<MenuDO>(iMenuDao.find(menuDO));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iMenuDao.find(menuDO));
        }

        return pageInfo;
    }

    @Override
    public int deleteMenu(int id) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setIntValue(id);
        return iMenuDao.deleteMenu(sqlValue);
    }

    @Override
    public List<MenuDO> selectLikeName(String name) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setMynode("'%"+name+"%'");
        return iMenuDao.selectLikeName(sqlValue);
    }

    @Override
    public PageInfo<MenuDO> getLikePage(int pageNum, int pageSize, String name) {
        SqlValue sqlValue =new SqlValue();
        sqlValue.setMynode("'%"+name+"%'");

        PageInfo<MenuDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<MenuDO>(iMenuDao.selectLikeName(sqlValue));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iMenuDao.selectLikeName(sqlValue));
        }

        return pageInfo;
    }
}
