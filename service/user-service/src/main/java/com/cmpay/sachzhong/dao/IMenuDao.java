/*
 * @ClassName IMenuDao
 * @Description 
 * @version 1.0
 * @Date 2020-06-23 10:01:22
 */
package com.cmpay.sachzhong.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.sachzhong.entity.MenuDO;
import com.cmpay.sachzhong.entity.MenuDOExample;
import com.cmpay.sachzhong.entity.MenuDOKey;
import java.util.List;

import com.cmpay.sachzhong.entity.RoleDO;
import com.cmpay.sachzhong.utils.SqlValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMenuDao extends BaseDao<MenuDO, MenuDOKey> {
    /**
     * @Title countByExample
     * @param example
     * @return 
     */
    int countByExample(MenuDOExample example);

    /**
     * @Title deleteByExample
     * @param example
     * @return 
     */
    int deleteByExample(MenuDOExample example);

    /**
     * @Title selectByExample
     * @param example
     * @return 
     */
    List<MenuDO> selectByExample(MenuDOExample example);

    /**
     * @Title updateByExampleSelective
     * @param record
     * @param example
     * @return 
     */
    int updateByExampleSelective(@Param("record") MenuDO record, @Param("example") MenuDOExample example);

    /**
     * @Title updateByExample
     * @param record
     * @param example
     * @return 
     */
    int updateByExample(@Param("record") MenuDO record, @Param("example") MenuDOExample example);

    int deleteMenu(SqlValue mysql);

    List<MenuDO> selectLikeName(SqlValue mysql);
}