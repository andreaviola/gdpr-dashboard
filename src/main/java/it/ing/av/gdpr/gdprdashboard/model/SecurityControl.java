package it.ing.av.gdpr.gdprdashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "pa_security_control")
@DynamicUpdate
public class SecurityControl extends BaseEntity {

	private static final long serialVersionUID = -693084364215510888L;

	@Column(unique = true)
	@NotEmpty
	private String code;

	@Column
	@NotEmpty
	private String name;

	private String type;

	@Column
	@Type(type = "text")
	private String notes;

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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "SecurityControl [code=" + code + ", name=" + name + ", type=" + type + ", notes=" + notes + "]";
	}

}
