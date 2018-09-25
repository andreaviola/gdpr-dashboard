package it.ing.av.gdpr.gdprdashboard.data.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.ing.av.gdpr.gdprdashboard.data.BuildingRepositoryCustom;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.Building;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

	private static final Logger log = LoggerFactory.getLogger(BuildingRepositoryImpl.class);

	private final EntityManager em;

	public BuildingRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Building.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Building> findBuildings(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT pb.* FROM pa_building pb ORDER BY pb.name ASC");
		log.debug("queryStr={}", queryStr.toString());
		Query query = em.createNativeQuery(
				DataTablesUtil.buildPaginatedSQLQuery(queryStr.toString(), request.getPaginationRequest()),
				Building.class);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countBuildings(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT COUNT(id) FROM pa_building");
		Query query = em.createNativeQuery(
				DataTablesUtil.buildCountSQLQuery(queryStr.toString(), request.getPaginationRequest()));
		return ((BigInteger) query.getSingleResult()).longValue();
	}

}
