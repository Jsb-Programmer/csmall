package com.cskaoyan.bean.vo.wxOrder;

import com.cskaoyan.bean.pojo.WxOrderGoods;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-14 17:20
 **/
@Data
public class OrderDetailDataVo implements Serializable {

    /**
     * orderInfo : {"consignee":"张松","address":"安徽省 合肥市 瑶海区 软件新城二期","addTime":"2021-08-14 15:30:00","orderSn":"20210814137528","actualPrice":99,"mobile":"18566549962","orderStatusText":"未付款","goodsPrice":99,"couponPrice":0,"id":97,"freightPrice":0,"handleOption":{"cancel":true,"delete":false,"pay":true,"comment":false,"confirm":false,"refund":false,"rebuy":false}}
     * orderGoods : [{"id":123,"orderId":97,"goodsId":1009013,"goodsName":"可水洗抗菌防螨丝羽绒枕","goodsSn":"1009013","productId":15,"number":1,"price":99,"specifications":["标准"],"picUrl":"http://yanxuan.nosdn.127.net/da56fda947d0f430d5f4cf4aba14e679.png","comment":0,"addTime":"2021-08-14 15:30:00","updateTime":"2021-08-14 15:30:00","deleted":false}]
     */

    private OrderInfoBean orderInfo;
    private List<WxOrderGoods> orderGoods;

    @Data
    public static class OrderInfoBean implements Serializable {
        /**
         * consignee : 张松
         * address : 安徽省 合肥市 瑶海区 软件新城二期
         * addTime : 2021-08-14 15:30:00
         * orderSn : 20210814137528
         * actualPrice : 99.0
         * mobile : 18566549962
         * orderStatusText : 未付款
         * goodsPrice : 99.0
         * couponPrice : 0.0
         * id : 97
         * freightPrice : 0.0
         * handleOption : {"cancel":true,"delete":false,"pay":true,"comment":false,"confirm":false,"refund":false,"rebuy":false}
         */

        private String consignee;
        private String address;
        private Date addTime;
        private String orderSn;
        private BigDecimal actualPrice;
        private String mobile;
        private String orderStatusText;
        private BigDecimal goodsPrice;
        private BigDecimal couponPrice;
        private Integer id;
        private BigDecimal freightPrice;
        private OrderHandlerOptionVo handleOption;


    }
}
