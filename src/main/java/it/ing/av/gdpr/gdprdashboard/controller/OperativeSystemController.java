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
import it.ing.av.gdpr.gdprdashboard.model.OperativeSystem;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationResponse;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationStatus;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;
import it.ing.av.gdpr.gdprdashboard.util.DateUtil;

@Controller
@RequestMapping("/operative-system")
public class OperativeSystemController {

	private static final Logger log = LoggerFactory.getLogger(OperativeSystemController.class);

	@Autowired
	private AssetService assetService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeOperativeSystem(Model model) {
		log.debug("> homeOperativeSystem()");
		model.addAttribute("operativeSystem", new OperativeSystem());
		return "asset/operative-system-search";
	}

	@RequestMapping(value = "/datatables", method = RequestMethod.GET)
	public @ResponseBody String ajaxOperativeSystemDataTables(HttpServletRequest request) throws IOException {
		log.info("> ajaxOperativeSystemDataTables");
		ObjectMapper mapperNS = new ObjectMapper();
		mapperNS.setDateFormat(DateUtil.sdfDate);
		// OperativeSystem eventSearchForm =
		// mapperNS.readValue(request.getParameter("eventSearchForm"),
		// OperativeSystem.class);
		// log.info("> eventSearchForm={}", eventSearchForm);
		ObjectMapper mapper = new ObjectMapper();
		DataTablesResults<OperativeSystem> dataTableResult = assetService.findOperativeSystems(request);
		String jsonString = mapper.writeValueAsString(dataTableResult);
		log.info("> return ajaxOperativeSystemDataTables(): {}", jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody OperativeSystem ajaxFindOperativeSystem(@PathVariable(name = "id", required = true) Long id) {
		return assetService.findOperativeSystem(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Map<String, Object> processSaveOperativeSystem(
			@PathVariable(name = "id", required = true) Long id, @Valid OperativeSystem operativeSystem,
			BindingResult result) {

		log.info("> processSaveOperativeSystem(operativeSystem={})", operativeSystem);

		Map<String, Object> ajaxResponse = new HashMap<String, Object>();

		ValidationResponse validationResponse = ValidationResponseUtils.validation(result);

		if (validationResponse.isSuccess()) {
			operativeSystem = assetService.saveOrUpdate(operativeSystem);
		}
		ajaxResponse.put("validationResponse", validationResponse);
		ajaxResponse.put("operativeSystem", operativeSystem);

		return ajaxResponse;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ValidationStatus ajaxDeleteOperativeSystem(
			@PathVariable(name = "id", required = true) Long id) {
		assetService.deleteOperativeSystem(id);
		return ValidationStatus.SUCCESS;
	}

}
