package it.ing.av.gdpr.gdprdashboard.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import it.ing.av.gdpr.gdprdashboard.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesResults;
import it.ing.av.gdpr.gdprdashboard.model.Application;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;

@Controller
@RequestMapping("/application")
public class ApplicationController {

	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private AssetService assetService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeApplication() {
		log.debug("> homeApplication()");
		return "asset/application-search";
	}

	@RequestMapping(value = "/datatables", method = RequestMethod.GET)
	public @ResponseBody String ajaxApplicationDataTables(HttpServletRequest request) throws IOException {
		log.info("> ajaxApplicationDataTables");
		ObjectMapper mapperNS = new ObjectMapper();
		mapperNS.setDateFormat(DateUtil.sdfDate);
		// Application eventSearchForm =
		// mapperNS.readValue(request.getParameter("eventSearchForm"),
		// Application.class);
		// log.info("> eventSearchForm={}", eventSearchForm);
		ObjectMapper mapper = new ObjectMapper();
		DataTablesResults<Application> dataTableResult = assetService.findApplications(request);
		String jsonString = mapper.writeValueAsString(dataTableResult);
		log.info("> return ajaxApplicationDataTables(): {}", jsonString);
		return jsonString;
	}

}
