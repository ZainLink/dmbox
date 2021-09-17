//package com.zkzy.portal.dumu.client.controller.base;
//
//
//import com.zkzy.portal.dumu.client.common.controller.BaseController;
//import com.zkzy.portal.common.upload.DiskFileOperator;
//import com.zkzy.portal.common.utils.RandomHelper;
//import com.zkzy.zyportal.system.api.exception.InvalidCaptchaException;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.*;
//
///**
// * Created by Zhangzy on 2017/6/16 0018.
// * 文件上传
// * 注意点:
// * 1.类上添加@RestController或在方法上添加@ResponseBody注解
// * 2.返回的数据在前台需要显示为json格式
// */
//
//
//@Controller
////@ConfigurationProperties(prefix="upload")
//public class FileUploadController extends BaseController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
//
//    public final static String img_arry[] = {".png", ".jpg", ".jpeg", ".bmp", ".gif"};
//
//    public final static String doc_arry[] = {".doc", ".docx", ".txt"};
//
//    public final static String xls_array[] = {".xls", ".xlsx"};
//
//    public final static String video_array[] = {".mkv", ".mp4", ".avi", ".rm", ".rmvb"};
//
//    public final static String music_array[] = {".wav", ".aif", ".mid", ".au", ".mp3", ".ogg"};
//
//    public final static String pdf_ayyay[] = {".pdf"};
//
//    public final static String fileParentId = "123660";
//
//    public final static String whiteList[] = {"png", "jpg", "jpeg", "bmp", "gif", "xls", "xlsx"};
//
//    public final static String otherlist[] = {"android-ver", "ios-ver"};
//
//
//    @PostMapping(value = "/add", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    @ApiOperation(value = "文件上传(new)")
//    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataType = "string", value = "authorization header", defaultValue = "Bearer ")})
//    public Map<String, String> uploadFile(
//            HttpServletRequest request, HttpServletResponse response
//    ) throws InvalidCaptchaException, IOException {
//        Map<String, String> resultMap = new HashMap<String, String>();
//        FileOutputStream fos = null;
//        String uploadFilePath = null;//保存的绝对路径
//        try {
//            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
//            Iterator<String> iterator = req.getFileNames();
//            while (iterator.hasNext()) {
//                MultipartFile file = req.getFile(iterator.next());
//                String fileNames = file.getOriginalFilename();
//                int split = fileNames.lastIndexOf(".");
//                //存储文件
//                String fileName = fileNames.substring(0, split);//文件名
//                String fileType = fileNames.substring(split + 1, fileNames.length());//文件后缀
//
//                if (!Arrays.asList(whiteList).contains(fileType.toLowerCase())) {
//                    resultMap.put("status", "fail");
//                    resultMap.put("desc", "非法上传");
//                    return resultMap;
//                }
//                byte[] fileBytes = file.getBytes();//文件内容
//                Long fsize = file.getSize();
//                String newFileNmae = this.getNewFileName(fileName, fileType);//新名称
//                String relativePath = File.separator + newFileNmae;//相对地址
//                uploadFilePath = DiskFileOperator.workFolderName + relativePath;
//                File file1 = new File(uploadFilePath);//保存路径
//                String fileParentPath = file1.getParent();//上级父路径
//                File fParent = new File(fileParentPath);
//                if (!fParent.exists()) {//上级父路径不存在则创建
//                    fParent.mkdirs();
//                }
//
//                fos = new FileOutputStream(file1);
//                fos.write(fileBytes);
//                String pdfPath = "";
//                if ("doc".equalsIgnoreCase(fileType) || "docx".equalsIgnoreCase(fileType) ||
//                        "xls".equalsIgnoreCase(fileType) || "xlsx".equalsIgnoreCase(fileType) ||
//                        "ppt".equalsIgnoreCase(fileType) || "pptx".equalsIgnoreCase(fileType) ||
//                        "txt".equalsIgnoreCase(fileType)) {
//                    int splits = newFileNmae.lastIndexOf(".");
//                    pdfPath = File.separator + newFileNmae.substring(0, splits) + ".pdf";
//                    resultMap.put("pdfPath", pdfPath);
//                } else {
//                    resultMap.put("pdfPath", pdfPath);
//                }
//                resultMap.put("status", "success");
//                resultMap.put("size", fsize.toString());
//                resultMap.put("fileBaseName", fileNames);
//                resultMap.put("fileUrl", relativePath);//返回保存的相对路径
//                resultMap.put("fileType", fileType);
//
//                Properties prop = System.getProperties();
//                String os = prop.getProperty("os.name");
//                if (os != null && os.toLowerCase().indexOf("linux") > -1) {
//                    Runtime.getRuntime().exec("chmod 777 -R " + uploadFilePath);//linux给权限
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultMap.put("status", "fail");
//        } finally {
//            if (fos != null) {
//                fos.close();
//            }
//            return resultMap;
//        }
//    }
//
//    /**
//     * 删除
//     *
//     * @param delFile
//     * @return
//     */
//    public Map<String, String> delfile(String delFile) {
//        DiskFileOperator dp = new DiskFileOperator();
//        dp.deleteFile(delFile);
//        Map<String, String> resultMap = new HashMap<String, String>();
//        resultMap.put("status", "success");
//        return resultMap;
//    }
//
//    /**
//     * 获取新名称
//     * 规则:uuid+图片原来的名称
//     *
//     * @param name
//     * @param suffix
//     * @return
//     */
//    public String getNewFileName(String name, String suffix) {
//        String uuid = RandomHelper.uuid();
//        return uuid + "." + suffix;
//    }
//
//
//    public String getFiletype(String filetype) {
//        filetype = "." + (filetype.toLowerCase());
//        if (Arrays.asList(img_arry).contains(filetype)) {
//            return "img";
//        } else if (Arrays.asList(doc_arry).contains(filetype)) {
//            return "docOffice";
//        } else if (Arrays.asList(xls_array).contains(filetype)) {
//            return "xlsOffice";
//        } else if (Arrays.asList(video_array).contains(filetype)) {
//            return "video";
//        } else if (Arrays.asList(music_array).contains(filetype)) {
//            return "music";
//        } else if (Arrays.asList(pdf_ayyay).contains(filetype)) {
//            return "pdf";
//        } else {
//            return "others";
//        }
//    }
//
//
//    @GetMapping(value = "/preview/{type}/{index}/{fileurl}", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "图片预览")
//    public void imgPreview(
//            HttpServletResponse response,
//            @PathVariable(name = "type", required = true) String type,
//            @PathVariable(name = "fileurl", required = true) String fileurl,
//            @PathVariable(name = "index", required = true) String index
//    ) {
//        if (StringUtils.isNoneEmpty(fileurl) && StringUtils.isNoneEmpty(type) && StringUtils.isNoneEmpty(index)) {
//            String other = fileurl;
//            fileurl = index.equals("01") ? fileurl : File.separator + index + File.separator + fileurl;
//            String fileNames = fileurl + "." + type;
//            int split = fileNames.lastIndexOf(".");
//            String fileType = "." + fileNames.substring(split + 1, fileNames.length());//文件后缀l
//            if (Arrays.asList(img_arry).contains(fileType.toLowerCase()) || Arrays.asList(otherlist).contains(other)) {//是图片才允许预览
//                OutputStream os = null;
//                InputStream in = null;
//                try {
//                    String uploadFilePath = null;//保存的绝对路径
//                    String relativePath = File.separator + fileNames;//相对地址
//                    uploadFilePath = DiskFileOperator.workFolderName + relativePath;
//                    File catalog = new File(uploadFilePath);
//                    if (catalog.exists()) {
//                        // 清空response
//                        response.reset();
//                        if (type.equals("json")) {
//                            response.setContentType("text/html;charset=UTF-8");
//                        } else {
//                            response.setContentType("image/jpeg");
//                        }
//                        //禁止图像缓存
//                        response.setHeader("Pragma", "no-cache");
//                        response.setHeader("Cache-Control", "no-cache");
//                        response.setDateHeader("Expires", 0);
//                        in = new BufferedInputStream(new FileInputStream(uploadFilePath), 4096);
//                        os = new BufferedOutputStream(response.getOutputStream());
//                        byte[] bytes = new byte[4096];
//                        int i = 0;
//                        while ((i = in.read(bytes)) > 0) {
//                            os.write(bytes, 0, i);
//                        }
//                        os.flush();
//                        os.close();
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    LOGGER.error(e.getMessage());
//                } finally {
//                    try {
//                        if (in != null) {
//                            in.close();
//                        }
//                        if (os != null) {
//                            os.flush();
//                            os.close();
//                        }
//                    } catch (Exception e) {
//                        LOGGER.error(e.getMessage());
//                    }
//                }
//            }
//
//        }
//    }
//
//
//    @GetMapping(value = "/download/{type}/{index}/{fileurl}", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "文件下载")
//    public void fileDownload(
//            HttpServletResponse response,
//            @PathVariable(name = "type", required = true) String type,
//            @PathVariable(name = "fileurl", required = true) String fileurl,
//            @PathVariable(name = "index", required = false) String index
//    ) {
//        if (StringUtils.isNoneEmpty(fileurl) && StringUtils.isNoneEmpty(type)) {
//            fileurl = index.equals("01") ? fileurl : File.separator + index + File.separator + fileurl;
//            String fileNames = fileurl + "." + type;
//            int split = fileNames.lastIndexOf(".");
//            String fileType = "." + fileNames.substring(split + 1, fileNames.length());//文件后缀
//            OutputStream os = null;
//            InputStream in = null;
//            try {
//                String uploadFilePath = null;//保存的绝对路径
//                String relativePath = File.separator + fileNames;//相对地址
//                uploadFilePath = DiskFileOperator.workFolderName + relativePath;
//                File catalog = new File(uploadFilePath);
//                if (catalog.exists()) {
//                    // 清空response
//                    response.reset();
//                    response.setContentType("multipart/form-data");
//                    response.setHeader("Content-Disposition",
//                            "attachment; filename=" + new String(RandomHelper.uuid().getBytes(), "ISO-8859-1") + "." + type);
//                    in = new BufferedInputStream(new FileInputStream(uploadFilePath), 4096);
//                    os = new BufferedOutputStream(response.getOutputStream());
//                    byte[] bytes = new byte[4096];
//                    int i = 0;
//                    while ((i = in.read(bytes)) > 0) {
//                        os.write(bytes, 0, i);
//                    }
//                    os.flush();
//                    os.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                LOGGER.error(e.getMessage());
//            } finally {
//                try {
//                    if (in != null) {
//                        in.close();
//                    }
//                    if (os != null) {
//                        os.flush();
//                        os.close();
//                    }
//                } catch (Exception e) {
//                    LOGGER.error(e.getMessage());
//                }
//            }
//
//
//        }
//    }
//
//
//}
