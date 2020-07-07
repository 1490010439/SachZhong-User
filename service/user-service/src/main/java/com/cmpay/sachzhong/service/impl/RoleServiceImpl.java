package com.cmpay.sachzhong.service.impl;

import com.cmpay.lemon.framework.utils.PageUtils;
import com.cmpay.sachzhong.dao.IRoleDao;
import com.cmpay.sachzhong.entity.RoleDO;
import com.cmpay.sachzhong.entity.RoleDOExample;
import com.cmpay.sachzhong.entity.RoleDOKey;
import com.cmpay.sachzhong.entity.UserDO;
import com.cmpay.sachzhong.service.RoleService;
import com.cmpay.sachzhong.utils.SqlValue;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @classname UserService
 * @author SachZhong 钟盛勤
 * @date 2020/6/20 14:41
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    IRoleDao iRoleDao;


    @Override
    public List<RoleDO> selectList() {
        RoleDOExample roleDOExample =new RoleDOExample();
        RoleDOExample.Criteria criteria =roleDOExample.createCriteria();
        return iRoleDao.selectByExample(roleDOExample);
    }

    @Override
    public RoleDO get(int id) {
        RoleDOKey roleDOKey=new RoleDOKey();
        roleDOKey.setRoleId(id);
        return iRoleDao.get(roleDOKey);
    }

    @Override
    public List<RoleDO> find(RoleDO entity) {
        return iRoleDao.find(entity);
    }

    @Override
    public int insert(RoleDO entity) {
        return iRoleDao.insert(entity);
    }

    @Override
    public int update(RoleDO entity) {
        RoleDOExample roleDOExample=new RoleDOExample();
        RoleDOExample.Criteria criteria = roleDOExample.createCriteria();
        criteria.andRoleIdEqualTo(entity.getRoleId());
        return iRoleDao.updateByExample(entity,roleDOExample);
    }

    @Override
    public int delete(int id) {
        RoleDOKey roleDOKey=new RoleDOKey();
        roleDOKey.setRoleId(id);
        return iRoleDao.delete(roleDOKey);
    }

    @Override
    public PageInfo<RoleDO> getPage(int pageNum, int pageSize, RoleDO roleDO) {
        PageInfo<RoleDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<RoleDO>(iRoleDao.find(roleDO));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iRoleDao.find(roleDO));
        }

        return pageInfo;
    }

    @Override
    public List<RoleDO> getById(int id) {
        RoleDOExample roleDOExample =new RoleDOExample();
        RoleDOExample.Criteria criteria =roleDOExample.createCriteria();
        criteria.andRoleIdEqualTo(id);
        return iRoleDao.selectByExample(roleDOExample);
    }

    @Override
    public List<RoleDO> getByRoleNumber(String roleNumber) {
        RoleDOExample roleDOExample =new RoleDOExample();
        RoleDOExample.Criteria criteria =roleDOExample.createCriteria();
        criteria.andRoleNumberEqualTo(roleNumber);
        return iRoleDao.selectByExample(roleDOExample);
    }

    @Override
    public int deleteRole(int id) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setIntValue(id);
        return iRoleDao.deleteRole(sqlValue);
    }

    @Override
    public List<RoleDO> selectLikeName(String name) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setMynode("'%"+name+"%'");
        return iRoleDao.selectLikeName(sqlValue);
    }

    @Override
    public PageInfo<RoleDO> getLikePage(int pageNum, int pageSize, String name) {
        SqlValue sqlValue =new SqlValue();
        sqlValue.setMynode("'%"+name+"%'");

        PageInfo<RoleDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<RoleDO>(iRoleDao.selectLikeName(sqlValue));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iRoleDao.selectLikeName(sqlValue));
        }

        return pageInfo;
    }
}
