package it.ing.av.gdpr.gdprdashboard.util;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static enum SidebarType {
		collapsed, expanded
	};

	public static String SIDEBAR_STATUS = "SIDEBAR_STATUS";

	public static void setSidebarStatus(HttpServletRequest request, SidebarType sidebarType) {
		request.getSession().setAttribute(SIDEBAR_STATUS, sidebarType);
	}

	public static SidebarType getSidebarStatus(HttpServletRequest request) {
		return (SidebarType) request.getSession().getAttribute(SIDEBAR_STATUS);
	}

}
