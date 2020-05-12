package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The persistent class for the TSSC_STATE database table.
 * 
 */
@Entity
@Table(name = "TSSC_STATE")
@NamedQuery(name = "TsscState.findAll", query = "SELECT t FROM TsscState t")
public class TsscState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_STATE_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_STATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_STATE_ID_GENERATOR")
	private long id;

	private String name;

	@Column(name = "TABLE_TYPE")
	private String type;

	// bi-directional many-to-one association to TsscAcceptanceCriteria
	@OneToMany(mappedBy = "tsscState")
	@JsonIgnore
	private List<TsscAcceptanceCriteria> tsscAcceptanceCriterias;

	// bi-directional many-to-one association to TsscAdmin
	@OneToMany(mappedBy = "tsscState")
	@JsonIgnore
	private List<TsscAdmin> tsscAdmins;

	// bi-directional many-to-one association to TsscDeliverable
	@OneToMany(mappedBy = "tsscState")
	@JsonIgnore
	private List<TsscDeliverable> tsscDeliverables;

	// bi-directional many-to-one association to TsscGame
	@OneToMany(mappedBy = "tsscState")
	@JsonIgnore
	private List<TsscGame> tsscGames;

	// bi-directional many-to-one association to TsscGroup
	@OneToMany(mappedBy = "tsscState")
	@JsonIgnore
	private List<TsscGroup> tsscGroups;

	// bi-directional many-to-one association to TsscSprint
	@OneToMany(mappedBy = "tsscState")
	@JsonIgnore
	private List<TsscSprint> tsscSprints;

	// bi-directional many-to-one association to TsscStory
	@OneToMany(mappedBy = "tsscState")
	@JsonIgnore
	private List<TsscStory> tsscStories;

	public TsscState() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TsscAcceptanceCriteria> getTsscAcceptanceCriterias() {
		return this.tsscAcceptanceCriterias;
	}

	public void setTsscAcceptanceCriterias(List<TsscAcceptanceCriteria> tsscAcceptanceCriterias) {
		this.tsscAcceptanceCriterias = tsscAcceptanceCriterias;
	}

	public TsscAcceptanceCriteria addTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().add(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscState(this);

		return tsscAcceptanceCriteria;
	}

	public TsscAcceptanceCriteria removeTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().remove(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscState(null);

		return tsscAcceptanceCriteria;
	}

	public List<TsscAdmin> getTsscAdmins() {
		return this.tsscAdmins;
	}

	public void setTsscAdmins(List<TsscAdmin> tsscAdmins) {
		this.tsscAdmins = tsscAdmins;
	}

	public TsscAdmin addTsscAdmin(TsscAdmin tsscAdmin) {
		getTsscAdmins().add(tsscAdmin);
		tsscAdmin.setTsscState(this);

		return tsscAdmin;
	}

	public TsscAdmin removeTsscAdmin(TsscAdmin tsscAdmin) {
		getTsscAdmins().remove(tsscAdmin);
		tsscAdmin.setTsscState(null);

		return tsscAdmin;
	}

	public List<TsscDeliverable> getTsscDeliverables() {
		return this.tsscDeliverables;
	}

	public void setTsscDeliverables(List<TsscDeliverable> tsscDeliverables) {
		this.tsscDeliverables = tsscDeliverables;
	}

	public TsscDeliverable addTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().add(tsscDeliverable);
		tsscDeliverable.setTsscState(this);

		return tsscDeliverable;
	}

	public TsscDeliverable removeTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().remove(tsscDeliverable);
		tsscDeliverable.setTsscState(null);

		return tsscDeliverable;
	}

	public List<TsscGame> getTsscGames() {
		return this.tsscGames;
	}

	public void setTsscGames(List<TsscGame> tsscGames) {
		this.tsscGames = tsscGames;
	}

	public TsscGame addTsscGame(TsscGame tsscGame) {
		getTsscGames().add(tsscGame);
		tsscGame.setTsscState(this);

		return tsscGame;
	}

	public TsscGame removeTsscGame(TsscGame tsscGame) {
		getTsscGames().remove(tsscGame);
		tsscGame.setTsscState(null);

		return tsscGame;
	}

	public List<TsscGroup> getTsscGroups() {
		return this.tsscGroups;
	}

	public void setTsscGroups(List<TsscGroup> tsscGroups) {
		this.tsscGroups = tsscGroups;
	}

	public TsscGroup addTsscGroup(TsscGroup tsscGroup) {
		getTsscGroups().add(tsscGroup);
		tsscGroup.setTsscState(this);

		return tsscGroup;
	}

	public TsscGroup removeTsscGroup(TsscGroup tsscGroup) {
		getTsscGroups().remove(tsscGroup);
		tsscGroup.setTsscState(null);

		return tsscGroup;
	}

	public List<TsscSprint> getTsscSprints() {
		return this.tsscSprints;
	}

	public void setTsscSprints(List<TsscSprint> tsscSprints) {
		this.tsscSprints = tsscSprints;
	}

	public TsscSprint addTsscSprint(TsscSprint tsscSprint) {
		getTsscSprints().add(tsscSprint);
		tsscSprint.setTsscState(this);

		return tsscSprint;
	}

	public TsscSprint removeTsscSprint(TsscSprint tsscSprint) {
		getTsscSprints().remove(tsscSprint);
		tsscSprint.setTsscState(null);

		return tsscSprint;
	}

	public List<TsscStory> getTsscStories() {
		return this.tsscStories;
	}

	public void setTsscStories(List<TsscStory> tsscStories) {
		this.tsscStories = tsscStories;
	}

	public TsscStory addTsscStory(TsscStory tsscStory) {
		getTsscStories().add(tsscStory);
		tsscStory.setTsscState(this);

		return tsscStory;
	}

	public TsscStory removeTsscStory(TsscStory tsscStory) {
		getTsscStories().remove(tsscStory);
		tsscStory.setTsscState(null);

		return tsscStory;
	}

}