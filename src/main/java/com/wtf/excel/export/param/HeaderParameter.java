package com.wtf.excel.export.param;

import com.wtf.excel.export.annotation.ExcelFormat;
import com.wtf.excel.export.generator.DefaultStyleGenerator;
import com.wtf.excel.export.generator.StyleGenerator;

/**
 * @auther strugglesnail
 * @date 2021/4/8 21:53
 * @desc
 */
public class HeaderParameter {
    // sheet名称
    private String sheetName = "file export";
    // sheet下标
    private int sheetIndex;
    // 标题
    private String title = "";
    // 行的起始位置
    private int rowIndex;
    // 列的起始位置
    private int colIndex;

    private Class<? extends StyleGenerator> styleGenerator = DefaultStyleGenerator.class;

    private ExcelFormat format = ExcelFormat.HSSF;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public ExcelFormat getFormat() {
        return format;
    }

    public void setFormat(ExcelFormat format) {
        this.format = format;
    }

    public Class<? extends StyleGenerator> getStyleGenerator() {
        return styleGenerator;
    }

    public void setStyleGenerator(Class<? extends StyleGenerator> styleGenerator) {
        this.styleGenerator = styleGenerator;
    }

    @Override
    public String toString() {
        return "HeaderParameter{" +
                "sheetName='" + sheetName + '\'' +
                ", sheetIndex=" + sheetIndex +
                '}';
    }
}
