package it.ing.av.gdpr.gdprdashboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ing.av.gdpr.gdprdashboard.controller.validation.ValidationResponseUtils;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesResults;
import it.ing.av.gdpr.gdprdashboard.model.SecurityControl;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationResponse;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationStatus;
import it.ing.av.gdpr.gdprdashboard.service.SecurityService;
import it.ing.av.gdpr.gdprdashboard.util.DateUtil;

@Controller
@RequestMapping("/security-control")
public class SecurityControlController {

	private static final Logger log = LoggerFactory.getLogger(SecurityControlController.class);

	@Autowired
	private SecurityService securityService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeSecurityControl(Model model) {
		log.debug("> homeSecurityControl()");
		model.addAttribute("securityControl", new SecurityControl());
		return "security/security-control-search";
	}

	@RequestMapping(value = "/datatables", method = RequestMethod.GET)
	public @ResponseBody String ajaxSecurityControlDataTables(HttpServletRequest request) throws IOException {
		log.info("> ajaxSecurityControlDataTables");
		ObjectMapper mapperNS = new ObjectMapper();
		mapperNS.setDateFormat(DateUtil.sdfDate);
		// SecurityControl eventSearchForm =
		// mapperNS.readValue(request.getParameter("eventSearchForm"),
		// SecurityControl.class);
		// log.info("> eventSearchForm={}", eventSearchForm);
		ObjectMapper mapper = new ObjectMapper();
		DataTablesResults<SecurityControl> dataTableResult = securityService.findSecurityControls(request);
		String jsonString = mapper.writeValueAsString(dataTableResult);
		log.info("> return ajaxSecurityControlDataTables(): {}", jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody SecurityControl ajaxFindSecurityControl(@PathVariable(name = "id", required = true) Long id) {
		return securityService.findSecurityControl(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Map<String, Object> processSaveSecurityControl(
			@PathVariable(name = "id", required = true) Long id, @Valid SecurityControl securityControl,
			BindingResult result) {

		log.info("> processSaveSecurityControl(securityControl={})", securityControl);

		Map<String, Object> ajaxResponse = new HashMap<String, Object>();

		ValidationResponse validationResponse = ValidationResponseUtils.validation(result);

		if (validationResponse.isSuccess()) {
			securityControl = securityService.saveOrUpdate(securityControl);
		}
		ajaxResponse.put("validationResponse", validationResponse);
		ajaxResponse.put("securityControl", securityControl);

		return ajaxResponse;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ValidationStatus ajaxDeleteSecurityControl(
			@PathVariable(name = "id", required = true) Long id) {
		securityService.deleteSecurityControl(id);
		return ValidationStatus.SUCCESS;
	}

}
