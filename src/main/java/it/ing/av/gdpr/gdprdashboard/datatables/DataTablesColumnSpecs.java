package it.ing.av.gdpr.gdprdashboard.datatables;

import javax.servlet.http.HttpServletRequest;

public class DataTablesColumnSpecs {

	private int index;
	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	private String search;
	private boolean regex;
	private String sortDir;

	public DataTablesColumnSpecs(HttpServletRequest request, int i) {
		this.setIndex(i);
		prepareColumnSpecs(request, i);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public boolean isOrderable() {
		return orderable;
	}

	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isRegex() {
		return regex;
	}

	public void setRegex(boolean regex) {
		this.regex = regex;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = (null != sortDir) ? sortDir.toUpperCase() : sortDir;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private void prepareColumnSpecs(HttpServletRequest request, int i) {

		this.setData(request.getParameter("columns[" + i + "][data]"));
		this.setName(request.getParameter("columns[" + i + "][name]"));
		this.setOrderable(Boolean.valueOf(request.getParameter("columns[" + i + "][orderable]")));
		this.setRegex(Boolean.valueOf(request.getParameter("columns[" + i + "][search][regex]")));
		this.setSearch(request.getParameter("columns[" + i + "][search][value]"));
		this.setSearchable(Boolean.valueOf(request.getParameter("columns[" + i + "][searchable]")));

		int sortableCol = Integer.parseInt(
				request.getParameter("order[0][column]") != null ? request.getParameter("order[0][column]") : "0");
		String sortDir = request.getParameter("order[0][dir]") != null ? request.getParameter("order[0][dir]") : "ASC";

		if (i == sortableCol) {
			this.setSortDir(sortDir);
		}
	}

}