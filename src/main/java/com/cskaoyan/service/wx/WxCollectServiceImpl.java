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
        PageHelper.startPage(page,size);
       // 新写一个连接查询的sql  sql里写死了一个userId
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        List<WxCollectListVo> collectList = goodsMapper.selectCollectJoinGoods(userId);

        PageInfo<WxCollectListVo> pageInfo = new PageInfo<>(collectList);
        long totalPages= pageInfo.getTotal();
        return  WxCollectVo.create(collectList,totalPages);
    }

    @Override
    public WxCollectAddVo addordelete(Collect collect) {
        //新加sql 根据valueId 查询collect表，如果查到 返回type是delelte，没有查到返回type是add
//        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        Collect collectSelect = collectMapper.selectByValueId(collect.getValueId());
        if (collectSelect==null){
            //bug 根据用户id插入
            collectMapper.insertSelective(collect);
            WxCollectAddVo hava= new WxCollectAddVo();
            hava.setType("add");
            return hava;
        }else if(collectSelect!=null&&collectSelect.getDeleted()==true){
            //把状态码改为0(false)，才能持续delete
            collectMapper.updateStatusToZeroByValueId(collect.getValueId());
            WxCollectAddVo hava= new WxCollectAddVo();
            hava.setType("add");
            return hava;
        }
        else if (collectSelect!=null){
            collectMapper.updateStatusByValueId(collect.getValueId());
            WxCollectAddVo noHave = new WxCollectAddVo();
            noHave.setType("delete");
            return noHave;
        }
        WxCollectAddVo noUse = new WxCollectAddVo();
        noUse.setType("空的啊");

        return  noUse;
    }
}
