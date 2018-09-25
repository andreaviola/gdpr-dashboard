package it.ing.av.gdpr.gdprdashboard.datatables;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataTablesUtil {

	private final static Logger log = LoggerFactory.getLogger(DataTablesUtil.class);

	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isObjectEmpty(Object object) {
		if (object == null)
			return true;
		else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return true;
			}
		} else if (object instanceof Collection) {
			return isCollectionEmpty((Collection<?>) object);
		}
		return false;
	}

	public static String getBeanToJsonString(Object beanClass) {
		String jsonString = null;
		try {
			jsonString = new ObjectMapper().writeValueAsString(beanClass);
		} catch (JsonProcessingException e) {
			log.error("Error converting bean to json String", e);
		}
		return jsonString;
	}

	public static String getBeanToJsonString(Object... beanClasses) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Object beanClass : beanClasses) {
			stringBuilder.append(getBeanToJsonString(beanClass));
			stringBuilder.append(", ");
		}
		return stringBuilder.toString();
	}

	public String concatenate(List<String> listOfItems, String separator) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> stit = listOfItems.iterator();

		while (stit.hasNext()) {
			sb.append(stit.next());
			if (stit.hasNext()) {
				sb.append(separator);
			}
		}

		return sb.toString();
	}

	public static String buildPaginatedQuery(String baseQuery, PaginationCriteria paginationCriteria) {
		StringBuilder sb = new StringBuilder(" #BASE_QUERY# #WHERE_CLAUSE# #ORDER_CLAUSE#");
		String finalQuery = null;
		if (!isObjectEmpty(paginationCriteria)) {
			finalQuery = sb.toString().replaceAll("#BASE_QUERY#", baseQuery);
			if (!isObjectEmpty(paginationCriteria.getFilterByClause())) {
				if (!baseQuery.toUpperCase().contains("WHERE")) {
					finalQuery = finalQuery.replaceAll("#WHERE_CLAUSE#", " WHERE " + paginationCriteria.getFilterByClause());
				} else {
					finalQuery = finalQuery.replaceAll("#WHERE_CLAUSE#", " AND " + paginationCriteria.getFilterByClause());
				}
			} else {
				finalQuery = finalQuery.replaceAll("#WHERE_CLAUSE#", " ");
			}
			finalQuery = finalQuery.replaceAll("#ORDER_CLAUSE#", paginationCriteria.getOrderByClause());
		}
		String query = (null == finalQuery) ? baseQuery : finalQuery;
		log.debug("query = {}", query);
		return query;
	}

	public static String buildCountQuery(String baseQuery, PaginationCriteria paginationCriteria) {
		StringBuilder sb = new StringBuilder("#BASE_QUERY# #WHERE_CLAUSE#");
		String finalQuery = null;
		if (!isObjectEmpty(paginationCriteria)) {
			finalQuery = sb.toString().replaceAll("#BASE_QUERY#", baseQuery);
			if (!isObjectEmpty(paginationCriteria.getFilterByClause())) {
				if (!baseQuery.toUpperCase().contains("WHERE")) {
					finalQuery = finalQuery.replaceAll("#WHERE_CLAUSE#", " WHERE " + paginationCriteria.getFilterByClause());
				} else {
					finalQuery = finalQuery.replaceAll("#WHERE_CLAUSE#", " AND " + paginationCriteria.getFilterByClause());
				}
			} else {
				finalQuery = finalQuery.replaceAll("#WHERE_CLAUSE#", " ");
			}
		}
		String query = (null == finalQuery) ? baseQuery : finalQuery;
		log.debug("query = {}", query);
		return query;
	}

	public static String buildCountSQLQuery(String baseQuery, PaginationCriteria paginationCriteria) {
		StringBuilder sb = new StringBuilder("#BASE_QUERY# #WHERE_CLAUSE#");
		String finalQuery = null;
		if (!isObjectEmpty(paginationCriteria)) {
			finalQuery = sb.toString().replaceAll("#BASE_QUERY#", baseQuery).replaceAll("#WHERE_CLAUSE#",
					((isObjectEmpty(paginationCriteria.getFilterByClause())) ? "" : " WHERE ")
							+ paginationCriteria.getFilterByClause());
		}
		String query = (null == finalQuery) ? baseQuery : finalQuery;
		log.debug("query = {}", query);
		return query;
	}

	public static String buildPaginatedSQLQuery(String baseQuery, PaginationCriteria paginationCriteria) {
		StringBuilder sb = new StringBuilder(
				"SELECT FILTERED_ORDERD_RESULTS.* FROM (SELECT BASEINFO.* FROM ( #BASE_QUERY# ) BASEINFO #WHERE_CLAUSE# #ORDER_CLAUSE# ) FILTERED_ORDERD_RESULTS LIMIT #PAGE_NUMBER#, #PAGE_SIZE#");
		String finalQuery = null;
		if (!isObjectEmpty(paginationCriteria)) {
			finalQuery = sb.toString().replaceAll("#BASE_QUERY#", baseQuery)
					.replaceAll("#WHERE_CLAUSE#",
							((isObjectEmpty(paginationCriteria.getFilterByClause())) ? "" : " WHERE ")
									+ paginationCriteria.getFilterByClause())
					.replaceAll("#ORDER_CLAUSE#", paginationCriteria.getOrderByClause())
					.replaceAll("#PAGE_NUMBER#", paginationCriteria.getPageNumber().toString())
					.replaceAll("#PAGE_SIZE#", paginationCriteria.getPageSize().toString());
		}
		String query = (null == finalQuery) ? baseQuery : finalQuery;
		log.debug("query = {}", query);
		return query;
	}

}