package com.cmpay.sachzhong.controller;

import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.sachzhong.dto.ResultDTO;
import com.cmpay.sachzhong.entity.*;
import com.cmpay.sachzhong.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * InterfaceController
 * @author SachZhong 
 * @date 2020/7/9 9:26
*/
@RestController
@RequestMapping("/sachzhong/interface")
public class InterfaceController {

    @Autowired
    MenuByOperationService menuByOperationService;

    @Autowired
    MenuService menuService;

    @Autowired
    OperationService operationService;

    @Autowired
    RoleByMenuService roleByMenuService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserByRoleService userByRoleService;

    @Autowired
    UserService userService;

    /**
     * @author SachZhong 钟盛勤
     * @date 2020/6/23 15:05
     * @info :根据用户ID获取所有信息
     */
    @Transactional
    @GetMapping("/getAllInfoByUserId")
    public DefaultRspDTO<ResultDTO> getAllInfoByUserId(int userid)
    {
        ResultDTO resultDTO=new ResultDTO();

        List<MenuByOperationDO> menuByOperationRspDTOS=new ArrayList<MenuByOperationDO>();

        List<MenuDO> menuRspDTOS=new ArrayList<MenuDO>();

        List<OperationDO> operationRspDTOS=new ArrayList<OperationDO>();

        List<RoleByMenuDO> roleByMenuRspDTOS=new ArrayList<RoleByMenuDO>();

        List<RoleDO> roleRspDTOS=new ArrayList<RoleDO>();

        List<UserByRoleDO> userByRoleRspDTOS=new ArrayList<UserByRoleDO>();

        List<UserDO> userRspDTOS=new ArrayList<UserDO>();


        //先查询用户信息
        userRspDTOS = userService.getById(userid);

        //再查询拥有的角色
        userByRoleRspDTOS = userByRoleService.getByUserid(userid);

        //遍历拥有的角色关联
        for (int i=0;i<userByRoleRspDTOS.size();i++)
        {
            //得到角色关联对象
            UserByRoleDO  userByRoleDO = userByRoleRspDTOS.get(i);

            //根据角色关联对象中的角色ID,获取角色
            //roleRspDTOS  =roleService.getById(userByRoleDO.getUserbyroleId());
            roleRspDTOS.addAll(roleService.getById(userByRoleDO.getUserbyroleId()));


            //根据角色ID获取关联的菜单
            //roleByMenuRspDTOS =roleByMenuService.getByRoleid(userByRoleDO.getUserbyroleId());
            roleByMenuRspDTOS.addAll(roleByMenuService.getByRoleid(userByRoleDO.getUserbyroleId()));
        }

        //遍历拥有的菜单关联
        for (int i=0;i<roleByMenuRspDTOS.size();i++)
        {
            RoleByMenuDO  roleByMenuDO = roleByMenuRspDTOS.get(i);

            //根据菜单关联对象中的菜单ID,获取菜单
            //menuRspDTOS  =menuService.getById(roleByMenuDO.getRolebymenuMenuid());
            menuRspDTOS.addAll(menuService.getById(roleByMenuDO.getRolebymenuMenuid()));

            //根据菜单ID获取关联的操作
            //menuByOperationRspDTOS =menuByOperationService.getByMenuid(roleByMenuDO.getRolebymenuMenuid());
            menuByOperationRspDTOS.addAll(menuByOperationService.getByMenuid(roleByMenuDO.getRolebymenuMenuid()));

        }

        //遍历拥有的操作关联
        for (int i=0;i<menuByOperationRspDTOS.size();i++)
        {
            MenuByOperationDO  menuByOperationDO = menuByOperationRspDTOS.get(i);

            //根据操作关联对象中的操作ID,获取操作
            operationRspDTOS.addAll(operationService.getById(menuByOperationDO.getMenubyoperationOperationid()));
        }


        resultDTO.setMenuByOperationRspDTOS(menuByOperationRspDTOS);
        resultDTO.setMenuRspDTOS(menuRspDTOS);
        resultDTO.setOperationRspDTOS(operationRspDTOS);
        resultDTO.setRoleByMenuRspDTOS(roleByMenuRspDTOS);
        resultDTO.setRoleRspDTOS(roleRspDTOS);
        resultDTO.setUserByRoleRspDTOS(userByRoleRspDTOS);
        resultDTO.setUserRspDTOS(userRspDTOS);

        return DefaultRspDTO.newSuccessInstance(resultDTO);
    }


}



