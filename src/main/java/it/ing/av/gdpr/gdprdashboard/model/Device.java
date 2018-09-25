package it.ing.av.gdpr.gdprdashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "pa_device")
public class Device extends BaseEntity {

	private static final long serialVersionUID = -18066305507339085L;

	// TODO gestire utente assegnatario

	@Column(unique = true)
	private String code;

	@Column(name = "serial_number", unique = true)
	private String serialNumber;

	private String brand;

	private String type;

	@NotEmpty
	private String model;

	@ManyToOne
	@JoinColumn(name = "operative_system_id")
	private OperativeSystem operativeSystem;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column
	@Type(type = "text")
	private String notes;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public OperativeSystem getOperativeSystem() {
		return operativeSystem;
	}

	public void setOperativeSystem(OperativeSystem operativeSystem) {
		this.operativeSystem = operativeSystem;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Device [code=" + code + ", serialNumber=" + serialNumber + ", brand=" + brand + ", type=" + type
				+ ", model=" + model + ", operativeSystem=" + operativeSystem + ", ipAddress=" + ipAddress + ", notes="
				+ notes + "]";
	}

}
