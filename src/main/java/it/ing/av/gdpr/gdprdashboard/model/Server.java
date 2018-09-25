package it.ing.av.gdpr.gdprdashboard.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import it.ing.av.gdpr.gdprdashboard.resolver.EntityIdResolver;

//@JsonIgnoreProperties(value={ "_applications" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = Server.class)
@Entity
@Table(name = "pa_server")
public class Server extends BaseEntity {

	private static final long serialVersionUID = 5015483993492647317L;

	private static final Logger log = LoggerFactory.getLogger(Server.class);

	// TODO gestire amministratori di sistema

	// @JsonView(GdprJsonView.ControllerView.class)
	@Column(unique = true)
	private String code;

	// @JsonView(GdprJsonView.ControllerView.class)
	@Column(name = "serial_number", unique = true)
	private String serialNumber;

	// @JsonView(GdprJsonView.ControllerView.class)
	private String name;

	// @JsonView(GdprJsonView.ControllerView.class)
	private String brand; // HP, DELL...

	// @JsonView(GdprJsonView.ControllerView.class)
	private String type; // DB Server, ecc...

	// @JsonView(GdprJsonView.ControllerView.class)
	@NotEmpty
	private String model;

	@ManyToOne
	@JoinColumn(name = "operative_system_id")
	private OperativeSystem operativeSystem;

	// @JsonView(GdprJsonView.ControllerView.class)
	@Column(name = "ip_address")
	private String ipAddress;

	// @JsonView(GdprJsonView.ControllerView.class)
	@Column
	@Type(type = "text")
	private String notes;

	// @JsonView(GdprJsonView.ControllerView.class)
	// @JsonManagedReference
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "pa_server_application", joinColumns = @JoinColumn(name = "server_id"), inverseJoinColumns = @JoinColumn(name = "application_id"))
	private List<Application> applications = new ArrayList<Application>();

	// protected List<Application> getApplicationsInternal() {
	// return this.applications;
	// }
	//
	// protected void setApplicationsInternal(List<Application> applications) {
	// if (applications == null) {
	// this.applications = new ArrayList<Application>();
	// } else {
	// this.applications = applications;
	// }
	// }

	public List<Application> getApplications() {
		// List<Application> sortedPets = new
		// ArrayList<>(getApplicationsInternal());
		// PropertyComparator.sort(sortedPets, new MutableSortDefinition("name",
		// true, true));
		// return Collections.unmodifiableList(sortedPets);
		return applications;
	}

	public void setApplications(List<Application> applications) {
		log.info("> setApplications(applications={})", applications);
		// setApplicationsInternal(applications);
		this.applications = applications;
		// for (Application application : applications) {
		// if (application.getServers() != null &&
		// !application.getServers().isEmpty()) {
		// application.getServers().add(this);
		// } else {
		// application.setServers(new ArrayList<Server>());
		// application.getServers().add(this);
		// }
		// }
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Server [code=" + code + ", serialNumber=" + serialNumber + ", name=" + name + ", brand=" + brand
				+ ", type=" + type + ", model=" + model + ", ipAddress=" + ipAddress + ", notes=" + notes
				+ ", applications=" + applications + "]";
	}

}
