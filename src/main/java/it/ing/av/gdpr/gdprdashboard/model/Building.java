package it.ing.av.gdpr.gdprdashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "pa_building")
public class Building extends BaseEntity {

	private static final long serialVersionUID = -3599638970071947263L;

	@Column(unique = true)
	private String code;

	@NotEmpty
	private String name;

	@Column
	private String type;

	@Column
	private String address;

	@Column
	@Type(type = "text")
	private String notes;

	// TODO onetomany lista misure sicurezza

	// TODO onetomany lista unità

	// TODO onetomany lista chiavi

	// TODO numero piani, ecc...?

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Building [code=" + code + ", name=" + name + ", type=" + type + ", address=" + address + ", notes="
				+ notes + "]";
	}

}
