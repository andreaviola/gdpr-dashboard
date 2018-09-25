package it.ing.av.gdpr.gdprdashboard.data;

import org.springframework.data.repository.CrudRepository;

import it.ing.av.gdpr.gdprdashboard.model.SecurityControl;

public interface SecurityControlRepository extends CrudRepository<SecurityControl, Long>, SecurityControlRepositoryCustom {

}