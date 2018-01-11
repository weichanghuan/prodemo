package com.controller.filehandle;

import com.po.SysRoleResource;
import com.service.SysRoleResourceService;
import com.utils.ImportExcelUtil;
import com.utils.JSONUtil;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileUpLoad {

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    // http://localhost:8080/MyArtifact/chk.s
    @RequestMapping("/chk.s")
    public String chk() {
        return "chk"; // 返回页面
    }

    // http://localhost:8080/MyArtifact/fileuploadAjaxPage.s
    @RequestMapping("/fileuploadAjaxPage.s")
    public String fileAjax() {
        return "ajaxfileupload"; // 返回页面
    }

    @RequestMapping("/importPicFile.s")
    @ResponseBody
    public String uploadfileAjax(HttpServletRequest request, @RequestParam(value = "file") CommonsMultipartFile file, String pname,
            HttpServletResponse response) throws Exception {
        // 注意一个文件对应一个CommonsMultipartFile类的对象
        /*
         * System.out.println("获取上传文件的名称:"+file.getOriginalFilename());
         * System.out.println("获取上传文件的类型:"+file.getContentType());
         * System.out.println("获取上传文件的大小:"+file.getSize());
         */
        // 上传文件
        String savePath = "E:\\ServerFiles";
        File file1 = new File(savePath);
        // 判断上传文件的保存目录是否存在
        if (!file1.exists() && !file1.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            // 创建目录
            file1.mkdir();
        }
        // 1.将文件保存在服务器目录
        // 获取保存文件的位置
        // 文件名(即为上传文件的名.用户自定)
        String uploadFileName = file.getOriginalFilename();
        // 扩展名(即为上传文件的扩展名.用户自定)
        String expname = uploadFileName.substring(uploadFileName.lastIndexOf("."));
        // 生成新的文件名
        String fileName = System.currentTimeMillis() + expname;
        // 文件的完成路径
        savePath = savePath + File.separator + uploadFileName;
        File saveFile = new File(savePath); // 创建文件
        file.transferTo(saveFile);// 保存文件
        // 解析文件
        List<List<Object>> readExcel = ImportExcelUtil.readExcel(savePath);
        SysRoleResource sysRoleResource = null;
        for (int i = 0; i < readExcel.size(); i++) {
            if (i != 0) {
                sysRoleResource = new SysRoleResource();
                sysRoleResource.setId(Integer.valueOf((String) readExcel.get(i).get(0)));
                sysRoleResource.setRoleId(Integer.valueOf((String) readExcel.get(i).get(1)));
                sysRoleResource.setResourceId(Integer.valueOf((String) readExcel.get(i).get(2)));
                sysRoleResourceService.insert(sysRoleResource);

            }

        }
        return null;
    }

    @RequestMapping("/importPicFile2.s")
    @ResponseBody
    public String uploadfileAjax2(HttpServletRequest request, @RequestParam(value = "file") CommonsMultipartFile file, String pname,
            HttpServletResponse response) throws Exception {
        DiskFileItem fi = (DiskFileItem) file.getFileItem();
        File file1 = fi.getStoreLocation();
        List<List<Object>> readExcel = ImportExcelUtil.readExcel(file1);
        String jSonString = JSONUtil.toJSonString(readExcel);
        System.out.println(jSonString);
        return jSonString;
    }

}
