package com.cskaoyan.bean.vo.system;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class RolePermission {
    List assignedPermissions;
    String systemPermissions ="****";

}
