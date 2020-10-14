package com.wtf.excel.export.factory;



/**
 * @auther: strugglesnail
 * @date: 2020/10/14 21:57
 * @desc: 导出工厂建造器
 */
public class WorkbookExportFactoryBuilder {

    public WorkbookExportFactory build(Class<?> clazz) {
        return new DefaultWorkbookExportFactory(clazz);
    }
}
