package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.WxListBaseParam;
import com.cskaoyan.bean.bo.goods.*;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.goods.*;
import com.cskaoyan.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.System;
import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    CommentMapper commentMapper;

    @Value("${img.failUrl}")
    String failUrl;
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
        if (goods.getPicUrl() != null && failUrl.equals(goods.getPicUrl())) goods.setPicUrl(null);
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
        if (goods.getPicUrl() != null && failUrl.equals(goods.getPicUrl())) goods.setPicUrl(null);
        // 将字符串类型的price装换为BigDecimal
        BigDecimal counterPrice = new BigDecimal(updateGoodBO.getGoods().getCounterPrice());
        BigDecimal retailPrice = new BigDecimal(updateGoodBO.getGoods().getRetailPrice());
        goods.setCounterPrice(counterPrice);
        goods.setRetailPrice(retailPrice);
        Date date = new Date(System.currentTimeMillis());
        goods.setUpdateTime(date);
        //TODO shareUrl未知，待做
        goodsMapper.updateByPrimaryKey(goods);

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

    /**
     * 未被逻辑删除的商品数
     * @return 商品数量
     */
    @Override
    public Integer count() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList.size();
    }

    /**
     * 显示L2级别的categoryID的商品
     * @param wxListBaseParam 从前端获取的数据
     * @return 响应
     */
    @Override
    public WxGoodsListVO list(WxListBaseParam wxListBaseParam) {
        WxGoodsListVO wxGoodsListVO = new WxGoodsListVO();
        // 获取goodsList
        PageHelper.startPage(wxListBaseParam.getPage(), wxListBaseParam.getSize());

        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if (wxListBaseParam.getCategoryId() != null && wxListBaseParam.getCategoryId() != 0)
            criteria.andCategoryIdEqualTo(wxListBaseParam.getCategoryId());
        if (wxListBaseParam.getKeyword() != null && wxListBaseParam.getKeyword().length() != 0)
            criteria.andNameLike("%" + wxListBaseParam.getKeyword() + "%");
        if (wxListBaseParam.getOrder() != null && wxListBaseParam.getOrder().length() != 0
            && wxListBaseParam.getSort() != null && wxListBaseParam.getSort().length() != 0)
            goodsExample.setOrderByClause(wxListBaseParam.getSort() + " " + wxListBaseParam.getOrder());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        wxGoodsListVO.setGoodsList(goodsList);
        // 获取count
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long count = pageInfo.getTotal();
        wxGoodsListVO.setCount(count);
        // 获取filterCategoryList;
        List<Category> filterCategoryList = new ArrayList<>();
        for (Goods goods : goodsList) {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andDeletedEqualTo(false);
            Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
            filterCategoryList.add(category);
        }
        wxGoodsListVO.setFilterCategoryList(filterCategoryList);
        return wxGoodsListVO;
    }

    /**
     * 根据L1级别的categoryId获取到从属category
     * @param id L1级别的categoryID
     * @return 响应
     */
    @Transactional
    @Override
    public WxCategoryVO category(Integer id) {
        WxCategoryVO wxCategoryVO = new WxCategoryVO();
        Category category = categoryMapper.selectByPrimaryKey(id);
        // 如果不是L1级category则category就是currentCategory
        CategoryExample brotherCategoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = brotherCategoryExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if (category.getPid() != 0) {
            wxCategoryVO.setCurrentCategory(category);
            // 查询brotherCategory
            criteria.andPidEqualTo(category.getPid());
            List<Category> brotherCategory = categoryMapper.selectByExample(brotherCategoryExample);
            wxCategoryVO.setBrotherCategory(brotherCategory);
            // 查询parentCategory
            Category parentCategory = categoryMapper.selectByPrimaryKey(category.getPid());
            wxCategoryVO.setParentCategory(parentCategory);
        } else {
            //此时category为parentCategory
            wxCategoryVO.setParentCategory(category);
            // 查询brotherCategory
            criteria.andPidEqualTo(category.getId());
            List<Category> brotherCategory = categoryMapper.selectByExample(brotherCategoryExample);
            wxCategoryVO.setBrotherCategory(brotherCategory);
            // 查询currentCategory
            // 判断是否存在
            if (brotherCategory.size() != 0)
                wxCategoryVO.setCurrentCategory(brotherCategory.get(0));
        }
        return wxCategoryVO;
    }

    /**
     * 获取商品详情
     * @param id 要查询的商品id
     * @return 详情
     */
    @Transactional
    @Override
    public WxDetailVO detailForWx(Integer id) {
        WxDetailVO wxDetailVO = new WxDetailVO();
        //获取info
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        wxDetailVO.setInfo(goods);
        // 获取specificationList
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria goodsSpecificationExampleCriteria = goodsSpecificationExample.createCriteria();
        goodsSpecificationExampleCriteria.andDeletedEqualTo(false);
        goodsSpecificationExampleCriteria.andGoodsIdEqualTo(id);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        List<SpecificationVO> specificationList = new ArrayList<>();
        Set<String> specificationNames = new HashSet<>();
        for (GoodsSpecification goodsSpecification : goodsSpecificationList) {
            if (specificationNames.add(goodsSpecification.getSpecification())) {
                SpecificationVO specificationVO = new SpecificationVO();
                specificationVO.setName(goodsSpecification.getSpecification());
                List<GoodsSpecification> valueList = new ArrayList<>();
                for (GoodsSpecification specification : goodsSpecificationList) {
                    if (specification.getSpecification().equals(goodsSpecification.getSpecification())) {
                        valueList.add(specification);
                    }
                }
                specificationVO.setValueList(valueList);
                specificationList.add(specificationVO);
            }
        }
        wxDetailVO.setSpecificationList(specificationList);
        // 获取groupon
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        GrouponRulesExample.Criteria grouponRulesExampleCriteria = grouponRulesExample.createCriteria();
        grouponRulesExampleCriteria.andDeletedEqualTo(false);
        grouponRulesExampleCriteria.andGoodsIdEqualTo(id);
        List<GrouponRules> groupon = grouponRulesMapper.selectByExample(grouponRulesExample);
        wxDetailVO.setGroupon(groupon);
        // 获取issue
        IssueExample issueExample = new IssueExample();
        issueExample.createCriteria().andDeletedEqualTo(false);
        List<Issue> issue = issueMapper.selectByExample(issueExample);
        wxDetailVO.setIssue(issue);
        // 获取 userHasCollect
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria collectExampleCriteria = collectExample.createCriteria();
        collectExampleCriteria.andDeletedEqualTo(false);
        collectExampleCriteria.andTypeEqualTo((byte) 0);    // 收藏的是商品
        collectExampleCriteria.andValueIdEqualTo(id);       // 收藏的商品id
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        wxDetailVO.setUserHasCollect(collects.size());
        // 获取shareImage
        wxDetailVO.setShareImage(goods.getShareUrl());
        // 获取comment
        CommentVO commentVO = new CommentVO();
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andDeletedEqualTo(false);
        commentExampleCriteria.andTypeEqualTo((byte) 0);
        commentExampleCriteria.andValueIdEqualTo(id);
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        commentVO.setCount(commentList.size());
        List<CommentDataVO> data = commentMapper.selectCommentWithUserByGoodsId(id);
        commentVO.setData(data);
        wxDetailVO.setComment(commentVO);
        // 获取attribute
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria goodsAttributeExampleCriteria = goodsAttributeExample.createCriteria();
        goodsAttributeExampleCriteria.andDeletedEqualTo(false);
        goodsAttributeExampleCriteria.andGoodsIdEqualTo(id);
        List<GoodsAttribute> attribute = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        wxDetailVO.setAttribute(attribute);
        // 获取brand
        Brand brand = brandMapper.selectByPrimaryKey(goods.getBrandId());
        wxDetailVO.setBrand(brand);
        // 获取productList
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria goodsProductExampleCriteria = goodsProductExample.createCriteria();
        goodsProductExampleCriteria.andDeletedEqualTo(false);
        goodsProductExampleCriteria.andGoodsIdEqualTo(id);
        List<GoodsProduct> productList = goodsProductMapper.selectByExample(goodsProductExample);
        wxDetailVO.setProductList(productList);
        return wxDetailVO;
    }

    @Override
    public List<Goods> related(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria goodsExampleCriteria = goodsExample.createCriteria();
        goodsExampleCriteria.andDeletedEqualTo(false);
        goodsExampleCriteria.andCategoryIdEqualTo(goods.getCategoryId());
        List<Goods> relatedGoods = goodsMapper.selectByExample(goodsExample);
        return relatedGoods;
    }
}
