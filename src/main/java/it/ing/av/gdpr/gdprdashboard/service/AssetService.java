package it.ing.av.gdpr.gdprdashboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesResults;
import it.ing.av.gdpr.gdprdashboard.model.Application;
import it.ing.av.gdpr.gdprdashboard.model.Building;
import it.ing.av.gdpr.gdprdashboard.model.Device;
import it.ing.av.gdpr.gdprdashboard.model.OperativeSystem;
import it.ing.av.gdpr.gdprdashboard.model.Server;

public interface AssetService {

	// Operative systems

	DataTablesResults<OperativeSystem> findOperativeSystems(HttpServletRequest request);

	OperativeSystem findOperativeSystem(Long id);

	OperativeSystem saveOrUpdate(OperativeSystem operativeSystem);

	void deleteOperativeSystem(Long id);

	// Devices

	DataTablesResults<Device> findDevices(HttpServletRequest request);

	// Applications

	DataTablesResults<Application> findApplications(HttpServletRequest request);

	List<Application> findApplicationsList();

	Application findApplication(Long id);

	// Building

	DataTablesResults<Building> findBuildings(HttpServletRequest request);

	Building findBuilding(Long id);

	Building saveOrUpdate(Building building);

	void deleteBuilding(Long id);

	// Building

	DataTablesResults<Server> findServers(HttpServletRequest request);

	Server findServer(Long id);

	Server saveOrUpdate(Server server);

	void deleteServer(Long id);

}
