package com.zkzy.portal.common.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
* @ClassName: ExcelUtil
* @Description: excel工具类(针对POI)
* @author yangl
* @date 2015-12-21 下午01:23:20
 */
public class ExcelPoiUtil {
	private static Workbook  wb=null;
	/**
	* @author: yangl
	* @Description: 获取workbook接口
	* @Date：2015-12-21
	* @param path 文件路径
	* @return  Workbook
	 */
	public static  Workbook getWorkbook(String path){
		InputStream is=null;
		try {
			is = new FileInputStream(new File(path));
			if (isExcel2003(path))  
	        {  
			    wb = new HSSFWorkbook(is);
	        }  
	        else  
	        {  
	            wb = new XSSFWorkbook(is);  
	        }  
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return wb;
	}
    /**
    * @author: yangl
    * @Description: 判断头部信息是否对应
    * @Date：2015-12-21
    * @param path 文件路径
    * @param marr 文件头数组
    * @param arrLen 数组长度
    * @param start 头部行的开始--开
    * @param end 头部行的结束----闭
    * @return  boolean true/false
     */
    public static boolean isHead(String path,String[] marr,int arrLen,int start,int end){
    	//默认不相等
    	boolean isl=false;
    	Workbook  wb=null;
		try {
			wb=getWorkbook(path);
			// 循环工作表Sheet
	        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
	            Sheet hssfSheet = wb.getSheetAt(numSheet);
	            if (hssfSheet == null) {
	                continue;
	            }
	            //文件头第一行
	            String[] arr=new String[arrLen];
	            for(int j=start;j<end;j++){
	            	Row hssfRow = hssfSheet.getRow(j);
		            if (hssfRow == null) {
		                continue;
		            }
		          //循环列
		            for(int i=0;i<arrLen;i++){
		            	Cell xm = hssfRow.getCell(i);
		            	if(xm==null){
		            		continue;
		            	}
		            	//去除空格
		            	arr[i]=(arr[i]==null?"":arr[i])+getValue(xm).replaceAll("\\s*", "");
		            }

	            }
	            iteratorArr(arr);
	            //数组比较
	            isl=Arrays.equals(arr, marr);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			isl=false;
		} 
		return isl;
    }
    //判断是否是2003版的Excel
    public static boolean isExcel2003(String filePath){  
        return filePath.matches("^.+\\.(?i)(xls)$");  
    } 
    //判断是否是2007版的Excel
    public static boolean isExcel2007(String filePath){  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
    } 
    public static void iteratorArr(String[] arr){
    	for (int i = 0; i < arr.length; i++) {
			System.out.print("\""+arr[i]+"\",");
		}
    }
    /**
     * 得到Excel表中的值
     */
    public static String getValue(Cell cell) {
    	String cellValue="";
    	//针对Excel里的公式
    	FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
    	CellValue c=evaluator.evaluate(cell);
    	// 以下是判断数据的类型  
    	if(c!=null){
    		switch (c.getCellType())  
            {  
            case Cell.CELL_TYPE_NUMERIC: // 数字  
                cellValue = c.getNumberValue() + "";  
                break;  

            case Cell.CELL_TYPE_STRING: // 字符串  
                cellValue = c.getStringValue();  
                break;  

            case Cell.CELL_TYPE_BOOLEAN: // Boolean  
                cellValue = c.getBooleanValue() + "";  
                break;  

            case Cell.CELL_TYPE_FORMULA: // 公式  
////                cellValue = cell.getCellFormula() + "";  
//            	cellValue= evaluator.evaluateFormulaCell(cell)+"";
//            	CellValue c=evaluator.evaluate(cell);
//            	System.out.println(c.toString());
                break;  

            case Cell.CELL_TYPE_BLANK: // 空值  
                break;  

            case Cell.CELL_TYPE_ERROR: // 故障  
                break;  

            default:  
                break;  
            }  
    	}
        return cellValue;
    }


	public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
		// 第一步，创建一个webbook，对应一个Excel文件
		if(wb == null){
			wb = new HSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCell cell = null;
		//创建标题
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		//创建内容
		for(int i=0;i<values.length;i++){
			row = sheet.createRow(i + 1);
			for(int j=0;j<values[i].length;j++){
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		return wb;
	}

}
