package it.ing.av.gdpr.gdprdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "it.ing.av.gdpr.gdprdashboard" })
public class GdprDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdprDashboardApplication.class, args);
	}
}
