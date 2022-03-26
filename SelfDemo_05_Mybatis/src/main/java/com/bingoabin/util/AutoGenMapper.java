package com.bingoabin.util;

import org.assertj.core.util.Strings;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HanJunsheng
 * @date 2021/7/2
 * @copyright sankuai.com
 */
public class AutoGenMapper {

    public static void autoGen(String genCfg) throws Exception{
        List<String> warnings = new ArrayList<>();
        //如果这里出现空指针，直接写绝对路径即可。
        //String genCfg = "/generatorConfig.xml";
        File configFile = new File(AutoGenMapper.class.getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(new VerboseProgressCallback());
        System.out.println(Strings.join(warnings).with("\n"));
        System.out.println(String.join("\n",warnings));
    }
}
