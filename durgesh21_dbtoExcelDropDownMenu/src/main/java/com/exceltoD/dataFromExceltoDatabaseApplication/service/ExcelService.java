package com.exceltoD.dataFromExceltoDatabaseApplication.service;

import com.exceltoD.dataFromExceltoDatabaseApplication.entity.Person;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public void createExcelWithDropdownAndData() throws IOException {
        List<Person> persons = new ArrayList<>();

        Person person1 = new Person();
        person1.setId(1); // Ensure to use Long instead of int for IDs
        person1.setName("Vedant");
        person1.setLanguagesKnown("english,hindi");

        Person person2 = new Person();
        person2.setId(2);
        person2.setName("Divo");
        person2.setLanguagesKnown("hindi");

        persons.add(person1);
        persons.add(person2);

        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Persons");

        // Define header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Languages Known");

        // Populate data rows
        int rowNum = 1;
        for (Person person : persons) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(person.getId());
            row.createCell(1).setCellValue(person.getName());

            // Create dropdown for languages known
            String[] languages = person.getLanguagesKnown().split(",");
            DataValidationHelper validationHelper = sheet.getDataValidationHelper();
            CellRangeAddressList addressList = new CellRangeAddressList(row.getRowNum(), row.getRowNum(), 2, 2);
            DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(languages);
            DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
            sheet.addValidationData(dataValidation);
        }

        // Auto-size columns
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to file
        FileOutputStream fileOut = new FileOutputStream("persons.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Clean up resources
        workbook.close();
    }
}


/*


ChatGPT
In the ExcelService class provided earlier, various classes and components are used to facilitate
the generation of an Excel file with dropdown menus. Here's an explanation of each class and its
necessity in the context of the code:

1. ExcelService Class
This is the main service class responsible for creating an Excel file (persons.xlsx) with dropdown
   menus based on data from Person objects.

Purpose: To orchestrate the creation of an Excel workbook, define its structure (headers and data rows), and
         apply dropdown validations for specific columns.

Necessity: This class coordinates the use of Apache POI library components (Workbook, Sheet, Row, Cell, DataValidationHelper,
       CellRangeAddressList, DataValidation) to achieve the desired functionality of generating an Excel file with formatted data and dropdown menus.

2. Person Class
This is an entity class representing the structure of a Person object with attributes like id, name, and languagesKnown.

Purpose: Defines the data structure for individual persons including their identifiers, names, and languages known.

Necessity: Provides the data source (persons list) from which information is extracted and written into the Excel file. It ensures consistency and structure in the data being processed.

3. Apache POI Components
Workbook (XSSFWorkbook): Represents the Excel workbook where sheets and data are managed.

Sheet: Represents a sheet within the workbook (Persons sheet in this case) where data is organized into rows and columns.

Row: Represents a row within a sheet where cells are organized horizontally.

Cell: Represents a single cell within a row where data can be stored or formatted.

DataValidationHelper: Helper class to assist in creating data validations such as dropdown menus.

CellRangeAddressList: Defines a range of cells to which a data validation (dropdown list) will be applied.

DataValidationConstraint: Defines the criteria for data validation (e.g., list of valid values).

DataValidation: Represents a data validation rule applied to a range of cells, such as a dropdown list.

Purpose: Apache POI components are crucial for creating, manipulating, and formatting Excel files programmatically. They provide APIs to manage workbook structure, data insertion, cell formatting, and applying advanced features like data validations (dropdowns).

Necessity: These components are essential for generating an Excel file (persons.xlsx), setting headers, populating rows with data from Person objects, and applying dropdown validations based on languages known by each person.

4. FileOutputStream and IOException
FileOutputStream: Writes data to a file (persons.xlsx) as a sequence of bytes.

IOException: Represents an exception that may occur during input-output operations, such as file writing.

Purpose: Used to save the generated Excel file (persons.xlsx) to the filesystem after it's been created and configured.

Necessity: Essential for persisting the workbook content into a file (persons.xlsx) on disk. It handles the writing and closing of the file stream to ensure data integrity and release of system resources.

5. List<Person> and ArrayList<>
List<Person>: A collection type that holds Person objects.

ArrayList<>: A specific implementation of the List<> interface that stores elements
in an array-like structure.

Purpose: Stores Person objects (person1, person2) with sample data (ID, name, languages known)
 before generating the Excel file.

Necessity: Provides a structured way to manage and iterate over multiple Person objects,
 ensuring data consistency and facilitating data population into the Excel sheet (Persons).

Summary
Each class and component used in the ExcelService class serves a specific purpose in the process
of generating an Excel file with dropdown menus:

ExcelService: Orchestrates Excel file creation, data population, and dropdown validation.
Person: Defines the structure and data source (persons list) for Person objects.
Apache POI Components: Facilitate Excel file manipulation, data insertion, and formatting.
FileOutputStream and IOException: Handle file writing operations and potential exceptions.
List<Person> and ArrayList<>: Manage sample data (Person objects) before Excel file generation.
Together, these components enable the ExcelService class to effectively generate an Excel file
(persons.xlsx) with structured data and dropdown menus based on the provided Person objects.
Adjustments and enhancements can be made based on specific application requirements, such as
 handling larger datasets or adding error handling mechanisms.

 */