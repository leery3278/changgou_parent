package com.changgou.addr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/1/29 17:32
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Kv {
    /**
     * 代码
     */
    private String key;
    /**
     * 取值
     */
    private String value;

}
