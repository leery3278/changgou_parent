package com.changgou.addr.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.Word;
import com.changgou.addr.domain.AdivConstants;
import com.changgou.addr.domain.ContactDTO;
import com.changgou.addr.domain.Kv;
import com.changgou.addr.service.AdivService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/2 9:31
 * @Version 1.0
 */
@Slf4j
@Service(value = "adivService")
public class AdivServiceImpl implements AdivService {

    private final String[] WHITE_LIST = {"区","市","市辖区","县"};

    @Resource
    private TokenizerEngine tokenizerEngine;

    @Resource
    private AdivConstants adivConstants;

    @Override
    public List<Kv> findAllProvince() {
        return adivConstants.getProvinces();
    }

    @Override
    public List<Kv> findAllCity() {
        return adivConstants.getCitys();
    }

    @Override
    public List<Kv> findAllCounty() {
        return adivConstants.getCountys();
    }

    @Override
    public List<Kv> getAdivTree() {
        return adivConstants.getAdivTree();
    }
    /**
     * 特征标签
     */
    private static final String tag = "00";

    /**
     * 手机正则表达式
     */
    private String PHONE_PATTERN = "^((\\\\+86)|(86))?[1][3456789][0-9]{9}$";
    /**
     * 电话正则表达式(不带区号)
     */
    private String TEL_WITH_AREACODE_PATTERN = "^[0][1-9]{2,3}-[0-9]{5,10}$";
    /**
     * 电话正则表达式
     */
    private String TEL_WITH_NO_AREACODE_PATTERN = "^[1-9]{1}[0-9]{5,8}$";

    @Override
    public ContactDTO parseAddress(String text) {
        //将输入的字符串进行中文分词
        Iterator<Word> result = tokenizerEngine.parse(text);
        List<Word> keyWords = CollUtil.newArrayList(result);
        //循环遍历关键词
        ContactDTO dto = new ContactDTO();
        //第一次遍历关键字，查询地址信息
        for(Word word : keyWords) {
            String keyWord = word.getText();
            log.info("关键字：{}",keyWord);
            //如果关键词为："区"，"市"，"市辖区"，"县"则跳过继续
            if(Arrays.asList(WHITE_LIST).contains(keyWord)) {
                continue;
            }
            //查找关键字
            findAdivLikeName(keyWord,dto);
            //如果区县不为空，则直接跳出循环
            if(dto.getCounty() != null) {
                break;
            }
        }
        //截断后的地址
        StringBuffer shortAddr = new StringBuffer();
        //再次遍历关键字，解析是否存在手机、电话、身份证等信息
        for(Word word : keyWords) {
            String keyWord = word.getText();
            log.info("解析是否存在手机、电话、身份证等信息，关键字为：{}",keyWord);
            //判断是否为身份证
            if(IdcardUtil.isValidCard(keyWord)) {
                dto.setCustId(keyWord);
                continue;
            }
            //判断是否为手机号
            if(Pattern.matches(PHONE_PATTERN,keyWord)) {
                dto.setPhone(keyWord);
                continue;
            }
            //判断是否为固话
            if(Pattern.matches(TEL_WITH_AREACODE_PATTERN,keyWord) || Pattern.matches(TEL_WITH_NO_AREACODE_PATTERN,keyWord)) {
                dto.setTel(keyWord);
                continue;
            }
            String dtoStr = dto.toString();
            //如果关键字不在联系信息中，则拼接截取后的地址
            if(!dtoStr.contains(keyWord)) {
                shortAddr.append(keyWord);
            }
        }
        log.info("截断后的地址为：{}，将标点符号等过滤",shortAddr.toString());
        dto.setShortAddress(shortAddr.toString().replaceAll("(?i)[^a-zA-Z09\u4E00-\u9FA5]",""));
        dto.setOriginalAddress(text);
        return  dto;
    }

    /**
     * 查找关键字
     * @param name
     * @param dto
     */
    public void findAdivLikeName(String name,ContactDTO dto) {
        //先查区县里是否包含关键字
        List<Kv> kvs = adivConstants.getCountys().stream().
                filter((kv -> kv.getValue().contains(name))).collect(Collectors.toList());
        if(!CollUtil.isEmpty(kvs)) {
            log.info("关键词[{}]为区县", name);
            Kv county = kvs.get(0);
            dto.setCounty(county);

            String cityKey = county.getKey().substring(0, 4) + tag;
            log.info("[{}]对应的城市代码为：[{}]", county.getKey(), cityKey);
            dto.setCity(new Kv(cityKey, adivConstants.getAdivDict().get(cityKey)));

            String provinceKey = county.getKey().substring(0, 2) + tag + tag;
            log.info("[{}]对应的省代码为：[{}]", county.getKey(), provinceKey);
            dto.setProvince(new Kv(provinceKey, adivConstants.getAdivDict().get(provinceKey)));
            return;
        }
            //如果不是区县，则查市级信息
            kvs = adivConstants.getCitys().stream().
                    filter((kv -> kv.getValue().contains(name))).collect(Collectors.toList());
            if(!CollUtil.isEmpty(kvs)) {
                log.info("关键词[{}]为市",name);
                Kv city = kvs.get(0);

                String provinceKey = city.getKey().substring(0,2) + tag + tag;
                log.info("[{}]对应的省代码为：[{}]",city.getKey(),provinceKey);
                dto.setProvince(new Kv(provinceKey,adivConstants.getAdivDict().get(provinceKey)));
                return;
        }

        //如果不是市级，则查省信息
        kvs = adivConstants.getProvinces().stream().
                filter((kv -> kv.getValue().contains(name))).collect(Collectors.toList());
        if(!CollUtil.isEmpty(kvs)) {
            log.info("关键词[{}]为省",name);
            Kv province = kvs.get(0);
            dto.setCity(new Kv(province.getKey(),province.getValue()));
            return;
        }

    }

}
