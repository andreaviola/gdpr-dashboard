package it.ing.av.gdpr.gdprdashboard.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.ing.av.gdpr.gdprdashboard.converter.ApplicationConverter;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;

@Configuration
@ComponentScan("it.ing.av.gdpr.gdprdashboard")
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private AssetService assetService;
	
	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
		//formatterRegistry.addFormatter(new ApplicationFormatter());
		formatterRegistry.addConverter(new ApplicationConverter(assetService));
	}
}