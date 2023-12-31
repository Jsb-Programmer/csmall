package com.cskaoyan.service.admin;

import com.cskaoyan.bean.BaseParam;
import com.cskaoyan.bean.BaseRespData;

import com.cskaoyan.bean.bo.auth.RegisterUserBO;
import com.cskaoyan.bean.bo.auth.ResetBO;
import com.cskaoyan.bean.bo.user.ReceivedAddressBO;
import com.cskaoyan.bean.bo.user.WxUserLoginBO;
import com.cskaoyan.bean.pojo.*;
import com.cskaoyan.bean.vo.user.UserInfoVO;
import com.cskaoyan.mapper.*;

import com.cskaoyan.utils.MD5Utils;
import com.cskaoyan.utils.NextDayUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-11 22:18
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    FootPrintMapper footPrintMapper;

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    RegionMapper regionMapper;

    @Value("${img.avatorImg}")
    String imgUrl;

    /**
     * Todo   criteria.andDeletedEqualTo(false);
     *
     * @param username  用于条件查找
     * @param mobile    同上
     * @param baseParam 提供 base限制条件
     * @return 返回BaseRespData ：list+total总数 用于BaseRespVO的data
     */
    @Override
    public BaseRespData<User> queryUsers(String username, String mobile, BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        UserExample userExample = new UserExample();
        //sort
        userExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        //concat
        UserExample.Criteria criteria = userExample.createCriteria();
        if (username != null && !"".equals(username.trim())) {
            criteria.andUsernameLike("%" + username + "%");
        }

        if (mobile != null && !"".equals(mobile.trim())) {
            criteria.andMobileEqualTo(mobile);
        }

        List<User> users = userMapper.selectByExample(userExample);

        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();

        return new BaseRespData<>(users, total);
    }

    /**
     * @param userId    用于条件查找
     * @param valueId   同上
     * @param baseParam 提供 base限制条件page limit sort order
     * @return 返回BaseRespData ：list+total总数 用于BaseRespVO的data
     */
    @Override
    public BaseRespData<Collect> queryCollects(String userId, String valueId, BaseParam baseParam) {
        //getPage当前页 getLimit当前页显示数量
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        CollectExample collectExample = new CollectExample();
        collectExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andDeletedEqualTo(false);

        int userid;
        int valueid;
        try {
            if (userId != null && !"".equals(userId.trim())) {
                userid = Integer.parseInt(userId);
                criteria.andUserIdEqualTo(userid);
            }

            if (valueId != null && !"".equals(valueId.trim())) {
                valueid = Integer.parseInt(valueId);
                criteria.andValueIdEqualTo(valueid);
            }
        } catch (Exception e) {
            return null;
        }

        List<Collect> collects = collectMapper.selectByExample(collectExample);

        long total = new PageInfo<>(collects).getTotal();
        return new BaseRespData<>(collects, total);
    }

    /**
     * @param userId    查询条件
     * @param goodsId   查询条件
     * @param baseParam 基础查询数据
     * @return baseData list+total
     */
    @Override
    public BaseRespData<FootPrint> queryFootPrints(String userId, String goodsId, BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        FootPrintExample footPrintExample = new FootPrintExample();

        footPrintExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        FootPrintExample.Criteria criteria = footPrintExample.createCriteria();
        criteria.andDeletedEqualTo(false);

        int userid;
        int goodsid;
        try {
            if (userId != null && !"".equals(userId.trim())) {
                userid = Integer.parseInt(userId);
                criteria.andUserIdEqualTo(userid);
            }

            if (goodsId != null && !"".equals(goodsId.trim())) {
                goodsid = Integer.parseInt(goodsId);
                criteria.andGoodsIdEqualTo(goodsid);
            }
        } catch (Exception e) {
            return null;
        }

        List<FootPrint> footPrints = footPrintMapper.selectByExample(footPrintExample);
        long total = new PageInfo<>(footPrints).getTotal();

        return new BaseRespData<>(footPrints, total);
    }

    /**
     * @param userId    查询条件
     * @param keyword   查询条件
     * @param baseParam 基础查询条件
     * @return baseDate total + list
     */
    @Override
    public BaseRespData<SearchHistory> querySearchHistory(String userId, String keyword, BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());

        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        searchHistoryExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        SearchHistoryExample.Criteria criteria = searchHistoryExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if (keyword != null && !"".equals(keyword.trim())) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        int userid;
        try {
            if (userId != null && !"".equals(userId.trim())) {
                userid = Integer.parseInt(userId);
                criteria.andUserIdEqualTo(userid);
            }
        } catch (Exception e) {
            return null;
        }

        List<SearchHistory> searchHistories = searchHistoryMapper.selectByExample(searchHistoryExample);

        long total = new PageInfo<>(searchHistories).getTotal();

        return new BaseRespData<>(searchHistories, total);
    }

    /**
     * @param username  条件参数
     * @param id        条件参数
     * @param baseParam 基础数据
     * @return baseData
     */
    @Override
    public BaseRespData<Feedback> queryFeedbacks(String username, String id, BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if (username != null && !"".equals(username.trim())) {
            criteria.andUsernameLike("%" + username + "%");
        }
        int fid;
        try {
            if (id != null && !"".equals(id.trim())) {
                fid = Integer.parseInt(id);
                criteria.andIdEqualTo(fid);
            }
        } catch (Exception e) {
            return null;
        }

        List<Feedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);

        long total = new PageInfo<>(feedbacks).getTotal();


        return new BaseRespData<>(feedbacks, total);
    }

    /**
     * @param name      查询参数
     * @param userId    查询参数
     * @param baseParam 查询参数
     * @return baseData list + total
     */
    @Override
    @Transactional
    public BaseRespData<ReceivedAddressBO> queryAddress(String name, String userId, BaseParam baseParam) {
        PageHelper.startPage(baseParam.getPage(), baseParam.getLimit());
        AddressExample addressExample = new AddressExample();
        addressExample.setOrderByClause(baseParam.getSort() + " " + baseParam.getOrder());
        AddressExample.Criteria criteria = addressExample.createCriteria();
        if (name != null && !"".equals(name.trim())) {
            criteria.andNameEqualTo(name);
        }
        int userid;
        try {
            if (userId != null && !"".equals(userId.trim())) {
                userid = Integer.parseInt(userId);
                criteria.andUserIdEqualTo(userid);
            }
        } catch (Exception e) {
            return null;
        }
        List<ReceivedAddressBO> receivedAddressBOS = new ArrayList<>();
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        for (Address address : addresses) {
            ReceivedAddressBO receivedAddressBO = new ReceivedAddressBO();
            BeanUtils.copyProperties(address, receivedAddressBO);
            Region area = regionMapper.selectByPrimaryKey(address.getAreaId());
            Region city = regionMapper.selectByPrimaryKey(address.getCityId());
            Region province = regionMapper.selectByPrimaryKey(address.getProvinceId());
            receivedAddressBO.setArea(area.getName());
            receivedAddressBO.setCity(city.getName());
            receivedAddressBO.setProvince(province.getName());
            receivedAddressBOS.add(receivedAddressBO);
        }
        long total = new PageInfo<>(addresses).getTotal();

        return new BaseRespData<>(receivedAddressBOS, total);
    }

    /**
     * for wechat login
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional
    public WxUserLoginBO userLoginInfo(String username, String password) throws Exception {
        Date nextDay = NextDayUtils.getNextDay(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String format = simpleDateFormat.format(nextDay);

        Subject subject = SecurityUtils.getSubject();
        Serializable id = subject.getSession().getId();

        User user = userMapper.selectByName(username);

        WxUserLoginBO wxUserLoginBO = new WxUserLoginBO();
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setNickName(user.getNickname());
        userInfoVO.setAvatarUrl(user.getAvatar());
        wxUserLoginBO.setToken(id.toString());
        wxUserLoginBO.setTokenExpire(format);
        wxUserLoginBO.setUserInfo(userInfoVO);
        //update login time
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(subject.getSession().getHost());
        userMapper.updateByPrimaryKeySelective(user);
        return wxUserLoginBO;
    }

    /**
     * 添加 user
     *
     * @param registerUserBO
     */
    @Override
    public int addUser(RegisterUserBO registerUserBO) throws Exception {
        User temp = userMapper.selectByName(registerUserBO.getUsername());
        if (temp != null) {
            return 400;
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(registerUserBO.getMobile());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0 && users != null) {
            return 401;
        }
        User user = new User();
        user.setAddTime(new Date());
        user.setUsername(registerUserBO.getUsername());
        user.setMobile(registerUserBO.getMobile());
        user.setPassword(MD5Utils.encrypt(registerUserBO.getPassword()));
        user.setWeixinOpenid(registerUserBO.getWxCode());
        user.setNickname(registerUserBO.getUsername());
        user.setAvatar(imgUrl);

        userMapper.insertSelective(user);
        return 200;
    }

    @Override
    public int resetPsw(ResetBO registerUserBO) throws Exception {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(registerUserBO.getMobile());
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            return 400;
        }
        User user = users.get(0);
        user.setPassword(MD5Utils.encrypt(registerUserBO.getPassword()));
        userMapper.updateByPrimaryKeySelective(user);
        return 200;
    }

}

/*
            {
        "userInfo": {
            "nickName": "test1",
            "avatarUrl": "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"
        },
        "tokenExpire": "2021-08-15T14:50:40.482",
        "token": "253suyngg3aoys84txj1qvc3k87ih4ry"
    }
         */