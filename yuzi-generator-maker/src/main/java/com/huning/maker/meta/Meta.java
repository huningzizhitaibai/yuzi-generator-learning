package com.huning.maker.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Meta {

    private String name;
    private String description;
    private String basePackage;
    private String version;
    private String author;
    private String createTime;
    private FileConfig fileConfig;
    private ModelConfig modelConfig;

    @NoArgsConstructor
    @Data
    public static class FileConfig {
        private String inputRootPath;
        private String outputRootPath;
        private String sourceRootPath;
        private String type;
        private List<Files> files;

        @NoArgsConstructor
        @Data
        public static class Files {
            private String inputPath;
            private String outputPath;
            private String type;
            private String generateType;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ModelConfig {
        private List<Models> models;

        @NoArgsConstructor
        @Data
        public static class Models {
            private String fieldName;
            private String type;
            private String description;
            private Object defaultValue;    //一定要注意将这里的defaultValue的类型改为Object，不然所有的默认都会是Boolean
            private String abbr;
        }
    }
}
