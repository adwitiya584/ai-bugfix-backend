package com.mohit.aibugfix.repository;

import com.mohit.aibugfix.entity.BugReport;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BugReportRepository extends JpaRepository<BugReport, Long> {


}