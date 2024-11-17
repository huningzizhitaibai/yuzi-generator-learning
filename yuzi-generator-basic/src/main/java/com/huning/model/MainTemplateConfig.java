package com.huning.model;

import lombok.Data;


/*
这个类用来处理需要用户输入的数据，也就是动态文件中动态的部分
这些数据需要根据用户输入的参数不同进行配置.
 */
@Data
public class MainTemplateConfig {
    private boolean loop;

    private String author;

    private String outputText = "sum = ";
}
