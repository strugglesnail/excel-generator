package com.wtf.excel.export.factory;



import com.wtf.excel.export.InvocableHandlerProperty;
import com.wtf.excel.export.PropertyArgumentResolverComposite;
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

    // sheet名称
    private String sheetName = "file export";
    // 标题
    private String title = "";
    // 行的起始位置
    private int rowIndex = 0;
    // 列的起始位置
    private int colIndex = 0;

    private DefaultWorkbookExportFactory(Builder builder) {
        sheetName = builder.sheetName;
        title = builder.title;
        rowIndex = builder.rowIndex;
        colIndex = builder.colIndex;
    }

    // 注册属性解析器
    @Override
    protected List<PropertyArgumentResolver> getDefaultPropertyArgumentResolvers() {
        List<PropertyArgumentResolver> resolvers = new ArrayList<>();
        resolvers.add(new HSSFPropertyArgumentProcessor());
        resolvers.add(new XSSFPropertyArgumentProcessor());
        resolvers.add(new SXSSFPropertyArgumentProcessor());
        return resolvers;
    }


    public static final class Builder {
        private String sheetName;
        private String title;
        private int rowIndex;
        private int colIndex;

        public Builder() {
        }

        public Builder sheetName(String val) {
            sheetName = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder rowIndex(int val) {
            rowIndex = val;
            return this;
        }

        public Builder colIndex(int val) {
            colIndex = val;
            return this;
        }

        public DefaultWorkbookExportFactory build() {
            return new DefaultWorkbookExportFactory(this);
        }
    }
}

class T {
    public static void main(String[] args) {
        DefaultWorkbookExportFactory.Builder builder = new DefaultWorkbookExportFactory.Builder();
        builder.colIndex(0).build().exportWorkbook(null, null)
    }
}
