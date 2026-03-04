package com.mohit.aibugfix.service;

import com.mohit.aibugfix.dto.BugReportRequest;

import com.mohit.aibugfix.dto.BugReportResponse;
import com.mohit.aibugfix.entity.BugReport;
import com.mohit.aibugfix.entity.User;
import com.mohit.aibugfix.repository.BugReportRepository;
import com.mohit.aibugfix.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class BugReportService {

    private final BugReportRepository repository;
    private final UserRepository userRepository;
    

    public BugReportService(BugReportRepository repository,
                            UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public BugReportResponse create(BugReportRequest request) {

        // 🔐 Get logged-in user email from JWT
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // 🔎 Fetch user from DB
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 📝 Create report
        BugReport report = new BugReport();
        report.setCodeSnippet(request.getCodeSnippet());
        report.setIssueDescription("Possible runtime issue detected.");
        report.setAiSuggestion("Add proper null checks and exception handling.");

        // 🔗 Link report to logged-in user
        report.setUser(user);

        BugReport saved = repository.save(report);

        return mapToResponse(saved);
    }

    public List<BugReportResponse> getAll() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return repository.findAll()
                .stream()
                .filter(bug -> bug.getUser().getId().equals(user.getId()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BugReportResponse mapToResponse(BugReport report) {

        BugReportResponse response = new BugReportResponse();
        response.setId(report.getId());
        response.setCodeSnippet(report.getCodeSnippet());
        response.setIssueDescription(report.getIssueDescription());
        response.setAiSuggestion(report.getAiSuggestion());
        response.setCreatedAt(report.getCreatedAt());
        return response;
    }

    public BugReportResponse getById(Long id) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        BugReport report = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found"));

        if (!report.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        return mapToResponse(report);
    }
}