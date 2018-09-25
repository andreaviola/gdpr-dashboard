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
import it.ing.av.gdpr.gdprdashboard.model.Device;
import it.ing.av.gdpr.gdprdashboard.service.AssetService;

@Controller
@RequestMapping("/device")
public class DeviceController {

	private static final Logger log = LoggerFactory.getLogger(DeviceController.class);

	@Autowired
	private AssetService assetService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeDevice() {
		log.debug("> homeDevice()");
		return "asset/device-search";
	}

	@RequestMapping(value = "/datatables", method = RequestMethod.GET)
	public @ResponseBody String ajaxDeviceDataTables(HttpServletRequest request) throws IOException {
		log.info("> ajaxDeviceDataTables");
		ObjectMapper mapperNS = new ObjectMapper();
		mapperNS.setDateFormat(DateUtil.sdfDate);
		// Device eventSearchForm =
		// mapperNS.readValue(request.getParameter("eventSearchForm"),
		// Device.class);
		// log.info("> eventSearchForm={}", eventSearchForm);
		ObjectMapper mapper = new ObjectMapper();
		DataTablesResults<Device> dataTableResult = assetService.findDevices(request);
		String jsonString = mapper.writeValueAsString(dataTableResult);
		log.info("> return ajaxDeviceDataTables(): {}", jsonString);
		return jsonString;
	}

}
