package com.grgbanking.utils;

/**
 * @Author: lzyang6
 * @License: (C) Copyright 2020-2020, grgbanking Corporation Limited.
 * @Contact: lzyang6@grgbanking.com
 * @Date: 2020/12/25 14:11
 * @Version: 1.0
 * @Description:
 */
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelUtils {

    private static int firstRow = 0;
    private static int lastRow = 0;


    /**
     * 导出excel工具类
     *
     * @param excelName sheet表名称
     * @param //fieldList 表头的定义集合
     * @param dataList  数据集合
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static HSSFWorkbook drawExcel(String excelName, String[] fields, List<String[]> dataList) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(excelName);
        sheet.createFreezePane(0, 1, 0, 1);
        CellRangeAddress cra = new CellRangeAddress(0, 1, 0, fields.length - 1);
        sheet.setAutoFilter(cra);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        style.setFont(font);

        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        font1.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font1.setFontHeightInPoints((short) 12);
        font1.setFontName("宋体");
        style1.setFont(font1);

        HSSFCell cell = null;
        for (int i = 0; i < fields.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(fields[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow((int) i + 1);
            String[] arrs = dataList.get(i);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < arrs.length; j++) {
                cell = row.createCell((short) j);
                cell.setCellStyle(style1);
                cell.setCellValue(arrs[j]);
            }
        }
        return wb;
    }

    public static SXSSFWorkbook drawExcel2007(String excelName, String[] fields, List<String[]> dataList) {
        // 声明一个工作薄
        SXSSFWorkbook workBook = new SXSSFWorkbook();
        // 生成一个表格
        SXSSFSheet sheet = workBook.createSheet();
        //设置单元格宽度
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 2500);
        sheet.setColumnWidth(4, 2500);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 7000);
        sheet.setColumnWidth(7, 4500);
        sheet.setColumnWidth(8, 2500);
        sheet.setColumnWidth(9, 5000);
        sheet.setColumnWidth(10, 6000);
        workBook.setSheetName(0, excelName);
        CellStyle style = workBook.createCellStyle();
        //右边框
        style.setBorderRight(BorderStyle.DASHED);
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workBook.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        style.setFont(font);
        // 创建表格标题行 第一行
        SXSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(fields[i]);
            cell.setCellStyle(style);
        }
        //插入需导出的数据
        for (int i = 0; i < dataList.size(); i++) {
            SXSSFRow row = sheet.createRow(i + 1);
            String[] arrs = dataList.get(i);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < arrs.length; j++) {
                SXSSFCell cells = row.createCell((short) j);
                //cells.setCellStyle(style);
                cells.setCellValue(arrs[j]);
            }
        }
        return workBook;
    }

    public static SXSSFWorkbook drawExcel2018(String excelName, String[] fields, List<String[]> dataList) {
        // 声明一个工作薄
        SXSSFWorkbook workBook = new SXSSFWorkbook();
        // 生成一个表格
        SXSSFSheet sheet = workBook.createSheet();
        sheet.createFreezePane(0, 1, 0, 1);
        CellRangeAddress cra = new CellRangeAddress(0, 1, 0, fields.length - 1);
        sheet.setAutoFilter(cra);
        workBook.setSheetName(0, excelName);
        CellStyle style = workBook.createCellStyle();
        Font font = workBook.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        style.setFont(font);

        // 创建表格标题行 第一行
        SXSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            titleRow.createCell(i).setCellValue(fields[i]);
        }
        //插入需导出的数据
        for (int i = 0; i < dataList.size(); i++) {
            SXSSFRow row = sheet.createRow(i + 1);
            String[] arrs = dataList.get(i);
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < arrs.length; j++) {
                SXSSFCell cells = row.createCell((short) j);
                cells.setCellStyle(style);
                //cells.setCellValue(StringUtils.isNotEmpty(arrs[j]) ? arrs[j] : "");
            }
        }
        return workBook;
    }

    /**
     * 导出excel工具类
     * 合并单元格
     *
     * @param excelName sheet表名称
     * @param //fieldList 表头的定义集合
     * @param dataList  数据集合
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static HSSFWorkbook drawExcelByCellRange(String excelName, String[] fields, List<String[]> dataList) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(excelName);
        sheet.createFreezePane(0, 1, 0, 1);
        CellRangeAddress cra = new CellRangeAddress(0, 1, 0, fields.length - 1);
        sheet.setAutoFilter(cra);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.getFontAt((short) 0);
        font.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font.setFontHeightInPoints((short) 12);//更改默认字体大小
        font.setFontName("宋体");//
        style.setFont(font);

        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        font1.setCharSet(HSSFFont.DEFAULT_CHARSET);
        font1.setFontHeightInPoints((short) 12);//更改默认字体大小
        font1.setFontName("宋体");//
        style1.setFont(font1);

        HSSFCell cell = null;
        for (int i = 0; i < fields.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(fields[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        try {
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow((int) i + 1);
                String[] arrs = dataList.get(i);
                // 第四步，创建单元格，并设置值
                for (int j = 0; j < arrs.length; j++) {
                    String arrsJ = arrs[j] == null ? "" : arrs[j];
                    String dataListArrsJ = "";
                    if (dataList.size() > i + 1) {
                        dataListArrsJ = dataList.get(i + 1)[j] == null ? "" : arrs[j];
                    } else {
                        dataListArrsJ = "";
                    }
                    cell = row.createCell((short) j);
                    cell.setCellStyle(style1);
                    cell.setCellValue(arrs[j]);
                    // 判断和下面单元格内容是否相同
                    if (dataList.size() > (i + 1) && j == 0 && dataList.get(i + 1)[0] != null && arrs[0].equals(dataList.get(i + 1)[0])) {
                        firstRow = i + 1;
                        lastRow = i + 2;
                        sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 2, j, j));//合并单元格
                    } else if (dataList.size() > (i + 1) && j != 0 && j < arrs.length - 3 && arrsJ.equals(dataListArrsJ)) {
                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, j, j));//合并单元格
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wb;
    }


    public static void downExcel(HSSFWorkbook wb, HttpServletResponse response, String excelName) {
        OutputStream os = null;
        // 输出
        response.reset();
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((excelName + ".xlsx").getBytes("UTF-8"), "ISO-8859-1"));
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName + ".xls", TygConstants.ALL_ENCODING));
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception e) {
            throw new RuntimeException("导出报表异常");
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("导出报表异常");
            }
        }
    }

}


