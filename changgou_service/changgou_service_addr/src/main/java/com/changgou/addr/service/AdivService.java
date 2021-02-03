package com.changgou.addr.service;

import com.changgou.addr.domain.ContactDTO;
import com.changgou.addr.domain.Kv;

import java.util.List;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/2 9:15
 * @Version 1.0
 */
public interface AdivService {
    /**
     * 获取所有的省份
     *
     * @param
     * @return {@link {@link List< Kv>}}
     * @throws
     * @Author leery
     * @Date 2021/2/2 9:16
     */
    List<Kv> findAllProvince();

    /**
     * 获取所有的城市
     *
     * @param
     * @return {@link {@link List< Kv>}}
     * @throws
     * @Author leery
     * @Date 2021/2/2 9:16
     */
    List<Kv> findAllCity();

    /**
     * 获取所有的区县
     *
     * @param
     * @return {@link {@link List< Kv>}}
     * @throws
     * @Author leery
     * @Date 2021/2/2 9:16
     */
    List<Kv> findAllCounty();

    /**
     * 获取行政地区树
     *
     * @param
     * @return {@link {@link List< Kv>}}
     * @throws
     * @Author leery
     * @Date 2021/2/2 9:16
     */
    List<Kv> getAdivTree();

    /**
     *
     * 地址识别
     * @param text
     * @return {@link {@link List< Kv>}}
     * @throws
     * @Author leery
     * @Date 2021/2/2 9:19
     */
    ContactDTO parseAddress(String text);
}
