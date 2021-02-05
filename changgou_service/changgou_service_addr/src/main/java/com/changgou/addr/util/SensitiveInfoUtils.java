package com.changgou.addr.util;

import cn.hutool.core.collection.CollUtil;
import com.changgou.addr.sensitive.SensitiveTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/3 15:16
 * @Version 1.0
 */
public class SensitiveInfoUtils {
    /**
     * 常用姓氏
     */
    public static final List SURNAME = CollUtil.newArrayList(
            "赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦",
            "尤","许","何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水",
            "窦","章","云","苏","潘","葛","奚","范","彭","郎","鲁","韦","昌","马","苗","凤","花","方","俞","任",
            "袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷","罗","毕","郝","邬",
            "安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和","穆",
            "萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋",
            "茅","庞","熊","纪","舒","屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路",
            "娄","危","江","童","颜","郭","梅","盛","林","刁","钟","徐","邱","骆","高","夏","蔡","田","樊","胡",
            "凌","霍","虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应","宗","丁","宣",
            "贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚","程","嵇","邢","滑","裴","陆",
            "荣","翁","荀","羊","於","惠","甄","麴","家","封","芮","羿","储","靳","汲","邴","糜","松","井","段",
            "富","巫","乌","焦","巴","弓","牧","隗","山","谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲",
            "伊","宫","宁","仇","栾","暴","甘","钭","厉","戎","祖","武","符","刘","景","詹","束","龙","叶","幸",
            "司","韶","郜","黎","蓟","薄","印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖","卓","蔺",
            "屠","蒙","池","乔","阴","欎","胥","能","苍","双","闻","莘","党","翟","谭","贡","劳","逄","姬","申",
            "扶","堵","冉","宰","郦","雍","舄","璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","郏","浦",
            "尚","农","温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习","宦","艾","鱼","容","向","古",
            "易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘","匡","国","文","寇","广","禄",
            "阙","东","殴","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂","晁","勾","敖","融","冷","訾",
            "辛","阚","那","简","饶","空","曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","後",
            "荆","红","游","竺","权","逯","盖","益","桓","公","晋","楚","闫","法","汝","鄢","涂","钦","归","海",
            "岳","帅","缑","亢","况","后","有","琴","商","牟","佘","佴","伯","赏","墨","哈","谯","笪","年","爱",
            "阳","佟","言","福","百","家","姓","终","仉","督"
    );

    /**
     * 中国复姓
     */
    public static final List COMPOUND_SURNAME = CollUtil.newArrayList(
            "万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","尉迟","公羊","澹台",
            "公冶","宗政","濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙",
            "慕容","鲜于","闾丘","司徒","司空","亓官","司寇","子车","颛孙","端木","巫马","公西","漆雕","乐正",
            "壤驷","公良","拓跋","夹谷","宰父","谷梁","东郭","段干","百里","呼延","微生","梁丘","左丘","东门",
            "西门","南宫","第五","羊舌","南门","太史"
    );

    /**
     * 【中文姓名】只显示第一个汉字，其他隐藏为2个星号<例子：李**>，为了更精准的匹配姓名，我们引用了常用的单姓和复姓
     *  进行匹配，否则日志中几乎所有的中文都被脱敏了，不利于排查
     */
    public static String chineseName(String fullName) {
        if(StringUtils.isBlank(fullName)) {
            return "";
        }
        if(fullName.length() < 2) {
            return fullName;
        }

        if(!SURNAME.contains(fullName.substring(0,1)) && !COMPOUND_SURNAME.contains(fullName.substring(0,1))) {
            return fullName;
        }
        String name = "";
        //复姓的处理
        if(COMPOUND_SURNAME.contains(StringUtils.left(fullName,2))) {
            name = StringUtils.left(fullName,2);
        }else {
            name = StringUtils.left(fullName,1);
        }
        return StringUtils.rightPad(name,StringUtils.length(fullName),"*");
    }

    /**
     *[中文姓名]只显示第一个汉子，其他隐藏为2个星号<例子：李**>
     *
     * @param familyName
     * @param givenName
     * @return {@link {@link String}}
     * @throws
     * @Author leery
     * @Date 2021/2/3 17:14
     */
    public static  String chineseName(String familyName,String givenName) {
        if(StringUtils.isBlank(familyName) || StringUtils.isBlank(givenName)) {
            return "";
        }
        return chineseName(familyName + givenName);
    }

    /**
     *
     *[身份证号] 显示最后六位，其他隐藏。共计18位或者15位。<例子：**************5762></>
     *
     * @param id
     * @return {@link {@link String}}
     * @throws
     * @Author leery
     * @Date 2021/2/3 17:37
     */
    public static String idCardNum(String id) {
        if(StringUtils.isBlank(id)) {
            return "";
        }
        String num = StringUtils.right(id,6);
        return StringUtils.leftPad(num,StringUtils.length(id),"*");
    }

    /**
     *
     *[固定电话] 后四位，其他隐藏<例子：021-****1234></>
     *
     * @param num 电话
     * @return {@link {@link String}}
     * @throws
     * @Author leery
     * @Date 2021/2/3 17:49
     */
    public static String fixedPhone(String num) {
        if(StringUtils.isBlank(num)) {
            return "";
        }
        int index = StringUtils.indexOf(num,"-");
        if(index > 1) {
            return StringUtils.substring(num,0,index+1).concat(StringUtils.leftPad(StringUtils.
                    right(StringUtils.substring(num,index),4),StringUtils.
                    length(StringUtils.substring(num,index)),"*"));
        }
        return StringUtils.leftPad(StringUtils.right(num,4),StringUtils.length(num),"*");
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子：138****1234></>
     * @param num
     * @return
     */
    public static String mobilePhone(String num) {
        if(StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num,3).concat(StringUtils.removeStart
                (StringUtils.leftPad(
                        StringUtils.right(num,4),StringUtils.length(num),"*"),"***"));
    }

    /**
     * 【地址】 只显示到地区，不显示详细地址，我们要对个人信息增强保护<>例子：北京市海淀区****</>
     *
     * @param address
     * @param sensitiveSize
     * @return {@link {@link String}}
     * @throws
     * @Author leery
     * @Date 2021/2/4 14:55
     */
    public static String address(String address,int sensitiveSize) {
        if(StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address,length - sensitiveSize),length,"*");
    }

    /**
     *[电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子：g**@163.com></>
     *
     * @param email
     * @return {@link {@link String}}
     * @throws
     * @Author leery
     * @Date 2021/2/4 15:16
     */
    public static String email(String email) {
        if(StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email,"@");
        if(index <= 1) {
            return email;
        }else {
            return StringUtils.rightPad(StringUtils.left(email,1),index,"*").
                    concat(StringUtils.mid(email,index,StringUtils.length(email)));
        }
    }

    /**
     *[银行卡号] 前四位，后四位，其他用星号隐藏每位1个星号<例子：6222**********1234></>
     *
     * @param cardNum
     * @return {@link {@link String}}
     * @throws
     * @Author leery
     * @Date 2021/2/4 15:29
     */
    public static String bankCard(String cardNum) {
        if(StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum,4).
                concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum,4),
                        StringUtils.length(cardNum),"*"),"******"));
    }

    /**
     *车牌号：<例子：沪GD**92></>
     *
     * @param plateNo
     * @return {@link {@link String}}
     * @throws
     * @Author leery
     * @Date 2021/2/4 15:32
     */
    public static String plateNo(String plateNo) {
        if(StringUtils.isBlank(plateNo)) {
            return "";
        }
        return StringUtils.left(plateNo,3).concat
            (StringUtils.leftPad(StringUtils.right(plateNo,2),
                StringUtils.substring(plateNo,3).length(),"*"));
    }

    public static String word(String word, SensitiveTypeEnum typeEnum) {
        switch (typeEnum) {
            case CHINESE_NAME: {
                return SensitiveInfoUtils.chineseName(word);
            }
            case ID_NO: {
                return SensitiveInfoUtils.idCardNum(word);
            }
            case FIXED_PHONE: {
                return SensitiveInfoUtils.fixedPhone(word);
            }
            case MOBILE_PHONE: {
                return SensitiveInfoUtils.mobilePhone(word);
            }
            case ADDRESS: {
                return SensitiveInfoUtils.address(word,4);
            }
            case EMAIL: {
                return SensitiveInfoUtils.email(word);
            }
            case BANK_ACCT: {
                return SensitiveInfoUtils.bankCard(word);
            }
            case PLATE_NO: {
                return SensitiveInfoUtils.plateNo(word);
            }
        }
        return word;
    }

}
