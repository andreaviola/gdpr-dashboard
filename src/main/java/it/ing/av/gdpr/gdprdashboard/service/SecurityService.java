package it.ing.av.gdpr.gdprdashboard.service;

import javax.servlet.http.HttpServletRequest;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesResults;
import it.ing.av.gdpr.gdprdashboard.model.SecurityControl;

public interface SecurityService {

	// Security controls

	DataTablesResults<SecurityControl> findSecurityControls(HttpServletRequest request);

	SecurityControl findSecurityControl(Long id);

	SecurityControl saveOrUpdate(SecurityControl securityControl);

	void deleteSecurityControl(Long id);

}
