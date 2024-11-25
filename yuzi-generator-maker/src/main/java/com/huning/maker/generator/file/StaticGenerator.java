package com.huning.maker.generator.file;


import cn.hutool.core.io.FileUtil;

//用于生成静态文件,静态文件反正也是静态的,直接用个静态方法调用算了.
public class StaticGenerator {
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath,outputPath,false);
    }


}
