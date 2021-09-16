package com.cskaoyan.bean.bo.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Checked {

    @JsonProperty("productIds")
    private List<Integer> productIds;
    @JsonProperty("isChecked")
    private Integer isChecked;
}
