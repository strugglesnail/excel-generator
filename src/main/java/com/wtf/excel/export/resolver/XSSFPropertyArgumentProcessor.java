package com.wtf.excel.export.resolver;

import com.wtf.excel.export.annotation.SXSSFExportExcel;
import com.wtf.excel.export.annotation.XSSFExportExcel;
import com.wtf.excel.export.converter.Converter;
import com.wtf.excel.export.generator.StyleGenerator;
import com.wtf.excel.export.param.PropertyParameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @auther: strugglesnail
 * @date: 2020/10/8 16:42
 * @desc: XSSF注解解析
 */
public class XSSFPropertyArgumentProcessor extends AbstractPropertyArgumentProcessor implements PropertyArgumentResolver {


    @Override
    public boolean supportsProperty(PropertyParameter parameter) {
        return parameter.hasPropertyAnnotation(XSSFExportExcel.class);
    }

    // 填充单元格
    @Override
    public Object resolverProperty(PropertyParameter parameter) {
        XSSFExportExcel annotation = (XSSFExportExcel) parameter.getPropertyAnnotation(XSSFExportExcel.class);
        if (Objects.nonNull(annotation)) {
            this.setCell(new Parameter(parameter, annotation));
        }
        return null;
    }

    @Override
    protected void setCell(ArgumentParameter argumentParameter) {
        this.setLocalCell((Parameter) argumentParameter);
    }

    // 设置标题、表头
    @Override
    protected void setHeader(PropertyParameter parameter) {
        Sheet sheet = parameter.getWorkbookParameter().getSheet();
        //设置单元格样式
        StyleGenerator styleGenerator = parameter.getWorkbookParameter().getBeanParameter().getStyleGenerator();
        Workbook workbook = parameter.getWorkbookParameter().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        styleGenerator.setHeaderBorder(cellStyle);
        styleGenerator.setHeaderColor(cellStyle);
        styleGenerator.setAlignment(cellStyle);
        styleGenerator.setHeaderFont(cellStyle, workbook.createFont());

        // rowIndex：表示指定的数据单元格所在行下标
        int rowIndex = parameter.getWorkbookParameter().getBeanParameter().getRowIndex();
        int colIndex = parameter.getWorkbookParameter().getBeanParameter().getColIndex();
        String headerTitle = parameter.getWorkbookParameter().getBeanParameter().getTitle();

        // 设置标题
        int maxIndex = this.getMaxIndex(parameter);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(rowIndex - 1, rowIndex - 1, colIndex, maxIndex + colIndex);
        sheet.addMergedRegion(cellRangeAddress);
        Row headerRow = sheet.createRow(rowIndex - 1);
        Cell headerCell = headerRow.createCell(colIndex);
        headerCell.setCellValue(headerTitle);
        styleGenerator.setMergedRegionBorder(cellStyle, cellRangeAddress, sheet, workbook);
        headerCell.setCellStyle(cellStyle);


        Field[] fields = parameter.getWorkbookParameter().getBeanParameter().getFields();
        // 指定行减一：表示表头始终在数据上面一行
        Row row = sheet.createRow(rowIndex);
        for (Field field : fields) {
            XSSFExportExcel annotation = field.getDeclaredAnnotation(XSSFExportExcel.class);
            // 设置列标题
            if (annotation != null && annotation.title().length() > 0) {
                int index = annotation.index();
                String title = annotation.title();
                Cell cell = row.createCell(index + colIndex);
                cell.setCellValue(title);
                cell.setCellStyle(cellStyle);
            }
            // 设置宽度
            if (annotation != null && annotation.width() != 0) {
                int index = annotation.index();
                int width = annotation.width();
                sheet.setColumnWidth(index + colIndex, width * 256);
            }
        }}

    // 解析表头及标题
    @Override
    public void resolverHeader (PropertyParameter parameter){
        this.setTitle(parameter);
        this.setHeader(parameter);
    }

    @Override
    public void setTitle (PropertyParameter parameter){
    }

    //设置单元格
    private void setLocalCell (Parameter parameter){
        Cell cell = parameter.getCell();
        Field field = parameter.getField();
        field.setAccessible(true);
        CellStyle style = parameter.getCellStyle();
        try {
            Object methodValue = field.get(parameter.getTarget());
            if (Objects.nonNull(methodValue)) {
                if (StringUtils.isNotBlank(parameter.getPattern())) {
                    if (methodValue instanceof Date) {
                        DataFormat dataFormat = parameter.getDateFormat();
                        style.setDataFormat(dataFormat.getFormat(parameter.getPattern()));
                        cell.setCellValue((Date) methodValue);
                    }
                } else {
                    Converter converter = parameter.getConverter();
                    String excelData = converter.convertToExcelData(methodValue.toString());
                    if (StringUtils.isNotBlank(excelData)) {
                        cell.setCellValue(excelData);
                    } else {
                        cell.setCellValue(methodValue.toString());
                    }
                }
            }
            parameter.getStyleGenerator().setCellColor(style);
            parameter.getStyleGenerator().setCellBorder(style);
            parameter.getStyleGenerator().setAlignment(style);
            parameter.getStyleGenerator().setCellFont(style, parameter.getWorkbook().createFont());
            cell.setCellStyle(style);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取最大下标
    private int getMaxIndex(PropertyParameter parameter) {
        List<Integer> indexs = new ArrayList<>();
        Field[] fields = parameter.getFields();
        for (Field field : fields) {
            XSSFExportExcel annotation = field.getDeclaredAnnotation(XSSFExportExcel.class);
            indexs.add(annotation.index());
        }
        return indexs.stream().reduce((curr, next) -> curr < next ? next : curr).get();
    }

    public static class Parameter<T> extends ArgumentParameter {
        private final int index;
        private final String title;
        private final int width;
        private final String pattern;
        private final DataFormat dateFormat;
        private final Converter converter;


        private final T target;
        private final Workbook workbook;
        private final CellStyle cellStyle;
        private final Cell cell;
        private final Field field;
        private final StyleGenerator styleGenerator;


        public Parameter(PropertyParameter<T> parameter, XSSFExportExcel annotation) {

            this.index = annotation.index();
            this.title = annotation.title();
            this.width = annotation.width();
            this.pattern = annotation.pattern();
            this.dateFormat = parameter.getWorkbook().createDataFormat();
            this.converter = getConverter(annotation);

            int colIndex = parameter.getWorkbookParameter().getBeanParameter().getColIndex();
            this.target = parameter.getTarget();
            this.cell = parameter.getRow().createCell(annotation.index() + colIndex);
            this.field = parameter.getField();
            this.workbook = parameter.getWorkbook();
            this.cellStyle = parameter.getWorkbookParameter().getCellStyle();
            this.styleGenerator = parameter.getWorkbookParameter().getBeanParameter().getStyleGenerator();
        }

        public int getIndex() {
            return index;
        }

        public String getTitle() {
            return title;
        }


        public int getWidth() {
            return width;
        }

        public String getPattern() {
            return pattern;
        }

        public DataFormat getDateFormat() {
            return dateFormat;
        }

        public Field getField() {
            return field;
        }

        public T getTarget() {
            return target;
        }

        public Cell getCell() {
            return cell;
        }

        public CellStyle getCellStyle() {
            return cellStyle;
        }

        public StyleGenerator getStyleGenerator() {
            return styleGenerator;
        }

        public Workbook getWorkbook() {
            return workbook;
        }

        public Converter getConverter() {
            return converter;
        }

        private Converter getConverter(XSSFExportExcel xssfExportExcel) {
            Converter converter = null;
            try {
                converter = xssfExportExcel.converter().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return converter;
        }

    }

}
