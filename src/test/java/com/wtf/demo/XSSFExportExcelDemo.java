package com.wtf.demo;


import com.wtf.converter.TypeConverter;
import com.wtf.excel.export.annotation.HeaderExportExcel;
import com.wtf.excel.export.annotation.XSSFExportExcel;

import java.util.Date;

@HeaderExportExcel(title = "XSSF")
public class XSSFExportExcelDemo {

    @XSSFExportExcel(title = "文本名称", index = 0)
    private String name;

    @XSSFExportExcel(title = "sheet名称", index = 1)
    private String sheetName;

    @XSSFExportExcel(title = "文本类型", index = 2, converter = TypeConverter.class)
    private int type;

    @XSSFExportExcel(title = "时间", index = 3, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExcelDemo{" +
                "name='" + name + '\'' +
                ", sheetName='" + sheetName + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }
}
