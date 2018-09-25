package it.ing.av.gdpr.gdprdashboard.data;

import org.springframework.data.repository.CrudRepository;

import it.ing.av.gdpr.gdprdashboard.model.Building;

public interface BuildingRepository extends CrudRepository<Building, Long>, BuildingRepositoryCustom {

}