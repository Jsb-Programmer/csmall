package com.cskaoyan.service;

import com.cskaoyan.bean.bo.market.CategoryCreateBO;
import com.cskaoyan.bean.bo.market.CategoryDeleteBO;
import com.cskaoyan.bean.bo.market.CategoryUpdateBO;
import com.cskaoyan.bean.pojo.Category;
import com.cskaoyan.bean.pojo.CategoryExample;
import com.cskaoyan.bean.pojo.UserExample;
import com.cskaoyan.bean.vo.market.CategoryCreateVO;
import com.cskaoyan.bean.vo.market.CategoryL1VO;
import com.cskaoyan.bean.vo.market.CategoryListChildrenVO;
import com.cskaoyan.bean.vo.market.CategoryListVO;
import com.cskaoyan.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/12 17:18
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryListVO> categoryList() {
        CategoryExample example = new CategoryExample();
        ArrayList<CategoryListVO> categoryListVOS = new ArrayList<>();
        ArrayList<CategoryListChildrenVO> childrenVOList = new ArrayList<>();

        //拼接条件
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);

        //查询到数据库的所有信息
        List<Category> categoryList = categoryMapper.selectByExample(example);
        for (Category category : categoryList) {
            if ("L2".equals(category.getLevel())){
                CategoryListChildrenVO categoryListChildrenVO = new CategoryListChildrenVO();
                BeanUtils.copyProperties(category,categoryListChildrenVO);
                childrenVOList.add(categoryListChildrenVO);
            }
        }
        for (Category category : categoryList) {
            if ("L1".equals(category.getLevel())){
                CategoryListVO categoryListVO = new CategoryListVO();
                BeanUtils.copyProperties(category,categoryListVO);
                ArrayList<CategoryListChildrenVO> childrenVOList2 = new ArrayList<>();
                for (CategoryListChildrenVO categoryListChildrenVO : childrenVOList) {
                    if (categoryListVO.getId() == categoryListChildrenVO.getPid()){
                        childrenVOList2.add(categoryListChildrenVO);
                    }
                }
                categoryListVO.setChildren(childrenVOList2);
                categoryListVOS.add(categoryListVO);
            }
        }
        return categoryListVOS;
    }

    /**
     * 添加商品类目
     * @param categoryCreateBO
     * @return
     */
    @Override
    public CategoryCreateVO categoryCreate(CategoryCreateBO categoryCreateBO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryCreateBO,category);
        category.setAddTime(new Date());
        category.setUpdateTime(new Date());
        category.setDeleted(false);
        categoryMapper.insertSelective(category);
        CategoryCreateVO categoryCreateVO = new CategoryCreateVO();
        BeanUtils.copyProperties(category,categoryCreateVO);
        return categoryCreateVO;
    }

    @Override
    public List<CategoryL1VO> categoryL1() {
        List<CategoryL1VO> categoryL1VOList = categoryMapper.selectALL();
        return categoryL1VOList;
    }

    @Override
    public void categoryUpdate(CategoryUpdateBO categoryUpdateBO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateBO,category);
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void categoryDelete(CategoryDeleteBO categoryDeleteBO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDeleteBO,category);
        category.setDeleted(true);
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
