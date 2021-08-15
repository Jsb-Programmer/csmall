package com.cskaoyan.bean.bo.system;

import lombok.Data;

import java.util.List;
@Data
public class RolePermissionsUpdate {
    List<String> permissions;
    Integer roleId;
}
