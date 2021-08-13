package com.cskaoyan.bean.vo.goodsVo;

import com.cskaoyan.bean.pojo.Order;

import java.util.List;

/**
 * @ClassName RespDetailData
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/12 10:41
 **/

public class RespDetailData<T> {


    /**
    * @author:Programmer
    * @Description: Gsonformat
    * @Date: 2021/8/12
    * @Param:
    * @return

     * orderGoods : [{"id":45,"orderId":34,"goodsId":1135002,"goodsName":"宫廷奢华真丝四件套","goodsSn":"1135002","productId":204,"number":1,"price":2599,"specifications":["标准"],"picUrl":"http://yanxuan.nosdn.127.net/45548f26cfd0c7c41e0afc3709d48286.png","comment":0,"addTime":"2021-07-14 17:53:46","updateTime":"2021-07-14 17:53:46","deleted":false}]
     * user : {"nickname":"测试用户","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"}
     * order : {"id":34,"userId":1,"orderSn":"20210714497973","orderStatus":103,"consignee":"刘师傅","mobile":"18375730021","address":"湖北省 武汉市 洪山区 珞喻路129号","message":"","goodsPrice":2599,"freightPrice":0,"couponPrice":20,"integralPrice":0,"grouponPrice":0,"orderPrice":2579,"actualPrice":2579,"comments":0,"endTime":"2021-07-14 18:40:36","addTime":"2021-07-14 17:53:46","updateTime":"2021-07-14 18:40:36","deleted":false}
     **/

    private UserBean user;
    private Order order;
    private List<OrderGoodsBean> orderGoods;



    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderGoodsBean> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoodsBean> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public RespDetailData() {
    }

    public RespDetailData(UserBean user, Order order, List<OrderGoodsBean> orderGoods) {
        this.user = user;
        this.order = order;
        this.orderGoods = orderGoods;
    }

    public static class UserBean {
        /**
         * nickname : 测试用户
         * avatar : https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80
         */

        private String nickname;
        private String avatar;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class OrderGoodsBean {
        /**
         * id : 45
         * orderId : 34
         * goodsId : 1135002
         * goodsName : 宫廷奢华真丝四件套
         * goodsSn : 1135002
         * productId : 204
         * number : 1
         * price : 2599.0
         * specifications : ["标准"]
         * picUrl : http://yanxuan.nosdn.127.net/45548f26cfd0c7c41e0afc3709d48286.png
         * comment : 0
         * addTime : 2021-07-14 17:53:46
         * updateTime : 2021-07-14 17:53:46
         * deleted : false
         */

        private int id;
        private int orderId;
        private int goodsId;
        private String goodsName;
        private String goodsSn;
        private int productId;
        private int number;
        private double price;
        private String picUrl;
        private int comment;
        private String addTime;
        private String updateTime;
        private boolean deleted;
        private List<String> specifications;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsSn() {
            return goodsSn;
        }

        public void setGoodsSn(String goodsSn) {
            this.goodsSn = goodsSn;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public List<String> getSpecifications() {
            return specifications;
        }

        public void setSpecifications(List<String> specifications) {
            this.specifications = specifications;
        }
    }
}
