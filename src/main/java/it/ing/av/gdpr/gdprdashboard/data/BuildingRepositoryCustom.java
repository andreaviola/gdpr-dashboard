package it.ing.av.gdpr.gdprdashboard.data;

import java.util.List;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.model.Building;

public interface BuildingRepositoryCustom {

	List<Building> findBuildings(DataTablesRequest request); //User utente
	Long countBuildings(DataTablesRequest request); //User utente
	
}
