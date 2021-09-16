package com.cskaoyan.bean.bo.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddBO {

    @JsonProperty("goodsId")
    private Integer goodsId;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("productId")
    private Integer productId;
}
