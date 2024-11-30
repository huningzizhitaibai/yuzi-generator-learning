package com.huning.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

//元信息的校验
public class MetaValidator {
    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);

        validAndFillFileConfig(meta);

        validAndFillModelConfig(meta);


    }

    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig != null) {
            List<Meta.ModelConfig.Models> modelInfoList = modelConfig.getModels();
            if (CollectionUtil.isNotEmpty(modelInfoList)) {
                for (Meta.ModelConfig.Models modelInfo : modelInfoList) {
                    // 输出路径默认值
                    String fieldName = modelInfo.getFieldName();
                    if (StrUtil.isBlank(fieldName)) {
                        throw new MetaException("未填写 fieldName");
                    }

                    String modelInfoType = modelInfo.getType();
                    if (StrUtil.isEmpty(modelInfoType)) {
                        modelInfo.setType("String");
                    }
                }
            }
        }
    }

    private static void validAndFillFileConfig(Meta meta) {
        //fileConfig 的校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();

        if (!(fileConfig == null)) {

            String sourceRootPath = fileConfig.getSourceRootPath();
            if (StrUtil.isBlank(sourceRootPath)) {
                throw new MetaException("未填写 sourceRootPath");
            }

            String inputRootPath = fileConfig.getInputRootPath();
            String defaultInputRootPath = ".source" + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
            if (StrUtil.isEmpty(defaultInputRootPath)) {
                fileConfig.setInputRootPath(defaultInputRootPath);
            }

            // outputRootPath: 默认为当前目录下的 generated
            String outputRootPath = fileConfig.getOutputRootPath();
            String defaultOutputRootPath = "generated";
            if (StrUtil.isEmpty(outputRootPath)) {
                fileConfig.setOutputRootPath(defaultOutputRootPath);
            }

            String fileConfigType = fileConfig.getType();
            String defaultType = "dir";
            if (StrUtil.isEmpty(fileConfigType)) {
                fileConfig.setType(defaultType);
            }

            //fileInfo 默认值
            List<Meta.FileConfig.Files> fileInFoList = fileConfig.getFiles();
            if(CollectionUtil.isEmpty(fileInFoList)){
                for (Meta.FileConfig.Files fileInfo : fileInFoList) {
                    String inputPath = fileInfo.getInputPath();
                    if (StrUtil.isBlank(inputPath)) {
                        throw new MetaException("未填写 inputPath");
                    }

                    // outputPath: 默认为 inputPath
                    String outputPath = fileInfo.getOutputPath();
                    if (StrUtil.isEmpty(outputPath)) {
                        fileInfo.setOutputPath(inputPath);
                    }

                    // type: 默认值 inputPath 有文件的后缀的为file，否则为dir
                    String type = fileInfo.getType();
                    if (StrUtil.isBlank(type)) {
                        // 无文件后缀
                        if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                            fileInfo.setType("dir");
                        } else {
                            fileInfo.setType("file");
                        }
                    }

                    // generateType: 如果文件不是ftl， generateType默认为static，否则为为dynamic
                    String generateType = fileInfo.getGenerateType();
                    if (StrUtil.isBlank(generateType)) {

                        //为动态模板
                        if (inputPath.endsWith(".ftl")) {
                            fileInfo.setGenerateType("dynamic");
                        } else {
                            fileInfo.setGenerateType("static");
                        }
                    }
                }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        String name = meta.getName();
        if (StrUtil.isBlank(name)) {
            //提供和填充默认值，一下内容思路相同
            name = "my-generator";
            meta.setName(name);
        }

        String description = meta.getDescription();
        if (StrUtil.isBlank(description)) {
            description = "我的模板代码生成器";
            meta.setDescription(description);
        }

        String author = meta.getAuthor();
        if (StrUtil.isBlank(author)) {
            author = "huning";
            meta.setAuthor(author);
        }

        String basePackage = meta.getBasePackage();
        if (StrUtil.isBlank(basePackage)) {
            basePackage = "com.huning";
            meta.setBasePackage(basePackage);
        }

        String version = meta.getVersion();
        if (StrUtil.isBlank(version)) {
            version = "1.0";
            meta.setVersion(version);
        }

        String createTime = meta.getCreateTime();
        if (StrUtil.isBlank(createTime)) {
            createTime = DateUtil.now();
            meta.setCreateTime(createTime);
        }
    }
}
