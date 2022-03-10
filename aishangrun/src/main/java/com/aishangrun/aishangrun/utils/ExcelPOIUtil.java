package com.aishangrun.aishangrun.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/08/31 16:55:48
 * Email: 1095737364@qq.com
 */
public class ExcelPOIUtil {
    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel
    /**
     * Excel导入
     */
    public static  List<Map<String,Object>> getBankListByExcel(String  pathQRCode,String qRCodeUrl,InputStream in, String fileName) throws Exception{
        List<Map<String,Object>> list = null;
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<Map<String,Object>>();
        //遍历Excel中所有的sheet
            sheet = work.getSheetAt(0);
            //遍历当前sheet中的所有行
            //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
            for (int j =1; j <= sheet.getLastRowNum(); j++) {
                //读取一行
                row = sheet.getRow(j);
                //遍历所有的列
               Map li = new HashMap();
               cell = row.getCell(0);
                String stringCellValue = cell.getStringCellValue();
                li.put("deviceSn",stringCellValue);
               cell = row.getCell(1);
                li.put("deviceImei",cell.getStringCellValue());
               cell = row.getCell(2);
                li.put("msisdn",cell.getStringCellValue());
                li.put("creatTime",new Date());

                QRCodeUtil.encode(qRCodeUrl, null, pathQRCode+File.separator+stringCellValue+".png", true);

                li.put("qRCodeUrl","/sys/device/qRCodeImg/"+stringCellValue+".png");
                list.add(li);
        }
        return list;
    }
    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

}
