package com.data.display.controller.orderController;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.Order;
import com.data.display.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.model.order.ExpressCompany;
import com.data.display.service.orderService.ExpressCompanyService;


@Controller
@RequestMapping("/expressCompany")
public class ExpressCompanyController {

    private static Logger _log = LoggerFactory.getLogger(ExpressCompanyController.class);

    @Autowired
	private ExpressCompanyService expressCompanyService;
	
	
	@RequestMapping("/getData")
    @ResponseBody
    public String getData(ExpressCompany  expressCompany) {
        List<ExpressCompany> list = expressCompanyService.getData(expressCompany);
        return JSON.toJSONString(list);
    }


    private DataTableResult excel2db() throws Exception {
	    String fullPath = "C:\\Users\\l\\Desktop\\100.xlsx";
        _log.debug("路径================="+fullPath);
        DataTableResult result = new DataTableResult();
        result.setError("导入成功");
        result.setDraw(1);
//        URL url = new URL(fullPath);
//        URLConnection connection = url.openConnection();
//        connection.setDoOutput(true);
        //判断是否为excel类型文件
        String extString = fullPath.substring(fullPath.lastIndexOf("."));
        File excel = new File(fullPath);
        InputStream is = new FileInputStream(excel);
//        InputStream is = null;
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        Row rowHeader = null;
                try {
//            is = connection.getInputStream();
                    if (".xls".equals(extString)) {
                        wb = new HSSFWorkbook(is);
                    } else if (".xlsx".equals(extString)) {
                        wb = new XSSFWorkbook(is);
                    } else {
                        wb = null;
                    }
                    if (wb != null) {
                        // 获取第一个sheet
                        sheet = wb.getSheetAt(0);
                        // 获取最大行数
                        int rownum = sheet.getPhysicalNumberOfRows();
                        // 获取第一行
                        rowHeader = sheet.getRow(3);
                        row = sheet.getRow(3);
                        // 获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                for (int i = 2; i < rownum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                            ExpressCompany expressCompany = new ExpressCompany();
                            expressCompany.setExpress_name(String.valueOf(row.getCell(0)));
                            expressCompany.setCompany_code(String.valueOf(row.getCell(1)));
                            expressCompany.setCountry_code(String.valueOf(row.getCell(1)));
                            expressCompany.setCreate_time(new Date());
                            expressCompanyService.addExpressCompany(expressCompany);
                    } else {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result.setError("导入失败");
        } catch (IOException e) {
            e.printStackTrace();
            result.setError("导入失败");
        }
        return result;
    }


}
