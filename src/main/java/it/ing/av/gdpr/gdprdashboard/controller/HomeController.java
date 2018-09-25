package it.ing.av.gdpr.gdprdashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.ing.av.gdpr.gdprdashboard.util.Util;
import it.ing.av.gdpr.gdprdashboard.util.Util.SidebarType;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		log.info("> home()");
		return "home/home";
	}

	@RequestMapping(value = "/sidebar-view", method = RequestMethod.GET)
	public @ResponseBody String sidebarView(@RequestParam SidebarType sidebarType, HttpServletRequest request) {
		log.info("Aggiornamento sidebar view -> sidebarView(" + sidebarType + ")");
		Util.setSidebarStatus(request, sidebarType);
		return Util.getSidebarStatus(request).toString();
	}

}
