package com.grgbanking.controller;

import com.grgbanking.utils.ExcelUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lzyang6
 * @License: (C) Copyright 2020-2020, grgbanking Corporation Limited.
 * @Contact: lzyang6@grgbanking.com
 * @Date: 2020/12/25 14:09
 * @Version: 1.0
 * @Description:
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        String[] fields = {"序号", "冠字号码", "钞票类别", "币种", "面额", "设备编号", "机具编号", "机构代码", "版别", "业务类型", "交易时间"};
        String excelName = "冠字号码详情_" + ".xls";
        List<String[]> dataList = new ArrayList<>();
        String[] first = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        dataList.add(first);
        SXSSFWorkbook wb = ExcelUtils.drawExcel2007(excelName, fields, dataList);
        OutputStream os = null;
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String((excelName+".xls").getBytes("UTF-8"),"ISO-8859-1"));
        os = response.getOutputStream();
        wb.write(os);
        wb.close();
    }
}
