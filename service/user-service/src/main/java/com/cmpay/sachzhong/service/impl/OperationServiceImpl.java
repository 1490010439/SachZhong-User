package com.cmpay.sachzhong.service.impl;

import com.cmpay.sachzhong.dao.IOperationDao;
import com.cmpay.sachzhong.entity.OperationDO;
import com.cmpay.sachzhong.entity.OperationDOKey;
import com.cmpay.sachzhong.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    IOperationDao iOperationDao;

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
        return iOperationDao.update(entity);
    }

    @Override
    public int delete(int id) {
        OperationDOKey operationDOKey =new OperationDOKey();
        operationDOKey.setOperationId(id);
        return iOperationDao.delete(operationDOKey);
    }
}
