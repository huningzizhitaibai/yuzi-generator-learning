package com.huning.maker;

import com.huning.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

//用于generator整体调用的入口
//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}