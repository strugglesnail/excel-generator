package com.wtf.excel;

import com.wtf.excel.export.factory.DefaultWorkbookExportFactory;
import com.wtf.excel.export.test.XSSFExportExcelDemo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wk")
public class WorkbookController {

    @Autowired
    private DefaultWorkbookExportFactory factory;

    @GetMapping("/export")
    public String export() throws IOException {
        List<XSSFExportExcelDemo> demos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            XSSFExportExcelDemo excelDemo = new XSSFExportExcelDemo();
            excelDemo.setName("文件导出" + i);
            excelDemo.setSheetName("sheet名称" + i);
            excelDemo.setType("excel类型" + i);
            excelDemo.setDate(new Date());
            demos.add(excelDemo);
        }
        Workbook workbook = factory.exportWorkbook(demos, XSSFExportExcelDemo.class);
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\user\\Desktop\\文件导出模板.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        return "success";
    }
}
