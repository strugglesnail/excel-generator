package com.wtf.excel.export.generator;

import org.apache.poi.ss.usermodel.*;

/**
 * @author strugglesnail
 * @date 2020/10/12
 * @desc 默认的样式
 */
public class DefaultStyleGenerator implements StyleGenerator {

    @Override
    public void setHeaderColor(CellStyle style) {
        style.setFillForegroundColor(IndexedColors.TAN.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }

    @Override
    public void setCellColor(CellStyle style) {

    }


}
