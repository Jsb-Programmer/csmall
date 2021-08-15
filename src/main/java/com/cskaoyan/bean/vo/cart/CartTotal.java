package com.cskaoyan.bean.vo.cart;

import lombok.Data;

@Data
public class CartTotal {
    private Integer goodsCount=0;
    private Integer checkedGoodsCount=0;
    private Double goodsAmount=0.0;
    private Double checkedGoodsAmount=0.0;

    public void addGoodsCount(Integer crea){
        this.goodsCount+=crea;
    }
    public void addCheckedGoodsCount(Integer crea){
        this.checkedGoodsCount+=crea;
    }
    public void addGoodsAmount(Double crea){
        this.goodsAmount+=crea;
    }
    public void addCheckedGoodsAmount(Double crea){
        this.checkedGoodsAmount+=crea;
    }
}
