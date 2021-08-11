package com.cskaoyan.mapper;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.db.CskaoyanmallUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<CskaoyanmallUser> select(@Param("username") String username,
                                  @Param("mobile") String mobile,
                                  @Param("param") BaseParam param);
}
