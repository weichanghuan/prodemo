package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @description:(文件操作工具类)
 * @author weichanghuan
 * @date 2017年10月9日 下午10:27:47
 * @since JDK 1.6
 */
public class FileUtils {

    /**
     * 
     * getFiles:(得到文件下所有的文件名)
     * 
     * @param 文件路径
     * @return
     * @throws IOException
     * @author weichanghuan
     * @date 2017年10月9日 下午10:31:38
     */
    @SuppressWarnings("unused")
    private static String[] getFiles(String folder) throws IOException {
        File _folder = new File(folder);
        String[] filesInFolder;

        if (_folder.isDirectory()) {
            filesInFolder = _folder.list();
            return filesInFolder;
        } else {
            throw new IOException("Path is not a directory");
        }
    }

    /**
     * 
     * deleteAll:(删除文件夹下子文件夹和文件) 若fileName为空则，删除文件夹下面所有文件
     * 
     * @param file
     *            文件路径
     * @param fileName
     *            文件名
     * @author weichanghuan
     * @date 2017年10月9日 下午10:33:58
     */
    public static void deleteFiles(File file, String fileName) {
        if (!file.exists()) {
            return;
        }
        if ("".equals(fileName) || null == fileName) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
            return;
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(fileName)) {
                files[i].delete();
            }
        }

    }

    /**
     * 
     * deleteAll:(TODO 这里用一句话描述这个方法的作用)
     * 
     * @param file
     *            文件路径
     * @param fileNames
     *            批量删除文件
     * @author weichanghuan
     * @date 2017年10月9日 下午10:40:04
     */
    public static void deleteFileArrays(File file, String[] fileNames) {
        if (!file.exists()) {
            return;
        }
        if (0 == fileNames.length || null == fileNames) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
            return;
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < fileNames.length; j++) {
                if (files[i].equals(fileNames[j])) {
                    files[i].delete();
                }
            }

        }

    }

    /**
     * 
     * 删除单个文件
     * 
     * @param fileName
     *            被删除的文件名
     * @return 如果删除成功，则返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除文件 " + fileName + " 成功!");
                return true;
            } else {
                System.out.println("删除文件 " + fileName + " 失败!");
                return false;
            }
        } else {
            System.out.println(fileName + " 文件不存在!");
            return true;
        }
    }

    /**
     * 
     * 删除文件，可以删除单个文件或文件夹
     * 
     * @param fileName
     *            被删除的文件名
     * @return 如果删除成功，则返回true，否是返回false
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println(fileName + " 文件不存在!");
            return true;
        } else {
            if (file.isFile()) {
                return FileUtils.deleteFile(fileName);
            } else {
                return FileUtils.deleteDirectory(fileName);
            }
        }
    }

    /**
     * 
     * 删除目录及目录下的文件
     * 
     * @param dirName
     *            被删除的目录所在的文件路径
     * @return 如果目录删除成功，则返回true，否则返回false
     */
    public static boolean deleteDirectory(String dirName) {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            System.out.println(dirNames + " 目录不存在!");
            return true;
        }
        boolean flag = true;
        // 列出全部文件及子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtils.deleteFile(files[i].getAbsolutePath());
                // 如果删除文件失败，则退出循环
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtils.deleteDirectory(files[i].getAbsolutePath());
                // 如果删除子目录失败，则退出循环
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            System.out.println("删除目录失败!");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录 " + dirName + " 成功!");
            return true;
        } else {
            System.out.println("删除目录 " + dirName + " 失败!");
            return false;
        }

    }

    /**
     * 得到文件的大小
     * 
     * @param fileName
     * @return
     */
    public static int getFileSize(String fileName) {

        File file = new File(fileName);
        FileInputStream fis = null;
        int size = 0;
        try {
            fis = new FileInputStream(file);
            size = fis.available();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 复制单个文件
     * 
     * @param srcFile
     *            包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest
     *            目标文件目录；若文件目录不存在则自动创建 如：E:/phsftp/dest
     * @throws IOException
     */
    public static void copyFile(String srcFile, String dirDest) {
        try {
            FileInputStream in = new FileInputStream(srcFile);
            mkdir(dirDest);
            FileOutputStream out = new FileOutputStream(dirDest + "/" + new File(srcFile).getName());
            int len;
            byte buffer[] = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println("复制文件操作出错:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 复制文件夹
     * 
     * @param oldPath
     *            String 源文件夹路径 如：E:/phsftp/src
     * @param newPath
     *            String 目标文件夹路径 如：E:/phsftp/dest
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            // 如果文件夹不存在 则新建文件夹
            mkdir(newPath);
            File file = new File(oldPath);
            String[] files = file.list();
            File temp = null;
            for (int i = 0; i < files.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + files[i]);
                } else {
                    temp = new File(oldPath + File.separator + files[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] buffer = new byte[1024 * 2];
                    int len;
                    while ((len = input.read(buffer)) != -1) {
                        output.write(buffer, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制文件夹操作出错:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建目录
     * 
     * @param dir
     *            目录
     */
    public static void mkdir(String dir) {
        try {
            String dirTemp = dir;
            File dirPath = new File(dirTemp);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("创建目录操作出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 移动文件到指定目录
     * 
     * @param oldPath
     *            包含路径的文件名 如：E:/phsftp/src/ljq.txt
     * @param newPath
     *            目标文件目录 如：E:/phsftp/dest
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * 移动文件到指定目录，不会删除文件夹
     * 
     * @param oldPath
     *            源文件目录 如：E:/phsftp/src
     * @param newPath
     *            目标文件目录 如：E:/phsftp/dest
     */
    public static void moveFiles(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        FileUtils.deleteFiles(new File(oldPath), null);
    }

    /**
     * 移动文件到指定目录，会删除文件夹
     * 
     * @param oldPath
     *            源文件目录 如：E:/phsftp/src
     * @param newPath
     *            目标文件目录 如：E:/phsftp/dest
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        FileUtils.deleteFiles(new File(oldPath), null);
    }

    /**
     * 
     * renameFile:(文件重命名)
     * 
     * @param url
     * @param new_name
     * @return
     * @throws Exception
     * @author weichanghuan
     * @date 2017年10月9日 下午11:03:58
     */
    public static boolean renameFile(String url, String new_name) throws Exception {
        String old_url = url;
        old_url = old_url.replace("\\", "/");
        File old_file = new File(old_url);
        if (!old_file.exists()) {
            throw new IOException("文件重命名失败，文件（" + old_file + "）不存在");
        }
        System.out.println(old_file.exists());

        String old_name = old_file.getName();
        // 获得父路径
        String parent = old_file.getParent();
        // 重命名
        String new_url = parent + "/" + new_name;
        File new_file = new File(new_url);
        old_file.renameTo(new_file);

        System.out.println("原文件：" + old_file.getName());
        System.out.println("新文件：" + new_file.getName());
        new_name = new_file.getName();
        old_name = old_file.getName();
        if (new_name.equals(old_name)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 复制单个文件
     * 
     * @param srcFileName
     *            待复制的文件名
     * @param descFileName
     *            目标文件名
     * @param coverlay
     *            如果目标文件已存在，是否覆盖
     * @return 如果复制成功，则返回true，否则返回false
     */
    public static boolean copyFileCover(String srcFileName, String descFileName, boolean coverlay) {
        File srcFile = new File(srcFileName);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            System.out.println("复制文件失败，源文件 " + srcFileName + " 不存在!");
            return false;
        }
        // 判断源文件是否是合法的文件
        else if (!srcFile.isFile()) {
            System.out.println("复制文件失败，" + srcFileName + " 不是一个文件!");
            return false;
        }
        File descFile = new File(descFileName);
        // 判断目标文件是否存在
        if (descFile.exists()) {
            // 如果目标文件存在，并且允许覆盖
            if (coverlay) {
                System.out.println("目标文件已存在，准备删除!");
                if (!FileUtils.delFile(descFileName)) {
                    System.out.println("删除目标文件 " + descFileName + " 失败!");
                    return false;
                }
            } else {
                System.out.println("复制文件失败，目标文件 " + descFileName + " 已存在!");
                return false;
            }
        } else {
            if (!descFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建目录
                System.out.println("目标文件所在的目录不存在，创建目录!");
                // 创建目标文件所在的目录
                if (!descFile.getParentFile().mkdirs()) {
                    System.out.println("创建目标文件所在的目录失败!");
                    return false;
                }
            }
        }
        // 准备复制文件
        // 读取的位数
        int readByte = 0;
        InputStream ins = null;
        OutputStream outs = null;
        try {
            // 打开源文件
            ins = new FileInputStream(srcFile);
            // 打开目标文件的输出流
            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[1024];
            // 一次读取1024个字节，当readByte为-1时表示文件已经读取完毕
            while ((readByte = ins.read(buf)) != -1) {
                // 将读取的字节流写入到输出流
                outs.write(buf, 0, readByte);
            }
            System.out.println("复制单个文件 " + srcFileName + " 到" + descFileName + "成功!");
            return true;
        } catch (Exception e) {
            System.out.println("复制文件失败：" + e.getMessage());
            return false;
        } finally {
            // 关闭输入输出流，首先关闭输出流，然后再关闭输入流
            if (outs != null) {
                try {
                    outs.close();
                } catch (IOException oute) {
                    oute.printStackTrace();
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException ine) {
                    ine.printStackTrace();
                }
            }
        }
    }
}
