package it.ing.av.gdpr.gdprdashboard.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import it.ing.av.gdpr.gdprdashboard.resolver.EntityIdResolver;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = Application.class)
@Entity
@Table(name = "pa_application")
public class Application extends BaseEntity {

	private static final long serialVersionUID = -5615027894916754695L;

	public Application() {
	}

	public Application(Long id) {
		this.id = id;
	}

	public Application(String id) {
		this(new Long(id));
	}

	// TODO gestire fornitore

	// @JsonView(GdprJsonView.ControllerView.class)
	@Column(unique = true)
	private String code;

	// @JsonView(GdprJsonView.ControllerView.class)
	@Column
	@NotEmpty
	private String name;

	// @JsonView(GdprJsonView.ControllerView.class)
	private String type;

	// @JsonView(GdprJsonView.ControllerView.class)
	private String brand; // TODO campo libero o lista?

	// @JsonView(GdprJsonView.ControllerView.class)
	@Column
	@Type(type = "text")
	private String notes;

	// @JsonBackReference
	@ManyToMany(mappedBy = "applications", fetch = FetchType.LAZY)
	private List<Server> servers = new ArrayList<>();

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	@Override
	public String toString() {
		return "Application [code=" + code + ", name=" + name + ", type=" + type + ", brand=" + brand + ", notes="
				+ notes + "]";
	}

}
