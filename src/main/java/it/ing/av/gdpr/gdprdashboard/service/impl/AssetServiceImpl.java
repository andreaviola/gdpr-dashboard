package it.ing.av.gdpr.gdprdashboard.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ing.av.gdpr.gdprdashboard.data.ApplicationRepository;
import it.ing.av.gdpr.gdprdashboard.data.BuildingRepository;
import it.ing.av.gdpr.gdprdashboard.data.DeviceRepository;
import it.ing.av.gdpr.gdprdashboard.data.OperativeSystemRepository;
import it.ing.av.gdpr.gdprdashboard.data.ServerRepository;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesResults;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.Application;
import it.ing.av.gdpr.gdprdashboard.model.Building;
import it.ing.av.gdpr.gdprdashboard.model.Device;
import it.ing.av.gdpr.gdprdashboard.model.OperativeSystem;
import it.ing.av.gdpr.gdprdashboard.model.Server;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

	private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);

	private OperativeSystemRepository operativeSystemRepository;
	private DeviceRepository deviceRepository;
	private ApplicationRepository applicationRepository;
	private BuildingRepository buildingRepository;
	private ServerRepository serverRepository;

	@Autowired
	public AssetServiceImpl(OperativeSystemRepository operativeSystemRepository, DeviceRepository deviceRepository,
			ApplicationRepository applicationRepository, BuildingRepository buildingRepository,
			ServerRepository serverRepository) {
		this.operativeSystemRepository = operativeSystemRepository;
		this.deviceRepository = deviceRepository;
		this.applicationRepository = applicationRepository;
		this.buildingRepository = buildingRepository;
		this.serverRepository = serverRepository;
	}

	@Override
	public DataTablesResults<OperativeSystem> findOperativeSystems(HttpServletRequest request) {
		log.info("> DataTablesResults<OperativeSystem> findOperativeSystems(request={})", request);
		DataTablesRequest dataTablesRequest = new DataTablesRequest(request);

		List<OperativeSystem> operativeSystems = Collections.emptyList();
		Long count = operativeSystemRepository.countOperativeSystems(dataTablesRequest);

		if (count > 0)
			operativeSystems = operativeSystemRepository.findOperativeSystems(dataTablesRequest);

		DataTablesResults<OperativeSystem> dataTableResult = new DataTablesResults<OperativeSystem>();
		dataTableResult.setDraw(dataTablesRequest.getDraw());
		dataTableResult.setListOfDataObjects(operativeSystems);
		dataTableResult.setRecordsTotal(Long.toString(operativeSystemRepository.count()));
		if (!DataTablesUtil.isObjectEmpty(operativeSystems)) {
			if (dataTablesRequest.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
			} else {
				dataTableResult.setRecordsFiltered(count.toString());
			}
		} else {
			dataTableResult.setRecordsFiltered(Integer.toString(0));
		}
		log.info("> return findOperativeSystems() = {}", dataTableResult);
		return dataTableResult;
	}

	@Override
	public OperativeSystem findOperativeSystem(Long id) {
		return operativeSystemRepository.findById(id).orElse(null);
	}

	@Override
	public OperativeSystem saveOrUpdate(OperativeSystem operativeSystem) {
		log.info("> OperativeSystem saveOrUpdate(OperativeSystem operativeSystem={})", operativeSystem);
		if (operativeSystem == null)
			throw new IllegalArgumentException("entity to save or update can not be null");

		OperativeSystem savedOperativeSystem = null;
		if (operativeSystem.isNew()) {
			savedOperativeSystem = operativeSystem;
		} else {
			savedOperativeSystem = operativeSystemRepository.findById(operativeSystem.getId()).orElse(null);
			if (savedOperativeSystem != null) {
				savedOperativeSystem.setCode(operativeSystem.getCode());
				savedOperativeSystem.setName(operativeSystem.getName());
				savedOperativeSystem.setNotes(operativeSystem.getNotes());
			}
		}
		savedOperativeSystem = operativeSystemRepository.save(savedOperativeSystem);
		log.info("> return saveOrUpdate() = {}", savedOperativeSystem);
		return savedOperativeSystem;

	}

	@Override
	public void deleteOperativeSystem(Long id) {
		log.info("> deleteOperativeSystem(id={})", id);
		operativeSystemRepository.deleteById(id);
	}

	@Override
	public DataTablesResults<Device> findDevices(HttpServletRequest request) {
		log.info("> DataTablesResults<Device> findDevices(request={})", request);
		DataTablesRequest dataTablesRequest = new DataTablesRequest(request);

		List<Device> devices = Collections.emptyList();
		Long count = deviceRepository.countDevices(dataTablesRequest);

		if (count > 0)
			devices = deviceRepository.findDevices(dataTablesRequest);

		DataTablesResults<Device> dataTableResult = new DataTablesResults<Device>();
		dataTableResult.setDraw(dataTablesRequest.getDraw());
		dataTableResult.setListOfDataObjects(devices);
		dataTableResult.setRecordsTotal(Long.toString(deviceRepository.count()));
		if (!DataTablesUtil.isObjectEmpty(devices)) {
			if (dataTablesRequest.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
			} else {
				dataTableResult.setRecordsFiltered(count.toString());
			}
		} else {
			dataTableResult.setRecordsFiltered(Integer.toString(0));
		}
		log.info("> return findDevices() = {}", dataTableResult);
		return dataTableResult;
	}

	@Override
	public DataTablesResults<Application> findApplications(HttpServletRequest request) {
		log.info("> DataTablesResults<Application> findApplications(request={})", request);
		DataTablesRequest dataTablesRequest = new DataTablesRequest(request);

		List<Application> applications = Collections.emptyList();
		Long count = applicationRepository.countApplications(dataTablesRequest);

		if (count > 0)
			applications = applicationRepository.findApplications(dataTablesRequest);

		DataTablesResults<Application> dataTableResult = new DataTablesResults<Application>();
		dataTableResult.setDraw(dataTablesRequest.getDraw());
		dataTableResult.setListOfDataObjects(applications);
		dataTableResult.setRecordsTotal(Long.toString(applicationRepository.count()));
		if (!DataTablesUtil.isObjectEmpty(applications)) {
			if (dataTablesRequest.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
			} else {
				dataTableResult.setRecordsFiltered(count.toString());
			}
		} else {
			dataTableResult.setRecordsFiltered(Integer.toString(0));
		}
		log.info("> return findApplications() = {}", dataTableResult);
		return dataTableResult;
	}
	
	@Override
	public Application findApplication(Long id) {
		return applicationRepository.findById(id).orElse(null);
	}
	
	@Override
	public DataTablesResults<Building> findBuildings(HttpServletRequest request) {
		log.info("> DataTablesResults<Building> findBuildings(request={})", request);
		DataTablesRequest dataTablesRequest = new DataTablesRequest(request);

		List<Building> buildings = Collections.emptyList();
		Long count = buildingRepository.countBuildings(dataTablesRequest);

		if (count > 0)
			buildings = buildingRepository.findBuildings(dataTablesRequest);

		DataTablesResults<Building> dataTableResult = new DataTablesResults<Building>();
		dataTableResult.setDraw(dataTablesRequest.getDraw());
		dataTableResult.setListOfDataObjects(buildings);
		dataTableResult.setRecordsTotal(Long.toString(buildingRepository.count()));
		if (!DataTablesUtil.isObjectEmpty(buildings)) {
			if (dataTablesRequest.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
			} else {
				dataTableResult.setRecordsFiltered(count.toString());
			}
		} else {
			dataTableResult.setRecordsFiltered(Integer.toString(0));
		}
		log.info("> return findBuildings() = {}", dataTableResult);
		return dataTableResult;
	}

	@Override
	public Building findBuilding(Long id) {
		return buildingRepository.findById(id).orElse(null);
	}

	@Override
	public Building saveOrUpdate(Building operativeSystem) {
		log.info("> Building saveOrUpdate(Building operativeSystem={})", operativeSystem);
		if (operativeSystem == null)
			throw new IllegalArgumentException("entity to save or update can not be null");

		Building savedBuilding = null;
		if (operativeSystem.isNew()) {
			savedBuilding = operativeSystem;
		} else {
			savedBuilding = buildingRepository.findById(operativeSystem.getId()).orElse(null);
			if (savedBuilding != null) {
				savedBuilding.setCode(operativeSystem.getCode());
				savedBuilding.setName(operativeSystem.getName());
				savedBuilding.setAddress(operativeSystem.getAddress());
				savedBuilding.setType(operativeSystem.getType());
				savedBuilding.setNotes(operativeSystem.getNotes());
			}
		}
		savedBuilding = buildingRepository.save(savedBuilding);
		log.info("> return saveOrUpdate() = {}", savedBuilding);
		return savedBuilding;

	}

	@Override
	public void deleteBuilding(Long id) {
		log.info("> deleteBuilding(id={})", id);
		buildingRepository.deleteById(id);
	}

	@Override
	public DataTablesResults<Server> findServers(HttpServletRequest request) {
		log.info("> DataTablesResults<Server> findServers(request={})", request);
		DataTablesRequest dataTablesRequest = new DataTablesRequest(request);

		List<Server> buildings = Collections.emptyList();
		Long count = serverRepository.countServers(dataTablesRequest);

		if (count > 0)
			buildings = serverRepository.findServers(dataTablesRequest);

		DataTablesResults<Server> dataTableResult = new DataTablesResults<Server>();
		dataTableResult.setDraw(dataTablesRequest.getDraw());
		dataTableResult.setListOfDataObjects(buildings);
		dataTableResult.setRecordsTotal(Long.toString(buildingRepository.count()));
		if (!DataTablesUtil.isObjectEmpty(buildings)) {
			if (dataTablesRequest.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
			} else {
				dataTableResult.setRecordsFiltered(count.toString());
			}
		} else {
			dataTableResult.setRecordsFiltered(Integer.toString(0));
		}
		log.info("> return findServers() = {}", dataTableResult);
		return dataTableResult;
	}

	@Override
	public Server findServer(Long id) {
		return serverRepository.findById(id).orElse(null);
	}

	@Override
	public Server saveOrUpdate(Server server) {
		log.info("> Server saveOrUpdate(Server operativeSystem={})", server);
		if (server == null)
			throw new IllegalArgumentException("entity to save or update can not be null");

		Server savedServer = null;
		if (server.isNew()) {
			savedServer = server;
		} else {
			savedServer = serverRepository.findById(server.getId()).orElse(null);
			if (savedServer != null) {
				savedServer.setCode(server.getCode());
				savedServer.setBrand(server.getBrand());
				savedServer.setIpAddress(server.getIpAddress());
				savedServer.setModel(server.getModel());
				savedServer.setSerialNumber(server.getSerialNumber());
				savedServer.setType(server.getType());
				savedServer.setNotes(server.getNotes());
				savedServer.setApplications(server.getApplications());
				savedServer.setOperativeSystem(server.getOperativeSystem());
			}
		}
		savedServer = serverRepository.save(savedServer);
		log.info("> return saveOrUpdate() = {}", savedServer);
		return savedServer;

	}

	@Override
	public void deleteServer(Long id) {
		log.info("> deleteServer(id={})", id);
		serverRepository.deleteById(id);
	}

	@Override
	public List<Application> findApplicationsList() {
		List<Application> applicationsList = new ArrayList<Application>();
		applicationRepository.findAll().forEach(applicationsList::add);
		return applicationsList;
	}

}
