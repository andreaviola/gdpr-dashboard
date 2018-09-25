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

import it.ing.av.gdpr.gdprdashboard.data.SecurityControlRepositoryCustom;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.SecurityControl;

@Repository
public class SecurityControlRepositoryImpl implements SecurityControlRepositoryCustom {

	private static final Logger log = LoggerFactory.getLogger(SecurityControlRepositoryImpl.class);

	private final EntityManager em;

	public SecurityControlRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(SecurityControl.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SecurityControl> findSecurityControls(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder()
				.append("SELECT psc.* FROM pa_security_control psc ORDER BY psc.name ASC");
		log.debug("queryStr={}", queryStr.toString());
		Query query = em.createNativeQuery(
				DataTablesUtil.buildPaginatedSQLQuery(queryStr.toString(), request.getPaginationRequest()),
				SecurityControl.class);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countSecurityControls(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT COUNT(id) FROM pa_security_control");
		Query query = em.createNativeQuery(DataTablesUtil.buildCountSQLQuery(queryStr.toString(), request.getPaginationRequest()));
		return ((BigInteger) query.getSingleResult()).longValue();
	}

}
