import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReadTest {
    String excelFilePath = "E:\\upload\\2-A1业务承接评价表-2.xlsx";
    String newExcelFilePath = "E:\\upload\\2.xlsx";
    @Test
    public void ReadExcel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        FileOutputStream fileOutStream = null;
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        String comment = cell.getCellComment().getString().toString();
        CellInfo cellInfo = JSON.parseObject(comment,CellInfo.class);
        JSONObject jsonObject = JSON.parseObject(comment);
        System.out.println(cell.getStringCellValue());

        sheet.shiftRows(42, sheet.getLastRowNum(), 3);
        row = sheet.getRow(41);
        cell = row.getCell(1);
        CellStyle newCellStyle = workbook.createCellStyle();
        newCellStyle.cloneStyleFrom(cell.getCellStyle()); ;
        Row newRow = sheet.createRow(42);
        for (int i = 0; i < newRow.getPhysicalNumberOfCells(); i++)
        {
            newRow.setRowStyle(newCellStyle);
        }

       // sheet.createRow(42);
      //  sheet.createRow(43);
     //   sheet.createRow(44);
        fileOutStream = new FileOutputStream(newExcelFilePath);
        workbook.write(fileOutStream);
        fileInputStream.close();
    }


    @Test
    public void CopySheet() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        FileOutputStream fileOutStream = null;
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Sheet newSheet = workbook.createSheet();
        ExcelUtil.copySheet(sheet,newSheet);


        fileOutStream = new FileOutputStream(newExcelFilePath);

        workbook.write(fileOutStream);
        fileInputStream.close();
    }

    @Test
    public void CopyRow() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        FileOutputStream fileOutStream = null;
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(41);

        sheet.shiftRows(42, sheet.getLastRowNum(), 3);
        sheet.createRow(42);

        Row desRow = sheet.getRow(42);
        desRow.setHeight(row.getHeight());
        ExcelUtil.copyRow(row,desRow);
        ExcelUtil.copyMergedRegion(sheet,row,desRow);

        fileOutStream = new FileOutputStream(newExcelFilePath);

        workbook.write(fileOutStream);
        fileInputStream.close();
    }


}
