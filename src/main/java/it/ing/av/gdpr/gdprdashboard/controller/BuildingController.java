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
import it.ing.av.gdpr.gdprdashboard.model.Building;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationResponse;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationStatus;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;
import it.ing.av.gdpr.gdprdashboard.util.DateUtil;

@Controller
@RequestMapping("/building")
public class BuildingController {

	private static final Logger log = LoggerFactory.getLogger(BuildingController.class);

	@Autowired
	private AssetService assetService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeBuilding(Model model) {
		log.debug("> homeBuilding()");
		model.addAttribute("building", new Building());
		return "asset/building-search";
	}

	@RequestMapping(value = "/datatables", method = RequestMethod.GET)
	public @ResponseBody String ajaxBuildingDataTables(HttpServletRequest request) throws IOException {
		log.info("> ajaxBuildingDataTables");
		ObjectMapper mapperNS = new ObjectMapper();
		mapperNS.setDateFormat(DateUtil.sdfDate);
		// Building eventSearchForm =
		// mapperNS.readValue(request.getParameter("eventSearchForm"),
		// Building.class);
		// log.info("> eventSearchForm={}", eventSearchForm);
		ObjectMapper mapper = new ObjectMapper();
		DataTablesResults<Building> dataTableResult = assetService.findBuildings(request);
		String jsonString = mapper.writeValueAsString(dataTableResult);
		log.info("> return ajaxBuildingDataTables(): {}", jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Building ajaxFindBuilding(@PathVariable(name = "id", required = true) Long id) {
		return assetService.findBuilding(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Map<String, Object> processSaveBuilding(
			@PathVariable(name = "id", required = true) Long id, @Valid Building building,
			BindingResult result) {

		log.info("> processSaveBuilding(building={})", building);

		Map<String, Object> ajaxResponse = new HashMap<String, Object>();

		ValidationResponse validationResponse = ValidationResponseUtils.validation(result);

		if (validationResponse.isSuccess()) {
			building = assetService.saveOrUpdate(building);
		}
		ajaxResponse.put("validationResponse", validationResponse);
		ajaxResponse.put("building", building);

		return ajaxResponse;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ValidationStatus ajaxDeleteBuilding(
			@PathVariable(name = "id", required = true) Long id) {
		assetService.deleteBuilding(id);
		return ValidationStatus.SUCCESS;
	}

}
