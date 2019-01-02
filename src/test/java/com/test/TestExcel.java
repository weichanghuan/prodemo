package com.test;

import com.utils.ImportExcelUtil;
import com.utils.JSONUtil;

import java.util.List;

public class TestExcel {
    public static void main(String[] args) throws Exception {
        List<List<Object>> lists = ImportExcelUtil.readExcel("/Users/wch/111.xlsx");
        System.out.println(JSONUtil.toJSonString(lists));


    }
}
