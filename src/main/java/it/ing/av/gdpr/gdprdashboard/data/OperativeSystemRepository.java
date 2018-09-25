package it.ing.av.gdpr.gdprdashboard.data;

import org.springframework.data.repository.CrudRepository;

import it.ing.av.gdpr.gdprdashboard.model.OperativeSystem;

public interface OperativeSystemRepository extends CrudRepository<OperativeSystem, Long>, OperativeSystemRepositoryCustom {

}