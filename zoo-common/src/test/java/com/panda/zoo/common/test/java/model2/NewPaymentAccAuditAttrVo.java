package com.panda.zoo.common.test.java.model2;

import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * Created mangcao with 2018/9/26 11:09
 * Description:
 */
public class NewPaymentAccAuditAttrVo implements Serializable {

    // 1.基本信息
    @NotNull(errorCode = "1002", message = "店铺名称不能为空!")
    private String shopName;        // 店铺名称
    @NotNull(errorCode = "1002", message = "店铺类型不能为空!")
    private Integer shopKind;       // 营业模式 1中餐  2西餐  3火锅 4 服鞋 5 便利店,超市 6.五金
    @NotNull(errorCode = "1002", message = "店铺电话不能为空!")
    private String shopPhone;       // 店铺电话
    @NotNull(errorCode = "1002", message = "联系人不能为空!")
    private String linkName;        // 联系人
    @NotNull(errorCode = "1002", message = "联系人手机号不能为空!")
    private String linkTel;         // 联系人手机号
    //用于保存连锁修改的时候出现的问题
    @NotNull(errorCode = "1002", message = "店铺类型名称不能为空!")
    private String shopKindName;    // 店铺类型名称

    // 省市区 + 详细地址

    private String countryId;
    //@NotNull(errorCode = "1002", message = "省不能为空!")
    private String province;// 省
    //@NotNull(errorCode = "1002", message = " 市不能为空!")
    private String city;    // 市
    //@NotNull(errorCode = "1002", message = " 区不能为空!")
    private String district;// 区
    //@NotNull(errorCode = "1002", message = "省不能为空!")
    private String provinceId;
    //@NotNull(errorCode = "1002", message = "市不能为空!")
    private String cityId;
    //@NotNull(errorCode = "1002", message = " 区不能为空!")
    private String districtId;// 区
    //@NotNull(errorCode = "1002", message = "详细地址不能为空!")
    private String address; // 详细地址




    // 2.法人信息
    @NotNull(errorCode = "1002", message = "法人证件类型不能为空!")
    private String certificateType;         // 法人证件类型  1身份证, 2其他 ， 3港澳通行证 ，4台湾通行证
    @NotNull(errorCode = "1002", message = "姓名不能为空!")
    private String certificateName;         // 证件人名称
    @NotNull(errorCode = "1002", message = "法人证件号码不能为空!")
    private String certificateNum;          // 法人证件号码
    private boolean certificatePerpetual;          //证件是否永久
    private long certificateStartTime; //证件起始时间
    private long certificateExpire; // 证件有效时间
    @NotNull(errorCode = "1002", message = "法人联系电话不能为空!")
    private String corporationLinkTel;      // 法人联系电话
    private String email;//邮箱



    // 3 营业证件
    @NotNull(errorCode = "1002", message = "商户类型不能为空!")
    private String merchantType;    // 商户类型 01.自然人 02.个体工商户 03.企业商户
    @NotNull(errorCode = "1002", message = "营业执照名称不能为空!", when = "groovy:_this.merchantType != '01'")
    private String licenseName;             // 营业执照名称
    @NotNull(errorCode = "1002", message = "统一社会信用代码不能为空!", when = "groovy:_this.merchantType != '01'")
    private String creditCode;              // 统一社会信用代码（营业执照注册号）
    @NotNull(errorCode = "1002", message = "法人姓名不能为空!")
    private String corporationName;         // 营业执照法人姓名
    private boolean licensePerpetual; // 营业执照是否永久
    private long licenseStartTime;//营业执照起时间
    private long licenseExpire;//营业执照有效期




    // 银行账户信息
    @NotNull(errorCode = "1002", message = "开户银行名称不能为空!")
    private String bankName;        // 开户银行
    @NotNull(errorCode = "1002", message = "开户省份名称不能为空!")
    private String bankProvince;    // 开户省份
    @NotNull(errorCode = "1002", message = "开户城市名称不能为空!")
    private String bankCity;        // 开户城市
    @NotNull(errorCode = "1002", message = "开户支行名称不能为空!")
    private String bankSubName;     // 开户支行
    @NotNull(errorCode = "1002", message = "账户类型不能为空!")
    private Integer accountType;            // 账户类型  0 个人账户 1对攻账户
    @NotNull(errorCode = "1002", message = "商家简称不能为空!")
    private String abbreviation;    // 商家简称
    @NotNull(errorCode = "1002", message = "开户人姓名不能为空!")
    private String accountName;             // 开户人名称
    @NotNull(errorCode = "1002", message = "开户银行不能为空!")
    private String bankCode;  // 开户银行
    @NotNull(errorCode = "1002", message = "开户省份不能为空!")
    private String bankProvinceCode;
    @NotNull(errorCode = "1002", message = "开户城市不能为空!")
    private String bankCityCode;

    @NotNull(errorCode = "1002", message = "开户支行不能为空!")
    private String bankSubCode;
//    private String idType;                  // 开户人证件类型
//    private String idNumber;                // 开户人证件号码
    @NotNull(errorCode = "1002", message = "银行帐号不能为空!")
    private String accountNumber;           // 银行帐号
    @NotNull(errorCode = "1002", message = "银行预留手机号不能为空!")
    private String accountMobile;           // 银行预留手机号

    // ??
//    private String weixin;//微信
//    private String qq;//qq


    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }



    @AssertValid
    @NotNull(errorCode = "1002", message = "图片信息不能为空!")
    private List<AuditImgVo> auditImgVos;

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopKind() {
        return shopKind;
    }

    public void setShopKind(Integer shopKind) {
        this.shopKind = shopKind;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getShopKindName() {
        return shopKindName;
    }

    public void setShopKindName(String shopKindName) {
        this.shopKindName = shopKindName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getCorporationLinkTel() {
        return corporationLinkTel;
    }

    public void setCorporationLinkTel(String corporationLinkTel) {
        this.corporationLinkTel = corporationLinkTel;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankSubName() {
        return bankSubName;
    }

    public void setBankSubName(String bankSubName) {
        this.bankSubName = bankSubName;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(String bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public String getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    public String getBankSubCode() {
        return bankSubCode;
    }

    public void setBankSubCode(String bankSubCode) {
        this.bankSubCode = bankSubCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public List<AuditImgVo> getAuditImgVos() {
        return auditImgVos;
    }

    public void setAuditImgVos(List<AuditImgVo> auditImgVos) {
        this.auditImgVos = auditImgVos;
    }

    public long getLicenseExpire() {
        return licenseExpire;
    }

    public void setLicenseExpire(long licenseExpire) {
        this.licenseExpire = licenseExpire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public boolean isCertificatePerpetual() {
        return certificatePerpetual;
    }

    public void setCertificatePerpetual(boolean certificatePerpetual) {
        this.certificatePerpetual = certificatePerpetual;
    }

    public long getCertificateStartTime() {
        return certificateStartTime;
    }

    public void setCertificateStartTime(long certificateStartTime) {
        this.certificateStartTime = certificateStartTime;
    }

    public long getCertificateExpire() {
        return certificateExpire;
    }

    public void setCertificateExpire(long certificateExpire) {
        this.certificateExpire = certificateExpire;
    }

    public boolean isLicensePerpetual() {
        return licensePerpetual;
    }

    public void setLicensePerpetual(boolean licensePerpetual) {
        this.licensePerpetual = licensePerpetual;
    }

    public long getLicenseStartTime() {
        return licenseStartTime;
    }

    public void setLicenseStartTime(long licenseStartTime) {
        this.licenseStartTime = licenseStartTime;
    }
}
