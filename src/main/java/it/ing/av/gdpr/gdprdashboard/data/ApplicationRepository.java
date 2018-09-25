package it.ing.av.gdpr.gdprdashboard.data;

import org.springframework.data.repository.CrudRepository;

import it.ing.av.gdpr.gdprdashboard.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long>,ApplicationRepositoryCustom {

}
