package com.grgbanking;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("poi")
public class PoiController {

    @RequestMapping("create")
    public void create(HttpServletResponse response){
        try {
            // 1.准备需要生成excel模板的数据
            List<ExportDefinition> edList = new ArrayList<>();
            edList.add(new ExportDefinition("建筑", "Building", "Building", "Room", "Room"));
            edList.add(new ExportDefinition("场室", "Room", "Room", "", ""));

            Workbook wb = LinkagePoiUtils.createData(edList,0,0,"终端信息", "dict_data", DictData.dict);

            // 5.保存excel到本地
            OutputStream os = response.getOutputStream();//new FileOutputStream("D:/4.xls");
            os = new FileOutputStream("my.xls");
            wb.write(os);
            os.close();
            wb.close();

            System.out.println("模板生成成功.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // 导出
    @RequestMapping(value = "/createExcelToDisk", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody
    String createExcelToDisk(HttpServletResponse response) throws IOException, IllegalAccessException, ClassNotFoundException {
        response.reset(); // 清除buffer缓存
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=contacts" + "Test" + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook = null;
        List<String> valueList = new ArrayList<>();
        valueList.add("1");
        valueList.add("2");
        valueList.add("3");
        try {
            workbook = ExcelUtils.exportContacts(valueList);
            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

}
