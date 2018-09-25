package it.ing.av.gdpr.gdprdashboard.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableJpaAuditing
public class GdprDashboardConfig {

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ITALY);
		return slr;
	}

//	@Bean
//	public AuditorAware<AuditableUser> auditorProvider() {
//		return new AuditorAwareImpl();
//	}

}
