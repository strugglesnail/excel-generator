package com.wtf.demo;


import com.wtf.converter.TypeConverter;
import com.wtf.excel.export.annotation.HeaderExportExcel;
import com.wtf.excel.export.annotation.SXSSFExportExcel;
import com.wtf.excel.export.generator.DefaultStyleGenerator;

import java.util.Date;

@HeaderExportExcel(rowIndex = 1, colIndex = 5, title = "复杂表头", sheetName = "复杂表头", styleGenerator = DefaultStyleGenerator.class)
public class SXSSFExportExcelDemo {

    @SXSSFExportExcel(title = {"文本名称"}, offset = {"1,3,0,0"}, index = 0)
    private String name;

    @SXSSFExportExcel(title = {"综合", "sheet名称"}, offset = {"1,1,1,3", "2,3,1,1"}, index = 1)
    private String sheetName;

    @SXSSFExportExcel(title = {"综合", "文本类型"}, offset = {"1,1,1,3", "2,3,2,2"}, index = 2, converter = TypeConverter.class)
    private int type;

    @SXSSFExportExcel(title = {"综合", "时间01"}, offset = {"1,1,1,3", "2,3,3,3"}, index = 3, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

//    @SXSSFExportExcel(title = "综合", offset = "0,0,1,2", index = 1)
    private String merge0;

    public String getMerge0() {
        return merge0;
    }

    public void setMerge0(String merge0) {
        this.merge0 = merge0;
    }

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
