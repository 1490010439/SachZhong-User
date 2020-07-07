package com.cmpay.sachzhong.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.common.utils.JudgeUtils;
import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.lemon.framework.utils.PageUtils;
import com.cmpay.sachzhong.dto.ListDTO;
import com.cmpay.sachzhong.dto.UserByRoleDTO;
import com.cmpay.sachzhong.dto.UserDTO;
import com.cmpay.sachzhong.dto.UserPageRspDTO;
import com.cmpay.sachzhong.entity.RoleDO;
import com.cmpay.sachzhong.entity.UserByRoleDO;
import com.cmpay.sachzhong.entity.UserDO;
import com.cmpay.sachzhong.service.RoleService;
import com.cmpay.sachzhong.service.UserByRoleService;
import com.cmpay.sachzhong.service.UserService;
import com.cmpay.sachzhong.utils.BeanConvertUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @classname UserController
 * @author SachZhong 钟盛勤
 * @date 2020/6/22 11:21
 * 用户接口
 */
@RestController
@RequestMapping("/sachzhong/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserByRoleService userByRoleService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/list")
    public DefaultRspDTO<UserPageRspDTO> list(@RequestBody UserPageRspDTO userPageReqDTO) {
        UserDO userDO = new UserDO();
        if (JudgeUtils.isNotNull(userPageReqDTO.getUsers())) {
            userDO = BeanUtils.copyPropertiesReturnDest(new UserDO(), userPageReqDTO.getUsers());
        }
        PageInfo<UserDO> pageInfo = userService.getPage(userPageReqDTO.getPageNum(), userPageReqDTO.getPageSize(), userDO);
        UserPageRspDTO userRspDTO = new UserPageRspDTO();
        userRspDTO.setUsers(BeanConvertUtils.convertList(pageInfo.getList(), UserDO.class));
        userRspDTO.setPageNum(pageInfo.getPageNum());
        userRspDTO.setPageSize(pageInfo.getPageSize());
        userRspDTO.setPages(pageInfo.getPages());
        userRspDTO.setTotal(pageInfo.getTotal());
        return DefaultRspDTO.newSuccessInstance(userRspDTO);
    }


    /**
     * 查询全部用户信息
     */
    @PostMapping("/list1")
    public DefaultRspDTO<List<UserDO>> list1()
    {
        List<UserDO> list = userService.selectUserList();
        return DefaultRspDTO.newSuccessInstance(list);
    }


    /**
     * 查询全部用户信息
     */
    @GetMapping("/info")
    public DefaultRspDTO<List<UserDO>> info()
    {
        List<UserDO> list = userService.selectUserList();
        return DefaultRspDTO.newSuccessInstance(list);
    }


    /**
     * 查询全部用户信息
     */
    @GetMapping("/getAll")
    public DefaultRspDTO<List<UserDO>> getAll()
    {
        List<UserDO> list = userService.selectUserList();
        return DefaultRspDTO.newSuccessInstance(list);
    }


    /**
     * 查询用户信息 根据ID
     */
    @GetMapping("/getById")
    public DefaultRspDTO<UserDO> getById(@Validated @QueryBody int id)
    {
        UserDO userDO = userService.getById(id).get(0);

        //根据ID获取用户
        UserByRoleDTO userByRoleDTO =new UserByRoleDTO();
        userByRoleDTO.setUserId(userDO.getUserId());
        userByRoleDTO.setUserName(userDO.getUserName());
        userByRoleDTO.setUserMail(userDO.getUserMail());
        userByRoleDTO.setUserPassword(userDO.getUserPassword());
        userByRoleDTO.setUserPhone(userDO.getUserPhone());
        userByRoleDTO.setUserType(userDO.getUserType());

        //获取用户关联的角色
        List<UserByRoleDO> userByRoles =  userByRoleService.getByUserid(userByRoleDTO.getUserId());

        //初始化角色列表
        List<RoleDO> roleIds=new ArrayList<RoleDO>();

        for(int i=0;i<userByRoles.size();i++)
        {
            //用户角色关联对象
            UserByRoleDO userByRoleDO = userByRoles.get(i);
            //充填角色列表
            roleIds.addAll( roleService.getById(userByRoleDO.getUserbyroleId()));
        }

        //把角色列表充填到 DTO对象
        userByRoleDTO.setRoleIdList(roleIds);

        return DefaultRspDTO.newSuccessInstance(userByRoleDTO);
    }

    /**
     * 查找用户信息
     */
    @GetMapping("/find")
    public DefaultRspDTO<List<UserDO>> find(@Validated @QueryBody UserDO userDO) {

        List<UserDO> list = userService.find(userDO);

        return DefaultRspDTO.newSuccessInstance(list);
    }


    /**
     * 查询用户信息 分页
     */
    @GetMapping("/getPage")
    public DefaultRspDTO<PageInfo> getPage(@Validated @RequestBody int pageNum, int pageSize, UserDO userDO) {

        PageInfo pageInfo = userService.getPage(pageNum,pageSize,userDO);

        return DefaultRspDTO.newSuccessInstance(pageInfo);
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/update1")
    public DefaultRspDTO<Integer> update1(@Validated @RequestBody UserDO userDO) {

        int result = userService.update(userDO);

        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 更新用户信息
     */
    @PostMapping("/update")
    public DefaultRspDTO<Integer> update(@Validated @RequestBody UserByRoleDTO userByRoleDTO) {

        List<Integer> roleIds =userByRoleDTO.getRoleIds();

        UserDO userDO =new UserDO();
        //充填用户
        userDO.setUserId(userByRoleDTO.getUserId());
        userDO.setUserName(userByRoleDTO.getUserName());
        userDO.setUserMail(userByRoleDTO.getUserMail());
        userDO.setUserPassword(userByRoleDTO.getUserPassword());
        userDO.setUserPhone(userByRoleDTO.getUserPhone());
        userDO.setUserType(userByRoleDTO.getUserType());
        userDO.setUserDeletetype(userByRoleDTO.getUserDeletetype());
        //设置注册时间
        LocalDateTime localDateTime =LocalDateTime.now();
        userDO.setUserRegtime(localDateTime);
        userDO.setUserUpdatetime(localDateTime);

        //插入用户
        int result = userService.update(userDO);

//        //插入用户角色关联表
//        for (int i=0;i<roleIds.size();i++)
//        {
//            //用户角色关联对象
//            UserByRoleDO userByRoleDO =new UserByRoleDO();
//
//            //角色ID
//            Integer roleId = roleIds.get(0);
//            userByRoleDO.setUserbyroleRoleid(roleId);
//
//            //用户ID
//            userByRoleDO.setUserbyroleUserid(userDO.getUserId());
//            userByRoleDO.setUserbyroleDeletetype("false");
//
//            result=userByRoleService.update(userByRoleDO);
//
//        }


        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 添加用户信息
     */
    @PostMapping("/insert")
    public DefaultRspDTO<Integer> insert(@Validated @RequestBody UserByRoleDTO userByRoleDTO) {

        //生成用户ID
        Integer userid = UUID.randomUUID().hashCode();

        List<Integer> roleIds =userByRoleDTO.getRoleIds();

        UserDO userDO =new UserDO();
        //充填用户
        userDO.setUserId(userid);
        userDO.setUserName(userByRoleDTO.getUserName());
        userDO.setUserMail(userByRoleDTO.getUserMail());
        userDO.setUserPassword(userByRoleDTO.getUserPassword());
        userDO.setUserPhone(userByRoleDTO.getUserPhone());
        userDO.setUserType(userByRoleDTO.getUserType());
        //设置注册时间
        LocalDateTime localDateTime =LocalDateTime.now();
        userDO.setUserRegtime(localDateTime);
        userDO.setUserDeletetype("false");

        //插入用户
        int result = userService.insert(userDO);

        //插入用户角色关联表
        for (int i=0;i<roleIds.size();i++)
        {
            //用户角色关联对象
            UserByRoleDO userByRoleDO =new UserByRoleDO();

            //生成关联表ID
            Integer userByRoleDOid = UUID.randomUUID().hashCode();
            System.out.println("userByRoleDOid:"+userByRoleDOid);
            userByRoleDO.setUserbyroleId(userByRoleDOid);
            userByRoleDO.setUserbyroleOpuserid(1);
            userByRoleDO.setUserbyroleBack("用户ID:"+userid.toString()+",角色ID:"+userByRoleDOid.toString());
            //角色ID
            Integer roleId = roleIds.get(0);
            userByRoleDO.setUserbyroleRoleid(roleId);

            //用户ID
            userByRoleDO.setUserbyroleUserid(userid);
            userByRoleDO.setUserbyroleDeletetype("false");
            LocalDateTime localDateTime1 =LocalDateTime.now();
            userByRoleDO.setUserbyroleFoundtime(localDateTime1);

            result=userByRoleService.insert(userByRoleDO);

        }


        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 删除用户信息
     */
    @DeleteMapping("/delete")
    public DefaultRspDTO<Integer> delete(@Validated @RequestBody ListDTO listDTO) {
        List<Integer> userNos=listDTO.getUserNos();
        int result = userService.deleteUser(userNos.get(0));
        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 删除用户信息
     */
    @DeleteMapping("/deleteById")
    public DefaultRspDTO<Integer> deleteById(@Validated @RequestBody int id) {

        int result = userService.delete(id);

        return DefaultRspDTO.newSuccessInstance(result);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :登陆
     *
     */
    @PostMapping("/login1")
    public DefaultRspDTO<List<UserDO>> login1(@Validated @RequestBody UserDTO user)
    {
        List<UserDO> list = userService.login(user.getUsername(),user.getPassword());
        return DefaultRspDTO.newSuccessInstance(list);
    }
    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :登陆
     *
     */
    @PostMapping("/loginJson")
    public DefaultRspDTO<List<UserDO>> loginJson(@Validated @RequestBody JSONObject data)
    {
        String username = data.getString("username");
        String password = data.getString("password");
        List<UserDO> list = userService.login(username,password);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :手机登陆 返回用户列表
     *
     */
    @PostMapping("/loginByPhone")
    public DefaultRspDTO<List<UserDO>> loginByPhone(@Validated @RequestBody  String phone, String password)
    {
        List<UserDO> list = userService.loginByPhone(phone,password);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :查询手机是否存在了 返回用户列表
     *
     */
    @GetMapping("/selectPhone")
    public DefaultRspDTO<List<UserDO>> selectPhone(@Validated @QueryBody String phone)
    {
        List<UserDO> list = userService.selectPhone(phone);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :设置查询 返回用户列表
     * select * from user where userid between 1 and 2 order by usertype  DESC
     */
    @GetMapping("/selectByBetween")
    public DefaultRspDTO<List<UserDO>> selectByBetween(@Validated @QueryBody String mynode, int betweenstart, int betweenend, String mynodeby, String DESCorASC)
    {
        List<UserDO> list = userService.selectByBetween(mynode,betweenstart,betweenend,mynodeby,DESCorASC);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :注册 返回0（失败），1（成功）
     *
     */
    @PutMapping("/reg")
    public DefaultRspDTO<Integer>  reg(@Validated @RequestBody UserDO user)
    {
        int result = userService.reg(user);
        return DefaultRspDTO.newSuccessInstance(result);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :根据用户名查询
     *
     */
    @GetMapping("/selectUserName1")
    public DefaultRspDTO<List<UserDO>> selectUserName1(@Validated @QueryBody String userName)
    {
        List<UserDO> list = userService.selectUserName(userName);

        return DefaultRspDTO.newSuccessInstance(list);
    }

    @GetMapping("/selectUserName")
    public DefaultRspDTO<UserPageRspDTO> selectUserName(@Validated  String userName) {
        System.out.println("userName:"+userName);

        PageInfo<UserDO> pageInfo = userService.getLikePage(1, 10, userName);
        UserPageRspDTO userRspDTO = new UserPageRspDTO();
        userRspDTO.setUsers(BeanConvertUtils.convertList(pageInfo.getList(), UserDO.class));
        userRspDTO.setPageNum(pageInfo.getPageNum());
        userRspDTO.setPageSize(pageInfo.getPageSize());
        userRspDTO.setPages(pageInfo.getPages());
        userRspDTO.setTotal(pageInfo.getTotal());
        return DefaultRspDTO.newSuccessInstance(userRspDTO);
    }


    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :根据用户名模糊查询
     *
     */
    @GetMapping("/selectUserLikeName")
    public DefaultRspDTO<List<UserDO>> selectUserLikeName(@Validated @QueryBody String username)
    {
        List<UserDO> list = userService.selectUserLikeName(username);
        return DefaultRspDTO.newSuccessInstance(list);
    }


    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :根据邮箱查询
     *
     */
    @GetMapping("/selectUsermail")
    public DefaultRspDTO<List<UserDO>> selectUsermail(@Validated @QueryBody String usermail)
    {
        List<UserDO> list = userService.selectUsermail(usermail);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :自定义分页
     *
     */
    @GetMapping("/selectUserPage")
    public DefaultRspDTO<List<UserDO>> selectUserPage(@Validated @QueryBody String userdeletetype,int page, int size)
    {
        List<UserDO> list = userService.selectUserPage(userdeletetype,page,size);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :统计删除或者未删除的用户
     *
     */
    @GetMapping("/selectUserCount")
    public DefaultRspDTO<Integer> selectUserCount(@Validated @QueryBody String userdeletetype)
    {
        int result = userService.selectUserCount(userdeletetype);
        return DefaultRspDTO.newSuccessInstance(result);
    }

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :更新用户金钱
     *
     */
    @GetMapping("/updateUserMoney")
    public DefaultRspDTO<Integer> updateUserMoney(@Validated @QueryBody float money,int id)
    {
        int result = userService.updateUserMoney(money,id);
        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/22 11:21
     * @info :模糊查询用户
     *
     */
    @GetMapping("/selectLike")
    public DefaultRspDTO<List<UserDO>> selectLike(@Validated @QueryBody String value)
    {
        List<UserDO> list = userService.selectLike(value);
        return DefaultRspDTO.newSuccessInstance(list);
    }


}
