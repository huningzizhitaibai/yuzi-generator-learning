package com.huning.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.huning.maker.generator.file.MainGenerator;
import com.huning.maker.model.DataModel;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    //具体执行的一些参数选项
    @CommandLine.Option(names = {"-l","--loop"},description = "是否循环", interactive = true, echo = true)
    private boolean loop=false;

    @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true,echo = true)
    private String author = "huning";

    @CommandLine.Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出文本", interactive = true,echo = true)
    private String outputText = "Sum = ";


    public Integer call() throws Exception {
        DataModel mainTemplateConfig = new DataModel();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息"+mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }


}
