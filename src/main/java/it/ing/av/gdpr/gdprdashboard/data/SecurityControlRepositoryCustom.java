package it.ing.av.gdpr.gdprdashboard.data;

import java.util.List;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.model.SecurityControl;

public interface SecurityControlRepositoryCustom {

	List<SecurityControl> findSecurityControls(DataTablesRequest request); //User utente
	Long countSecurityControls(DataTablesRequest request); //User utente
	
}
