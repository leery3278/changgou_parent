package com.changgou.addr.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/1 14:14
 */
@Data
public class AdivConstants {
    /**
     * 县区
     */
    private List<Kv> countys;
    /**
     * 市
     */
    private List<Kv> citys;
    /**
     * 省
     */
    private List<Kv> provinces;
    /**
     *
     */
    private List<Kv> adivTree;

    private Map<String, String> adivDict;

}
