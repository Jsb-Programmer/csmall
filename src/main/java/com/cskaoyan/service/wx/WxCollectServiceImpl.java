package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Collect;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectAddVo;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectListVo;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectVo;
import com.cskaoyan.mapper.CollectMapper;
import com.cskaoyan.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

/**
 * @ClassName WxCollectServiceImpl
 * @Description TODO  通过shiro获取userI
 * @Author wpb
 * @Date 2021/8/15 18:32
 **/
@Service
public class WxCollectServiceImpl implements WxCollectService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CollectMapper collectMapper;

    @Override
    public WxCollectVo querry(Integer type, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        List<WxCollectListVo> collectList = goodsMapper.selectCollectJoinGoods(userId);
        PageInfo<WxCollectListVo> pageInfo = new PageInfo<>(collectList);
        long totalPages = pageInfo.getTotal();
        return WxCollectVo.create(collectList, totalPages);
    }

    @Override
    public WxCollectAddVo addordelete(Collect collect) {
        //新加sql 根据valueId 查询collect表，如果查到 返回type是delelte，没有查到返回type是add
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        Collect collectSelect = collectMapper.selectByValueId(collect.getValueId());
        collect.setUserId(userId);
        collect.setAddTime(new Date());
        // 2021 10/9
        collect.setUpdateTime(new Date());

        if (collectSelect == null) {
            //收藏有两种情况，需要分情况讨论。
            //1.原来表中没有记录，直接插入 根据用户id插入
            int i = collectMapper.insertSelective(collect);
            WxCollectAddVo hava = new WxCollectAddVo();
            hava.setType("add");
            return hava;
            //2. 虽然在前端取消了收藏，但在数据库中，这条记录仍然存在，getDeleted字段值为1，用户还可能反复收藏，
            //此时不用新插入一条数据，而是直接把原来的记录对应的getDeleted字段值修改即可。
        } else if (collectSelect != null && collectSelect.getDeleted() == true) {
            //把状态码改为0(false)，才能持续delete
            collectMapper.updateStatusToZeroByValueId(collect.getValueId());
            WxCollectAddVo hava = new WxCollectAddVo();
            hava.setType("add");
            return hava;
            //取消收藏，不删除记录，仅更改对应的字段值。假删除
        } else if (collectSelect != null) {
            collectMapper.updateStatusByValueId(collect.getValueId());
            WxCollectAddVo noHave = new WxCollectAddVo();
            noHave.setType("delete");
            return noHave;
        }
        // 没有进入if时随便返回一个值，不至于报错。
        WxCollectAddVo noUse = new WxCollectAddVo();
        noUse.setType("空的啊");

        return noUse;
    }
}
