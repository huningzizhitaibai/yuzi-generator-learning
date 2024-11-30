package com.huning.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.huning.maker.generator.JarGenerator;
import com.huning.maker.generator.ScriptGenerator;
import com.huning.maker.generator.file.DynamicFileGenerator;
import com.huning.maker.meta.Meta;
import com.huning.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {

    //将static删除，使这个方法可以被覆写
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
//        System.out.println(meta);

        //0. 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        //在增加这段代码时发现，所有get方法好像是编译器自己生成的，我并没有写具体的实现. 好像就是Lombok的作用
        //1.复制原始文件
        String sourceRootPath = copySource(meta, outputPath);

        //2.代码生成
        // 读取 resources 目录
        generateCode(meta, outputPath);


        // 3.构建 jar 包
        JarGenerator.doGenerate(outputPath);

        //4.封装脚本
        String shellOutputFilePath = outputPath + File.separator +"generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar",meta.getName(),meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath,jarPath);

        //5.生成精简版本（产物包）
        buildDist(outputPath, jarPath, shellOutputFilePath, sourceRootPath);
    }

    protected void buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceRootPath) {
        String distOutputFilePath = outputPath + "-dist";
        String targetAbsolutePath = distOutputFilePath +File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        //这里其实就是生成了两个包，一个详细，一个精简。所以直接把详细包中能够用到的文件复制过来就行了
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        //拷贝脚本文件
        //相当于只要能够执行就行，所以只要脚本和资源，不需要相关信息。
        FileUtil.copy(shellOutputFilePath, distOutputFilePath,true);
        //拷贝源模板文件
        FileUtil.copy(sourceRootPath,distOutputFilePath,true);
    }

    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath +File.separator + "./source";
        FileUtil.copy(sourceRootPath,sourceCopyDestPath,false);
        return sourceRootPath;
    }

    protected static void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // Java 包基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/doGenerate/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        //README.md
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);
    }
}
