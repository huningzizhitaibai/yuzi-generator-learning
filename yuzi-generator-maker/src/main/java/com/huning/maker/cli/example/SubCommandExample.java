package com.huning.maker.cli.example;

import picocli.CommandLine;

public class SubCommandExample implements Runnable {
    @Override
    public void run() {
        System.out.println("执行主命令");
    }

    @CommandLine.Command(name = "add", description = "增加", mixinStandardHelpOptions = true)
    static class AddSubCommand implements Runnable {
        public void run() {
            System.out.println("执行增加命令");
        }
    }

    @CommandLine.Command(name = "delete", description = "删除", mixinStandardHelpOptions = true)
    static class DeleteSubCommand implements Runnable {
        public void run() {
                System.out.println("执行删除命令");
        }
    }

    @CommandLine.Command(name = "query", description = "查询", mixinStandardHelpOptions = true)
    static class QuerySubCommand implements Runnable {
        public void run() {
            System.out.println("执行查询命令");
        }
    }

    public static void main(String[] args) {
        //执行主命令
        String[] myArgs = new String[]{ };

        //查看主命令的帮助手册
//        String[] myArgs = new String[] {"--help"};
    }

}
