package com.exceltoD.dataFromExceltoDatabaseApplication.helper;


import com.exceltoD.dataFromExceltoDatabaseApplication.entity.Attendance;
import jdk.jfr.Category;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.lang.reflect.Field;

public class Helper {

    public static String[] HEADERS={
            "id",
            "name",
            "attendanceType"
    };

    /*
    A ByteArrayinputStream contain an internal buffer
    that contains bytes that may be read from the strem.
    An internal counter keeps track of the next byte to be
    supplied by the read method

     */

    public static String SHEET_NAME="attendance_type";

    /*
    //manually
    public static ByteArrayInputStream dataToExcel(List<Attendance> list) throws IOException {

        //create work book
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            //create sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            //create row : header row
            Row row = sheet.createRow(0);

            for(int i=-0;i<HEADERS.length;i++)
            {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            int rowIndex = 1;
            for(Attendance attendance:list)
            {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(attendance.getId());
                dataRow.createCell(1).setCellValue(attendance.getName());
                dataRow.createCell(2).setCellValue(attendance.getAttendanceType());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail to import data excel");
            return null;
        }
        finally {
            workbook.close();
            out.close();
        }

    }


     */

    public static ByteArrayInputStream dataToExcel(List<Attendance> list) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Sheet sheet = workbook.createSheet("attendance_data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            Field[] fields = Attendance.class.getDeclaredFields();
            int colIdx = 0;
            for (Field field : fields) {
                Cell cell = headerRow.createCell(colIdx++);
                cell.setCellValue(field.getName());
            }

            // Create data rows
            int rowIdx = 1;
            for (Attendance attendance : list) {
                Row row = sheet.createRow(rowIdx++);
                colIdx = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(attendance);
                        Cell cell = row.createCell(colIdx++);
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        } // Add more conditions as needed for other data types
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } finally {
            workbook.close();
            out.close();
        }
    }
}
