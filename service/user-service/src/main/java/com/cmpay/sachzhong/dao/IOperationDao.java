/*
 * @ClassName IOperationDao
 * @Description 
 * @version 1.0
 * @Date 2020-06-23 10:01:22
 */
package com.cmpay.sachzhong.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.sachzhong.entity.OperationDO;
import com.cmpay.sachzhong.entity.OperationDOExample;
import com.cmpay.sachzhong.entity.OperationDOKey;
import java.util.List;

import com.cmpay.sachzhong.utils.SqlValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IOperationDao extends BaseDao<OperationDO, OperationDOKey> {
    /**
     * @Title countByExample
     * @param example
     * @return 
     */
    int countByExample(OperationDOExample example);

    /**
     * @Title deleteByExample
     * @param example
     * @return 
     */
    int deleteByExample(OperationDOExample example);

    /**
     * @Title selectByExample
     * @param example
     * @return 
     */
    List<OperationDO> selectByExample(OperationDOExample example);

    /**
     * @Title updateByExampleSelective
     * @param record
     * @param example
     * @return 
     */
    int updateByExampleSelective(@Param("record") OperationDO record, @Param("example") OperationDOExample example);

    /**
     * @Title updateByExample
     * @param record
     * @param example
     * @return 
     */
    int updateByExample(@Param("record") OperationDO record, @Param("example") OperationDOExample example);

    int deleteOperation(SqlValue mysql);

    List<OperationDO> selectLikeName(SqlValue mysql);
}