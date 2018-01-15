package com.test;

import com.utils.ImportExcelUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestDemo5 {
    public static void main(String[] args) {
        List<List<Object>> readExcel = null;
        try {
            readExcel = ImportExcelUtil.readExcel(new File("E:/1111.xlsx"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(readExcel);
    }

}
