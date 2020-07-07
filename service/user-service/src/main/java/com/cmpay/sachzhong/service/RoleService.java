package com.cmpay.sachzhong.service;

import com.cmpay.sachzhong.entity.RoleDO;
import com.cmpay.sachzhong.entity.UserDO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @classname UserService
 * @author SachZhong 钟盛勤
 * @date 2020/6/20 14:41
 */
public interface RoleService {

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :查询所有列表
     *
     */
    List<RoleDO> selectList();

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

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :分页
     *
     */
    PageInfo<RoleDO> getPage(int pageNum, int pageSize, RoleDO roleDO );


    /**
     * 根据ID查找
     * @param id
     * @return
     */
    List<RoleDO> getById(int id);

    /**
     * 根据 roleNumber 操作编号 查找
     * @param roleNumber
     * @return
     */
    List<RoleDO> getByRoleNumber(String roleNumber);

    int deleteRole(int id);

    List<RoleDO> selectLikeName(String name);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :分页
     *
     */
    PageInfo<RoleDO> getLikePage(int pageNum, int pageSize, String name );



}
