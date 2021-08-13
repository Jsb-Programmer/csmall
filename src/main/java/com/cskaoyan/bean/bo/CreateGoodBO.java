package com.cskaoyan.bean.bo;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class CreateGoodBO {

    /**
     * goods : {"picUrl":"http://182.92.235.201:8083/wx/storage/fetch/r84mllwnjpzmq59zvglk.jpg","gallery":["http://182.92.235.201:8083/wx/storage/fetch/tcyw8hj05g5w8yf5tmkd.jpg","http://182.92.235.201:8083/wx/storage/fetch/9q3h62388gyv0y3hgo4d.jpg","http://182.92.235.201:8083/wx/storage/fetch/62zwk7dy1ox5p5ce8jt5.jpg"],"goodsSn":"jst123","name":"大熊猫","counterPrice":"12000","retailPrice":"9000","isNew":true,"isHot":true,"isOnSale":true,"keywords":"panda","categoryId":1005012,"brandId":1040000,"brief":"大熊猫（学名：Ailuropoda melanoleuca）：属于食肉目熊科大熊猫亚科大熊猫属唯一的哺乳动物。","detail":"<p>大熊猫（学名：<em>Ailuropoda melanoleuca<\/em>）：属于食肉目熊科大熊猫亚科大熊猫属唯一的<a href=\"https://baike.baidu.com/item/%E5%93%BA%E4%B9%B3%E5%8A%A8%E7%89%A9/62197\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"62197\">哺乳动物<\/a>。仅有二个<a href=\"https://baike.baidu.com/item/%E4%BA%9A%E7%A7%8D/6290944\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"6290944\">亚种<\/a>。雄性个体稍大于雌性。体型肥硕似熊、丰腴富态，头圆尾短，头躯长1.2-1.8米，尾长10-12厘米。体重80-120千克，最重可达180千克，体色为黑白两色，脸颊圆，有很大的黑眼圈，标志性的内八字的行走方式，也有解剖刀般锋利的爪子。大熊猫皮肤厚，最厚处可达10毫米。黑白相间的外表，有利于隐蔽在密林的树上和积雪的地面而不易被天敌发现。<\/p>","unit":"个"}
     * specifications : [{"specification":"male","value":"xxx","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/mmgczs2f11zan2n904de.jpg"},{"specification":"female","value":"yyy","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/p3mzm410b0sqv8co8e67.jpg"}]
     * products : [{"id":0,"specifications":["xxx","yyy"],"price":"12000","number":"9000","url":"http://182.92.235.201:8083/wx/storage/fetch/1r2sulh327xoi81k393r.jpg"}]
     * attributes : [{"attribute":"xmxmmx","value":"xssxsx"}]
     */

    @Valid
    private CreateGoodBeanBO goods;
    private List<CreateGoodSpecificationsBO> specifications;
    private List<@Valid CreateGoodProductsBO> products;
    private List<CreateGoodAttributesBO> attributes;
}
