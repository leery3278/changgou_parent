package com.changgou.addr;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import com.changgou.addr.dict.DictEnum;
import com.changgou.addr.domain.AdivConstants;
import com.changgou.addr.domain.AdivTree;
import com.changgou.addr.domain.Kv;
import com.changgou.addr.util.TreeUtil;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@EnableSwagger2Doc
@SpringBootApplication
public class AddressAutoDetectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressAutoDetectApplication.class,args);
    }

    @Bean(name ="adivConstants")
    public AdivConstants init() {

        AdivConstants constants = new AdivConstants();

        List<Kv> provinces = new ArrayList<>();

        List<Kv> citys = new ArrayList<>();

        List<Kv> countys = new ArrayList<>();

        List<AdivTree> adivTrees = new ArrayList<>();
        /**
         * 特征标签
         */
        String tag = "00";
        //读取文件
        File file = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath());
        File dict = new File(file.getParentFile().getParentFile().getAbsolutePath() + "/library/default.dic");
        List<String> lines = FileUtil.readLines(dict,"UTF-8");

        HashMap<String,String> adivDict = new HashMap<>(lines.size());

        for(String line : lines) {
            //以tab为分隔
            String[] contents = line.split("\t");
            //编号
            String id = contents[2];
            //名称
            String name = contents[0];
            log.info("编号：{}，名称：{}",id,name);

            //放入map字典里
            adivDict.put(id,name);

            Kv kv = new Kv();
            //编号 530000
            kv.setKey(id);
            //取值 云南省
            kv.setValue(name);
            //省
            String province = contents[2].substring(0,2);
            //市
            String city = contents[2].substring(2,4);
            //区县
            String county = contents[2].substring(4,6);

            /**
             * 初始化省、市、区
             */
            if(!tag.equals(county)) {
                countys.add(kv);
            }
            if(!tag.equals(city) && tag.equals(county)) {
                citys.add(kv);
            }
            if(tag.equals(city) && tag.equals(county)) {
                provinces.add(kv);
            }
            /**
             * 树结果
             */
            AdivTree adivTree = new AdivTree();
            adivTree.setKey(contents[2]);
            adivTree.setValue(contents[0]);
            adivTree.setParentKey(adivTree.getParentKey());
            adivTrees.add(adivTree);
        }
        List china = TreeUtil.build(adivTrees, DictEnum.ADIV_TYPE.COUNTRY.value);
        constants.setAdivTree(china);

        constants.setAdivTree(china);
        constants.setCitys(citys);
        constants.setCountys(countys);
        constants.setProvinces(provinces);
        constants.setAdivDict(adivDict);

        return constants;
    }

    /**
     * 初始化中文分词引擎
     * @return
     */
    @Bean(value = "tokenizerEngine")
    public TokenizerEngine engine() {
        return TokenizerUtil.createEngine();
    }

}
