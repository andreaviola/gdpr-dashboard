package it.ing.av.gdpr.gdprdashboard.data;

import java.util.List;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.model.OperativeSystem;

public interface OperativeSystemRepositoryCustom {
	
	List<OperativeSystem> findOperativeSystems(DataTablesRequest request); //User utente
	Long countOperativeSystems(DataTablesRequest request); //User utente
	
}
