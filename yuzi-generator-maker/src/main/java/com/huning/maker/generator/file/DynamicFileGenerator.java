package com.huning.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynamicFileGenerator {

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
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        configuration.setDefaultEncoding("utf-8");
        //获取编写好的模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        //如果文件不存在则创建文件和父目录

        //bug记录
        //直接通过代码不全使用的的FileUtil.mkdir()其实易得这样的话创造出来的从字节上看是路径
        //但是在之后的代码中其实是直接生成了文件，在查阅源代码中发现其实用的是touch，通过Linux基本的命令可知bug点
        if(!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }

        //生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);


        out.close();
    }

}
