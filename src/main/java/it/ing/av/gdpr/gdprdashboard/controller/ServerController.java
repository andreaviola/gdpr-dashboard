package it.ing.av.gdpr.gdprdashboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ing.av.gdpr.gdprdashboard.controller.validation.ValidationResponseUtils;
import it.ing.av.gdpr.gdprdashboard.datatables.DataTablesResults;
import it.ing.av.gdpr.gdprdashboard.model.Application;
import it.ing.av.gdpr.gdprdashboard.model.Server;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationResponse;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationStatus;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;
import it.ing.av.gdpr.gdprdashboard.util.DateUtil;

@Controller
@RequestMapping("/server")
public class ServerController {

	private static final Logger log = LoggerFactory.getLogger(ServerController.class);

	@Autowired
	private AssetService assetService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeServer(Model model) {
		log.debug("> homeServer()");
		model.addAttribute("server", new Server());
		return "asset/server-search";
	}

	@ModelAttribute(name = "applicationsList")
	public List<Application> applicationsList() {
		return assetService.findApplicationsList();
	}

	@RequestMapping(value = "/datatables", method = RequestMethod.GET)
	public @ResponseBody String ajaxServerDataTables(HttpServletRequest request) throws IOException {
		log.info("> ajaxServerDataTables");
		ObjectMapper mapperNS = new ObjectMapper();
		mapperNS.setDateFormat(DateUtil.sdfDate);
		// Server eventSearchForm =
		// mapperNS.readValue(request.getParameter("eventSearchForm"),
		// Server.class);
		// log.info("> eventSearchForm={}", eventSearchForm);
		ObjectMapper mapper = new ObjectMapper();
		DataTablesResults<Server> dataTableResult = assetService.findServers(request);
		String jsonString = mapper.writeValueAsString(dataTableResult);
		log.info("> return ajaxServerDataTables(): {}", jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Server ajaxFindServer(@PathVariable(name = "id", required = true) Long id) {
		return assetService.findServer(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody Map<String, Object> processSaveServer(@Valid @RequestBody Server server,
			BindingResult result) {

		log.info("> processSaveServer(server={})", server);

		Map<String, Object> ajaxResponse = new HashMap<String, Object>();

		ValidationResponse validationResponse = ValidationResponseUtils.validation(result);

		if (validationResponse.isSuccess()) {
			server = assetService.saveOrUpdate(server);
		}
		ajaxResponse.put("validationResponse", validationResponse);
		ajaxResponse.put("server", server);

		return ajaxResponse;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ValidationStatus ajaxDeleteServer(@PathVariable(name = "id", required = true) Long id) {
		assetService.deleteServer(id);
		return ValidationStatus.SUCCESS;
	}

}
