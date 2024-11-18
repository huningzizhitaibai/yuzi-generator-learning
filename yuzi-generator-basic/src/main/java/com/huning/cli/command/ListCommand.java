package com.huning.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command(name = "list", description = "常看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    //覆写run单开了一个线程
    public void run() {
        String projecPath = System.getProperty("user.dir");

        File parentFile = new File(projecPath).getParentFile();

        String inputPath = new File(parentFile,"yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }

}
