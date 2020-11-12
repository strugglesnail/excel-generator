package com.wtf.excel.imp.factory;


import com.wtf.excel.imp.handler.ImportDataHandler;

import java.io.InputStream;
import java.util.List;

/**
 * @auther strugglesnail
 * @date 2020/10/29 19:24
 * @desc
 */
public class WorkbookImportFactoryBuilder<T> {

    //
    private InputStream stream;
    private Class<T> target;
    private static WorkbookImportFactory factory;

    private WorkbookImportFactoryBuilder(Builder builder) {
        stream = builder.stream;
        target = builder.target;
        factory = builder.factory != null ? builder.factory : new DefaultWorkbookImportFactory();
    }

    // 创建导入工厂
    public List<T> get(){
        return factory.getExcelData(stream, target, null);
    }
    // 获取导入数据（）
    public List<T> get(ImportDataHandler<T> handler){
        return factory.getExcelData(stream, target, handler);
    }


    public static final class Builder<T> {
        private InputStream stream;
        private Class<T> target;
        private WorkbookImportFactory factory;

        public Builder() {
        }

        public Builder stream(InputStream inputStream) {
            stream = inputStream;
            return this;
        }

        public Builder target(Class<T> c) {
            target = c;
            return this;
        }

        public Builder factory(WorkbookImportFactory workbookImportFactory) {
            factory = workbookImportFactory;
            return this;
        }

        public WorkbookImportFactoryBuilder build() {
            return new WorkbookImportFactoryBuilder(this);
        }
    }

    public static void main(String[] args) {
        WorkbookImportFactoryBuilder builder = new Builder().stream(null)
                .build();
        builder.get();
    }
}
