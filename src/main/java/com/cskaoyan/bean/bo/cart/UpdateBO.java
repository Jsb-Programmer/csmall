package com.cskaoyan.bean.bo.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateBO {

    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("goodsId")
    private Integer goodsId;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("id")
    private Integer id;
}
