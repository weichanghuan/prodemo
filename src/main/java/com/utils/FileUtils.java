package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @description:(�ļ�����������)
 * @author weichanghuan
 * @date 2017��10��9�� ����10:27:47
 * @since JDK 1.6
 */
public class FileUtils {

    /**
     * 
     * getFiles:(�õ��ļ������е��ļ���)
     * 
     * @param �ļ�·��
     * @return
     * @throws IOException
     * @author weichanghuan
     * @date 2017��10��9�� ����10:31:38
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
     * deleteAll:(ɾ���ļ��������ļ��к��ļ�) ��fileNameΪ����ɾ���ļ������������ļ�
     * 
     * @param file
     *            �ļ�·��
     * @param fileName
     *            �ļ���
     * @author weichanghuan
     * @date 2017��10��9�� ����10:33:58
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
     * deleteAll:(TODO ������һ�仰�����������������)
     * 
     * @param file
     *            �ļ�·��
     * @param fileNames
     *            ����ɾ���ļ�
     * @author weichanghuan
     * @date 2017��10��9�� ����10:40:04
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
     * ɾ�������ļ�
     * 
     * @param fileName
     *            ��ɾ�����ļ���
     * @return ���ɾ���ɹ����򷵻�true�����򷵻�false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("ɾ���ļ� " + fileName + " �ɹ�!");
                return true;
            } else {
                System.out.println("ɾ���ļ� " + fileName + " ʧ��!");
                return false;
            }
        } else {
            System.out.println(fileName + " �ļ�������!");
            return true;
        }
    }

    /**
     * 
     * ɾ���ļ�������ɾ�������ļ����ļ���
     * 
     * @param fileName
     *            ��ɾ�����ļ���
     * @return ���ɾ���ɹ����򷵻�true�����Ƿ���false
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println(fileName + " �ļ�������!");
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
     * ɾ��Ŀ¼��Ŀ¼�µ��ļ�
     * 
     * @param dirName
     *            ��ɾ����Ŀ¼���ڵ��ļ�·��
     * @return ���Ŀ¼ɾ���ɹ����򷵻�true�����򷵻�false
     */
    public static boolean deleteDirectory(String dirName) {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            System.out.println(dirNames + " Ŀ¼������!");
            return true;
        }
        boolean flag = true;
        // �г�ȫ���ļ�����Ŀ¼
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // ɾ�����ļ�
            if (files[i].isFile()) {
                flag = FileUtils.deleteFile(files[i].getAbsolutePath());
                // ���ɾ���ļ�ʧ�ܣ����˳�ѭ��
                if (!flag) {
                    break;
                }
            }
            // ɾ����Ŀ¼
            else if (files[i].isDirectory()) {
                flag = FileUtils.deleteDirectory(files[i].getAbsolutePath());
                // ���ɾ����Ŀ¼ʧ�ܣ����˳�ѭ��
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            System.out.println("ɾ��Ŀ¼ʧ��!");
            return false;
        }
        // ɾ����ǰĿ¼
        if (dirFile.delete()) {
            System.out.println("ɾ��Ŀ¼ " + dirName + " �ɹ�!");
            return true;
        } else {
            System.out.println("ɾ��Ŀ¼ " + dirName + " ʧ��!");
            return false;
        }

    }

    /**
     * �õ��ļ��Ĵ�С
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
     * ���Ƶ����ļ�
     * 
     * @param srcFile
     *            ����·����Դ�ļ� �磺E:/phsftp/src/abc.txt
     * @param dirDest
     *            Ŀ���ļ�Ŀ¼�����ļ�Ŀ¼���������Զ����� �磺E:/phsftp/dest
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
            System.out.println("�����ļ���������:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * �����ļ���
     * 
     * @param oldPath
     *            String Դ�ļ���·�� �磺E:/phsftp/src
     * @param newPath
     *            String Ŀ���ļ���·�� �磺E:/phsftp/dest
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            // ����ļ��в����� ���½��ļ���
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
                if (temp.isDirectory()) {// ��������ļ���
                    copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("�����ļ��в�������:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ����Ŀ¼
     * 
     * @param dir
     *            Ŀ¼
     */
    public static void mkdir(String dir) {
        try {
            String dirTemp = dir;
            File dirPath = new File(dirTemp);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("����Ŀ¼��������: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * �ƶ��ļ���ָ��Ŀ¼
     * 
     * @param oldPath
     *            ����·�����ļ��� �磺E:/phsftp/src/ljq.txt
     * @param newPath
     *            Ŀ���ļ�Ŀ¼ �磺E:/phsftp/dest
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * �ƶ��ļ���ָ��Ŀ¼������ɾ���ļ���
     * 
     * @param oldPath
     *            Դ�ļ�Ŀ¼ �磺E:/phsftp/src
     * @param newPath
     *            Ŀ���ļ�Ŀ¼ �磺E:/phsftp/dest
     */
    public static void moveFiles(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        FileUtils.deleteFiles(new File(oldPath), null);
    }

    /**
     * �ƶ��ļ���ָ��Ŀ¼����ɾ���ļ���
     * 
     * @param oldPath
     *            Դ�ļ�Ŀ¼ �磺E:/phsftp/src
     * @param newPath
     *            Ŀ���ļ�Ŀ¼ �磺E:/phsftp/dest
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        FileUtils.deleteFiles(new File(oldPath), null);
    }

    /**
     * 
     * renameFile:(�ļ�������)
     * 
     * @param url
     * @param new_name
     * @return
     * @throws Exception
     * @author weichanghuan
     * @date 2017��10��9�� ����11:03:58
     */
    public static boolean renameFile(String url, String new_name) throws Exception {
        String old_url = url;
        old_url = old_url.replace("\\", "/");
        File old_file = new File(old_url);
        if (!old_file.exists()) {
            throw new IOException("�ļ�������ʧ�ܣ��ļ���" + old_file + "��������");
        }
        System.out.println(old_file.exists());

        String old_name = old_file.getName();
        // ��ø�·��
        String parent = old_file.getParent();
        // ������
        String new_url = parent + "/" + new_name;
        File new_file = new File(new_url);
        old_file.renameTo(new_file);

        System.out.println("ԭ�ļ���" + old_file.getName());
        System.out.println("���ļ���" + new_file.getName());
        new_name = new_file.getName();
        old_name = old_file.getName();
        if (new_name.equals(old_name)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * ���Ƶ����ļ�
     * 
     * @param srcFileName
     *            �����Ƶ��ļ���
     * @param descFileName
     *            Ŀ���ļ���
     * @param coverlay
     *            ���Ŀ���ļ��Ѵ��ڣ��Ƿ񸲸�
     * @return ������Ƴɹ����򷵻�true�����򷵻�false
     */
    public static boolean copyFileCover(String srcFileName, String descFileName, boolean coverlay) {
        File srcFile = new File(srcFileName);
        // �ж�Դ�ļ��Ƿ����
        if (!srcFile.exists()) {
            System.out.println("�����ļ�ʧ�ܣ�Դ�ļ� " + srcFileName + " ������!");
            return false;
        }
        // �ж�Դ�ļ��Ƿ��ǺϷ����ļ�
        else if (!srcFile.isFile()) {
            System.out.println("�����ļ�ʧ�ܣ�" + srcFileName + " ����һ���ļ�!");
            return false;
        }
        File descFile = new File(descFileName);
        // �ж�Ŀ���ļ��Ƿ����
        if (descFile.exists()) {
            // ���Ŀ���ļ����ڣ�����������
            if (coverlay) {
                System.out.println("Ŀ���ļ��Ѵ��ڣ�׼��ɾ��!");
                if (!FileUtils.delFile(descFileName)) {
                    System.out.println("ɾ��Ŀ���ļ� " + descFileName + " ʧ��!");
                    return false;
                }
            } else {
                System.out.println("�����ļ�ʧ�ܣ�Ŀ���ļ� " + descFileName + " �Ѵ���!");
                return false;
            }
        } else {
            if (!descFile.getParentFile().exists()) {
                // ���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽�Ŀ¼
                System.out.println("Ŀ���ļ����ڵ�Ŀ¼�����ڣ�����Ŀ¼!");
                // ����Ŀ���ļ����ڵ�Ŀ¼
                if (!descFile.getParentFile().mkdirs()) {
                    System.out.println("����Ŀ���ļ����ڵ�Ŀ¼ʧ��!");
                    return false;
                }
            }
        }
        // ׼�������ļ�
        // ��ȡ��λ��
        int readByte = 0;
        InputStream ins = null;
        OutputStream outs = null;
        try {
            // ��Դ�ļ�
            ins = new FileInputStream(srcFile);
            // ��Ŀ���ļ��������
            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[1024];
            // һ�ζ�ȡ1024���ֽڣ���readByteΪ-1ʱ��ʾ�ļ��Ѿ���ȡ���
            while ((readByte = ins.read(buf)) != -1) {
                // ����ȡ���ֽ���д�뵽�����
                outs.write(buf, 0, readByte);
            }
            System.out.println("���Ƶ����ļ� " + srcFileName + " ��" + descFileName + "�ɹ�!");
            return true;
        } catch (Exception e) {
            System.out.println("�����ļ�ʧ�ܣ�" + e.getMessage());
            return false;
        } finally {
            // �ر���������������ȹر��������Ȼ���ٹر�������
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
