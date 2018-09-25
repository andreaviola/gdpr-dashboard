package it.ing.av.gdpr.gdprdashboard.data;

import java.util.List;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.model.Application;

public interface ApplicationRepositoryCustom {

	List<Application> findApplications(DataTablesRequest request);
	Long countApplications(DataTablesRequest request);
	
}
