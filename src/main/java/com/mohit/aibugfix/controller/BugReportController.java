package com.mohit.aibugfix.controller;

import com.mohit.aibugfix.dto.BugReportRequest;
import com.mohit.aibugfix.dto.BugReportResponse;
import com.mohit.aibugfix.service.BugReportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
@CrossOrigin
public class BugReportController {

    private final BugReportService service;

    public BugReportController(BugReportService service) {
        this.service = service;
    }

    @PostMapping
    public BugReportResponse create(@Valid @RequestBody BugReportRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<BugReportResponse> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public BugReportResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }
}