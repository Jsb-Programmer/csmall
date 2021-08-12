package com.cskaoyan.service;

import com.cskaoyan.bean.pojo.System;
import com.cskaoyan.bean.pojo.SystemExample;
import com.cskaoyan.bean.vo.config.ExpressConfigVO;
import com.cskaoyan.bean.vo.config.MallConfigVO;
import com.cskaoyan.bean.vo.config.OrderConfigVO;
import com.cskaoyan.bean.vo.config.WxConfigVO;
import com.cskaoyan.mapper.SystemMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @program: mall
 * @description:
 * @author: Liu
 * @create: 2021-08-12 15:15
 **/

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    SystemMapper systemMapper;

    /**
     * GET商场配置
     *
     * @return MallConfigVO
     */
    @SneakyThrows
    @Override
    public MallConfigVO getMallConfig() {
        MallConfigVO mallConfigVO = new MallConfigVO();
        System system = new System();

        // 遍历mallconfig的所有成员变量，成员变量名为system的keyName值
        Field[] declaredFields = mallConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();
            system.setKeyName(fieldName);

            // 根据keyName查找value；
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            List<System> systemList = systemMapper.selectByExample(systemExample);
            for (System system1 : systemList) {
                String keyValue = system1.getKeyValue();

                // 获得的keyValue赋值给mallConfig对应的成员变量值
                declaredField.setAccessible(true);
                declaredField.set(mallConfigVO, keyValue);
            }
        }

        return mallConfigVO;
    }

    /**
     * POST商场配置
     * @return int
     */
    @SneakyThrows
    @Override
    public int postMallConfig(MallConfigVO mallConfigVO) {
        int affectRows = 0;
        System system = new System();

        // 遍历mallConfig的成员变量
        Field[] declaredFields = mallConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            // 获取成员变量名和对应的值
            String fieldName = declaredField.getName();
            declaredField.setAccessible(true);
            String value = (String) declaredField.get(mallConfigVO);
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();

            // 赋值给system的keyName和value
            system.setKeyName(fieldName);
            system.setKeyValue(value);
            system.setUpdateTime(new Date());

            // 根据keyName修改value
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            int i = systemMapper.updateByExampleSelective(system, systemExample);
            affectRows += i;

        }
        return affectRows;
    }

    /**
     * GET运费配置
     * @return ExpressConfigVO
     */
    @SneakyThrows
    @Override
    public ExpressConfigVO getExpressConfig() {
        ExpressConfigVO expressConfigVO = new ExpressConfigVO();
        System system = new System();

        // 遍历expressConfigVO的所有成员变量，成员变量名为system的keyName值
        Field[] declaredFields = expressConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();
            system.setKeyName(fieldName);

            // 根据keyName查找value；
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            List<System> systemList = systemMapper.selectByExample(systemExample);
            for (System system1 : systemList) {
                String keyValue = system1.getKeyValue();

                // 获得的keyValue赋值给mallConfig对应的成员变量值
                declaredField.setAccessible(true);
                declaredField.set(expressConfigVO, keyValue);
            }
        }

        return expressConfigVO;
    }

    /**
     * POST运费配置
     * @return int
     */
    @SneakyThrows
    @Override
    public int postExpressConfig(ExpressConfigVO expressConfigVO) {
        int affectRows = 0;
        System system = new System();

        // 遍历mallConfig的成员变量
        Field[] declaredFields = expressConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            // 获取成员变量名和对应的值
            String fieldName = declaredField.getName();
            declaredField.setAccessible(true);
            String value = (String) declaredField.get(expressConfigVO);
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();

            // 赋值给system的keyName和value
            system.setKeyName(fieldName);
            system.setKeyValue(value);
            system.setUpdateTime(new Date());

            // 根据keyName修改value
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            int i = systemMapper.updateByExampleSelective(system, systemExample);
            affectRows += i;

        }
        return affectRows;
    }

    /**
     * GET订单配置
     * @return MallConfigVO
     */
    @SneakyThrows
    @Override
    public OrderConfigVO getOrderConfig() {
        OrderConfigVO orderConfigVO = new OrderConfigVO();
        System system = new System();

        // 遍历mallconfig的所有成员变量，成员变量名为system的keyName值
        Field[] declaredFields = orderConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();
            system.setKeyName(fieldName);

            // 根据keyName查找value；
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            List<System> systemList = systemMapper.selectByExample(systemExample);
            for (System system1 : systemList) {
                String keyValue = system1.getKeyValue();

                // 获得的keyValue赋值给mallConfig对应的成员变量值
                declaredField.setAccessible(true);
                declaredField.set(orderConfigVO, keyValue);
            }
        }

        return orderConfigVO;
    }

    /**
     * POST商场配置
     * @return int
     */
    @SneakyThrows
    @Override
    public int postOrderConfig(OrderConfigVO orderConfigVO) {
        int affectRows = 0;
        System system = new System();

        // 遍历mallConfig的成员变量
        Field[] declaredFields = orderConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            // 获取成员变量名和对应的值
            String fieldName = declaredField.getName();
            declaredField.setAccessible(true);
            String value = (String) declaredField.get(orderConfigVO);
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();

            // 赋值给system的keyName和value
            system.setKeyName(fieldName);
            system.setKeyValue(value);
            system.setUpdateTime(new Date());

            // 根据keyName修改value
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            int i = systemMapper.updateByExampleSelective(system, systemExample);
            affectRows += i;

        }
        return affectRows;
    }

    /**
     * GET小程序配置
     */
    @SneakyThrows
    @Override
    public WxConfigVO getWxConfig() {
        WxConfigVO wxConfigVO = new WxConfigVO();
        System system = new System();

        // 遍历mallconfig的所有成员变量，成员变量名为system的keyName值
        Field[] declaredFields = wxConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();
            system.setKeyName(fieldName);

            // 根据keyName查找value；
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            List<System> systemList = systemMapper.selectByExample(systemExample);
            for (System system1 : systemList) {
                String keyValue = system1.getKeyValue();

                // 获得的keyValue赋值给mallConfig对应的成员变量值
                declaredField.setAccessible(true);
                declaredField.set(wxConfigVO, keyValue);
            }
        }

        return wxConfigVO;
    }

    /**
     * POST小程序配置
     * @param wxConfigVO wxconfig
     * @return affectrows
     */
    @SneakyThrows
    @Override
    public int postWxConfig(WxConfigVO wxConfigVO) {
        int affectRows = 0;
        System system = new System();

        // 遍历mallConfig的成员变量
        Field[] declaredFields = wxConfigVO.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            // 获取成员变量名和对应的值
            String fieldName = declaredField.getName();
            declaredField.setAccessible(true);
            String value = (String) declaredField.get(wxConfigVO);
            StringBuffer stringBuffer = new StringBuffer(fieldName);
            fieldName = stringBuffer.insert(8, "_").toString();

            // 赋值给system的keyName和value
            system.setKeyName(fieldName);
            system.setKeyValue(value);
            system.setUpdateTime(new Date());

            // 根据keyName修改value
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andKeyNameEqualTo(fieldName);
            int i = systemMapper.updateByExampleSelective(system, systemExample);
            affectRows += i;

        }
        return affectRows;
    }
}
