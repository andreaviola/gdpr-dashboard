package it.ing.av.gdpr.gdprdashboard.service.impl;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ing.av.gdpr.gdprdashboard.data.SecurityControlRepository;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesRequest;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesResults;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesUtil;
import it.ing.av.gdpr.gdprdashboard.model.SecurityControl;
import it.ing.av.gdpr.gdprdashboard.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

	private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

	private SecurityControlRepository securityControlRepository;
	
	@Autowired
	public SecurityServiceImpl(SecurityControlRepository securityControlRepository) {
		this.securityControlRepository = securityControlRepository;
	}

	@Override
	public DataTablesResults<SecurityControl> findSecurityControls(HttpServletRequest request) {
		log.info("> DataTablesResults<SecurityControl> findSecurityControls(request={})", request);
		DataTablesRequest dataTablesRequest = new DataTablesRequest(request);

		List<SecurityControl> operativeSystems = Collections.emptyList();
		Long count = securityControlRepository.countSecurityControls(dataTablesRequest);

		if (count > 0)
			operativeSystems = securityControlRepository.findSecurityControls(dataTablesRequest);

		DataTablesResults<SecurityControl> dataTableResult = new DataTablesResults<SecurityControl>();
		dataTableResult.setDraw(dataTablesRequest.getDraw());
		dataTableResult.setListOfDataObjects(operativeSystems);
		dataTableResult.setRecordsTotal(Long.toString(securityControlRepository.count()));
		if (!DataTablesUtil.isObjectEmpty(operativeSystems)) {
			if (dataTablesRequest.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
			} else {
				dataTableResult.setRecordsFiltered(count.toString());
			}
		} else {
			dataTableResult.setRecordsFiltered(Integer.toString(0));
		}
		log.info("> return findSecurityControls() = {}", dataTableResult);
		return dataTableResult;
	}

	@Override
	public SecurityControl findSecurityControl(Long id) {
		return securityControlRepository.findById(id).orElse(null);
	}

	@Override
	public SecurityControl saveOrUpdate(SecurityControl operativeSystem) {
		log.info("> SecurityControl saveOrUpdate(SecurityControl operativeSystem={})", operativeSystem);
		if (operativeSystem == null)
			throw new IllegalArgumentException("entity to save or update can not be null");

		SecurityControl savedSecurityControl = null;
		if (operativeSystem.isNew()) {
			savedSecurityControl = operativeSystem;
		} else {
			savedSecurityControl = securityControlRepository.findById(operativeSystem.getId()).orElse(null);
			if (savedSecurityControl != null) {
				savedSecurityControl.setCode(operativeSystem.getCode());
				savedSecurityControl.setName(operativeSystem.getName());
				savedSecurityControl.setType(operativeSystem.getType());
				savedSecurityControl.setNotes(operativeSystem.getNotes());
			}
		}
		savedSecurityControl = securityControlRepository.save(savedSecurityControl);
		log.info("> return saveOrUpdate() = {}", savedSecurityControl);
		return savedSecurityControl;

	}

	@Override
	public void deleteSecurityControl(Long id) {
		log.info("> deleteSecurityControl(id={})", id);
		securityControlRepository.deleteById(id);
	}

}
