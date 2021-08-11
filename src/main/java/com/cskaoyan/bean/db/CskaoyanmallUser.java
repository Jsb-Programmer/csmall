package com.cskaoyan.bean.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CskaoyanmallUser {
    Integer id;
    String username;
    String password;
    String gender;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    Date birthday;
    Date lastLoginTime;
}
