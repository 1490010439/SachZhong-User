package com.cmpay.sachzhong.controller;

import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.common.utils.JudgeUtils;
import com.cmpay.lemon.framework.annotation.QueryBody;
import com.cmpay.lemon.framework.data.DefaultRspDTO;
import com.cmpay.sachzhong.dto.MenuPageRspDTO;
import com.cmpay.sachzhong.dto.OperationPageRspDTO;
import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.entity.OperationDO;
import com.cmpay.sachzhong.service.OperationService;
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
 * @classname OperationController
 * @author SachZhong 钟盛勤
 * @date 2020/6/22 17:21
 * 操作接口
 */

@RestController
@RequestMapping("/sachzhong/operation")
public class OperationController {

    @Autowired
    OperationService operationService;


    /**
     * 查询全部信息
     */
    @GetMapping("/select")
    public DefaultRspDTO<List<OperationDO>> select()
    {
        List<OperationDO> list = operationService.selectList();
        return DefaultRspDTO.newSuccessInstance(list);
    }

    @GetMapping("/list")
    public DefaultRspDTO<OperationPageRspDTO> list(@Validated @QueryBody OperationPageRspDTO operationPageRspDTO) {
        OperationDO operationDO = new OperationDO();
        if (JudgeUtils.isNotNull(operationPageRspDTO.getOperations())) {
            operationDO = BeanUtils.copyPropertiesReturnDest(new OperationDO(), operationPageRspDTO.getOperations());
        }
        PageInfo<OperationDO> pageInfo = operationService.getPage(operationPageRspDTO.getPageNum(), operationPageRspDTO.getPageSize(), operationDO);
        OperationPageRspDTO operationPageRspDTO1 = new OperationPageRspDTO();
        operationPageRspDTO1.setOperations(BeanConvertUtils.convertList(pageInfo.getList(), OperationDO.class));
        operationPageRspDTO1.setPageNum(pageInfo.getPageNum());
        operationPageRspDTO1.setPageSize(pageInfo.getPageSize());
        operationPageRspDTO1.setPages(pageInfo.getPages());
        operationPageRspDTO1.setTotal(pageInfo.getTotal());
        return DefaultRspDTO.newSuccessInstance(operationPageRspDTO1);
    }

    /**
     * 查询信息 根据ID
     */
    @GetMapping("/getById")
    public DefaultRspDTO<OperationDO> getById(@QueryBody int id)
    {
        OperationDO list = operationService.getById(id).get(0);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * 查找信息
     */
    @GetMapping("/find")
    public DefaultRspDTO<List<OperationDO>> find(@QueryBody OperationDO operationDO) {

        List<OperationDO> list = operationService.find(operationDO);

        return DefaultRspDTO.newSuccessInstance(list);
    }



    /**
     * 更新信息
     */
    @PostMapping("/update")
    public DefaultRspDTO<Integer> update(@RequestBody OperationDO operationDO) {

        //设置时间
        LocalDateTime localDateTime =LocalDateTime.now();
        operationDO.setOperationUpdatetime(localDateTime);
        operationDO.setOperationOpuserid(1);

        int result = operationService.update(operationDO);

        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 添加信息
     */
    @PostMapping("/insert")
    public DefaultRspDTO<Integer> insert(@RequestBody OperationDO operationDO) {

        //生成用户ID
        Integer id = UUID.randomUUID().hashCode();
        operationDO.setOperationId(id);

        //设置时间
        LocalDateTime localDateTime =LocalDateTime.now();
        operationDO.setOperationFoundtime(localDateTime);
        operationDO.setOperationUpdatetime(localDateTime);
        //设置基本信息
        operationDO.setOperationOpuserid(1);
        operationDO.setOperationDeletetype("false");

        String number ="SachZhong_op_"+new Date().toString();
        operationDO.setOperationNumber(number);

        int result = operationService.insert(operationDO);

        return DefaultRspDTO.newSuccessInstance(result);
    }

    /**
     * 删除信息
     */
    @DeleteMapping("/delete")
    public DefaultRspDTO<Integer> delete(@RequestBody int id) {

        int result = operationService.delete(id);

        return DefaultRspDTO.newSuccessInstance(result);
    }

    /**
     * 根据 operationNumber 操作编号 查找
     */
    @GetMapping("/getByOperationNumber")
    public DefaultRspDTO<List<OperationDO>> getByOperationNumber(@RequestBody String operationNumber)
    {
        List<OperationDO> list = operationService.getByOperationNumber(operationNumber);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    /**
     * 删除信息
     */
    @DeleteMapping("/deleteOperation")
    public DefaultRspDTO<Integer> deleteOperation(@Validated @RequestBody int id) {
        int result = operationService.deleteOperation(id);
        return DefaultRspDTO.newSuccessInstance(result);
    }


    /**
     * 根据 name  查找
     * @param name
     * @return
     */
    @GetMapping("/selectLikeName")
    public DefaultRspDTO<List<OperationDO>> selectLikeName(@Validated @QueryBody String name)
    {
        List<OperationDO> list = operationService.selectLikeName(name);
        return DefaultRspDTO.newSuccessInstance(list);
    }

    @GetMapping("/selectLikeNamePage")
    public DefaultRspDTO<OperationPageRspDTO> selectLikeNamePage(@Validated @QueryBody   String name) {
        System.out.println("name:"+name);
        PageInfo<OperationDO> pageInfo = operationService.getLikePage(1, 10, name);
        OperationPageRspDTO operationPageRspDTO = new OperationPageRspDTO();
        operationPageRspDTO.setOperations(BeanConvertUtils.convertList(pageInfo.getList(), OperationDO.class));
        operationPageRspDTO.setPageNum(pageInfo.getPageNum());
        operationPageRspDTO.setPageSize(pageInfo.getPageSize());
        operationPageRspDTO.setPages(pageInfo.getPages());
        operationPageRspDTO.setTotal(pageInfo.getTotal());
        return DefaultRspDTO.newSuccessInstance(operationPageRspDTO);
    }

}
