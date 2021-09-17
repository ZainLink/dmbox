package com.zkzy.portal.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Thinkpad on 2019/12/27.
 */
public class CreateFileUtil {

    /**
     * 生成.json格式文件
     */
    public static boolean createJsonFile(String jsonString,String fileName,HttpServletResponse response) {
        // 标记文件生成是否成功
        boolean flag = true;

        try {
            BufferedOutputStream bufferedOutputStream = null;
            OutputStream out = null;
            // 下面几行是为了解决文件名乱码的问题
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1") + ".json");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            out = response.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(out);
            bufferedOutputStream.write(jsonString.getBytes("UTF-8"));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        // 返回是否成功的标记
        return flag;
    }


    public static void copy(String pathname, String topathname) throws Exception {
        File file = new File(pathname);
        //复制到的位置
        File toFile = new File(topathname);
        if (!toFile.exists()) { // 如果父目录不存在，创建父目录
            toFile.mkdirs();
        }
        copy(file, toFile);
    }

    public static void copy(File file, File toFile) throws Exception {
        byte[] b = new byte[1024];
        int a;
        FileInputStream fis;
        FileOutputStream fos;
        if (file.isDirectory()) {
            String filepath = file.getAbsolutePath();
            filepath = filepath.replaceAll("\\\\", "/");
            String toFilepath = toFile.getAbsolutePath();
            toFilepath = toFilepath.replaceAll("\\\\", "/");
            int lastIndexOf = filepath.lastIndexOf("/");
            toFilepath = toFilepath + filepath.substring(lastIndexOf, filepath.length());
            File copy = new File(toFilepath);
            //复制文件夹
            if (!copy.exists()) {
                copy.mkdir();
            }
            //遍历文件夹
            for (File f : file.listFiles()) {
                copy(f, copy);
            }
        } else {
            if (toFile.isDirectory()) {
                String filepath = file.getAbsolutePath();
                filepath = filepath.replaceAll("\\\\", "/");
                String toFilepath = toFile.getAbsolutePath();
                toFilepath = toFilepath.replaceAll("\\\\", "/");
                int lastIndexOf = filepath.lastIndexOf("/");
                toFilepath = toFilepath + filepath.substring(lastIndexOf, filepath.length());

                //写文件
                File newFile = new File(toFilepath);
                fis = new FileInputStream(file);
                fos = new FileOutputStream(newFile);
                while ((a = fis.read(b)) != -1) {
                    fos.write(b, 0, a);
                }
            } else {
                //写文件
                fis = new FileInputStream(file);
                fos = new FileOutputStream(toFile);
                while ((a = fis.read(b)) != -1) {
                    fos.write(b, 0, a);
                }
            }

        }
    }
}
