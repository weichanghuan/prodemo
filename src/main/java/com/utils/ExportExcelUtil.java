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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author 危常焕
 * @description: 导出excel工具类
 * @reason: 加载数据，生成excel文件，提供下载
 * @date 2017/9/11 19:33
 * @since JDK 1.7
 */
public class ExportExcelUtil {

    /**
     * downloadExcel:下载excel. <br/>
     *
     * @param response 请求返回
     * @param content  数据内容
     * @param title    文件头标题
     * @param fileName 文件名称
     * @return void
     * @author bin.li
     * @date 2017/9/11 19:59
     */
    public static void downloadExcel(HttpServletResponse response, List<LinkedHashMap<String, Object>> content, String[] title, String fileName) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            if (content.size() > 0) {
                createWorkBook(content, title).write(os);
            }
            exportExcel(response, os, fileName);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * createWorkBook:创建excel工作区. <br/>
     *
     * @param content 数据内容
     * @param title   头信息
     * @return org.apache.poi.ss.usermodel.Workbook
     * @author bin.li
     * @date 2017/9/11 20:02
     */
    private static Workbook createWorkBook(List<LinkedHashMap<String, Object>> content, String title[]) {
        // 获取key值
        Object[] objs = content.get(0).keySet().toArray();
        String[] keys = Arrays.asList(objs).toArray(new String[0]);

        // 创建excel工作簿
        Workbook workbook = new HSSFWorkbook();

        // 创建第一个sheet（页），并命名
        Sheet sheet = workbook.createSheet("Sheet1");
        // 定义样式并设置
        CellStyle cs = workbook.createCellStyle();
        CellStyle cs2 = workbook.createCellStyle();
        setStyle(keys, workbook, sheet, cs, cs2);
        // 生成表格
        generateTable(content, title, keys, workbook, sheet, cs, cs2, 0);
        setAutoWith(keys, sheet);
        return workbook;
    }

    /**
     * exportExcel:导出excel. <br/>
     *
     * @param response 返回结果
     * @param os       输出流
     * @param fileName 文件名称
     * @return void
     * @author bin.li
     * @date 2017/9/11 20:03
     */
    private static void exportExcel(HttpServletResponse response, ByteArrayOutputStream os, String fileName) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * setStyle:设置单元格字体大小和样式. <br/>
     *
     * @param keys  单元格数据
     * @param wb    excel工作区
     * @param sheet excel表单
     * @param cs    单元格样式1
     * @param cs2   单元格样式2
     * @return void
     * @author bin.li
     * @date 2017/9/11 20:04
     */
    private static void setStyle(String[] keys, Workbook wb, Sheet sheet, CellStyle cs, CellStyle cs2) {
        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();
        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 12);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 11);
        // f2.setColor(IndexedColors.BLACK.getIndex());
        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
    }

    /**
     * generateTable:生成表格. <br/>
     *
     * @param content 数据内容
     * @param title   头信息
     * @param keys    数据
     * @param wb      工作区
     * @param sheet   单元
     * @param cs      单元格1
     * @param cs2     单元格2
     * @param start   从第几行开始
     * @return void
     * @author bin.li
     * @date 2017/9/11 20:06
     */
    private static void generateTable(List<LinkedHashMap<String, Object>> content, String[] title, String[] keys, Workbook wb, Sheet sheet, CellStyle cs,
                                      CellStyle cs2, int start) {
        // 创建第一行
        Row row = sheet.createRow(start);
        // 设置列名
        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cs);
        }
        // 设置每行每列的值
        for (short offset = 0; offset < content.size(); offset++) {
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(start + offset + 1);
            // 在row行上创建一个方格
            for (short j = 0; j < keys.length; j++) {
                Cell cell = row1.createCell(j);
                cell.setCellValue(content.get(offset).get(keys[j]) == null ? " " : content.get(offset).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
    }

    /**
     * setAutoWith:设置单元格宽度. <br/>
     *
     * @param keys  数据行数
     * @param sheet 表单
     * @return void
     * @author bin.li
     * @date 2017/9/11 20:07
     */
    private static void setAutoWith(String[] keys, Sheet sheet) {
        for (int i = 0; i < keys.length; i++) {
            // 自适应宽度
            sheet.autoSizeColumn(i);
        }
    }

}
