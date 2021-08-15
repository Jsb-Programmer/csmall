package com.cskaoyan.bean.vo.wxComment;

import lombok.Data;

import java.util.List;

/**
 * @author yangbo
 * @description
 * @date 2021/8/15 19:10
 */
@Data
public class ListCommentVO<T> {

    /**
     * userInfo : {"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"}
     * addTime : 2018-02-01 00:00:00
     * picList : []
     * content : 毛球要掉毛，第二次洗的时候掉了个毛球，心痛。白色太不经脏了，哭
     */

    private UserInfoBean userInfo;
    private String addTime;
    private String content;
    private List<T> picList;

    @Data
    public static class UserInfoBean {

        /**
         * nickName : 测试用户
         * avatarUrl : https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80
         */

        private String nickName;
        private String avatarUrl;

    }
}
