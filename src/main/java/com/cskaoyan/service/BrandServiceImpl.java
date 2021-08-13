package com.cskaoyan.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.cskaoyan.bean.BaseRespData;
import com.cskaoyan.bean.bo.market.BaseParamBO;
import com.cskaoyan.bean.bo.market.BrandCreateBO;
import com.cskaoyan.bean.bo.market.BrandDeleteBO;
import com.cskaoyan.bean.bo.market.BrandUpdateBO;
import com.cskaoyan.bean.pojo.Brand;
import com.cskaoyan.bean.pojo.BrandExample;
import com.cskaoyan.bean.vo.market.BaseRespDataVO;
import com.cskaoyan.bean.vo.market.BrandCreateVO;
import com.cskaoyan.bean.vo.market.BrandDeleteVO;
import com.cskaoyan.bean.vo.market.BrandUpdateVO;
import com.cskaoyan.mapper.BrandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BrandServiceImpl
 * @Description 品牌制造商
 * @Author 王昀昊
 * @Date 2021/8/11 23:42
 * @Version 1.0
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    /**
     * 回显品牌制造商
     * @param id
     * @param name
     * @param baseParamBO
     * @return
     */
    @Override
    public BaseRespDataVO brandList(Integer id, String name, BaseParamBO baseParamBO) {
        PageHelper.startPage(baseParamBO.getPage(), baseParamBO.getLimit());
        BrandExample example = new BrandExample();
        //构造排序
        example.setOrderByClause(baseParamBO.getSort() + " " + baseParamBO.getOrder());
        //拼接条件
        BrandExample.Criteria criteria = example.createCriteria();
        if (name != null && !"".equals(name)) {
            criteria.andNameLike("%" + name + "%");//like需要自行拼接好%
        }
        if (id != null && !"".equals(id)) {
            criteria.andIdEqualTo(id);
        }
        criteria.andDeletedEqualTo(false);

        List<Brand> items = brandMapper.selectByExample(example);
        //通过example拼接条件 and username like #{} and mobile = #{}

        //可以获得分页信息,在PageInfo中放入查询结果
        PageInfo<Brand> pageInfo = new PageInfo<>(items);
        long total = pageInfo.getTotal(); //select count(*) 根据上面执行的查询拼接后面的

        return BaseRespDataVO.create(items,total);

    }

    /**
     * 添加品牌制造商
     * @param brandCreateBO
     * @return
     */
    @Override
    public BrandCreateVO brandCreate(BrandCreateBO brandCreateBO) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandCreateBO,brand);
        BigDecimal bigDecimal = new BigDecimal(brandCreateBO.getFloorPrice());
        brand.setFloorPrice(bigDecimal);
        brand.setAddTime(new Date());
        brand.setUpdateTime(new Date());
        byte count = brandMapper.selectCount();
        brand.setSortOrder(++count);
        brandMapper.insertSelective(brand);
        Brand brandByPrimaryKey = brandMapper.selectByPrimaryKey(brand.getId());
        BrandCreateVO brandCreateVO = new BrandCreateVO(brand.getId(), brand.getName(), brand.getDesc(), brand.getPicUrl(), brand.getFloorPrice(), new Date(), new Date());
        return brandCreateVO;
    }

    /**
     * 修改品牌制造商
     * @param brandUpdateBO
     * @return
     */
    @Override
    public BrandUpdateVO brandUpdate(BrandUpdateBO brandUpdateBO) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandUpdateBO,brand);
        brandMapper.updateByPrimaryKeySelective(brand);
        BrandUpdateVO brandUpdateVO = new BrandUpdateVO();
        BeanUtils.copyProperties(brandUpdateBO,new BrandUpdateVO());
        return brandUpdateVO;
    }

    /**
     * 删除品牌制造商
     * @param brandDeleteBO
     * @return
     */
    @Override
    public void brandDelete(BrandDeleteBO brandDeleteBO) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDeleteBO, brand);
        brand.setDeleted(true);
        brandMapper.updateByPrimaryKeySelective(brand);
    }
}
