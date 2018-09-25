package it.ing.av.gdpr.gdprdashboard.datatables;

public enum SortOrder {

	ASC("ASC"), DESC("DESC");

	private final String value;

	SortOrder(String value) {
		this.value = value;
	}

	public static SortOrder fromValue(String value) {
		for (SortOrder sortOrder : SortOrder.values()) {
			if (sortOrder.name().equals(value)) {
				return sortOrder;
			}
		}
		throw new IllegalArgumentException(value);
	}

	public String value() {
		return value;
	}

}