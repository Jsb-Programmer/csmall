package com.cskaoyan.bean.vo.wxCollectVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName WxCollectVo
 * @Description TODO
 * @Author Programmer
 * @Date 2021/8/15 23:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxCollectVo<T> {

    /**
     * totalPages : 2
     * collectList : [{"brief":"阿斯顿撒多","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/f8hczjrls6ezwdtkmiwh.png","valueId":1181019,"name":"萨达","id":138,"type":0,"retailPrice":34},{"brief":"aaa","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/rjhs8i29bfahd5hi7okm.jpg","valueId":1181056,"name":"ok","id":137,"type":0,"retailPrice":600},{"brief":"","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/cttz51i99tt8jgsfo8ii.png","valueId":1181043,"name":"qqq","id":136,"type":0,"retailPrice":11},{"brief":"最新款","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/7c43tgnkhtof6auug9q3.jpeg","valueId":1181045,"name":"兰博基尼","id":135,"type":0,"retailPrice":99.99},{"brief":"这还不冲吗","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/sbdcljutvg50wnemx73d.jpg","valueId":1181053,"name":"mm","id":134,"type":0,"retailPrice":222},{"brief":"苹果商品简介","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/0vs6ish0dhvhoqwiqx8s.jpg","valueId":1181017,"name":"苹果","id":127,"type":0,"retailPrice":30},{"brief":"9","picUrl":"","valueId":1181010,"name":"9999","id":126,"type":0,"retailPrice":9},{"brief":"设计师原款，精致绣花","picUrl":"http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png","valueId":1006002,"name":"轻奢纯棉刺绣水洗四件套","id":123,"type":0,"retailPrice":899},{"brief":"经典桌角，美观稳固","picUrl":"http://yanxuan.nosdn.127.net/bcf2a72face2c4221dfdc9b3c97d4062.png","valueId":1097006,"name":"原素系列折角实木圆桌","id":121,"type":0,"retailPrice":999},{"brief":"hukhk","picUrl":"","valueId":1181046,"name":"汽车","id":113,"type":0,"retailPrice":100}]
     */


    private List<T> collectList;
    private long totalPages;



    public static <T> WxCollectVo create(List<T> collectList, long totalPages){
        return new WxCollectVo(collectList,totalPages);
    }


}
