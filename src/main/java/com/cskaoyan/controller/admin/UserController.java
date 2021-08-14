
package com.cskaoyan.controller.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.user.ReceivedAddressBO;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-11 21:03
 **/

@RestController
@RequestMapping("admin")
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping("user/list")
    public BaseRespVo getUsers(String username,String mobile,BaseParam baseParam){
        BaseRespData<User> userBaseRespData = userService.queryUsers(username, mobile, baseParam);
        return BaseRespVo.ok(userBaseRespData);
    }

    @RequestMapping("address/list")
    public BaseRespVo address(String name , String userId ,BaseParam baseParam){
        BaseRespData<ReceivedAddressBO> receivedAddress = userService.queryAddress(name,userId,baseParam);
        if (receivedAddress == null){
            return BaseRespVo.fail("参数值不对");
        }
        return BaseRespVo.ok(receivedAddress);
    }

    @RequestMapping("collect/list")
    public BaseRespVo collectList(String userId , String valueId, BaseParam baseParam){
        BaseRespData<Collect> collectBaseRespData = userService.queryCollects(userId,valueId,baseParam);
        if(collectBaseRespData == null){
            return BaseRespVo.fail("参数值不对");
        }
        return BaseRespVo.ok(collectBaseRespData);
    }

    @RequestMapping("footprint/list")
    public BaseRespVo footprint(String userId,String goodsId,BaseParam baseParam){
        BaseRespData<FootPrint> printBaseRespData = userService.queryFootPrints(userId,goodsId,baseParam);
        if(printBaseRespData == null){
            return BaseRespVo.fail("参数值不对");
        }
        return BaseRespVo.ok(printBaseRespData);
    }

    @RequestMapping("history/list")
    public BaseRespVo history(String userId , String keyword , BaseParam baseParam){
        BaseRespData<SearchHistory> searchHistoryBaseRespData = userService.querySearchHistory(userId , keyword ,baseParam);
        if (searchHistoryBaseRespData == null){
            return BaseRespVo.fail("参数值不对");
        }
        return BaseRespVo.ok(searchHistoryBaseRespData);

    }

    @RequestMapping("feedback/list")
    public BaseRespVo feedback(String username,String id ,BaseParam baseParam){
        BaseRespData<Feedback> feedbackBaseRespData = userService.queryFeedbacks(username,id,baseParam);
        if (feedbackBaseRespData==null){
            return BaseRespVo.fail("参数值不对");
        }
        return BaseRespVo.ok(feedbackBaseRespData);
    }

}

