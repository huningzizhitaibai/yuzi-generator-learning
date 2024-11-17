package com.huning.generator;

import com.huning.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynamicGenerator {

    /**
     * 根据模板和输入的参数生成代码
     * @param inputPath 模板路径
     * @param outputPath 输出文件路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath,Object model)
        throws IOException, TemplateException {
        //new出Configuration对象,参数为FreeMarker版本号
        //有点像创建出一个工作台
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

        //指定模板文件所在路径
        configuration.setDirectoryForTemplateLoading(new File(inputPath));

        configuration.setDefaultEncoding("UTF-8");
        //获取编写好的模板
        Template template = configuration.getTemplate("MainTemplate.java.ftl");



        Writer out = new FileWriter(outputPath+"MainTemplate.java");
        //将模型数据进行注入
        template.process(model, out);

        out.close();
    }

    public static void main(String[] args) throws IOException, TemplateException {
        //main方法中要做的就是获取参数,虽然还可以根据封装, 再将这个功能也进行一个方法调用
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + "/src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + "/MainTemplate.java";

        //创建编写好的数据模型,并对模型进行数据注入
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("huning");
        mainTemplateConfig.setOutputText("求和结果: ");
        mainTemplateConfig.setLoop(false);
        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }


}
