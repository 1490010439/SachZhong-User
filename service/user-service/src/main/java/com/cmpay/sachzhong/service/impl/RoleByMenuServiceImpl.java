package com.cmpay.sachzhong.service.impl;

import com.cmpay.sachzhong.dao.IRoleByMenuDao;
import com.cmpay.sachzhong.entity.RoleByMenuDO;
import com.cmpay.sachzhong.entity.RoleByMenuDOExample;
import com.cmpay.sachzhong.entity.RoleByMenuDOKey;
import com.cmpay.sachzhong.service.RoleByMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @classname UserService
 * @author SachZhong 钟盛勤
 * @date 2020/6/20 14:41
 */
@Service
public class RoleByMenuServiceImpl implements RoleByMenuService {

    @Autowired
    IRoleByMenuDao iRoleByMenuDao;

    @Override
    public RoleByMenuDO get(int id) {
        RoleByMenuDOKey roleByMenuDOKey=new RoleByMenuDOKey();
        roleByMenuDOKey.setRolebymenuId(id);
        return iRoleByMenuDao.get(roleByMenuDOKey);
    }

    @Override
    public List<RoleByMenuDO> find(RoleByMenuDO entity) {
        return iRoleByMenuDao.find(entity);
    }

    @Override
    public int insert(RoleByMenuDO entity) {
        return iRoleByMenuDao.insert(entity);
    }

    @Override
    public int update(RoleByMenuDO entity) {
        return iRoleByMenuDao.update(entity);
    }

    @Override
    public int delete(int id) {
        RoleByMenuDOKey roleByMenuDOKey=new RoleByMenuDOKey();
        roleByMenuDOKey.setRolebymenuId(id);
        return iRoleByMenuDao.delete(roleByMenuDOKey);
    }

    @Override
    public List<RoleByMenuDO> getById(int id) {

        RoleByMenuDOExample roleByMenuDOExample =new RoleByMenuDOExample();
        RoleByMenuDOExample.Criteria criteria =roleByMenuDOExample.createCriteria();
        criteria.andRolebymenuIdEqualTo(id);
        return iRoleByMenuDao.selectByExample(roleByMenuDOExample);
    }

    @Override
    public List<RoleByMenuDO> getByRoleid(int roleid) {
        RoleByMenuDOExample roleByMenuDOExample =new RoleByMenuDOExample();
        RoleByMenuDOExample.Criteria criteria =roleByMenuDOExample.createCriteria();
        criteria.andRolebymenuRoleidEqualTo(roleid);
        return iRoleByMenuDao.selectByExample(roleByMenuDOExample);
    }

    @Override
    public List<RoleByMenuDO> getByMenuid(int menuid) {
        RoleByMenuDOExample roleByMenuDOExample =new RoleByMenuDOExample();
        RoleByMenuDOExample.Criteria criteria =roleByMenuDOExample.createCriteria();
        criteria.andRolebymenuMenuidEqualTo(menuid);
        return iRoleByMenuDao.selectByExample(roleByMenuDOExample);
    }
}
