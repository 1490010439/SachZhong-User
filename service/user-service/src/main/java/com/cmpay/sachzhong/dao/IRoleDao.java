/*
 * @ClassName IRoleDao
 * @Description 
 * @version 1.0
 * @Date 2020-06-23 10:01:22
 */
package com.cmpay.sachzhong.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.sachzhong.entity.RoleDO;
import com.cmpay.sachzhong.entity.RoleDOExample;
import com.cmpay.sachzhong.entity.RoleDOKey;
import java.util.List;
import com.cmpay.sachzhong.utils.SqlValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IRoleDao extends BaseDao<RoleDO, RoleDOKey> {
    /**
     * @Title countByExample
     * @param example
     * @return 
     */
    int countByExample(RoleDOExample example);

    /**
     * @Title deleteByExample
     * @param example
     * @return 
     */
    int deleteByExample(RoleDOExample example);

    /**
     * @Title selectByExample
     * @param example
     * @return 
     */
    List<RoleDO> selectByExample(RoleDOExample example);

    /**
     * @Title updateByExampleSelective
     * @param record
     * @param example
     * @return 
     */
    int updateByExampleSelective(@Param("record") RoleDO record, @Param("example") RoleDOExample example);

    /**
     * @Title updateByExample
     * @param record
     * @param example
     * @return 
     */
    int updateByExample(@Param("record") RoleDO record, @Param("example") RoleDOExample example);

    int deleteRole(SqlValue mysql);

    List<RoleDO> selectLikeName(SqlValue mysql);
}