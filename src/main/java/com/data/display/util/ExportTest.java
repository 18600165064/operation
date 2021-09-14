package com.data.display.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;

public class ExportTest {

    private static Logger _log = LoggerFactory.getLogger(ExportTest.class);


    /**
     * 创建excel文档，
     *
     * @param list        数据
     * @param keys        list中map的key数组集合
     * @param columnNames excel的列名
     */
    public static Workbook createWorkBook(List<LinkedHashMap<String, Object>> list, String sheetName, String[] keys, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(sheetName);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < keys.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建两种单元格格式
        CellStyle columnStyle = wb.createCellStyle();
        CellStyle valueStyle = wb.createCellStyle();

        // 创建两种字体
        Font columnFont = wb.createFont();
        Font valueFont = wb.createFont();

        setFontParameters(columnFont,true);//加粗
        setFontParameters(valueFont, false);

        setCellStyle(columnStyle, columnFont);
        setCellStyle(columnStyle, valueFont);

        setColumnName(sheet,columnNames,columnStyle);
        setColumnValue(sheet,list,keys,valueStyle);

        return wb;
    }

    // 创建字体样式
    public static void setFontParameters(Font font,boolean isBold){
        font.setFontHeightInPoints((short) 10);
        font.setColor(IndexedColors.BLACK.getIndex());
        if(isBold) {
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        }
    }

    // 设置元格的样式
    public static void setCellStyle(CellStyle cellStyle,Font font){
        cellStyle.setFont(font);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    }

    public static void setColumnName(Sheet sheet,String columnNames[],CellStyle cellStyle){
        // 创建第一行
        Row row = sheet.createRow((short) 0);
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cellStyle);
        }
    }

    private static void setColumnValue(Sheet sheet, List<LinkedHashMap<String, Object>> list, String[] keys, CellStyle valueStyle) {
        for (int i = 0; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(i + 1);
            // 在row行上创建一个方格
            for (int j = 0; j < keys.length; j++) {
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list.get(i).get(keys[j]).toString());
                cell.setCellStyle(valueStyle);
            }
        }
    }

    /**
     * 创建包含多个Sheet的excel表格
     *
     * @param list
     * @param fileName
     * @param sheetName
     * @param keys
     * @param columnNames
     * @return
     */
    public static Workbook createWorkBookForMoreSheet(List<List<LinkedHashMap<String, Object>>> list, String fileName, String sheetName[], List<String[]> keys, List<String[]> columnNames) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        for (int n = 0; n < list.size(); n++) {
            // 创建第一个sheet（页），并命名
            Sheet sheet = wb.createSheet(sheetName[n]);
            // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
            for (int i = 0; i < keys.get(n).length; i++) {
                sheet.setColumnWidth((short) i, (short) (35.7 * 150));
            }

            // 创建第一行
            Row row = sheet.createRow((short) 0);

            // 创建两种单元格格式
            CellStyle columnStyle = wb.createCellStyle();
            CellStyle valueStyle = wb.createCellStyle();

            // 创建两种字体
            Font columnFont = wb.createFont();
            Font valueFont = wb.createFont();

            setFontParameters(columnFont,true);
            setFontParameters(valueFont, false);

            setCellStyle(columnStyle, columnFont);
            setCellStyle(columnStyle, valueFont);

            setColumnNameForSheets(n, sheet, columnNames, columnStyle);
            setColumnValueForSheets(n,sheet,list,keys,valueStyle);

        }
        return wb;
    }

    private static void setColumnValueForSheets(int n, Sheet sheet, List<List<LinkedHashMap<String, Object>>> list, List<String[]> keys, CellStyle valueStyle) {
        for (int i = 0; i < list.get(n).size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(i + 1);
            // 在row行上创建一个方格
            for (int j = 0; j < keys.get(n).length; j++) {
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(n).get(i).get(keys.get(n)[j]) == null ? " " : list.get(n).get(i).get(keys.get(n)[j]).toString());
                cell.setCellStyle(valueStyle);
            }
        }
    }

    public static void setColumnNameForSheets(int n,Sheet sheet,  List<String[]> columnNames,CellStyle cellStyle){
        // 创建第一行
        Row row = sheet.createRow((short) 0);
        for (int i = 0; i < columnNames.get(n).length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames.get(n)[i]);
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * @param data        数据列表
     * @param fileName    文件名
     * @param keys        数据列表中map的key
     * @param columnNames 列名
     * @param request
     * @param response
     * @throws IOException
     */
    public static void downExcel(List<LinkedHashMap<String, Object>> data, String fileName,
                                 String[] keys, String columnNames[],
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            createWorkBook(data, fileName, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置response参数，可以打开下载页面
        setResponseParameters(response,fileName);

        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        writeWorkBook(os,bis,bos,out);
    }

    /**
     * 导出包含多个sheet的excel表格
     *
     * @param data
     * @param fileName
     * @param sheetName
     * @param keys
     * @param columnNames
     * @param response
     * @throws IOException
     */
    public static void downExcelMoreSheet(List data, String fileName, String sheetName[],
                                          List keys, List columnNames, HttpServletResponse response) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExportTest.createWorkBookForMoreSheet(data, fileName, sheetName, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置response参数，可以打开下载页面
        setResponseParameters(response,fileName);

        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        writeWorkBook(os,bis,bos,out);

    }

    private static void writeWorkBook(ByteArrayOutputStream os,BufferedInputStream bis, BufferedOutputStream bos,ServletOutputStream out) throws IOException {
        try {
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    private static void setResponseParameters(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
    }


}
