package com.data.display.util;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExportExeclUtil {

    private static Logger _log = LoggerFactory.getLogger(ExportExeclUtil.class);

    public static HSSFWorkbook makeExcel(List<Object[]> dataset,String[] headers, String filename) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(filename);
        // 设置表格默认列宽度为18个字节
        sheet.setDefaultColumnWidth((short) 18);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }

        // 遍历集合数据，产生数据行
        Iterator it = dataset.iterator();
        int index = 0;
        String textValue = "";
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object[] o = (Object[]) it.next();
            for (short i = 0; i < o.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                if (null != o[i]) {//对象可能为空
                    textValue = o[i].toString();
                } else {
                    textValue = "";
                }
                cell.setCellValue(textValue);
            }
        }
        return workbook;
    }

    /**workbook
     * 数据超过65535需要分隔为多个工作薄
     *
     * @param dataset
     * @param headers
     * @param filename
     * @return
     */
    public static HSSFWorkbook makeExcelLargeData(List<Object[]> dataset,
                                                  String[] headers, String filename) {
        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //分隔工作表
        int filenums = dataset.size() / 65535 + 1;
        for (int m = 0; m < filenums; m++) {
            int ix = m * 65535;
            int len = m < filenums - 1 ? 65535 : dataset.size() - ix;
            //创建一个尺寸刚才大1的容器，防止arraylist容量大量增长
            List<Object[]> data = new ArrayList<Object[]>(len + 1);
            for (int n = 0; n < len; n++) {
                data.add(dataset.get(ix + n));
            }
            String fn = filename;
            if (m > 0) {
                fn += m;
            }

            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(fn);
            // 设置表格默认列宽度为18个字节
            sheet.setDefaultColumnWidth((short) 18);
            // 生成一个样式
            HSSFCellStyle style = workbook.createCellStyle();
            // 设置这些样式
            style.setFillForegroundColor(HSSFColor.WHITE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);
            // 生成并设置另一个样式
            HSSFCellStyle style2 = workbook.createCellStyle();
            style2.setFillForegroundColor(HSSFColor.WHITE.index);
            style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成另一个字体
            HSSFFont font2 = workbook.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style2.setFont(font2);

            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            for (short i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(headers[i]);
            }

            // 遍历集合数据，产生数据行
            Iterator it = data.iterator();
            int index = 0;
            String textValue = "";
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                Object[] o = (Object[]) it.next();
                for (short i = 0; i < o.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(style2);
                    if (null != o[i]) {//对象可能为空
                        textValue = o[i].toString();
                    } else {
                        textValue = "";
                    }
                    cell.setCellValue(textValue);
                }
            }
        }

        return workbook;
    }

    public static Workbook copyExcel(List<Object[]> dataset) {
        try {
            String filePath = File.separator+"data"+File.separator+"springboot"+File.separator+"20190918导出.xlsx";
            _log.debug("文件路径"+filePath);
            File excel = new File(filePath);
//            String filePath = "C:/Users/l/Desktop/导出模板.xlsx";
//            File excel = new File(filePath);

            //判断是否为excel类型文件
            String extString = filePath.substring(filePath.lastIndexOf("."));

            FileInputStream is = new FileInputStream(excel);
            Workbook workbook = null;
            if (".xls".equals(extString)) {
                workbook = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                workbook = new XSSFWorkbook(is);
            } else {
                workbook = null;
            }


//            POIFSFileSystem fs  =new POIFSFileSystem(is);
            Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet里的内容
            // 遍历集合数据，产生数据行
            Row row = sheet.createRow(2);
            Iterator it = dataset.iterator();
            int index = 1;
            String textValue = "";
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                Object[] o = (Object[]) it.next();
                System.out.println(JSON.toJSONString(o));
                for (short i = 0; i < o.length; i++) {
                    Cell cell = row.createCell(i);
                    if (null != o[i]) {//对象可能为空
                        textValue = o[i].toString();
                    } else {
                        textValue = "";
                    }
                    cell.setCellValue(textValue);
                }
            }
            return workbook;
        } catch (IOException e) {
            _log.error("导出错误"+e);
        }
        return null;
    }

    public static void downloadFile(Workbook workbook,HttpServletRequest request, HttpServletResponse response) {
        _log.debug("文件名称==============="+StringUtil.dataRandom());
//        workbook.
        BufferedOutputStream bos = null;
        try {
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String((StringUtil.dataRandom() + ".xlsx")
                    .getBytes("utf-8"), "ISO8859-1"));
            response.setContentType("application/octet-stream");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            workbook.write(bos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}