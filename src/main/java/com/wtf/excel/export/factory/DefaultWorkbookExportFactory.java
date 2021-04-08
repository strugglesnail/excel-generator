package com.wtf.excel.export.factory;



import com.wtf.excel.export.InvocableHandlerProperty;
import com.wtf.excel.export.PropertyArgumentResolverComposite;
import com.wtf.excel.export.annotation.ExcelFormat;
import com.wtf.excel.export.generator.StyleGenerator;
import com.wtf.excel.export.param.PropertyParameter;
import com.wtf.excel.export.resolver.HSSFPropertyArgumentProcessor;
import com.wtf.excel.export.resolver.PropertyArgumentResolver;
import com.wtf.excel.export.resolver.SXSSFPropertyArgumentProcessor;
import com.wtf.excel.export.resolver.XSSFPropertyArgumentProcessor;

import java.util.ArrayList;
import java.util.List;
/**
 * @auther strugglesnail
 * @date 2020/10/29 20:11
 * @desc
 */
public class DefaultWorkbookExportFactory extends AbstractWorkbookExportFactory {




    // 注册属性解析器
    @Override
    protected List<PropertyArgumentResolver> getDefaultPropertyArgumentResolvers() {
        List<PropertyArgumentResolver> resolvers = new ArrayList<>();
        resolvers.add(new HSSFPropertyArgumentProcessor());
        resolvers.add(new XSSFPropertyArgumentProcessor());
        resolvers.add(new SXSSFPropertyArgumentProcessor());
        return resolvers;
    }






}

