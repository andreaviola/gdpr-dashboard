package it.ing.av.gdpr.gdprdashboard.data;

import org.springframework.data.repository.CrudRepository;

import it.ing.av.gdpr.gdprdashboard.model.Device;

public interface DeviceRepository extends CrudRepository<Device, Long>,DeviceRepositoryCustom {

}