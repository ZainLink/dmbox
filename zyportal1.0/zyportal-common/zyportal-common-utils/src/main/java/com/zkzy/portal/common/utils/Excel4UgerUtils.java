package com.zkzy.portal.common.utils;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@Author: UGER
 *@Created: 2020/7/3 10:32
 *@Description: Excel 工具类   1.支持占位符替换文字  2. 占位符勾选和未勾选
 */
public class Excel4UgerUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportUtils.class);
    public static final int[] icons = {0x2610, 0x2611, 0x2612}; // 框, 勾选, 叉

    public static final String UNCHECKED = new String(icons, 0, 1); //未选中的框
    public static final String CHECKED = new String(icons, 1, 1);   //勾选中的框
    public static final String unchecked_cross = new String(icons, 2, 1);  // 叉掉的框

    public static void main(String[] args) {
        String key = "sfgqqygp";
        String str = "dfsdffsf{sfgqqygp[33]}ssd{sfgqqygp[15]}fsff{sfgqqygp[12]}大幅飒飒大";


        Pattern pattern = Pattern.compile("\\{"+key+"\\[(.*?)\\]\\}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

//        Short bbb = null;
//        String bbbStr = String.valueOf(bbb);
//        System.out.println(bbbStr);
//        System.out.println(StringUtils.isEmpty(bbbStr));


    }

    /**
     *@Author: UGER
     *@Created: 2020/7/3 9:33
     *@Description: 替换excel模板中的占位符, 适用于文本
     *@Return: java.lang.String
     */
    public static XSSFCell cellStrReplace(XSSFCell cell, Map<String,String> resource, Font font){
        // 遍历 resource, 替换{resource.key} 为 resource.value
        if(MapUtils.isNotEmpty(resource)){
            for (Map.Entry<String, String> entry : resource.entrySet()) {
                XSSFRichTextString xssfRichTextString = cell.getRichStringCellValue();
                String replaceKey = "{"+entry.getKey()+"}";
                String replaceValue = entry.getValue()==null?"":entry.getValue();
                int startIndex = -1;
                // 有字体格式的
                if(font != null){
                    startIndex = xssfRichTextString.getString().indexOf(replaceKey);
                }
                String cellStr = xssfRichTextString.getString().replace(replaceKey , replaceValue);
                cell.setCellValue(cellStr);
                if(startIndex >= 0){
                    cell.getRichStringCellValue().applyFont(startIndex,startIndex+replaceValue.length(),font);
                }
            }
        }
        return cell;
    }
    public static XSSFCell cellStrReplace(XSSFRow row, int cellIndex, Map<String,String> resource, Font font){
        XSSFCell cell = row.getCell(cellIndex);
        return cellStrReplace(cell, resource, font);
    }

    /**
    *@Author: UGER
    *@Created: 2020/7/3 17:18
    *@Description: 替换excel模板中的占位符, 适用于勾选框
    *@Param: [cell, resource]
    *@Return: org.apache.poi.xssf.usermodel.XSSFCell
    **/
    public static XSSFCell cellCheckReplace(XSSFCell cell, Map<String,String> resource){
        // 遍历 resource, 替换{resource.key[value]} 为 勾选,其余为未勾选状态
        if(MapUtils.isNotEmpty(resource)){
            for (Map.Entry<String, String> entry : resource.entrySet()) {
                XSSFRichTextString xssfRichTextString = cell.getRichStringCellValue();
                String entVal = (entry.getValue()==null?"":entry.getValue());
                String replaceKey = "{"+entry.getKey()+"["+entVal+"]}";

                String cellStr = xssfRichTextString.getString();
                Pattern pattern = Pattern.compile("\\{"+entry.getKey()+"\\[(.*?)\\]\\}");
                Matcher matcher = pattern.matcher(cellStr);
                while (matcher.find()) {
                    String curReplaceKey = matcher.group(0);
                    if(curReplaceKey.equals(replaceKey)){
                        cellStr = cellStr.replace(curReplaceKey, CHECKED);
                    }else{
                        cellStr = cellStr.replace(curReplaceKey, UNCHECKED);
                    }
                }
                cell.setCellValue(cellStr);
            }
        }
        return cell;
    }
    public static XSSFCell cellCheckReplace(XSSFRow row, int cellIndex, Map<String,String> resource){
        XSSFCell cell = row.getCell(cellIndex);
        return cellCheckReplace(cell, resource);
    }

    /**
     *@Author: UGER
     *@Created: 2020/7/3 13:48
     *@Description: Short 转 String
     *@Param: [s]
     *@Return: java.lang.String
     */
    public static  String short2String(Short s){
        if(s == null){
            return "";
        }
        return String.valueOf(s);
    }
    /**
     *@Author: UGER
     *@Created: 2020/7/3 14:39
     *@Description: BigDecimal 转 String
     *@Param: [s]
     *@Return: java.lang.String
     */
    public static String bigDecimal2String(BigDecimal b){
        if(b == null){
            return "";
        }
        return b.toString();
    }
    /**
     *@Author: UGER
     *@Created: 2020/7/3 15:16
     *@Description: String -> String
     *@Param: [str]
     *@Return: java.lang.String
     */
    public static String strNullable(String str){
        return Optional.ofNullable(str).orElse("");
    }

    /**
    *@Author: UGER
    *@Created: 2020/7/6 9:37
    *@Description: 获取文件后缀名
    *@Param: [file]
    *@Return: java.lang.String
    */
    public static String getFileSuffix(File file) {
        if (file == null) {
            return null;
        }
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    /**
    *@Author: UGER
    *@Created: 2020/7/6 9:38
    *@Description: 获取文件后缀名
    *@Param: [fileName]
    *@Return: java.lang.String
    */
    public static String getFileSuffix(String fileName) {
        if (org.springframework.util.StringUtils.isEmpty(fileName) || fileName.indexOf(".") == -1) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    /**
    *@Author: UGER
    *@Created: 2020/7/6 9:39
    *@Description: 获取row
    *@Param: [sheet, rowIndex]
    *@Return: org.apache.poi.xssf.usermodel.XSSFRow
    */
    public XSSFRow getRow(XSSFSheet sheet, int rowIndex){
        XSSFRow row = sheet.getRow(rowIndex);
        if(row == null){
            row = sheet.createRow(rowIndex);
        }
        return row;
    }
    /**
    *@Author: UGER
    *@Created: 2020/7/6 9:39
    *@Description: 获取cell
    *@Param: [row, cellIndex, xssfCellStyle]
    *@Return: org.apache.poi.xssf.usermodel.XSSFCell
    */
    public XSSFCell getCell(XSSFRow row, int cellIndex, XSSFCellStyle xssfCellStyle){
        XSSFCell cell = row.getCell(cellIndex);
        if(cell == null){
            cell = row.createCell(cellIndex);
        }
        if(xssfCellStyle != null){
            cell.setCellStyle(xssfCellStyle);
        }
        return cell;
    }


}
