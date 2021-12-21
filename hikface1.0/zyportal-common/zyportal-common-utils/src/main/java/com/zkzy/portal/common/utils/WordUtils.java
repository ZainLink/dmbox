package com.zkzy.portal.common.utils;

import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public class WordUtils {

    /**
     * 生成一个word
     * @param t 模板
     * @param outPath 生成路径
     * @param dataMap 模板数据
     * @return
     */
    public static boolean createWord(Template t,
                                  String outPath,
                                  Map<String,Object> dataMap) {
        Writer out = null;
        try {
            File outFile = new File(outPath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 对图片进行编码
     * @param imgFile
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


}
