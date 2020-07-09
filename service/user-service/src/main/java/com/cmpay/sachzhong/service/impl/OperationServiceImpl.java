package com.cmpay.sachzhong.service.impl;

import com.cmpay.lemon.framework.utils.PageUtils;
import com.cmpay.sachzhong.dao.IOperationDao;
import com.cmpay.sachzhong.entity.*;
import com.cmpay.sachzhong.service.OperationService;
import com.cmpay.sachzhong.utils.SqlValue;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    IOperationDao iOperationDao;

    @Override
    public List<OperationDO> selectList() {

        OperationDOExample operationDOExample =new OperationDOExample();
        OperationDOExample.Criteria criteria =operationDOExample.createCriteria();
        return iOperationDao.selectByExample(operationDOExample);
    }

    @Override
    public OperationDO get(int id) {
        OperationDOKey operationDOKey =new OperationDOKey();
        operationDOKey.setOperationId(id);
        return iOperationDao.get(operationDOKey);
    }

    @Override
    public List<OperationDO> find(OperationDO entity) {
        return iOperationDao.find(entity);
    }

    @Override
    public int insert(OperationDO entity) {
        return iOperationDao.insert(entity);
    }

    @Override
    public int update(OperationDO entity) {
        OperationDOExample operationDOExample=new OperationDOExample();
        OperationDOExample.Criteria criteria = operationDOExample.createCriteria();
        criteria.andOperationIdEqualTo(entity.getOperationId());
        return iOperationDao.updateByExample(entity,operationDOExample);
    }

    @Override
    public int delete(int id) {
        OperationDOKey operationDOKey =new OperationDOKey();
        operationDOKey.setOperationId(id);
        return iOperationDao.delete(operationDOKey);
    }

    @Override
    public List<OperationDO> getById(int id) {

        OperationDOExample operationDOExample =new OperationDOExample();
        OperationDOExample.Criteria criteria =operationDOExample.createCriteria();
        criteria.andOperationIdEqualTo(id);
        return iOperationDao.selectByExample(operationDOExample);
    }

    @Override
    public List<OperationDO> getByOperationNumber(String operationNumber) {

        OperationDOExample operationDOExample =new OperationDOExample();
        OperationDOExample.Criteria criteria =operationDOExample.createCriteria();
        criteria.andOperationNumberEqualTo(operationNumber);
        return iOperationDao.selectByExample(operationDOExample);
    }

    @Override
    public PageInfo<OperationDO> getPage(int pageNum, int pageSize, OperationDO operationDO) {
        PageInfo<OperationDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<OperationDO>(iOperationDao.find(operationDO));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iOperationDao.find(operationDO));
        }
        return pageInfo;
    }

    @Override
    public int deleteOperation(int id) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setIntValue(id);
        return iOperationDao.deleteOperation(sqlValue);
    }

    @Override
    public List<OperationDO> selectLikeName(String name) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setMynode("'%"+name+"%'");
        return iOperationDao.selectLikeName(sqlValue);
    }

    @Override
    public PageInfo<OperationDO> getLikePage(int pageNum, int pageSize, String name) {
        SqlValue sqlValue =new SqlValue();
        sqlValue.setMynode("'%"+name+"%'");

        PageInfo<OperationDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<OperationDO>(iOperationDao.selectLikeName(sqlValue));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iOperationDao.selectLikeName(sqlValue));
        }

        return pageInfo;
    }


}
