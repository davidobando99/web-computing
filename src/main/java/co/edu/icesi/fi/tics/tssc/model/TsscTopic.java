package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.icesi.fi.tics.tssc.validations.ValidationTopic;

/**
 * The persistent class for the TSSC_TOPIC database table.
 * 
 */
@Entity
@Table(name = "TSSC_TOPIC")
@NamedQuery(name = "TsscTopic.findAll", query = "SELECT t FROM TsscTopic t")
public class TsscTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_TOPIC_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_TOPIC_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_TOPIC_ID_GENERATOR")
	private long id;

	@NotBlank(message = "Agrega una descripción", groups=ValidationTopic.class)
	private String description;

	@NotBlank(groups=ValidationTopic.class, message = "Ingresa un nombre.")
	private String name;

	@Min(value = 1, message = "Sprints deben ser mayor a cero", groups=ValidationTopic.class)
	@Column(name = "DEFAULT_SPRINTS")
	private long defaultSprints;

	@Min(value = 1, message = "Groups deben ser mayor a cero", groups=ValidationTopic.class)
	@Column(name = "DEFAULT_GROUPS")
	private long defaultGroups;

	@NotBlank(message = "Ingresa por favor un prefijo de grupo", groups=ValidationTopic.class)
	@Column(name = "GROUP_PREFIX")
	private String groupPrefix;

	// bi-directional many-to-one association to TsscStory
	@OneToMany(mappedBy = "tsscTopic")
	@JsonIgnore
	private List<TsscGame> tsscGames;

	// bi-directional many-to-one association to TsscStory
	@OneToMany(mappedBy = "tsscTopic")
	@JsonIgnore
	private List<TsscStory> tsscStories;
	
	@OneToMany
	@JoinColumn(name = "TSSC_TIMECONTROL_ID")
	private List<TsscTimecontrol> tsscTimecontrol;

	public TsscTopic() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDefaultSprints() {
		return defaultSprints;
	}

	public void setDefaultSprints(long defaultSprints) {
		this.defaultSprints = defaultSprints;
	}

	public long getDefaultGroups() {
		return defaultGroups;
	}

	public void setDefaultGroups(long defaultGroups) {
		this.defaultGroups = defaultGroups;
	}

	public String getGroupPrefix() {
		return groupPrefix;
	}

	public void setGroupPrefix(String groupPrefix) {
		this.groupPrefix = groupPrefix;
	}

	public List<TsscStory> getTsscStories() {
		return this.tsscStories;
	}

	public void setTsscStories(List<TsscStory> tsscStories) {
		this.tsscStories = tsscStories;
	}
	
	public List<TsscTimecontrol> getTssTimecontrol() {
		return this.tsscTimecontrol;
	}

	public void setTsscTimecontrol(List<TsscTimecontrol> tsscTimecontrol) {
		this.tsscTimecontrol = tsscTimecontrol;
	}
	
	public List<TsscGame> getTsscGames() {
		return this.tsscGames;
	}

	public void setTsscGames(List<TsscGame> tsscGames) {
		this.tsscGames = tsscGames;
	}

	public TsscStory addTsscStory(TsscStory tsscStory) {
		getTsscStories().add(tsscStory);
		tsscStory.setTsscTopic(this);

		return tsscStory;
	}

	public TsscStory removeTsscStory(TsscStory tsscStory) {
		getTsscStories().remove(tsscStory);
		tsscStory.setTsscTopic(null);

		return tsscStory;
	}
	
	
	
}