package it.ing.av.gdpr.gdprdashboard;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
	
import it.ing.av.gdpr.gdprdashboard.configuration.HttpSessionConfig;

public class HttpSessionInitializer extends AbstractHttpSessionApplicationInitializer {
	
	public HttpSessionInitializer() {
		super(HttpSessionConfig.class);
	}
	
}
