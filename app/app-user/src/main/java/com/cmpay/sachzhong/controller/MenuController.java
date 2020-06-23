package com.cmpay.sachzhong.controller;

import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @classname RoleController
 * @author SachZhong 钟盛勤
 * @date 2020/6/22 17:21
 * 菜单接口
 */

@RestController
@RequestMapping("/sachzhong/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 查询信息 根据ID
     */
    @GetMapping("/getById")
    public DefaultRspDTO< List<MenuDO>> getById(@QueryBody int id)
    {
        List<MenuDO> list = menuService.getById(id);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * 查找信息
     */
    @GetMapping("/find")
    public DefaultRspDTO<List<MenuDO>> find(@QueryBody MenuDO menuDO) {

        List<MenuDO> list = menuService.find(menuDO);

        return DefaultRspDTO.newSuccessInstance(list);
    }


    /**
     * 更新信息
     */
    @PutMapping("/update")
    public DefaultRspDTO<Integer> update(@QueryBody MenuDO menuDO) {

        int result = menuService.update(menuDO);

        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 添加信息
     */
    @PostMapping("/insert")
    public DefaultRspDTO<Integer> insert(@QueryBody MenuDO menuDO) {

        int result = menuService.insert(menuDO);

        return DefaultRspDTO.newSuccessInstance(result);
    }

    /**
     * 删除信息
     */
    @DeleteMapping("/delete")
    public DefaultRspDTO<Integer> delete(@QueryBody int id) {

        int result = menuService.delete(id);

        return DefaultRspDTO.newSuccessInstance(result);
    }



    /**
     * 根据 menuNumber 菜单编号 查找
     */
    @GetMapping("/getByMenuNumber")
    public DefaultRspDTO<List<MenuDO>> getByMenuNumber(String menuNumber)
    {
        List<MenuDO> list = menuService.getByMenuNumber(menuNumber);
        return DefaultRspDTO.newSuccessInstance(list);
    }


}
