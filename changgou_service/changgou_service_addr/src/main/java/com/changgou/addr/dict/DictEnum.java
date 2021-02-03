package com.changgou.addr.dict;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/1/29 14:45
 */
public interface DictEnum {

    enum ADIV_TYPE {
        /**
         * 地区标志
         */
        COUNTRY("000000","中国"),
        PROVINCE("PROVINCE","省份"),
        CITY("CITY","市"),
        COUNTY("COUNTY","区县"),;
        public final static String ENUMID = "ADIV_TYPE";
        public final static String ENUMMANE = "地区标志";
        public final String value;
        public final String desc;

        ADIV_TYPE(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

}
