package org.eclipse.jakarta.infrastracture.repository;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.eclipse.jakarta.dto.ReportDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {
    private List<ReportDto> reports = new ArrayList<>();

    public List<ReportDto> findAll() {
        return reports;
    }

    public void create(ReportDto report) {
        reports.add(report);
    }
    
    public void delete(ReportDto report) {
    	if(reports.contains(report)) {
    		reports.remove(report);
    	}
    }
}