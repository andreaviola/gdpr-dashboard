package it.ing.av.gdpr.gdprdashboard.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import it.ing.av.gdpr.gdprdashboard.model.Application;

public class ApplicationFormatter implements Formatter<Application> {

	private static final Logger log = LoggerFactory.getLogger(ApplicationFormatter.class);

	@Override
	public String print(Application application, Locale locale) {
		log.info("> print(application={}, locale={})", application, locale);
		return application.getId().toString();
	}

	@Override
	public Application parse(String id, Locale locale) throws ParseException {
		log.info("> parse(id={}, locale={})", id, locale);
		Application application = new Application();
		application.setId(Long.valueOf(id));
		return application;
	}
}