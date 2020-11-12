package com.wtf.converter;

import com.wtf.excel.export.converter.Converter;

/**
 * @author strugglesnail
 * @date 2020/11/12
 * @desc 类型转换测试
 */
public class TypeConverter implements Converter {

    @Override
    public String convertToExcelData(String original) {
        if ("0".equals(original)) {
            return "hssf";
        } else if ("1".equals(original)) {
            return "xssf";
        }
        return null;
    }
}
