package org.eclipse.jakarta.infrastracture.repository;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.eclipse.jakarta.dto.ReportDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {
    private List<ReportDto> reports = new ArrayList<>();
    private int idCounter;

    public List<ReportDto> findAll() {
        return reports;
    }
    
    public ReportDto findReport (int id) {
    	for(ReportDto report: reports) {
    		if (report.getId() == id) return report;
    	}
    	return null;
    }
    

    public void create(ReportDto report) {
    	report.setId(++idCounter);
        reports.add(report);
    }
    
    public void delete(int id) {
    	ReportDto report = findReport(id);
    	if (report != null) reports.remove(report);
    }
    
    public void update(int id, String newTitle, String newDetail) {
    	ReportDto report = findReport(id);
    	if (report != null) {
    		report.setTitle(newTitle);
    		report.setDetail(newDetail);
    	}	
    }
    
}