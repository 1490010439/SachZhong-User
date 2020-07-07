package com.cmpay.sachzhong.service.impl;

import com.cmpay.lemon.framework.utils.PageUtils;
import com.cmpay.sachzhong.dao.IUserDao;
import com.cmpay.sachzhong.entity.UserDO;
import com.cmpay.sachzhong.entity.UserDOExample;
import com.cmpay.sachzhong.entity.UserDOKey;
import com.cmpay.sachzhong.service.UserService;
import com.cmpay.sachzhong.utils.SqlValue;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @classname UserServiceImpl
 * @author SachZhong 钟盛勤
 * @date 2020/6/20 14:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    IUserDao iUserDao;

    @Override
    public UserDO get(int id) {

        UserDOKey userDOKey=new UserDOKey();
        userDOKey.setUserId(id);

        return iUserDao.get(userDOKey);
    }

    @Override
    public List<UserDO> find(UserDO entity) {
        return iUserDao.find(entity);
    }

    @Override
    public int insert(UserDO entity) {
        return iUserDao.insert(entity);
    }

    @Override
    public int update(UserDO entity) {
        UserDOExample userDOExample=new UserDOExample();
        UserDOExample.Criteria criteria = userDOExample.createCriteria();
        criteria.andUserIdEqualTo(entity.getUserId());
        return iUserDao.updateByExample(entity,userDOExample);
    }

    @Override
    public int delete(int id) {

        UserDOKey userDOKey=new UserDOKey();
        userDOKey.setUserId(id);

        return iUserDao.delete(userDOKey);
    }

    @Override
    public int deleteUser(int id) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setIntValue(id);
        return iUserDao.deleteUser(sqlValue);
    }

    @Override
    public List<UserDO> getById(int id) {
        UserDOExample userDOExample=new UserDOExample();
        UserDOExample.Criteria criteria = userDOExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        return iUserDao.selectByExample(userDOExample);
    }

    @Override
    public PageInfo<UserDO> getPage(int pageNum, int pageSize, UserDO userDO) {

        PageInfo<UserDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<UserDO>(iUserDao.find(userDO));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iUserDao.find(userDO));
        }

        return pageInfo;
    }

    @Override
    public List<UserDO> selectUserList() {

        UserDOExample UserDOExample=new UserDOExample();
        List<UserDO> mylist=iUserDao.selectByExample(UserDOExample);
        return mylist;
    }

    @Override
    public List<UserDO> login(String username, String password) {
        UserDOExample UserDOExample=new UserDOExample();
        UserDOExample.Criteria criteria = UserDOExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        criteria.andUserPasswordEqualTo(password);
        List<UserDO> mylist=iUserDao.selectByExample(UserDOExample);
        return mylist;
    }

    @Override
    public List<UserDO> loginByPhone(String phone, String password) {

        //设置查询语句
        UserDOExample UserDOExample=new UserDOExample();
        UserDOExample.Criteria criteria = UserDOExample.createCriteria();
        criteria.andUserPhoneEqualTo(phone);
        criteria.andUserPasswordEqualTo(password);
        List<UserDO> mylist=iUserDao.selectByExample(UserDOExample);

        return mylist;

    }

    @Override
    public List<UserDO> selectPhone(String phone) {

        //设置查询语句
        UserDOExample UserDOExample=new UserDOExample();
        UserDOExample.Criteria criteria = UserDOExample.createCriteria();
        criteria.andUserPhoneEqualTo(phone);

        return iUserDao.selectByExample(UserDOExample);

    }

    @Override
    public List<UserDO> selectByBetween(String mynode, int betweenstart, int betweenend, String mynodeby, String DESCorASC) {
        //select * from user where userid between 1 and 2 order by usertype  DESC
        SqlValue mysql =new SqlValue(mynode,betweenstart,betweenend,mynodeby,DESCorASC);
        return iUserDao.selectByBetween(mysql);
    }



    @Override
    public int reg(UserDO user) {
        return iUserDao.insert(user);
    }


    @Override
    public List<UserDO> selectUserName(String username) {
        //设置查询语句
        UserDOExample UserDOExample=new UserDOExample();
        UserDOExample.Criteria criteria = UserDOExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        return iUserDao.selectByExample(UserDOExample);
    }

    @Override
    public List<UserDO> selectUserLikeName(String username) {

        SqlValue sqlValue=new SqlValue();
        sqlValue.setMynode("'%"+username+"%'");
        return iUserDao.selectUserLikeName(sqlValue);
    }

    @Override
    public PageInfo<UserDO> getLikePage(int pageNum, int pageSize, String userName) {

        SqlValue sqlValue =new SqlValue();
        sqlValue.setMynode("'%"+userName+"%'");

        PageInfo<UserDO> pageInfo = null;
        if (pageNum == 0 || pageSize == 0){

            pageInfo =new PageInfo<UserDO>(iUserDao.selectUserLikeName(sqlValue));
        }
        else {
            pageInfo = PageUtils.pageQueryWithCount(pageNum,pageSize,()-> iUserDao.selectUserLikeName(sqlValue));
        }

        return pageInfo;
    }

    @Override
    public List<UserDO> selectUsermail(String usermail) {
        //设置查询语句
        UserDOExample UserDOExample=new UserDOExample();
        UserDOExample.Criteria criteria = UserDOExample.createCriteria();
        criteria.andUserMailEqualTo(usermail);
        return iUserDao.selectByExample(UserDOExample);
    }

    @Override
    public List<UserDO> selectUserPage(String userdeletetype,int page, int size){

        //如果每页的大小小于1,赋值1
        if(size<=0)
        {
            size=1;
        }

        //进行分页，分页从第一页开始
        page=(page-1)*size;

        //如果分页输错，小于0了,赋值0，也就是第一页开始
        if(page<=0)
        {
            page=0;
        }

        SqlValue sqlValue=new SqlValue();
        sqlValue.setMynode(userdeletetype);
        sqlValue.setBetweenStart(page);
        sqlValue.setBetweenEnd(size);
        return iUserDao.selectUserPage(sqlValue);
    }

    @Override
    public int selectUserCount(String userdeletetype) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setMynode(userdeletetype);
        return iUserDao.selectUserCount(sqlValue);
    }


    @Override
    public int updateUserMoney(float money, int id) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setFloatNode(money);
        sqlValue.setIntValue(id);
        return iUserDao.updateUserMoney(sqlValue);
    }

    @Override
    public List<UserDO> selectLike(String value) {
        SqlValue sqlValue=new SqlValue();
        sqlValue.setMynode("'%"+value+"%'");
        return iUserDao.selectLike(sqlValue);
    }



}
