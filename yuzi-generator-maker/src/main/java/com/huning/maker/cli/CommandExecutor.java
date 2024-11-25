package com.huning.maker.cli;

import com.huning.maker.cli.command.ConfigCommand;
import com.huning.maker.cli.command.GenerateCommand;
import com.huning.maker.cli.command.ListCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "yuzi", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{
    private final CommandLine commandLine;


    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        //没有输入子命令时，给出提示，其实就是用户常见的用户输入错误
        System.out.println("请输入具体的命令，或者输入 --help 查看命令提示");
    }

    /**
     * 执行命令
     * 对命令进行执行， 读取用户输入的语句，分析其请求的服务
     * @param args 包含用户输入的一些命令选型，总体是一个字符串语句
     */
    public Integer doExecute(String[] args){
        return commandLine.execute(args);
    }
}
