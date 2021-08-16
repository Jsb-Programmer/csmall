package com.cskaoyan.mapper;

import com.cskaoyan.bean.pojo.Goods;
import com.cskaoyan.bean.pojo.GoodsExample;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectListVo;
import com.cskaoyan.bean.vo.wxCollectVo.WxCollectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    Goods selectGoodsForGroupon(Integer goodsId);
    // 新加sql 需要获取collect表里的userId 需要传一个userId.这里先写死
    List<WxCollectListVo> selectCollectJoinGoods();
}