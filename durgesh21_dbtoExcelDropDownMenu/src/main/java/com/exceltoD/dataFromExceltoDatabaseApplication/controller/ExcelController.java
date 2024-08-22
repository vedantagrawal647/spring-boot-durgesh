package com.exceltoD.dataFromExceltoDatabaseApplication.controller;



import com.exceltoD.dataFromExceltoDatabaseApplication.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/dropdown")
    public String generateExcelWithDropdown() {
        try {
            excelService.createExcelWithDropdownAndData();
            return "Excel file with dropdown created successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to create Excel file with dropdown.";
        }
    }
}

