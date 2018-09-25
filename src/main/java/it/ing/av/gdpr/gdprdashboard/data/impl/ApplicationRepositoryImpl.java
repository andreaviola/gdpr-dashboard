package it.ing.av.gdpr.gdprdashboard.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.ing.av.gdpr.gdprdashboard.data.ApplicationRepositoryCustom;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.Application;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {

	private static final Logger log = LoggerFactory.getLogger(ApplicationRepositoryImpl.class);

	private final EntityManager em;

	public ApplicationRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Application.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Application> findApplications(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT d FROM Application d");
		log.debug("queryStr={}", queryStr.toString());
		Query query = em
				.createQuery(DataTablesUtil.buildPaginatedQuery(queryStr.toString(), request.getPaginationRequest()),
						Application.class)
				.setFirstResult(request.getPaginationRequest().getPageNumber())
				.setMaxResults(request.getPaginationRequest().getPageSize());
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countApplications(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT COUNT(d.id) FROM Application d");
		Query query = em
				.createQuery(DataTablesUtil.buildCountQuery(queryStr.toString(), request.getPaginationRequest()));
		return (Long)query.getSingleResult();
	}

}
