package com.wtf.excel.export.factory;



import com.wtf.excel.export.InvocableHandlerProperty;
import com.wtf.excel.export.PropertyArgumentResolverComposite;
import com.wtf.excel.export.param.BeanParameter;
import com.wtf.excel.export.param.PropertyParameter;
import com.wtf.excel.export.resolver.HSSFPropertyArgumentProcessor;
import com.wtf.excel.export.resolver.PropertyArgumentResolver;
import com.wtf.excel.export.resolver.SXSSFPropertyArgumentProcessor;
import com.wtf.excel.export.resolver.XSSFPropertyArgumentProcessor;

import java.util.ArrayList;
import java.util.List;

public class DefaultWorkbookExportFactory extends AbstractWorkbookExportFactory {

    private PropertyArgumentResolverComposite propertyArgumentResolverComposite = new PropertyArgumentResolverComposite();

    private InvocableHandlerProperty handlerProperty = new InvocableHandlerProperty();

    public DefaultWorkbookExportFactory() {
        // 初始化属性参数解析器
        this.initArgumentResolverComposite();
    }


    private void initArgumentResolverComposite() {
        propertyArgumentResolverComposite = (new PropertyArgumentResolverComposite()).addResolvers(getDefaultPropertyArgumentResolvers());
        handlerProperty.setPropertyArgumentResolverComposite(this.propertyArgumentResolverComposite);
    }

    // 注册属性解析器
    private List<PropertyArgumentResolver> getDefaultPropertyArgumentResolvers() {
        List<PropertyArgumentResolver> resolvers = new ArrayList<>();
        resolvers.add(new HSSFPropertyArgumentProcessor());
        resolvers.add(new XSSFPropertyArgumentProcessor());
        resolvers.add(new SXSSFPropertyArgumentProcessor());
        return resolvers;
    }

    @Override
    protected <T> void createHeader(PropertyParameter<T> propertyParameter) {
        handlerProperty.handlerHeader(propertyParameter);
    }

    @Override
    protected <T> void createCell(PropertyParameter<T> propertyParameter) {
        handlerProperty.handlerProperty(propertyParameter);
    }
}
