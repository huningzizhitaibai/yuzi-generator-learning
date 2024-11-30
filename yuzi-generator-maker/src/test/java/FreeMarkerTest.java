import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {
    @Test
    public void test() throws IOException , TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        configuration.setDirectoryForTemplateLoading(new File("src/doGenerate/resources/templates"));

        configuration.setDefaultEncoding("utf-8");

        Template template = configuration.getTemplate("myweb.html.ftl");

        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2024);
        List<Map<String,Object>> menuItems = new ArrayList<>();
        Map<String,Object> menuItem1 = new HashMap<>();

        //给hashmap喂数据, 直接向模板中注入.
        menuItem1.put("url","https://laoyujianli.com");
        menuItem1.put("label","老鱼简历");

        Map<String,Object> menuItem2 = new HashMap<>();
        menuItem2.put("url","https://codefather.cn");
        menuItem2.put("label", "编程导航");

        //将建好的对象注入列表
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);

        dataModel.put("menuItems",menuItems);

        Writer out = new FileWriter("myweb.html");

        template.process(dataModel,out);
    }
}
