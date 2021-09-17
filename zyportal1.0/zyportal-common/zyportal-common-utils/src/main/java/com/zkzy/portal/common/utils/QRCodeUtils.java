/**
 * @Title: QRCodeUtils.java
 * @Package com.zkzy.portal.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author UGER
 * @date 2017年1月11日 下午2:15:49
 * @version V1.0
 */
package com.zkzy.portal.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


/**
 * 项目名称：zyportal_v4_common
 * 类名称：QRCodeUtils
 * 类描述：   二维码操作类
 * 创建人：UGER
 * 创建时间：2017年1月11日 下午2:15:49
 * 修改人：Administrator
 * 修改时间：2017年1月11日 下午2:15:49
 * 修改备注：
 *
 * @version V1.0
 */
public class QRCodeUtils {

    /**
     * 生成二维码
     *
     * @param text       文本
     * @param width      宽度
     * @param height     高度
     * @param QRCodePath 路径
     * @return
     */
    public static boolean getQRCode(String text, Integer width, Integer height, String QRCodePath) {
        try {
            width = (width == null ? 200 : width);
            height = (height == null ? 200 : height);
            String format = "png";
            File file = new File(QRCodePath);
            if (!file.exists()) {
                System.out.println("二维码保存文件夹不存在,创建");
                file.mkdirs();//创建文件夹
            }
            QRCodePath = QRCodePath + File.separator + text + "." + format;
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            File outputFile = new File(QRCodePath);
//	        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);//报错,提取了里面的部分方法使用
            BufferedImage image = toBufferedImage(bitMatrix);
            ImageIO.write(image, format, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

        }
        return true;
    }


    public static boolean getQRCodeByHref(String text, Integer width, Integer height, String QRCodePath,String QRCodeName) {
        try {
            width = (width == null ? 200 : width);
            height = (height == null ? 200 : height);
            String format = "png";
            File file = new File(QRCodePath);
            if (!file.exists()) {
                System.out.println("二维码保存文件夹不存在,创建");
                file.mkdirs();//创建文件夹
            }
            QRCodePath = QRCodePath + File.separator + QRCodeName + "." + format;
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            File outputFile = new File(QRCodePath);
//	        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);//报错,提取了里面的部分方法使用
            BufferedImage image = toBufferedImage(bitMatrix);
            ImageIO.write(image, format, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        getQRCodeByHref("https://xlbj.wxxinquranqi.online/h5/equipment-detail.html?id=359369083140281&dtype=NBiot", 300, 300, "D:"+File.separator+"apache-tomcat-7.0.65-myeclipse2014_3"+File.separator+"webapps"+File.separator+"data"+File.separator+"files"+File.separator,"test");

    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, 2);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? -16777216 : -1);
            }
        }
        return image;
    }


}
