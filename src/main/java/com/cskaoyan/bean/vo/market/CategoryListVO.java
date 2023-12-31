package com.cskaoyan.bean.vo.market;

import lombok.Data;

import java.util.List;

/**
 * @ClassName CategoryListVO
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 17:19
 * @Version 1.0
 **/
@Data
public class CategoryListVO {

    /**
     * id : 1005001
     * name : 餐厨
     * keywords :
     * desc : 爱，囿于厨房
     * iconUrl : http://yanxuan.nosdn.127.net/ad8b00d084cb7d0958998edb5fee9c0a.png
     * picUrl : http://yanxuan.nosdn.127.net/3708dbcb35ad5abf9e001500f73db615.png
     * level : L1
     * children : [{"id":1005007,"name":"锅具","keywords":"","desc":"一口好锅，炖煮生活一日三餐","iconUrl":"http://yanxuan.nosdn.127.net/4aab4598017b5749e3b63309d25e9f6b.png","picUrl":"http://yanxuan.nosdn.127.net/d2db0d1d0622c621a8aa5a7c06b0fc6d.png","level":"L2"},{"id":1005008,"name":"餐具","keywords":"","desc":"餐桌上的舞蹈","iconUrl":"http://yanxuan.nosdn.127.net/f109afbb7e7a00c243c1da29991a5aa3.png","picUrl":"http://yanxuan.nosdn.127.net/695ed861a63d8c0fc51a51f42a5a993b.png","level":"L2"},{"id":1005009,"name":"清洁","keywords":"","desc":"环保便利，聪明之选","iconUrl":"http://yanxuan.nosdn.127.net/e8b67fe8b8db2ecc2e126a0aa631def0.png","picUrl":"http://yanxuan.nosdn.127.net/3a40faaef0a52627357d98ceed7a3c45.png","level":"L2"},{"id":1007000,"name":"杯壶","keywords":"","desc":"精工生产制作，匠人手艺","iconUrl":"http://yanxuan.nosdn.127.net/0b244d3575b737c8f0ed7e84c5c4abd2.png","picUrl":"http://yanxuan.nosdn.127.net/ec53828a3814171079178a59fb2593da.png","level":"L2"},{"id":1008011,"name":"清洁保鲜","keywords":"","desc":"真空保鲜，美味不限时","iconUrl":"http://yanxuan.nosdn.127.net/dc4d6c35b9f4abb42d2eeaf345710589.png","picUrl":"http://yanxuan.nosdn.127.net/04cd632e1589adcc4345e40e8ad75d2b.png","level":"L2"},{"id":1008012,"name":"功能厨具","keywords":"","desc":"下厨省力小帮手","iconUrl":"http://yanxuan.nosdn.127.net/22db4ccbf52dc62c723ac83aa587812a.png","picUrl":"http://yanxuan.nosdn.127.net/5b94463017437467a93ae4af17c2ba4f.png","level":"L2"},{"id":1008013,"name":"茶具咖啡具","keywords":"","desc":"先进工艺制造，功夫体验","iconUrl":"http://yanxuan.nosdn.127.net/9ea192cd2719c8348f42ec17842ba763.png","picUrl":"http://yanxuan.nosdn.127.net/be3ba4056e274e311d1c23bd2931018d.png","level":"L2"},{"id":1013005,"name":"刀剪砧板","keywords":"","desc":"传统工艺 源自中国刀城","iconUrl":"http://yanxuan.nosdn.127.net/9d481ea4c2e9e6eda35aa720d407332e.png","picUrl":"http://yanxuan.nosdn.127.net/555afbfe05dab48c1a3b90dcaf89b4f2.png","level":"L2"},{"id":1023000,"name":"厨房小电","keywords":"","desc":"厨房里的省心小电器","iconUrl":"http://yanxuan.nosdn.127.net/521bd0c02d283b80ba49e73ca84df250.png","picUrl":"http://yanxuan.nosdn.127.net/c09d784ba592e4fadabbaef6b2e95a95.png","level":"L2"}]
     */

    private int id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private Integer pid;
    private List<CategoryListChildrenVO> children;

}
