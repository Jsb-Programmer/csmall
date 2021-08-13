package com.cskaoyan.bean.vo.groupon;

import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.pojo.Groupon;
import com.cskaoyan.bean.pojo.GrouponRules;
import lombok.Data;


/**
 * @author yangbo
 * @description
 * @date 2021/8/13 9:49
 */
@Data
public class GrouponListRecordVO {
    private Groupon groupon;
    private Goods goods;
    private GrouponRules rules;
}
