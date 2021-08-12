package com.cskaoyan.bean.vo.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class AdminList {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("roleIds")
    private List<Integer> roleIds;

}
