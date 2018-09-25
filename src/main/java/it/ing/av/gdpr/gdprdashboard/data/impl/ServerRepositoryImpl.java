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

import it.ing.av.gdpr.gdprdashboard.data.ServerRepositoryCustom;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.Server;

@Repository
public class ServerRepositoryImpl implements ServerRepositoryCustom {

	private static final Logger log = LoggerFactory.getLogger(ServerRepositoryImpl.class);

	private final EntityManager em;

	public ServerRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Server.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Server> findServers(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder()
				.append("SELECT ps.* FROM pa_server ps ORDER BY ps.model ASC"); //LEFT JOIN pa_server_application psa ON ps.id = psa.server_id 
		log.debug("queryStr={}", queryStr.toString());
		Query query = em.createNativeQuery(
				DataTablesUtil.buildPaginatedSQLQuery(queryStr.toString(), request.getPaginationRequest()),
				Server.class);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countServers(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT COUNT(id) FROM pa_server");
		Query query = em.createNativeQuery(DataTablesUtil.buildCountSQLQuery(queryStr.toString(), request.getPaginationRequest()));
		return ((BigInteger) query.getSingleResult()).longValue();
	}

}
