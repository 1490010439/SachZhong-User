package com.cmpay.sachzhong.service;

import com.cmpay.sachzhong.entity.RoleDO;

import java.util.List;

/**
 * @classname UserService
 * @author SachZhong 钟盛勤
 * @date 2020/6/20 14:41
 */
public interface RoleService {

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    RoleDO get(int id);

    /**
     * 根据条件查找
     * @param entity
     * @return
     */
    List<RoleDO> find(RoleDO entity);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    int insert(RoleDO entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int update(RoleDO entity);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int delete(int id);

}