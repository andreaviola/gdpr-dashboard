package it.ing.av.gdpr.gdprdashboard.resolver;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityIdResolver implements ObjectIdResolver {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private EntityManager entityManager;

	public EntityIdResolver(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void bindItem(final ObjectIdGenerator.IdKey id, final Object pojo) {

	}

	@Override
	public Object resolveId(final ObjectIdGenerator.IdKey id) {
		log.info("id={}", id);
		return this.entityManager.find(id.scope, id.key);
	}

	@Override
	public ObjectIdResolver newForDeserialization(final Object context) {
		return this;
	}

	@Override
	public boolean canUseFor(final ObjectIdResolver resolverType) {
		return false;
	}

}