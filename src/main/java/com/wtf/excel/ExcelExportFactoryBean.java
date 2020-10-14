package com.wtf.excel;

import com.wtf.excel.export.factory.WorkbookExportFactory;
import com.wtf.excel.export.factory.WorkbookExportFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @auther: strugglesnail
 * @date: 2020/10/14 21:15
 * @desc: 
 */
public class ExcelExportFactoryBean implements FactoryBean<WorkbookExportFactory>, InitializingBean {

    private WorkbookExportFactoryBuilder workbookExportFactoryBuilder = new WorkbookExportFactoryBuilder();

    private WorkbookExportFactory workbookExportFactory;

    private Class<?> target;

    @Override
    public WorkbookExportFactory getObject() throws Exception {
        if (this.workbookExportFactory == null) {
            afterPropertiesSet();
        }
        return this.workbookExportFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.workbookExportFactory = this.workbookExportFactoryBuilder.build(target);
    }
}
