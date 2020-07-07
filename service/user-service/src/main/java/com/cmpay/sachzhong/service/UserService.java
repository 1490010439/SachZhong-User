package com.cmpay.sachzhong.service;

import com.cmpay.sachzhong.entity.RoleDO;
import com.cmpay.sachzhong.entity.UserDO;
import com.cmpay.sachzhong.utils.SqlValue;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @classname UserService
 * @author SachZhong 钟盛勤
 * @date 2020/6/20 14:41
 */
public interface UserService {

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    UserDO get(int id);

    /**
     * 根据条件查找
     * @param entity
     * @return
     */
    List<UserDO> find(UserDO entity);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    int insert(UserDO entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int update(UserDO entity);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int  deleteUser(int id);


    /**
     * 根据ID查找
     * @param id
     * @return
     */
    List<UserDO> getById(int id);

  /**
   * @author SachZhong 钟盛勤
   * @date 2020/6/22 11:21
   * @info :分页
   *
   */
    PageInfo<UserDO> getPage(int pageNum, int pageSize, UserDO userDO );



    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :查询所有用户列表 返回用户列表
     *
     */
     List<UserDO> selectUserList();

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :登陆 username password
     *
     */
    List<UserDO> login(String username, String password);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :手机登陆 返回用户列表
     *
     */
     List<UserDO> loginByPhone(String phone, String password);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :查询手机是否存在了 返回用户列表
     *
     */
     List<UserDO> selectPhone(String phone);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :设置查询 返回用户列表
     * select * from user where userid between 1 and 2 order by usertype  DESC
     */

     List<UserDO> selectByBetween(String mynode, int betweenstart, int betweenend, String mynodeby, String DESCorASC);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :注册 返回0（失败），1（成功）
     *
     */
     int reg(UserDO user);



    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :根据用户名查询
     *
     */
    List<UserDO> selectUserName(String username);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :根据用户名模糊查询
     *
     */
     List<UserDO> selectUserLikeName(String username);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :分页
     *
     */
    PageInfo<UserDO> getLikePage(int pageNum, int pageSize, String userName );



    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :根据邮箱查询
     *
     */
     List<UserDO> selectUsermail(String usermail);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :自定义分页
     *
     */
     List<UserDO> selectUserPage(String userdeletetype,int page, int size);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :统计删除或者未删除的用户
     *
     */
     int  selectUserCount(String userdeletetype);

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :更新用户金钱
     *
     */
    int updateUserMoney(float money,int id);


    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :模糊查询用户
     *
     */
    List<UserDO> selectLike(String value);

}
