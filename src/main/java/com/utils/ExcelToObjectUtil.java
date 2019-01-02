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

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author weichanghuan
 * @description: Excel转换成对象工具类
 * @reason: Excel数据解析成对象
 * @date 2017/7/26 8:13
 * @since JDK 1.6
 */
public class ExcelToObjectUtil {

    /**
     * Excel表中属性集合
     */
    private static List<String> fieldList = new ArrayList<>();

    /**
     * Excel表中属性和对象的关系映射
     */
    private static Map<String, String> fieldMapped = new HashMap<>();

    private static final String LANG_STRING = "java.lang.String";
    private static final String LANG_INTEGER = "java.lang.Integer";
    private static final String LANG_DOUBLE = "java.lang.Double";
    private static final String LANG_SHORT = "java.lang.Short";
    private static final String LANG_LONG = "java.lang.Long";
    private static final String LANG_FLOAT = "java.lang.Float";
    private static final String LANG_BOOLEAN = "java.lang.Boolean";

    /**
     * excelToObj:excel数据转集合对象. <br/>
     * 判断excel文件版本不同流程
     *
     * @param filePath   文件路径
     * @param classe     反射对象
     * @param mapped     头信息集合
     * @param headRowNum 头信息行数
     * @retu rn java.util.List<T>
     * @author bin.li
     * @date 2017/8/8 18:22
     */
    public static <T> List<T> excelToObj(String filePath, Class<T> classe, Map<String, String> mapped, int headRowNum) throws Exception {
        Workbook wb = null;
        InputStream inp = new FileInputStream(filePath);
        try {
            wb = WorkbookFactory.create(inp);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return xlsToObj(wb, classe, mapped, headRowNum);
    }

    /**
     * xlsToObj:excel对象转对象. <br/>
     * 支持不同版本的excel格式.<br/>
     *
     * @param wb         excel工作区对象
     * @param classe     要解析成的对象
     * @param mapped     对象属性和excle表中的字段的映射关系 key为Excel表中的字段，value为classe中对应的属性名称
     * @param headRowNum 头信息行数
     * @return java.util.List<T>
     * @author bin.li
     * @date 2017/9/22 13:17
     */
    public static <T> List<T> xlsToObj(Workbook wb, Class<T> classe, Map<String, String> mapped, int headRowNum) throws Exception {
        return excelToObj(wb, classe, mapped, headRowNum);
    }

    /**
     * excelToObj:解析excel中的字段成对象. <br/>
     *
     * @param workbook   excel对象
     * @param classe     要解析成的对象
     * @param mapped     对象属性和excle表中的字段的映射关系 key为Excel表中的字段，value为classe中对应的属性名称
     * @param headRowNum 头信息行数
     * @return java.util.List<T> 泛型
     * @author bin.li
     * @date 2017/7/26 8:40
     */
    private static <T> List<T> excelToObj(Workbook workbook, Class<T> classe, Map<String, String> mapped, int headRowNum) throws Exception {
        // 创建对象集合
        List<T> list = new ArrayList<T>();
        // 循环所有表格生成对象
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            // 生成Excel表中的属性字段和对象属性的映射关系
            createFieldMapped(sheet, mapped, headRowNum);
            // 生成对象，并读取Excel表中的字段给对象设置相应属性，并添加到list中
            createObjs(list, sheet, classe, headRowNum);
        }
        fieldList = new ArrayList<String>();
        fieldMapped = new HashMap<String, String>();
        return list;
    }

    /**
     * createObjs:生成Excel表中的属性字段和对象属性的映射关系. <br/>
     *
     * @param list   excel数据集合
     * @param sheet  excel表格
     * @param classe 要解析成的对象
     * @return void
     * @author bin.li
     * @date 2017/7/26 8:43
     */
    private static <T> void createObjs(List<T> list, Sheet sheet, Class<T> classe, int headRowNum) throws Exception {
        // 第0行默认为对象属性，从第1行读取字段作为对象的属性
        for (int rowNum = headRowNum; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            // 创建一个对象
            T obj = classe.newInstance();
            list.add(obj);
            for (int i = 0; i < fieldList.size(); i++) {
                // 获取该列属性在对象中对应的属性
                String key = fieldList.get(i);
                key = fieldMapped.get(key);
                // excle表中的行
                Cell cell = row.getCell(i);
                // 设置对象属性值
                setObjField(obj, classe, key, cell);
            }
        }
    }

    /**
     * createFieldMapped:生成对象，并读取Excel表中的字段给对象设置相应属性，并添加到list中. <br/>
     *
     * @param sheet      excel表格
     * @param mapped     对象属性和excle表中的字段的映射关系
     * @param headRowNum 头信息行数
     * @return void
     * @author bin.li
     * @date 2017/7/26 8:45
     */
    private static void createFieldMapped(Sheet sheet, Map<String, String> mapped, int headRowNum) {
        for (int rowNum = 0; rowNum < headRowNum; rowNum++) {// 头两行都是头信息的情况
            Row fieldsRow = sheet.getRow(rowNum);
            if (fieldsRow == null) {
                continue;
            }
            // 判断是否存在映射关系，没有则默认使用表格中第0行作为对象的属性名
            boolean isMapping = (mapped != null && !mapped.isEmpty());
            for (short fieldIndex = fieldsRow.getFirstCellNum(); fieldIndex < fieldsRow.getLastCellNum(); fieldIndex++) {

                Cell cell = fieldsRow.getCell(fieldIndex);
                String cellFiedl = getCellValue(cell);
                if (StringUtil.isNotEmpty(cellFiedl)) {
                    // TODO 暂时特殊处理鹏扬指令文件
                    if (headRowNum == 2 && ("指令信息".equals(cellFiedl) || "成交信息".equals(cellFiedl))) {
                        continue;
                    }
                    if (headRowNum == 2 && "指令数量".equals(cellFiedl)) {
                        fieldList.add(6, cellFiedl);
                    } else if (headRowNum == 2 && "指令价格".equals(cellFiedl)) {
                        fieldList.add(7, cellFiedl);
                    } else if (headRowNum == 2 && "指令金额".equals(cellFiedl)) {
                        fieldList.add(8, cellFiedl);
                    } else if (headRowNum == 2 && "价格偏差(%)".equals(cellFiedl)) {
                        fieldList.add(9, cellFiedl);
                    } else if (headRowNum == 2 && "成交数量".equals(cellFiedl)) {
                        fieldList.add(11, cellFiedl);
                    } else if (headRowNum == 2 && "成交金额".equals(cellFiedl)) {
                        fieldList.add(12, cellFiedl);
                    } else if (headRowNum == 2 && "当日均价".equals(cellFiedl)) {
                        fieldList.add(13, cellFiedl);
                    } else if (headRowNum == 2 && "成交比例(%)".equals(cellFiedl)) {
                        fieldList.add(14, cellFiedl);
                    } else if (headRowNum == 2 && "未成交数量".equals(cellFiedl)) {
                        fieldList.add(15, cellFiedl);
                    } else if (headRowNum == 2 && "未成交金额".equals(cellFiedl)) {
                        fieldList.add(16, cellFiedl);
                    } else {
                        fieldList.add(cellFiedl);
                    }
                }
                // 处理对象属性和exle的映射
                if (isMapping) {
                    String value = mapped.get(cellFiedl);

                    if (value != null && !"".equals(value)) {
                        fieldMapped.put(cellFiedl, value);
                    } else {
                        // fieldMapped.put(cellFiedl, cellFiedl);
                    }

                } else {
                    // fieldMapped.put(cellFiedl, cellFiedl);
                }
            }
        }
        // //拿到第0行，每列默认为对象属性名
        // Row fieldsRow = sheet.getRow(sheet.getFirstRowNum());
        // if (fieldsRow == null) {
        // return;
        // }
    }

    /**
     * setObjField:根据映射关系，给属性设置值. <br/>
     *
     * @param obj    对象
     * @param classe 要解析成的对象
     * @param key    对应属性key
     * @param cell   单元格
     * @return void
     * @author bin.li
     * @date 2017/7/26 8:47
     */
    private static void setObjField(Object obj, Class classe, String key, Cell cell) throws IllegalAccessException {
        // 获取要设置的属性
        Field field = null;
        Field[] fields = classe.getDeclaredFields();
        for (Field f : fields) {
            if (f.getName().equals(key)) {
                field = f;
                break;
            }
        }
        if (field == null) {
            return;
        }
        // 判断field类型
        Object value = convertValue(field, cell);
        // 设置属性
        field.setAccessible(true);
        field.set(obj, value);
    }

    /**
     * convertValue:把cell的value转换成和对象一样的属性类型. <br/>
     *
     * @param field 字段属性
     * @param cell  单元格
     * @return java.lang.Object
     * @author bin.li
     * @date 2017/7/26 8:48
     */
    private static Object convertValue(Field field, Cell cell) {
        String type = field.getType().getName();
        // String cellValue = getCellValue(cell);

        if (LANG_STRING.equals(type)) {
            return getCellValue(cell);
        }
        if ("int".equals(type) || LANG_INTEGER.equals(type)) {
            Integer integer = 0;
            try {
                String cellValue = getCellValue(cell);
                double dValue = Double.valueOf(cellValue);
                if (dValue % 1 == 0) {
                    integer = Integer.valueOf((int) dValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            return integer;
        }
        if ("double".equals(type) || LANG_DOUBLE.equals(type)) {
            Double aDouble = 0.0;
            try {
                String cellValue = getCellValue(cell);
                if ("-".equals(cellValue) || "N/A".equals(cellValue) || StringUtil.isEmpty(cellValue)) {
                    cellValue = "0";
                }
                cellValue = cellValue.replace(",", "");

                aDouble = Double.valueOf(cellValue);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return aDouble;
        }
        if ("short".equals(type) || LANG_SHORT.equals(type)) {
            Short value = 0;
            try {
                String cellValue = getCellValue(cell);
                double dValue = Double.valueOf(cellValue);
                if (dValue % 1 == 0) {
                    value = Short.valueOf((short) dValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return value;
        }
        if ("long".equals(type) || LANG_LONG.equals(type)) {
            Long value = 0L;
            try {
                String cellValue = getCellValue(cell);
                if ("-".equals(cellValue) || "N/A".equals(cellValue) || StringUtil.isEmpty(cellValue)) {
                    cellValue = "0";
                }
                cellValue = cellValue.replace(",", "");
                double dValue = Double.valueOf(cellValue);
                if (dValue % 1 == 0) {
                    value = Long.valueOf((long) dValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return value;
        }
        if ("float".equals(type) || LANG_FLOAT.equals(type)) {
            Float value = 0F;
            try {
                value = Float.valueOf(getCellValue(cell));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return value;
        }
        if ("boolean".equals(type) || LANG_BOOLEAN.equals(type)) {
            Boolean value = false;
            try {
                value = Boolean.valueOf(getCellValue(cell));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return value;
        }
        if ("char".equals(type)) {
            char value = 0;
            try {
                String sValue = String.valueOf(getCellValue(cell));
                if (sValue.length() > 0) {
                    value = sValue.charAt(0);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return value;
        }
        return null;
    }

    /**
     * getCellValue:从cell中获取Str值. <br/>
     *
     * @param cell 单元格
     * @return java.lang.String
     * @author bin.li
     * @date 2017/7/26 8:49
     */
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }

        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }

        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        }

        return "";
    }

    public static void main(String[] args) {
        String filePath1 = "E:/temp/upload/8fe4029e-ea6b-403c-b40a-fda6e224df8e.xlsx";
        HashMap<String, String> map = new HashMap<>();
        map.put("交易通道", "tradeChannel");
        map.put("指令序号", "instructionSerialNo");
        map.put("产品代码", "productCode");
        map.put("产品名称", "productName");
        map.put("委托方向", "consignation");
        map.put("基金代码", "fundCode");
        map.put("基金简称", "fundName");
        map.put("指令金额", "instructionAmount");
        map.put("指令数量", "instructionNum");
        map.put("巨额赎回", "largeRedemptionFlag");
        map.put("分红方式", "dividendMethod");
        map.put("转入证券代码", "intoFundCode");
        map.put("转入证券名称", "intoFundName");
        map.put("外部撤销处理状态", "outsideCancelHandleStatus");
        map.put("指令状态", "instructionStatus");
        map.put("指令下达人", "instructionIssuer");
        map.put("下达日期", "issuerDate");
        map.put("下达时间", "issuerTime");
        map.put("业务类型", "businessType");
        map.put("投资渠道", "distributorName");
        map.put("指令修改序号", "instructionUpdateSerialNo");
        map.put("基金序号", "fundNo");
        map.put("组合名称", "groupName");
        map.put("证券代码", "securitiesCode");
        map.put("证券名称", "securitiesName");
        map.put("转入组合", "intoGroup");
        map.put("基金管理人", "fundManager");
        map.put("持仓数量", "positionNum");
        map.put("转入证券持仓数量", "intoSecuritiesPosition");
        map.put("申请传真编号", "applyFaxNo");
        map.put("申请传真打印次数", "applyFaxPrintTimes");
        map.put("撤销传真编号", "cancelFaxNo");
        map.put("撤销传真打印次数", "cancelFaxPrintTimes");
        map.put("交易市场", "tradeMarket");
        map.put("风险国家/地区", "riskPlace");
        map.put("投资类型", "investmentType");
        map.put("转入投资类型", "intoInvestmentType");
        map.put("指令操作级别", "instructionOperationLevel");
        map.put("执行状态", "executeStatus");
        map.put("交易说明", "tradeDescription");
        map.put("起始时间", "startTime");
        map.put("终止时间", "endTime");
        map.put("分发时间", "distributeTime");
        map.put("到期日期", "expireDate");
        map.put("订单状态", "orderStatus");
        map.put("错误信息", "errorMessage");
        map.put("撤销错误信息", "cancelErrorMessage");
        map.put("指令备注", "instructionRemark");
        map.put("ric", "ric");
        map.put("bloomberg", "bloomberg");
        map.put("sedol", "sedol");
        map.put("cusip", "cusip");
        map.put("isin", "isin");

        try {
            List<ExcelDataRecord> excelDataRecordList = ExcelToObjectUtil.excelToObj(filePath1, ExcelDataRecord.class, map, 1);
            for (ExcelDataRecord excelDataRecord : excelDataRecordList) {
                System.out.println(excelDataRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
