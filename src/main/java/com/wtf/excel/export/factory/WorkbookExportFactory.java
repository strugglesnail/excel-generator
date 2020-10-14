package com.wtf.excel.export.factory;

import com.wtf.excel.export.param.BeanParameter;
import org.apache.poi.ss.usermodel.Workbook;

public interface WorkbookExportFactory {
    Workbook createWorkbook(BeanParameter beanParameter);
    Workbook getWorkbook();
}
