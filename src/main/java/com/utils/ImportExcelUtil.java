/**
 * Copyright (c) 2017, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd.
 * All right reserved.
 * <p>
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT
 * MANAGEMENT CO., LTD.  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT MANAGEMENT
 * CO., LTD.
 */
package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author weichanghuan
 * @description: Excel操作工具类
 * @reason: 解析EXCEL文件
 * @date 2017/7/25 16:22
 * @since JDK 1.6
 */
public class ImportExcelUtil {

    /**
     * readExcel:读取excel方法. <br/>
     * 当且仅当只有一个sheet， 默认从第一个sheet读取数据.<br/>
     *
     * @param file
     *            文件名
     * @return java.util.List<java.util.List<java.lang.Object>>
     * @author weichanghuan
     * @date 2017/7/25 18:25
     */
    public static List<List<Object>> readExcel(File file) throws IOException {
        List<List<Object>> list = new ArrayList<>();
        if (file.exists() && file.isFile()) {
            Workbook wb = getWorkbook(file);
            if (wb != null) {
                Sheet sheet = getSheet(wb, 0);
                list = getSheetData(wb, sheet);
            }
        }
        return list;
    }

    /**
     * readExcel:读取excel方法. <br/>
     * 当且仅当只有一个sheet， 默认从第一个sheet读取数据.<br/>
     *
     * @param filePath
     *            文件名
     * @return java.util.List<java.util.List<java.lang.Object>>
     * @author weichanghuan
     * @date 2017/7/25 18:30
     */
    public static List<List<Object>> readExcel(String filePath) throws IOException {
        File file = new File(filePath);
        return readExcel(file);
    }

    /**
     * readExcel:读取excel方法. <br/>
     * 根据sheet下标索引读取sheet数据.<br/>
     *
     * @param file
     *            文件名
     * @param sheetIndex
     *            下标索引
     * @return java.util.List<java.util.List<java.lang.Object>>
     * @author weichanghuan
     * @date 2017/7/25 18:26
     */
    public static List<List<Object>> readExcel(File file, int sheetIndex) throws IOException {
        List<List<Object>> list = new ArrayList<>();
        if (file.exists() && file.isFile()) {
            Workbook wb = getWorkbook(file);
            if (wb != null) {
                Sheet sheet = getSheet(wb, sheetIndex);
                list = getSheetData(wb, sheet);
            }
        }
        return list;
    }

    /**
     * getWorkbook:根据excel文件来获取workbook. <br/>
     *
     * @param file
     *            文件名
     * @return org.apache.poi.ss.usermodel.Workbook
     * @author weichanghuan
     * @date 2017/7/25 18:31
     */
    public static Workbook getWorkbook(File file) throws IOException {
        Workbook wb = null;
        if (file.exists() && file.isFile()) {
            InputStream inp = new FileInputStream(file);
            try {
                wb = WorkbookFactory.create(inp);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    /**
     * getSheet:根据workbook和sheet的下标索引值来获取sheet. <br/>
     *
     * @param wb
     *            表格
     * @param sheetIndex
     *            sheet的下标索引值
     * @return org.apache.poi.ss.usermodel.Sheet
     * @author weichanghuan
     * @date 2017/7/25 18:32
     */
    public static Sheet getSheet(Workbook wb, int sheetIndex) {
        return wb.getSheetAt(sheetIndex);
    }

    /**
     * getSheetData:根据sheet获取该sheet的所有数据. <br/>
     *
     * @param wb
     *            表格
     * @param sheet
     *            表格页
     * @return java.util.List<java.util.List<java.lang.Object>>
     * @author weichanghuan
     * @date 2017/7/25 18:32
     */
    public static List<List<Object>> getSheetData(Workbook wb, Sheet sheet) {
        List<List<Object>> list = new ArrayList<List<Object>>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            boolean allRowIsBlank = isBlankRow(wb, row);
            if (allRowIsBlank) { // 整行都空，就跳过
                continue;
            }
            List<Object> rowData = getRowData(wb, row);
            list.add(rowData);
        }
        return list;
    }

    /**
     * isBlankRow:判断整行是不是都为空. <br/>
     *
     * @param wb
     *            表格
     * @param row
     *            每行
     * @return boolean
     * @author weichanghuan
     * @date 2017/7/25 18:34
     */
    public static boolean isBlankRow(Workbook wb, Row row) {
        boolean allRowIsBlank = true;
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Object cellValue = getCellValue(wb, cellIterator.next());
            if (cellValue != null && !"".equals(cellValue)) {
                allRowIsBlank = false;
                break;
            }
        }
        return allRowIsBlank;
    }

    /**
     * getRowData:获取行的数据. <br/>
     *
     * @param wb
     *            表格
     * @param row
     *            每行
     * @return java.util.List<java.lang.Object>
     * @author weichanghuan
     * @date 2017/7/25 18:34
     */
    public static List<Object> getRowData(Workbook wb, Row row) {
        List<Object> rowData = new ArrayList<Object>();
        /**
         * 不建议用row.cellIterator(), 因为空列会被跳过， 后面的列会前移， 建议用for循环，
         * row.getLastCellNum()是获取最后一个不为空的列是第几个 结论：空行可以跳过， 空列最好不要跳过
         */
        /*
         * Iterator<Cell> cellIterator = row.cellIterator(); while
         * (cellIterator.hasNext()) { Cell cell = cellIterator.next(); Object
         * cellValue = getCellValue(wb, cell); rowData.add(cellValue); }
         */
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            Object cellValue = getCellValue(wb, cell);
            rowData.add(cellValue);
        }
        return rowData;
    }

    /**
     * getCellValue:获取单元格值. <br/>
     *
     * @param wb
     *            表格
     * @param cell
     *            单元格
     * @return java.lang.Object
     * @author weichanghuan
     * @date 2017/7/25 18:35
     */
    public static Object getCellValue(Workbook wb, Cell cell) {
        if (cell == null || (cell.getCellType() == Cell.CELL_TYPE_STRING && StringUtil.isBlank(cell.getStringCellValue()))) {
            return null;
        }
        /*
         * if (cell == null) { return ""; }
         */
        // 如果该单元格为数字， 则设置该单元格类型为文本格式
        /*
         * CellStyle cellStyle = wb.createCellStyle(); DataFormat dataFormat =
         * wb.createDataFormat();
         * cellStyle.setDataFormat(dataFormat.getFormat("@"));
         * cell.setCellStyle(cellStyle);
         * cell.setCellType(Cell.CELL_TYPE_STRING);
         */

        DecimalFormat df = new DecimalFormat("0");// 格式化 number String字符
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化日期字符串
        DecimalFormat nf = new DecimalFormat("0");// 格式化数字

        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_BLANK:
                // return "";
                return null;
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                    String value = df.format(cell.getNumericCellValue());
                    if (StringUtil.isBlank(value)) {
                        return null;
                    }
                    return value;
                } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    String value = nf.format(cell.getNumericCellValue());
                    if (StringUtil.isBlank(value)) {
                        return null;
                    }
                    return value;
                } else {
                    return cell.getNumericCellValue();
                    /*
                     * double doubleValue = cell.getNumericCellValue(); long
                     * longValue = (long) doubleValue; if (doubleValue -
                     * longValue > 0) { return String.valueOf(doubleValue); }
                     * else { return longValue; }
                     */
                    /*
                     * DecimalFormat df = new DecimalFormat("#"); String value =
                     * df.format(cell.getNumericCellValue()).toString(); return
                     * value;
                     */
                }
            case Cell.CELL_TYPE_STRING:
                String value = cell.getStringCellValue();
                if (StringUtil.isBlank(value)) {
                    return null;
                } else {
                    return value;
                }
                // return cell.getRichStringCellValue();
            default:
                // return "";
                return null;
        }
    }

    public static void main(String[] args) {
        List<List<Object>> list;
        try {
            list = readExcel(new File("E:/temp/upload/8fe4029e-ea6b-403c-b40a-fda6e224df8e.xlsx"), 0);
            System.out.println(list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
