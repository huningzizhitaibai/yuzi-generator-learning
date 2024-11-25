package com.huning.maker.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {
   @Option(names = {"-u", "--user"}, description = "User name")
    String username;

    //当加上参数arity时, 如果没有参数输入就会启动interactive模式.
   @Option(names = {"-p", "--password"}, description = "Passphrase", interactive = true, arity = "0..1")
    String password;

   @Option(names = {"-cp", "--checkPassword"}, description = "Check Password", interactive = true, arity = "0..1")
   String checkPassword;

   public Integer call() throws Exception {
       System.out.println("password = "+password);
       System.out.println("checkPassword = " + checkPassword);
       return 0;
   }

   public static void main(String[] args) throws Exception {
       new CommandLine(new Login()).execute("-u", "user123", "-p","12345","-cp");
   }
}
