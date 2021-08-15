package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.system.RoleOptions;
import com.cskaoyan.bean.bo.system.RolePermissionsUpdate;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.system.RolePermission;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.Date;
import java.util.HashSet;
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
     * @return  返回值为0   密码长度小于6    {"errno":602,"errmsg":"管理员密码长度不能小于6"}
     *          返回值为1                 {"errno":601,"errmsg":"管理员名称不符合规定"}
     */
    @Override
    public Object adminCreate(Admin admin) {
        //管理员名称不符合规定
        if (admin.getUsername().length()<6) {
            return 1;
        }
        //管理员密码长度不能小于6
        if (admin.getPassword().length()<6) {
            return 0;
        }
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
    public Object adminUpdate(Admin admin) {
        //管理员名称不符合规定
        if (admin.getUsername().length()<6) {
            return 1;
        }
        //管理员密码长度不能小于6
        if (admin.getPassword().length()<6) {
            return 0;
        }

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
     * @return
     */
    @Override
    public String roleDelete(Role role) {
        //判断role是否有管理员正在使用
        // TODO: 2021/8/13
        //每次从数据库中查询10管理员信息,判断改角色是否正在使用
        int pageSize = 20;
        for (int i = 1; true; i++) {
            PageHelper.startPage(i,pageSize);
            AdminExample adminExample = new AdminExample();
            adminExample.setOrderByClause("id");
            adminExample.createCriteria().andDeletedEqualTo(false);
            List<Admin> admins = adminMapper.selectByExample(adminExample);
            for (Admin admin : admins) {
                Integer[] roleIds = admin.getRoleIds();
                for (Integer roleId : roleIds) {
                    if (roleId == role.getId()) {
                        return "当前角色存在管理员:"+admin.getUsername()+"，不能删除";
                    }
                }
            }

            if (admins.size()<pageSize)break;
        }
        //将deleted设置为true
        role.setDeleted(true);
        int i = roleMapper.updateByPrimaryKeySelective(role);
        return null;
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


    /**
     * 逻辑删除对象
     * @param storage
     * @return
     */
    @Override
    public int storageDelete(Storage storage) {
        //将deleted设置为true
        storage.setDeleted(true);
        int i = storageMapper.updateByPrimaryKeySelective(storage);
        return i;
    }

    @Autowired
    PermissionMapper permissionMapper;
    /**
     * 授权界面数据回显
     * @param roleId
     * @return
     */
    @Override
    public RolePermission rolePermissions(Integer roleId) {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(roleId);
        List<String> list = permissionMapper.selectPermissionByRoleId(roleId);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setAssignedPermissions(list);
        return rolePermission;
    }

    /**
     * 修改角色的权限
     * @param rolePermissionsUpdate
     */
    @Override
    public void rolePermissionsUpdate(RolePermissionsUpdate rolePermissionsUpdate) {
        //接收的权限总集合
        HashSet<String> reci = new HashSet<>(rolePermissionsUpdate.getPermissions());
        //原权限集合
        HashSet<String> old = new HashSet<>(permissionMapper.selectPermissionByRoleId(rolePermissionsUpdate.getRoleId()));
        //保留的权限集合,先克隆reci
        HashSet<String> retain = (HashSet<String>) reci.clone();
        boolean b = retain.retainAll(old);
        //需要被逻辑删除的权限集合
        HashSet<String> deleted = (HashSet<String>) old.clone();
        boolean b1 = deleted.removeAll(reci);
        //被删除的历史权限
        List<String> histroyDel= permissionMapper.selectDeletedPermissionByRoleId(rolePermissionsUpdate.getRoleId());
        //增加的权限
        HashSet<String> incre = (HashSet<String>) reci.clone();
        incre.removeAll(old);
        //增加权限中历史的删除权限
        HashSet<String> increDel = (HashSet<String>) incre.clone();
        increDel.retainAll(histroyDel);
        //新增权限
        HashSet<String> increNew = (HashSet<String>) incre.clone();
        increNew.removeAll(histroyDel);


        //逻辑删除
        for (String s : deleted) {
            Permission permission = new Permission();
            Date date = new Date(System.currentTimeMillis());
            permission.setUpdateTime(date);
            permission.setDeleted(true);
            PermissionExample permissionExample = new PermissionExample();
            PermissionExample.Criteria criteria = permissionExample.createCriteria();
            criteria.andRoleIdEqualTo(rolePermissionsUpdate.getRoleId());
            criteria.andPermissionEqualTo(s);
            int i = permissionMapper.updateByExampleSelective(permission, permissionExample);
        }
        //保留权限不需要操作

        //新增权限
        for (String s : increNew) {
            Permission permission = new Permission();
            permission.setPermission(s);
            permission.setRoleId(rolePermissionsUpdate.getRoleId());
            Date date = new Date(System.currentTimeMillis());
            permission.setAddTime(date);
            permission.setUpdateTime(date);
            permission.setDeleted(false);
            permissionMapper.insert(permission);
        }
        //恢复历史删除的权限
        for (String s : increDel) {
            Permission permission = new Permission();
            Date date = new Date(System.currentTimeMillis());
            permission.setUpdateTime(date);
            permission.setDeleted(false);
            PermissionExample permissionExample = new PermissionExample();
            PermissionExample.Criteria criteria = permissionExample.createCriteria();
            criteria.andRoleIdEqualTo(rolePermissionsUpdate.getRoleId());
            criteria.andPermissionEqualTo(s);
            int i = permissionMapper.updateByExampleSelective(permission, permissionExample);
        }
    }
}
