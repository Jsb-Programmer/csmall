package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.system.RoleOptions;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.mapper.AdminMapper;
import com.cskaoyan.mapper.LogMapper;
import com.cskaoyan.mapper.RoleMapper;
import com.cskaoyan.mapper.StorageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.System;
import java.util.Date;
import java.util.List;

/**
 * 系统管理-张明臣
 */
@Service
public class SystemServiceImpl implements SystemService{

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    LogMapper logMapper;

    /**
     * 查询管理员列表
     * @param username 模糊查询的名字
     * @param param 分页信息
     * @return 查询到的数据
     */
    @Override
    public BaseRespData adminList(String username, BaseParam param) {
        //分页信息录入
        PageHelper.startPage(param.getPage(),param.getLimit());
        //构造排序
        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause(param.getSort() + " " + param.getOrder());
        //拼接条件
        AdminExample.Criteria criteria = adminExample.createCriteria();
        if (username != null && !"".equals(username)){
            criteria.andUsernameLike("%"+username+"%");
        }
        //只筛选没有被逻辑删除的
        criteria.andDeletedEqualTo(false);
        //数据库查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();

        return BaseRespData.create(admins,total);
    }


    /**
     * create admin
     * @param admin
     * @return
     */
    @Override
    public Admin adminCreate(Admin admin) {
        Date time = new Date(System.currentTimeMillis());
        //第一次修改的时间默认为创建的时间
        admin.setAddTime(time);
        admin.setUpdateTime(time);
        admin.setDeleted(false);
        //最后一次登录的时间和ip默认不传
        int insert = adminMapper.insert(admin);

        //将添加到数据库的管理员信息返回
        int id = adminMapper.selectIdByUsername(admin.getUsername());
        admin.setId(id);

        return admin;
    }

    /**
     * 修改admin信息
     * @param admin
     * @return
     */
    @Override
    public Admin adminUpdate(Admin admin) {
        admin.setUpdateTime(new Date(System.currentTimeMillis()));
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        //通过id检索
        criteria.andIdEqualTo(admin.getId());
        adminMapper.updateByExampleSelective(admin,adminExample);
        return admin;
    }

    /**
     * 逻辑删除管理员
     * @param admin
     * @return
     */
    @Override
    public Integer adminDelete(Admin admin) {
        //将deleted设置为true
        admin.setDeleted(true);
        int i = adminMapper.updateByPrimaryKeySelective(admin);
        return i;
    }


    /**
     * 模糊查询log
     * @param name
     * @param param
     * @return
     */
    @Override
    public BaseRespData logList(String name, BaseParam param) {
        //分页信息录入
        PageHelper.startPage(param.getPage(),param.getLimit());
        //构造排序
        LogExample logExample = new LogExample();
        logExample.setOrderByClause(param.getSort() + " " + param.getOrder());
        //拼接条件
        LogExample.Criteria criteria = logExample.createCriteria();
        if (name != null && !"".equals(name)){
            criteria.andAdminLike("%"+name+"%");
        }
        //只筛选没有被逻辑删除的
        criteria.andDeletedEqualTo(false);
        //数据库查询
        List<Log> logs = logMapper.selectByExample(logExample);

        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        long total = logPageInfo.getTotal();

        return BaseRespData.create(logs,total);
    }


    /**
     *
     * @param name
     * @param param
     * @return
     */
    @Override
    public BaseRespData roleList(String name, BaseParam param) {
        //分页信息录入
        PageHelper.startPage(param.getPage(),param.getLimit());
        //构造排序
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause(param.getSort() + " " + param.getOrder());
        //拼接条件
        RoleExample.Criteria criteria = roleExample.createCriteria();
        if (name != null && !"".equals(name)){
            criteria.andNameLike("%"+name+"%");
        }
        //只筛选没有被逻辑删除的
        criteria.andDeletedEqualTo(false);
        //数据库查询
        List<Role> roles = roleMapper.selectByExample(roleExample);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        long total = rolePageInfo.getTotal();

        return BaseRespData.create(roles,total);
    }

    @Autowired
    StorageMapper storageMapper;

    @Override
    public BaseRespData storageList(String name, String key, BaseParam param) {
        //分页信息录入
        PageHelper.startPage(param.getPage(),param.getLimit());
        //构造排序
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(param.getSort() + " " + param.getOrder());
        StorageExample.Criteria criteria = storageExample.createCriteria();
        //拼接条件
        if (name != null && !"".equals(name)){
            criteria.andNameLike("%"+name+"%");
        }
        if (name != null && !"".equals(name)){
            criteria.andKeyLike("%"+key+"%");
        }
        //只筛选没有被逻辑删除的
        criteria.andDeletedEqualTo(false);
        //数据库查询
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        PageInfo<Storage> storagePageInfo = new PageInfo<>(storages);
        long total = storagePageInfo.getTotal();
        return BaseRespData.create(storages,total);
    }

    /**
     * role的创建
     * @param name
     * @param desc
     * @return
     */
    @Override
    public Role roleCreate(String name, String desc) {
        Role role = new Role();
        role.setName(name);
        role.setDesc(desc);
        role.setEnabled(true);
        role.setAddTime(new Date(System.currentTimeMillis()));
        role.setDeleted(false);
        roleMapper.insert(role);
        return role;
    }

    /**
     * 根据id进项逻辑删除角色,但是需要判断该角色有无管理员正在使用
     * @param role
     */
    @Override
    public void roleDelete(Role role) {
        //将deleted设置为true
        role.setDeleted(true);
        int i = roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 根据输入的role 将role的信息进项更新
     * @param role
     */
    @Override
    public void roleUpdate(Role role) {
        role.setUpdateTime(new Date(System.currentTimeMillis()));
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        //按id进行检索
        criteria.andIdEqualTo(role.getId());
        //不更新加入的时间
        role.setAddTime(null);
        roleMapper.updateByExampleSelective(role,roleExample);
    }

    /**
     * 获取所有角色id name
     * @return
     */
    @Override
    public List<RoleOptions> roleOptions() {
        List<RoleOptions> list = roleMapper.selectAllRoleCusto();
        return list;
    }

    /**
     * 修改对象名
     * @param storage
     * @return
     */
    @Override
    public int storageUpdate(Storage storage) {
        int i = storageMapper.updateByPrimaryKeySelective(storage);
        return i;
    }

    @Override
    public int storageDelete(Storage storage) {
        //将deleted设置为true
        storage.setDeleted(true);
        int i = storageMapper.updateByPrimaryKeySelective(storage);
        return i;
    }


}
