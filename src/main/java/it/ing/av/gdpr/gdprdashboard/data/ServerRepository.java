package it.ing.av.gdpr.gdprdashboard.data;

import org.springframework.data.repository.CrudRepository;

import it.ing.av.gdpr.gdprdashboard.model.Server;

public interface ServerRepository extends CrudRepository<Server, Long>, ServerRepositoryCustom {

}