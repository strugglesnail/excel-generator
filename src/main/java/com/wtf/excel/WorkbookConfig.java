package com.wtf.excel;

import com.wtf.excel.export.factory.DefaultWorkbookExportFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkbookConfig {

    @Bean
    public DefaultWorkbookExportFactory create() {
        return new DefaultWorkbookExportFactory();
    }
}
