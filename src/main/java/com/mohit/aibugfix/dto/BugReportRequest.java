package com.mohit.aibugfix.dto;

import jakarta.validation.constraints.NotBlank;

public class BugReportRequest {

    @NotBlank
    private String codeSnippet;

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }
}