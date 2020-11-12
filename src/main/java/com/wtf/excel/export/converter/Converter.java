package com.wtf.excel.export.converter;

/**
 * @author strugglesnail
 * @date 2020/11/12 8:48
 * @desc
 */
public interface Converter {

    // 转换Excel类型数据
    default String convertToExcelData(String original) { return null; };
}
