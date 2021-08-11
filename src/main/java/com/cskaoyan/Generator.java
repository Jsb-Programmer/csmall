package com.cskaoyan;
/**
 * @ClassName Generator
 * @Description mybatis逆向工程的main方法
 * @Author Programmer
 * @Date 2021/8/11 20:37
 **/
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true; //指向逆向工程配置文件
        //加载配置文件的时候，加载的相对路径，相对workingDirectory的相对路径
        File configFile = new File("src/main/resources/generatorConfig.xml");
        System.out.println(configFile.getAbsolutePath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator =
                new MyBatisGenerator(config, callback, warnings);

        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        try {
            Generator generatorSqlmap = new Generator();
            generatorSqlmap.generator();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
