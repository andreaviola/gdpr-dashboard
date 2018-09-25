package it.ing.av.gdpr.gdprdashboard.datatables;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataTablesResults<T> {

	private String draw;
	private String recordsFiltered;
	private String recordsTotal;

	@JsonProperty("data")
	List<T> listOfDataObjects;

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public String getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(String recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(String recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public List<T> getListOfDataObjects() {
		return listOfDataObjects;
	}

	public void setListOfDataObjects(List<T> listOfDataObjects) {
		this.listOfDataObjects = listOfDataObjects;
	}

	@Override
	public String toString() {
		return "DataTablesResults [draw=" + draw + ", recordsFiltered=" + recordsFiltered + ", recordsTotal="
				+ recordsTotal + ", listOfDataObjects=" + listOfDataObjects + "]";
	}

}
