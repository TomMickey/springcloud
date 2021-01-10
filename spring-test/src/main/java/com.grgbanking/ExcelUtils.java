package com.grgbanking;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static XSSFWorkbook exportContacts(List<String> valueList)
            throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        XSSFWorkbook xssfWorkbook = null;
        String sheetName = "客户列表";
        xssfWorkbook = createExcelFile(valueList, sheetName);
        return xssfWorkbook;
    }

    public static XSSFWorkbook createExcelFile(List<String> valueList, String sheetName)
            throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        // 创建新的Excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell = row.createCell(1);
        cell.setCellValue("年龄");
        for (int i = 0; i < valueList.size(); i++) {
            row = sheet.createRow((int) i + 1);
            row.createCell(i).setCellValue(valueList.get(i));
        }
        return workbook;
    }

}
