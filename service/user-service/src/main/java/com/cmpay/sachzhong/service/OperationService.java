package com.cmpay.sachzhong.service;

import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.entity.OperationDO;

import java.util.List;

public interface OperationService {


    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :查询所有列表
     *
     */
    List<OperationDO> selectList();

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    OperationDO get(int id);

    /**
     * 根据条件查找
     * @param entity
     * @return
     */
    List<OperationDO> find(OperationDO entity);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    int insert(OperationDO entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int update(OperationDO entity);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int delete(int id);


    /**
     * 根据ID查找
     * @param id
     * @return
     */
    List<OperationDO> getById(int id);

    /**
     * 根据 operationNumber 操作编号 查找
     * @param operationNumber
     * @return
     */
    List<OperationDO> getByOperationNumber(String operationNumber);

}
