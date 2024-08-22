package com.exceltoD.dataFromExceltoDatabaseApplication.controller;

import com.exceltoD.dataFromExceltoDatabaseApplication.service.AttendanceService;
import com.exceltoD.dataFromExceltoDatabaseApplication.entity.Attendance;
import com.exceltoD.dataFromExceltoDatabaseApplication.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/attendance/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file)
    {
        if(Helper.checkExcelFormat(file))
        {
            //true
            this.attendanceService.save(file);
            return ResponseEntity.ok(Map.of("message","File is uploaded and data is save to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file");
    }

    @GetMapping("/attendance")
    public List<Attendance> getAllAttendence(){
        return this.attendanceService.getAllAttendance();
    }

}
