package com.wtf;

import com.wtf.excel.export.factory.DefaultWorkbookExportFactory;
import com.wtf.excel.export.test.HSSFExportExcelDemo;
import com.wtf.excel.export.test.SXSSFExportExcelDemo;
import com.wtf.excel.export.test.XSSFExportExcelDemo;
import com.wtf.excel.imp.factory.WorkbookImportFactoryBuilder;
import com.wtf.excel.imp.test.ImportDemo;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelTest {

    // Excel导出
    @Test
    public void testHSSF() throws IOException {
        List<HSSFExportExcelDemo> demos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HSSFExportExcelDemo excelDemo = new HSSFExportExcelDemo();
            excelDemo.setName("文件导出" + i);
            excelDemo.setSheetName("sheet名称" + i);
            excelDemo.setType("excel类型" + i);
            excelDemo.setDate(new Date());
            demos.add(excelDemo);
        }
        getTestExcel(demos, HSSFExportExcelDemo.class);
    }

    // Excel导出
    @Test
    public void testXSSF() throws IOException {
        List<XSSFExportExcelDemo> demos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            XSSFExportExcelDemo excelDemo = new XSSFExportExcelDemo();
            excelDemo.setName("文件导出" + i);
            excelDemo.setSheetName("sheet名称" + i);
            excelDemo.setType("excel类型" + i);
            excelDemo.setDate(new Date());
            demos.add(excelDemo);
        }
        getTestExcel(demos, XSSFExportExcelDemo.class);
    }

    // Excel导出
    @Test
    public void testSXSSF() throws IOException {
        List<SXSSFExportExcelDemo> demos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SXSSFExportExcelDemo excelDemo = new SXSSFExportExcelDemo();
            excelDemo.setName("文件导出" + i);
            excelDemo.setSheetName("sheet名称" + i);
            excelDemo.setType("excel类型" + i);
            excelDemo.setDate(new Date());
            demos.add(excelDemo);
        }
        getTestExcel(demos, SXSSFExportExcelDemo.class);
    }

    // Excel导入
    @Test
    public void testImport() throws FileNotFoundException {
        InputStream stream = new FileInputStream(new File("C:\\Users\\86188\\Desktop\\ExcelDemo.xlsx"));
        WorkbookImportFactoryBuilder<ImportDemo> builder = new WorkbookImportFactoryBuilder.Builder()
                .stream(stream)
                .target(ImportDemo.class)
                .build();
//        List<ImportDemo> importDemos = builder.get();
        List<ImportDemo> importDemos = builder.get((demo) -> {
            demo.setDate(new Date());
        });
        System.out.println(importDemos);
    }


    private void getTestExcel(List<?> demos, Class c) throws IOException {
        DefaultWorkbookExportFactory factory = new DefaultWorkbookExportFactory();
        Workbook workbook = factory.exportWorkbook(demos, c);
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\86188\\Desktop\\文件导出模板.xlsx");
        workbook.write(outputStream);
        outputStream.close();
    }
}
