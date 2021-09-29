package com.zkzy.portal.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            //log.error("NoSuchAlgorithmException: md5", ne);
        }
    }

    /**
     * 对一个文件求他的md5值
     *
     * @param f 要求md5值的文件
     * @return md5串
     */
    public static String md5(File f) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }

            //return new String(Hex.encodeHex(md.digest()));
            return null;
        } catch (FileNotFoundException e) {
            // log.error("md5 file " + f.getAbsolutePath() + " failed:" + e.getMessage());
            return null;
        } catch (IOException e) {
            // log.error("md5 file " + f.getAbsolutePath() + " failed:" + e.getMessage());
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                // log.error("文件操作失败",ex);
            }
        }
    }

    /**
     * 求一个字符串的md5值
     *
     * @param plainText 字符串
     * @return md5 value
     */
    public static String md5(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(plainText.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for(int offset=0; offset<b.length; offset++){
                i = b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re_md5;

    }

    public static String bytes2Hex(byte[] bytes)     //加密字节数组转十六进制字符串
    {
        final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * 可以比较两个文件是否内容相等
     *
     * @param args
     */
    public static void main(String[] args) {
        File newFile = new File("D:/files/paoding-analysis.jar.new");
        File oldFile = new File("D:/files/paoding-analysis.jar.old");
        String s1 = md5(newFile);
        String s2 = md5(oldFile);
        System.out.println(s1.equals(s2));
        System.out.println(s1);
        System.out.println(s2);
    }
}
