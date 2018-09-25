package it.ing.av.gdpr.gdprdashboard.data;

import java.util.List;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.model.Device;

public interface DeviceRepositoryCustom {

	List<Device> findDevices(DataTablesRequest request);
	Long countDevices(DataTablesRequest request);
	
}
