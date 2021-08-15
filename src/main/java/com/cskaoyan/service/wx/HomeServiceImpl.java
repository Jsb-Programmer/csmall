package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.home.WxFloorGoodsVO;
import com.cskaoyan.bean.vo.home.WxGroupOnVO;
import com.cskaoyan.bean.vo.home.WxIndexVO;
import com.cskaoyan.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    AdMapper adMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    TopicMapper topicMapper;

    @Transactional
    @Override
    public WxIndexVO index() {
        WxIndexVO wxIndexVO = new WxIndexVO();
        // 获取newGoodsList
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria newGoodsCriteria = goodsExample.createCriteria();
        newGoodsCriteria.andIsNewEqualTo(true);
        newGoodsCriteria.andDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        wxIndexVO.setNewGoodsList(goodsList);
        // 获取couponList
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andDeletedEqualTo(false);
        List<Coupon> couponList = couponMapper.selectByExample(couponExample);
        wxIndexVO.setCouponList(couponList);
        // 获取channel(category)
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria categoryExampleCriteria = categoryExample.createCriteria();
        categoryExampleCriteria.andLevelEqualTo("L1");
        categoryExampleCriteria.andDeletedEqualTo(false);
        List<Category> channel = categoryMapper.selectByExample(categoryExample);
        wxIndexVO.setChannel(channel);
        // 获取grouponList
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        grouponRulesExample.createCriteria().andDeletedEqualTo(false);
        List<GrouponRules> grouponRulesList = grouponRulesMapper.selectByExample(grouponRulesExample);
        ArrayList<WxGroupOnVO> grouponList = new ArrayList<>();
        for (GrouponRules grouponRule : grouponRulesList) {
            WxGroupOnVO wxGroupOnVO = new WxGroupOnVO();
            Integer goodsId = grouponRule.getGoodsId();
            Goods good = goodsMapper.selectByPrimaryKey(goodsId);
            // 如果商品被删除则不予显示
            if (good.getDeleted())
                continue;
            // 获取groupon_price
            BigDecimal discount = grouponRule.getDiscount();
            BigDecimal retailPrice = good.getRetailPrice();
            BigDecimal groupon_price = retailPrice.multiply(discount).divide(new BigDecimal(100));
            wxGroupOnVO.setGroupon_price(groupon_price);
            // 获取good
            wxGroupOnVO.setGoods(good);
            // 获取groupon_member
            wxGroupOnVO.setGroupon_member(grouponRule.getDiscountMember());
            grouponList.add(wxGroupOnVO);
        }
        wxIndexVO.setGrouponList(grouponList);
        // 获取banner
        AdExample adExample = new AdExample();
        adExample.createCriteria().andDeletedEqualTo(false);
        List<Ad> banner = adMapper.selectByExample(adExample);
        wxIndexVO.setBanner(banner);
        // 获取brandList
        BrandExample brandExample = new BrandExample();
        brandExample.setOrderByClause("sort_order limit 0, 8");
        brandExample.createCriteria().andDeletedEqualTo(false);
        List<Brand> brandList = brandMapper.selectByExample(brandExample);
        wxIndexVO.setBrandList(brandList);
        // 获取hotGoodsList
        GoodsExample hotGoodsExample = new GoodsExample();
        hotGoodsExample.setOrderByClause("sort_order limit 0, 10");
        GoodsExample.Criteria hotGoodsExampleCriteria = hotGoodsExample.createCriteria();
        hotGoodsExampleCriteria.andIsHotEqualTo(true);
        hotGoodsExampleCriteria.andDeletedEqualTo(false);
        List<Goods> hotGoodsList = goodsMapper.selectByExample(hotGoodsExample);
        wxIndexVO.setHotGoodsList(hotGoodsList);
        // 获取topicList
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andDeletedEqualTo(false);
        List<Topic> topicList = topicMapper.selectByExample(topicExample);
        wxIndexVO.setTopicList(topicList);
        // 获取floorGoodsList
        List<WxFloorGoodsVO> floorGoodsList = new ArrayList<>();
        for (Category category : channel) {
            WxFloorGoodsVO wxFloorGoodsVO = new WxFloorGoodsVO();
            wxFloorGoodsVO.setName(category.getName());
            wxFloorGoodsVO.setId(category.getId());

            CategoryExample example = new CategoryExample();
            CategoryExample.Criteria exampleCriteria = example.createCriteria();
            exampleCriteria.andDeletedEqualTo(false);
            example.setOrderByClause("sort_order limit 0, 5");
            exampleCriteria.andPidEqualTo(category.getId());
            List<Category> categoryList = categoryMapper.selectByExample(example);
//            for (Category childCategory : categoryList) {
//                GoodsExample goodsExample1 = new GoodsExample();
//                GoodsExample.Criteria goodsExample1Criteria = goodsExample1.createCriteria();
//                goodsExample1Criteria.andDeletedEqualTo(false);
//                goodsExample1Criteria.andCategoryIdEqualTo(childCategory.getId());
//                List<Goods> list = goodsMapper.selectByExample(goodsExample1);
//                goods.addAll(list);
//            }
            GoodsExample goodsExample1 = new GoodsExample();
            GoodsExample.Criteria goodsExample1Criteria = goodsExample1.createCriteria();
            goodsExample1Criteria.andDeletedEqualTo(false);
            goodsExample1Criteria.andCategoryIdEqualTo(categoryList.get(0).getId());
            goodsExample1.setOrderByClause("sort_order limit 0, 4");
            List<Goods> goods = goodsMapper.selectByExample(goodsExample1);
            wxFloorGoodsVO.setGoodsList(goods);
            floorGoodsList.add(wxFloorGoodsVO);
        }
        wxIndexVO.setFloorGoodsList(floorGoodsList);
        return wxIndexVO;
    }
}
