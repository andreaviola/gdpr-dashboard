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

import it.ing.av.gdpr.gdprdashboard.data.OperativeSystemRepositoryCustom;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.OperativeSystem;

@Repository
public class OperativeSystemRepositoryImpl implements OperativeSystemRepositoryCustom {

	private static final Logger log = LoggerFactory.getLogger(OperativeSystemRepositoryImpl.class);

	private final EntityManager em;

	public OperativeSystemRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(OperativeSystem.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OperativeSystem> findOperativeSystems(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder()
				.append("SELECT pos.* FROM pa_operative_system pos ORDER BY pos.name ASC");
		log.debug("queryStr={}", queryStr.toString());
		Query query = em.createNativeQuery(
				DataTablesUtil.buildPaginatedSQLQuery(queryStr.toString(), request.getPaginationRequest()),
				OperativeSystem.class);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countOperativeSystems(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT COUNT(id) FROM pa_operative_system");
		Query query = em.createNativeQuery(DataTablesUtil.buildCountSQLQuery(queryStr.toString(), request.getPaginationRequest()));
		return ((BigInteger) query.getSingleResult()).longValue();
	}

}
