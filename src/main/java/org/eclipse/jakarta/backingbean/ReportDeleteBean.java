package org.eclipse.jakarta.backingbean;
import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;


@Named
@RequestScoped
public class ReportDeleteBean {
    
    @Inject
    private ReportRepository reportRepository;
    
    public String delete(int id) {
    	//  the actual report object gets passed
    	reportRepository.delete(id);
        return "/reportList.xhtml?faces-redirect=true";
    }
}
