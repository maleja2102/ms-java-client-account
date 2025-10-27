package com.devsu.ms_java_account.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.ms_java_account.application.dto.AccountReportDTO;
import com.devsu.ms_java_account.application.service.ReportService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/reports")
public class ReportController {
   private final ReportService service;
   
    public ReportController(ReportService service) {
         this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AccountReportDTO>> getReport(
        @RequestParam Long clientId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate starDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        if(starDate.isAfter(endDate)){
            return ResponseEntity.badRequest().build();
        }
        List<AccountReportDTO> report = service.generateReport(clientId, starDate, endDate);
        if (report.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(report);
    }
    
}
