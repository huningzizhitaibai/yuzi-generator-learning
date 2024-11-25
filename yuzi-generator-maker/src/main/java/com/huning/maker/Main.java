package com.huning.maker;

import com.huning.maker.cli.CommandExecutor;

//用于generator整体调用的入口
//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图
public class Main {
    public static void main(String[] args) {
//        args = new String[]{"generate","-l","-a", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}