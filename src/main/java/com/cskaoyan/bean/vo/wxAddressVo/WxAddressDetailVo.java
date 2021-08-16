package com.cskaoyan.bean.vo.wxAddressVo;

/**
 * @ClassName WxAddressDetailVo
 * @Description TODO
 * @Author wpb
 * @Date 2021/8/15 1:19
 **/
public class WxAddressDetailVo {

    /**
     * isDefault : false
     * areaId : 376
     * address : bushui
     * cityName : 市辖区
     * areaName : 东城区
     * name : qwe
     * mobile : 13812341234
     * id : 26
     * cityId : 32
     * provinceName : 北京市
     * provinceId : 1
     */

    private boolean isDefault;
    private int areaId;
    private String address;
    private String cityName;
    private String areaName;
    private String name;
    private String mobile;
    private int id;
    private int cityId;
    private String provinceName;
    private int provinceId;

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
