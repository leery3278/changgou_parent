package com.changgou.addr.sensitive;

/**
 * 脱敏类型
 *
 * @Author leery
 * @Date 2021/2/3 14:59
 * @Version 1.0
 */
public enum SensitiveTypeEnum {
    /**
     * 中文姓名
     */
    CHINESE_NAME,
    /**
     * 身份证
     */
    ID_NO,
    /**
     * 座机
     */
    FIXED_PHONE,
    /**
     * 手机号
     */
    MOBILE_PHONE,
    /**
     * 地址
     */
    ADDRESS,
    /**
     * 电子邮件
     */
    EMAIL,
    /**
     * 银行
     */
    BANK_ACCT,
    /**
     * 密码
     */
    PASSWORD,
    /**
     * 联行号
     */
    CNAPS_CODE,
    /**
     * 车牌
     */
    PLATE_NO;
}
