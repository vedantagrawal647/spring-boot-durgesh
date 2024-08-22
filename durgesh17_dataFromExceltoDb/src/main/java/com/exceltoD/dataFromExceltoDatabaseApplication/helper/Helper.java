package com.exceltoD.dataFromExceltoDatabaseApplication.helper;

import com.exceltoD.dataFromExceltoDatabaseApplication.entity.Attendance;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file)
    {
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }
        else {
            return false;
        }
    }

    //convert excel to list of Attendance data
    public static List<Attendance> convertExceltoListOfAttendance(InputStream is){
        List<Attendance> list = new ArrayList<>();

        try{
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Attendance Type");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext())
            {
                Row row = iterator.next();
                if(rowNumber==0)
                {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;

                Attendance attendance = new Attendance();
                while (cells.hasNext())
                {
                    Cell cell = cells.next();

                    switch (cid){
                        case 0:
                            attendance.setName(cell.getStringCellValue());
                            break;
                        case 1:
                            attendance.setAttendanceType(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(attendance);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }

}
