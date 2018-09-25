package it.ing.av.gdpr.gdprdashboard.datatables;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.style.ToStringCreator;

public class DataTablesRequest {

	private String uniqueId;
	private String draw;
	private Integer start;
	private Integer length;
	private String search;
	private boolean regex;
	private List<DataTablesColumnSpecs> columns;
	private DataTablesColumnSpecs order;
	private boolean isGlobalSearch;

	private int maxParamsToCheck = 0;

	public DataTablesRequest(HttpServletRequest request) {
		prepareDataTablesRequest(request);
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
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

	public List<DataTablesColumnSpecs> getColumns() {
		return columns;
	}

	public void setColumns(List<DataTablesColumnSpecs> columns) {
		this.columns = columns;
	}

	public DataTablesColumnSpecs getOrder() {
		return order;
	}

	public void setOrder(DataTablesColumnSpecs order) {
		this.order = order;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public boolean isGlobalSearch() {
		return isGlobalSearch;
	}

	public void setGlobalSearch(boolean isGlobalSearch) {
		this.isGlobalSearch = isGlobalSearch;
	}

	private void prepareDataTablesRequest(HttpServletRequest request) {

		Enumeration<String> parameterNames = request.getParameterNames();

		if (parameterNames.hasMoreElements()) {

			this.setStart(Integer.parseInt(request.getParameter(PaginationCriteria.PAGE_NUMBER)));
			this.setLength(Integer.parseInt(request.getParameter(PaginationCriteria.PAGE_SIZE)));
			this.setUniqueId(request.getParameter("_"));
			this.setDraw(request.getParameter(PaginationCriteria.DRAW));

			this.setSearch(request.getParameter("search[value]"));
			this.setRegex(Boolean.valueOf(request.getParameter("search[regex]")));

			int sortableCol = Integer.parseInt(
					request.getParameter("order[0][column]") != null ? request.getParameter("order[0][column]") : "0");

			List<DataTablesColumnSpecs> columns = new ArrayList<DataTablesColumnSpecs>();

			if (!DataTablesUtil.isObjectEmpty(this.getSearch())) {
				this.setGlobalSearch(true);
			}

			maxParamsToCheck = getNumberOfColumns(request);

			for (int i = 0; i < maxParamsToCheck; i++) {
				if (null != request.getParameter("columns[" + i + "][data]")
						&& !"null".equalsIgnoreCase(request.getParameter("columns[" + i + "][data]"))
						&& !DataTablesUtil.isObjectEmpty(request.getParameter("columns[" + i + "][data]"))) {
					DataTablesColumnSpecs colSpec = new DataTablesColumnSpecs(request, i);
					if (i == sortableCol) {
						this.setOrder(colSpec);
					}
					columns.add(colSpec);

					if (!DataTablesUtil.isObjectEmpty(colSpec.getSearch())) {
						this.setGlobalSearch(false);
					}
				}
			}

			if (!DataTablesUtil.isObjectEmpty(columns)) {
				this.setColumns(columns);
			}
		}
	}

	private int getNumberOfColumns(HttpServletRequest request) {
		Pattern p = Pattern.compile("columns\\[[0-9]+\\]\\[data\\]");
		@SuppressWarnings("rawtypes")
		Enumeration params = request.getParameterNames();
		List<String> lstOfParams = new ArrayList<String>();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			Matcher m = p.matcher(paramName);
			if (m.matches()) {
				lstOfParams.add(paramName);
			}
		}
		return lstOfParams.size();
	}

	public PaginationCriteria getPaginationRequest() {

		PaginationCriteria pagination = new PaginationCriteria();
		pagination.setPageNumber(this.getStart());
		pagination.setPageSize(this.getLength());

		SortBy sortBy = null;
		if (!DataTablesUtil.isObjectEmpty(this.getOrder())) {
			sortBy = new SortBy();
			sortBy.addSort(this.getOrder().getName(), SortOrder.fromValue(this.getOrder().getSortDir()));
		}

		FilterBy filterBy = new FilterBy();
		filterBy.setGlobalSearch(this.isGlobalSearch());
		if (!DataTablesUtil.isObjectEmpty(this.getColumns())) {
			for (DataTablesColumnSpecs colSpec : this.getColumns()) {
				if (colSpec.isSearchable()) {
					if (!DataTablesUtil.isObjectEmpty(this.getSearch())
							|| !DataTablesUtil.isObjectEmpty(colSpec.getSearch())) {
						filterBy.addFilter(colSpec.getName(),
								(this.isGlobalSearch()) ? this.getSearch() : colSpec.getSearch());
					}
				}
			}
		}
		pagination.setSortBy(sortBy);
		pagination.setFilterBy(filterBy);

		return pagination;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("uniqueId", getUniqueId()).append("draw", getDraw())
				.append("start", getStart()).append("length", getLength()).append("search", getSearch())
				.append("regex", isRegex()).append("globalSearch", isGlobalSearch()).toString();
	}

}
