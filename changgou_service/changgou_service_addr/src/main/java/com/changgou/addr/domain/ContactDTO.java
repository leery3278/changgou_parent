package com.changgou.addr.domain;

import lombok.Data;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/1 14:14
 */
@Data
public class ContactDTO {
    /**
     * 县区
     */
    private Kv county;
    /**
     * 市
     */
    private Kv city;
    /**
     * 省
     */
    private Kv province;
    /**
     * 截断后地址
     */
    private String shortAddress;
    /**
     * 原始地址
     */
    private String originalAddress;
    /**
     * 手机
     */
    private String phone;
    /**
     * 电话
     */
    private String tel;
    /**
     * 身份证
     */
    private String custId;

}
