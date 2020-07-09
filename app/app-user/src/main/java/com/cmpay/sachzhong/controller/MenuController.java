package com.cmpay.sachzhong.controller;

import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.common.utils.JudgeUtils;
import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.sachzhong.dto.MenuListDTO;
import com.cmpay.sachzhong.dto.MenuPageRspDTO;
import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.service.MenuService;
import com.cmpay.sachzhong.utils.BeanConvertUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @classname MenuController
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
     * 查询全部信息
     */
    @GetMapping("/select")
    public DefaultRspDTO<List<MenuDO>> select()
    {
        List<MenuDO> list = menuService.selectList();
        return DefaultRspDTO.newSuccessInstance(list);
    }


    @GetMapping("/list")
    public DefaultRspDTO<MenuPageRspDTO> list(@Validated @QueryBody MenuPageRspDTO menuPageRspDTO) {
        MenuDO menuDO = new MenuDO();
        if (JudgeUtils.isNotNull(menuPageRspDTO.getMenus())) {
            menuDO = BeanUtils.copyPropertiesReturnDest(new MenuDO(), menuPageRspDTO.getMenus());
        }
        PageInfo<MenuDO> pageInfo = menuService.getPage(menuPageRspDTO.getPageNum(), menuPageRspDTO.getPageSize(), menuDO);
        MenuPageRspDTO menuPageRspDTO1 = new MenuPageRspDTO();
        menuPageRspDTO1.setMenus(BeanConvertUtils.convertList(pageInfo.getList(), MenuDO.class));
        menuPageRspDTO1.setPageNum(pageInfo.getPageNum());
        menuPageRspDTO1.setPageSize(pageInfo.getPageSize());
        menuPageRspDTO1.setPages(pageInfo.getPages());
        menuPageRspDTO1.setTotal(pageInfo.getTotal());
        return DefaultRspDTO.newSuccessInstance(menuPageRspDTO1);
    }


    /**
     * 查询信息 根据ID
     */
    @GetMapping("/getById")
    public DefaultRspDTO<MenuDO> getById(@QueryBody int id)
    {
        MenuDO menuDO = menuService.getById(id).get(0);
        return DefaultRspDTO.newSuccessInstance(menuDO);
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
    @PostMapping("/update")
    public DefaultRspDTO<Integer> update(@RequestBody MenuDO menuDO) {

        //设置时间
        LocalDateTime localDateTime =LocalDateTime.now();
        menuDO.setMenuUpdatetime(localDateTime);
        menuDO.setMenuOpuserid(1);
        int result = menuService.update(menuDO);

        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 添加信息
     */
    @PostMapping("/insert")
    public DefaultRspDTO<Integer> insert(@RequestBody MenuDO menuDO) {

        //生成用户ID
        Integer id = UUID.randomUUID().hashCode();

        menuDO.setMenuId(id);

        //设置时间
        LocalDateTime localDateTime =LocalDateTime.now();
        menuDO.setMenuFoundtime(localDateTime);
        menuDO.setMenuUpdatetime(localDateTime);
        //设置基本信息
        menuDO.setMenuOpuserid(1);
        menuDO.setMenuDeletetype("false");

        String number ="SachZhong_menu_"+new Date().toString();
        menuDO.setMenuNumber(number);

        int result = menuService.insert(menuDO);

        return DefaultRspDTO.newSuccessInstance(result);
    }

    /**
     * 删除信息
     */
    @DeleteMapping("/delete")
    public DefaultRspDTO<Integer> delete(@Validated @RequestBody MenuListDTO menuListDTO) {
        List<Integer> menuIds = menuListDTO.getRoleIds();
        int result = menuService.delete(menuIds.get(0));
        return DefaultRspDTO.newSuccessInstance(result);
    }



    /**
     * 根据 menuNumber 菜单编号 查找
     */
    @GetMapping("/getByMenuNumber")
    public DefaultRspDTO<List<MenuDO>> getByMenuNumber(@QueryBody String menuNumber)
    {
        List<MenuDO> list = menuService.getByMenuNumber(menuNumber);
        return DefaultRspDTO.newSuccessInstance(list);
    }


    /**
     * 删除信息
     */
    @DeleteMapping("/deleteMenu")
    public DefaultRspDTO<Integer> deleteMenu(@Validated @RequestBody int id) {

        int result = menuService.deleteMenu(id);

        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 根据 name  查找
     * @param name
     * @return
     */
    @GetMapping("/selectLikeName")
    public DefaultRspDTO<List<MenuDO>> selectLikeName(@Validated @QueryBody String name)
    {
        List<MenuDO> list = menuService.selectLikeName(name);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    @GetMapping("/selectLikeNamePage")
    public DefaultRspDTO<MenuPageRspDTO> selectLikeNamePage(@Validated @QueryBody   String name) {
        System.out.println("name:"+name);
        PageInfo<MenuDO> pageInfo = menuService.getLikePage(1, 10, name);
        MenuPageRspDTO menuRspDTO = new MenuPageRspDTO();
        menuRspDTO.setMenus(BeanConvertUtils.convertList(pageInfo.getList(), MenuDO.class));
        menuRspDTO.setPageNum(pageInfo.getPageNum());
        menuRspDTO.setPageSize(pageInfo.getPageSize());
        menuRspDTO.setPages(pageInfo.getPages());
        menuRspDTO.setTotal(pageInfo.getTotal());
        return DefaultRspDTO.newSuccessInstance(menuRspDTO);
    }

}
