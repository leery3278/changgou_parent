package com.changgou.addr.domain;

import com.changgou.addr.dict.DictEnum;
import lombok.Data;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/1 14:42
 */
@Data
public class AdivTree extends TreeNode{
    /**
     * 特征标签
     */
    private String tag = "00";
    /**
     * 地址类型
     */
    private String type;
    /**
     * 获取父节点
     */
    @Override
    public String getParentKey() {
        //省
        String province = key.substring(0,2);
        //市
        String city = key.substring(2,4);
        //区县
        String county = key.substring(4,6);
        this.type = DictEnum.ADIV_TYPE.PROVINCE.value;
        //最后两位不是00，则为区县，返回上级市
        if(!tag.equals(county)) {
            this.type = DictEnum.ADIV_TYPE.COUNTY.value;
            return province + city + tag;
        }else {
            //最后两位是00，但中间两位不是00，则为市，返回上级市省
            if(!tag.equals(city)) {
                this.type = DictEnum.ADIV_TYPE.CITY.value;
                return province + tag + tag;
            }
        }
        return DictEnum.ADIV_TYPE.COUNTRY.value;
    }
}
