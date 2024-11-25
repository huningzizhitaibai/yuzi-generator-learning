package com.huning.maker.cli.command;

//用于查看所有的Command能够调用的字段属性, 有点像是--help命令后列出所有的可用的命令

import com.huning.maker.model.DataModel;
import picocli.CommandLine;

import java.lang.reflect.Field;
@CommandLine.Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    public void run() {
        //新建一个线程实现config的逻辑
        System.out.println("查看参数信息");

        //获取要打印的属性值
        Class<?> myclass = DataModel.class;

        //获取类的所有字段
        Field[] fields = myclass.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
            System.out.println("Modifiers: " +  java.lang.reflect.Modifier.toString(field.getModifiers()));
            System.out.println("---");
        }
    }

}
