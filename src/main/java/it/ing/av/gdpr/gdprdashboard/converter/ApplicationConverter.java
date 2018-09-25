package it.ing.av.gdpr.gdprdashboard.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import it.ing.av.gdpr.gdprdashboard.model.Application;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;

public class ApplicationConverter implements Converter<String, Application> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private AssetService assetService;

	public ApplicationConverter(AssetService assetService) {
		this.assetService = assetService;
	}

	@Override
	public Application convert(String id) {
		log.info("id={}", id);
		Application application = assetService.findApplication(Long.valueOf(id));
		log.info("application={}", application);
		return application;
	}
}