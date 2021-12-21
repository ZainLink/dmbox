package com.zkzy.portal.common.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class ExcelExportUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportUtils.class);
    public static final int[] icons = {0x2610, 0x2611, 0x2612}; // 框, 勾选, 叉

    public static final String UNCHECKED = new String(icons, 0, 1); //未选中的框
    public static final String CHECKED = new String(icons, 1, 1);   //勾选中的框
    public static final String unchecked_cross = new String(icons, 2, 1);  // 叉掉的框


    /**
     * 读取excel模板，并复制到新文件中供写入和下载
     * @param file  文件模板
     * @param realPath 保存文件的路径
     * @param  newFileName  新的文件名
     * @return
     */
    public static File createNewFile(File file, String realPath,String newFileName) {
        // 判断路径是否存在
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 写入到新的excel
        File newFile = new File(realPath, newFileName);
        try {
            newFile.createNewFile();
            // 复制模板到新文件
            fileChannelCopy(file, newFile);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return newFile;
    }

    /**
     * 复制文件
     * @param s
     * @param t
     */
    public static void fileChannelCopy(File s, File t) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(s), 1024);
                out = new BufferedOutputStream(new FileOutputStream(t), 1024);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     *根据当前row行，来创建index标记的列数,并赋值数据
     */
    private static void createRowAndCell(Object obj, XSSFRow row, XSSFCell cell, int index) {
        cell = row.getCell(index);
        if (cell == null) {
            cell = row.createCell(index);
        }

        if (obj != null)
            cell.setCellValue(obj.toString());
        else
            cell.setCellValue("");
    }

    /**
     *  删除文件
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 文件流写入response
     * @param response
     * @param file          源文件
     * @param fileName      文件名.文件后缀
     */
    public static void setResponse(HttpServletResponse response, File file,String fileName){
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            // 下载
            fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            response.reset();
            response.setContentType("text/html;charset=UTF-8");
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/x-msdownload");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName );
            response.addHeader("Content-Length", "" + file.length());
            toClient.write(buffer);
            toClient.flush();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(toClient!=null){
                try {
                    toClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
