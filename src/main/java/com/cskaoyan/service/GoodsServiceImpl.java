package com.cskaoyan.service;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.goods.*;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.goods.*;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.System;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;

    /**
     * 查询商品列表service层
     * @param goodsSn 商品编号
     * @param name 商品名称
     * @param baseParam
     * @return
     */
    @Transactional
    @Override
    public BaseRespData query(String goodsSn, String name, BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if (goodsSn != null && goodsSn.length() != 0)
            criteria.andGoodsSnEqualTo(goodsSn);
        if (name != null && name.length() != 0)
            criteria.andNameLike("%" + name + "%");
        goodsExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        List<Goods> goodsList = goodsMapper.selectByExampleWithBLOBs(goodsExample);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        return BaseRespData.create(goodsList, total);
    }

    /**
     * 新建商品，并将信息插入到各自对应的表中
     * @param createGoodBO 从前端获取到的信息
     */
    @Transactional
    @Override
    public void create(CreateGoodBO createGoodBO) {
        //插入cskaoyanmall_goods表
        Goods goods = new Goods();
        BeanUtils.copyProperties(createGoodBO.getGoods(), goods);
        Date date = new Date(System.currentTimeMillis());
        // 将字符串类型的price装换为BigDecimal
        BigDecimal counterPrice = new BigDecimal(createGoodBO.getGoods().getCounterPrice());
        BigDecimal retailPrice = new BigDecimal(createGoodBO.getGoods().getRetailPrice());
        goods.setCounterPrice(counterPrice);
        goods.setRetailPrice(retailPrice);
        goods.setAddTime(date);
        goods.setUpdateTime(date);
        //TODO shareUrl未知，暂时未设置
        goodsMapper.insertSelective(goods);

        //插入cskaoyanmall_goods_specification表
        List<CreateGoodSpecificationsBO> specifications = createGoodBO.getSpecifications();
        for (CreateGoodSpecificationsBO specification : specifications) {
            GoodsSpecification goodsSpecification = new GoodsSpecification();
            BeanUtils.copyProperties(specification, goodsSpecification);
            goodsSpecification.setAddTime(date);
            goodsSpecification.setUpdateTime(date);
            goodsSpecification.setGoodsId(goods.getId());
            goodsSpecificationMapper.insertSelective(goodsSpecification);
        }

        //插入cskaoyanmall_goods_product表
        List<CreateGoodProductsBO> products = createGoodBO.getProducts();
        for (CreateGoodProductsBO product : products) {
            GoodsProduct goodsProduct = new GoodsProduct();
            BeanUtils.copyProperties(product, goodsProduct);
            goodsProduct.setId(null);
            goodsProduct.setGoodsId(goods.getId());
            BigDecimal price = new BigDecimal(product.getPrice());
            goodsProduct.setPrice(price);
            goodsProduct.setNumber(Integer.parseInt(product.getNumber()));
            goodsProduct.setAddTime(date);
            goodsProduct.setUpdateTime(date);
            goodsProductMapper.insertSelective(goodsProduct);
        }

        //插入cskaoyanmall_goods_attribute表
        List<CreateGoodAttributesBO> attributes = createGoodBO.getAttributes();
        for (CreateGoodAttributesBO attribute : attributes) {
            GoodsAttribute goodsAttribute = new GoodsAttribute();
            BeanUtils.copyProperties(attribute, goodsAttribute);
            goodsAttribute.setAddTime(date);
            goodsAttribute.setUpdateTime(date);
            goodsAttribute.setGoodsId(goods.getId());
            goodsAttributeMapper.insertSelective(goodsAttribute);
        }
    }

    /**
     * 获取商品的category和brand
     * @return
     */
    @Transactional
    @Override
    public CatAndBrandVO catAndBrand() {
        CatAndBrandVO catAndBrandVO = new CatAndBrandVO();
        catAndBrandVO.setCategoryList(new ArrayList<>());
        catAndBrandVO.setBrandList(new ArrayList<>());
        // 获取CategoryList
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andLevelEqualTo("L1");
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        for (Category category : categories) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setValue(category.getId());
            categoryVO.setLabel(category.getName());
            categoryVO.setChildren(new ArrayList<>());
            CategoryExample example = new CategoryExample();
            CategoryExample.Criteria exampleCriteria = example.createCriteria();
            exampleCriteria.andDeletedEqualTo(false);
            exampleCriteria.andPidEqualTo(category.getId());
            List<Category> categoryList = categoryMapper.selectByExample(example);
            for (Category category1 : categoryList) {
                CategoryChildrenVO categoryChildrenVO = new CategoryChildrenVO();
                categoryChildrenVO.setValue(category1.getId());
                categoryChildrenVO.setLabel(category1.getName());
                categoryVO.getChildren().add(categoryChildrenVO);
            }
            catAndBrandVO.getCategoryList().add(categoryVO);
        }
        //获取BrandList
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andDeletedEqualTo(false);
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        for (Brand brand : brands) {
            BrandVO brandVO = new BrandVO();
            brandVO.setValue(brand.getId());
            brandVO.setLabel(brand.getName());
            catAndBrandVO.getBrandList().add(brandVO);
        }
        return catAndBrandVO;
    }

    /**
     * 展示商品的细节
     * @param id
     * @return
     */
    @Transactional
    @Override
    public GoodDetailVO detail(int id) {
        GoodDetailVO goodDetailVO = new GoodDetailVO();
        // 获取goods表中的数据
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods, goodsVO);
        goodDetailVO.setGoods(goodsVO);
        // 获取类目细节
        Integer[] categoryIds = new Integer[2];
        categoryIds[1] = goods.getCategoryId();
        Category category = categoryMapper.selectByPrimaryKey(categoryIds[1]);
        categoryIds[0] = category.getPid();
        goodDetailVO.setCategoryIds(categoryIds);
        // 获取attributes表中的数据
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria goodsAttributeExampleCriteria = goodsAttributeExample.createCriteria();
        goodsAttributeExampleCriteria.andGoodsIdEqualTo(goods.getId());
        goodsAttributeExampleCriteria.andDeletedEqualTo(false);
        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        goodDetailVO.setAttributes(goodsAttributes);
        // 获取specifications表中的数据
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria goodsSpecificationExampleCriteria = goodsSpecificationExample.createCriteria();
        goodsSpecificationExampleCriteria.andGoodsIdEqualTo(goods.getId());
        goodsSpecificationExampleCriteria.andDeletedEqualTo(false);
        List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        goodDetailVO.setSpecifications(goodsSpecifications);
        // 获取product表中的数据
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria goodsProductExampleCriteria = goodsProductExample.createCriteria();
        goodsProductExampleCriteria.andGoodsIdEqualTo(goods.getId());
        goodsProductExampleCriteria.andDeletedEqualTo(false);
        List<GoodsProduct> goodsProducts = goodsProductMapper.selectByExample(goodsProductExample);
        goodDetailVO.setProducts(goodsProducts);

        return goodDetailVO;
    }

    /**
     * 更新商品及关联的数据
     * @param updateGoodBO 从前端获取到的数据
     */
    @Transactional
    @Override
    public void update(UpdateGoodBO updateGoodBO) {
        UpdateGoodBeanBO updateGoodBeanBO = updateGoodBO.getGoods();
        Goods goods = new Goods();
        BeanUtils.copyProperties(updateGoodBeanBO, goods);
        // 将字符串类型的price装换为BigDecimal
        BigDecimal counterPrice = new BigDecimal(updateGoodBO.getGoods().getCounterPrice());
        BigDecimal retailPrice = new BigDecimal(updateGoodBO.getGoods().getRetailPrice());
        goods.setCounterPrice(counterPrice);
        goods.setRetailPrice(retailPrice);
        Date date = new Date(System.currentTimeMillis());
        goods.setUpdateTime(date);
        //TODO shareUrl未知，待做
        goodsMapper.updateByPrimaryKeySelective(goods);

        // 先将specifications表中关联的数据逻辑删除
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        GoodsSpecification goodsSpecification = new GoodsSpecification();
        goodsSpecification.setDeleted(true);
        goodsSpecificationMapper.updateByExampleSelective(goodsSpecification, goodsSpecificationExample);
        List<GoodsSpecification> specifications = updateGoodBO.getSpecifications();
        for (GoodsSpecification specification : specifications) {
            if (specification.getId() == null) {
                GoodsSpecification spec = new GoodsSpecification();
                BeanUtils.copyProperties(specification, spec);
                spec.setGoodsId(goods.getId());
                spec.setAddTime(date);
                spec.setUpdateTime(date);
                goodsSpecificationMapper.insertSelective(spec);
            } else {
                specification.setUpdateTime(date);
                goodsSpecificationMapper.updateByPrimaryKeySelective(specification);
            }
        }

        // 先将product表中的关联的数据逻辑删除
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        goodsProductExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProduct.setDeleted(true);
        goodsProductMapper.updateByExampleSelective(goodsProduct, goodsProductExample);
        List<UpdateGoodsProductBO> products = updateGoodBO.getProducts();
        for (UpdateGoodsProductBO product : products) {
            GoodsProduct pro = new GoodsProduct();
            BeanUtils.copyProperties(product, pro);
            pro.setId(null);
            pro.setGoodsId(goods.getId());
            BigDecimal price = new BigDecimal(product.getPrice());
            goodsProduct.setPrice(price);
            goodsProduct.setNumber(Integer.parseInt(product.getNumber()));
            pro.setAddTime(date);
            pro.setUpdateTime(date);
            goodsProductMapper.insertSelective(pro);
        }

        // 先将attributes表中的关联数据逻辑删除
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttributeExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        GoodsAttribute goodsAttribute = new GoodsAttribute();
        goodsAttribute.setDeleted(true);
        goodsAttributeMapper.updateByExampleSelective(goodsAttribute, goodsAttributeExample);
        List<GoodsAttribute> attributes = updateGoodBO.getAttributes();
        for (GoodsAttribute attribute : attributes) {
            if (attribute.getId() == null) {
                GoodsAttribute attribute1 = new GoodsAttribute();
                BeanUtils.copyProperties(attribute, attribute1);
                attribute1.setGoodsId(goods.getId());
                attribute1.setAddTime(date);
                attribute1.setUpdateTime(date);
                goodsAttributeMapper.insertSelective(attribute1);
            } else {
                attribute.setUpdateTime(date);
                goodsAttributeMapper.updateByPrimaryKeySelective(attribute);
            }
        }
    }

    /**
     * 删除商品
     * @param goods 从前端获取到的商品数据
     */
    @Override
    public void delete(Goods goods) {
        // 逻辑删除goods表中的数据
        goods.setDeleted(true);
        goodsMapper.updateByPrimaryKeySelective(goods);

        // 逻辑删除specification表中的数据
        GoodsSpecification goodsSpecification = new GoodsSpecification();
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecification.setDeleted(true);
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        goodsSpecificationMapper.updateByExampleSelective(goodsSpecification, goodsSpecificationExample);

        // 逻辑删除product表中的数据
        GoodsProduct goodsProduct = new GoodsProduct();
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        goodsProduct.setDeleted(true);
        goodsProductExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        goodsProductMapper.updateByExampleSelective(goodsProduct, goodsProductExample);

        // 逻辑删除attributes表中的数据
        GoodsAttribute goodsAttribute = new GoodsAttribute();
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttribute.setDeleted(true);
        goodsAttributeExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        goodsAttributeMapper.updateByExampleSelective(goodsAttribute, goodsAttributeExample);
    }
}