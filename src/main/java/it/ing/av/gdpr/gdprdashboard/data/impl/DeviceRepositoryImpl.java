package it.ing.av.gdpr.gdprdashboard.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.ing.av.gdpr.gdprdashboard.data.DeviceRepositoryCustom;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.Device;

@Repository
public class DeviceRepositoryImpl implements DeviceRepositoryCustom {

	private static final Logger log = LoggerFactory.getLogger(DeviceRepositoryImpl.class);

	private final EntityManager em;

	public DeviceRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Device.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Device> findDevices(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT d FROM Device d");
		// .append("SELECT pa.* FROM pa_device pa ORDER BY pa.model ASC");
		log.debug("queryStr={}", queryStr.toString());
		Query query = em
				.createQuery(DataTablesUtil.buildPaginatedQuery(queryStr.toString(), request.getPaginationRequest()),
						Device.class)
				.setFirstResult(request.getPaginationRequest().getPageNumber())
				.setMaxResults(request.getPaginationRequest().getPageSize());
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countDevices(DataTablesRequest request) {
		StringBuilder queryStr = new StringBuilder().append("SELECT COUNT(d.id) FROM Device d");
		Query query = em
				.createQuery(DataTablesUtil.buildCountQuery(queryStr.toString(), request.getPaginationRequest()));
		return (Long)query.getSingleResult();
	}

}
