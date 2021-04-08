package com.wtf.excel.export.factory;

import com.wtf.excel.export.InvocableHandlerProperty;
import com.wtf.excel.export.PropertyArgumentResolverComposite;
import com.wtf.excel.export.param.BeanParameter;
import com.wtf.excel.export.param.PropertyParameter;
import com.wtf.excel.export.param.WorkbookParameter;
import com.wtf.excel.export.resolver.PropertyArgumentResolver;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author strugglesnail
 * @date 2020/10/12
 * @desc 导出工厂
 */
public abstract class AbstractWorkbookExportFactory implements WorkbookExportFactory {

    private PropertyArgumentResolverComposite propertyArgumentResolverComposite = new PropertyArgumentResolverComposite();

    private InvocableHandlerProperty handlerProperty = new InvocableHandlerProperty();

    private final Map<String, BeanParameter> cacheParameter = new ConcurrentHashMap(256);

    private Workbook workbook;

    private Sheet sheet;

    private BeanParameter beanParameter;

    private WorkbookParameter workbookParameter;

    private void initArgumentResolverComposite() {
        propertyArgumentResolverComposite.addResolvers(getDefaultPropertyArgumentResolvers());
        handlerProperty.setPropertyArgumentResolverComposite(this.propertyArgumentResolverComposite);
    }

    public void init(BeanParameter beanParameter) {
        this.workbook = createWorkbook(beanParameter);
        this.sheet = getSheet(beanParameter);
        this.beanParameter = beanParameter;
        this.workbookParameter = new WorkbookParameter(workbook, sheet, workbook.createCellStyle(), workbook.getCreationHelper(), beanParameter);

    }

    @Override
    public final Workbook createWorkbook(BeanParameter beanParameter) {
        Workbook workbook = null;
        if (beanParameter.getFormat() == null) {
            throw new IllegalArgumentException("Missing @HeaderExportExcel annotation info");
        }
        switch (beanParameter.getFormat()) {
            case HSSF:
                workbook = new HSSFWorkbook();
                break;
            case XSSF:
                workbook = new XSSFWorkbook();
                break;
            case SXSSF:
                workbook = new SXSSFWorkbook();
                break;
        }
        return workbook;
    }

    @Override
    public Workbook getWorkbook() {
        return workbook;
    }

    protected final Sheet getSheet(BeanParameter beanParameter) {
        return workbook.createSheet(beanParameter.getSheetName());
    }

    // 填充每一行
    protected <T> void setRow(List<T> rowList) {
        int index = beanParameter.getRowIndex();
        for (T target : rowList) {
            Row row = sheet.createRow(++index);
            this.setProperty(target, row);
        }
    }

    // 填充单元格
    private <T> void setProperty(T target, Row row) {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            this.createCell(new PropertyParameter<>(workbookParameter, row, field, target));
        }
    }

    // 设置标题、表头
    private void setHeader() {
        createHeader(new PropertyParameter<>(workbookParameter));
    }

    // 获取工作簿
    public <T> Workbook exportWorkbook(List<T> dataList, Class target) {
        String cacheKey = target.getSimpleName();
        BeanParameter parameter = cacheParameter.get(cacheKey);
        if (parameter == null) {
            parameter = new BeanParameter(target);
            cacheParameter.put(cacheKey, parameter);
        }
        init(parameter);
        // 设置表头
        setHeader();
        // 设置单元格
        setRow(dataList);
        return workbook;
    }



    // 创建header
    protected <T> void createHeader(PropertyParameter<T> propertyParameter) {
        handlerProperty.handlerHeader(propertyParameter);
    }

    // 创建单元格
    protected <T> void createCell(PropertyParameter<T> propertyParameter) {
        handlerProperty.handlerProperty(propertyParameter);
    }

    protected abstract List<PropertyArgumentResolver> getDefaultPropertyArgumentResolvers();
}
