package com.huning.maker.meta;


import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

//用于实现单例的设计模式
//在程序运行中只产生一个meta对象的实例
public class MetaManager {
    private static volatile Meta meta;
    private MetaManager() {
        //私有构造函数，防止被外部实例化
    }

    public static Meta getMetaObject(){
        if(meta == null){
            synchronized (MetaManager.class){
                if(meta == null){
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta(){
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson,Meta.class);

        MetaValidator.doValidAndFill(newMeta);
        return newMeta;
    }
}
