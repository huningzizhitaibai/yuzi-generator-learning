package com.huning.generator;

import com.huning.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void doGenerate(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();

        //将整体的打包文件生成再了父文件下, 相当于是不做测试了.

        String inputPath =  new File(projectPath+"/acm-template").getAbsolutePath();
        String outputPath =  projectPath;

        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        //这里的模板用的是据提供的模板后自己开发的模板, 对静态copy中的整体文件进行覆写
        String inputDynamicFilePath = projectPath+"/src/main/resources/templates/";
        String outputDynamicFilePath = outputPath+"/acm-template/src/com/yupi/acm/";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }

    public static void main(String[] args) throws IOException, TemplateException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果是: ");
        mainTemplateConfig.setAuthor("huning");
        doGenerate(mainTemplateConfig);
    }
}
