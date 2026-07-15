package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;


@Named
@RequestScoped
public class ReportViewBean {
    private static ReportDto report;
    
    @Inject
    private ReportRepository reportRepository;
    
    public String getTitle() {
    	return report.getTitle();
    }
    
    public String getDetail() {
    	return report.getDetail();
    }
    
    public String redirectToViewPage(int id) {
    	report = reportRepository.findReport(id);
    	return (report != null) ?  "/reportView.xhtml?faces-redirect=true" : "reportList.xhtml?faces-redirect=true";
    }
    
    public String redirectToListPage() {
    	return "/reportList.xhtml?faces-redirect=true";
    }
}
