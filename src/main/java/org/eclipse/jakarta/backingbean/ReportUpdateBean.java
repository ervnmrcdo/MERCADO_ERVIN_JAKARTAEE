package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;

@Named
@RequestScoped
public class ReportUpdateBean {
    @NotBlank(message = "You cannot leave the title blank.")
    private static String newTitle;
    private static String newDetail;
    private static int reportID;
    
    @Inject
    private ReportRepository reportRepository;
    
    public String getTitle(){
        return newTitle;
    }
    
    public void setTitle(String title){
        newTitle = title;
    }

    public String getDetail(){
        return newDetail;
    }
    
    public void setDetail(String detail){
        newDetail = detail;
    }
    
    public String update() {
    	reportRepository.update(reportID, newTitle, newDetail);
    	
    	newTitle = "";
    	newDetail = "";
    	return "/reportList.xhtml?faces-redirect=true";
    }
    
    public String redirectToUpdatePage(int id) {
    	ReportDto report = reportRepository.findReport(id);
    	if (report != null) {
    		reportID = report.getId();
    		newTitle = report.getTitle();
    		newDetail = report.getDetail();
    		return "/reportUpdate.xhtml?faces-redirect=true";
    	} else {
    		return "/reportList.xhtml?faces-redirect=true";

    	}
    }
	
}
