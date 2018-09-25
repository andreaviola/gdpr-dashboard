package it.ing.av.gdpr.gdprdashboard.data;

import java.util.List;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.model.Server;

public interface ServerRepositoryCustom {

	List<Server> findServers(DataTablesRequest request); //User utente
	Long countServers(DataTablesRequest request); //User utente
	
}
