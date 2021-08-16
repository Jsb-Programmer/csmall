package com.cskaoyan.service.wx;

import com.cskaoyan.bean.pojo.Category;
import com.cskaoyan.bean.pojo.CategoryExample;
import com.cskaoyan.bean.vo.brandcs.CatalogCurrentVO;
import com.cskaoyan.bean.vo.brandcs.CatalogIndexVO;
import com.cskaoyan.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CatalogServiceImpl
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/14 11:14
 * @Version 1.0
 **/
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CatalogCurrentVO catalogCurrent(Integer id) {
        CatalogCurrentVO catalogCurrentVO = new CatalogCurrentVO();

        //获取当前一级类目
        Category category = categoryMapper.selectById(id);
        catalogCurrentVO.setCurrentCategory(category);

        //获取当前类目下的所有二级类目
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(id);
        criteria.andDeletedEqualTo(false);
        List<Category> categoryList2 = categoryMapper.selectByExample(example);
        catalogCurrentVO.setCurrentSubCategory(categoryList2);

        return catalogCurrentVO;
    }

    @Override
    public CatalogIndexVO catalogIndex() {
        //获取所有一级类目
        CatalogIndexVO catalogIndexVO = new CatalogIndexVO();
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andLevelEqualTo("L1");
        List<Category> categoryList = categoryMapper.selectByExample(example);
        catalogIndexVO.setCategoryList(categoryList);

        //获取当前一级类目
        catalogIndexVO.setCurrentCategory(categoryList.get(0));

        //获取当前类目下的所有二级类目
        CategoryExample example2 = new CategoryExample();
        CategoryExample.Criteria criteria1 = example2.createCriteria();
        criteria1.andPidEqualTo(categoryList.get(0).getId());
        criteria1.andDeletedEqualTo(false);
        List<Category> categoryList2 = categoryMapper.selectByExample(example2);
        catalogIndexVO.setCurrentSubCategory(categoryList2);

        return catalogIndexVO;
    }
}
