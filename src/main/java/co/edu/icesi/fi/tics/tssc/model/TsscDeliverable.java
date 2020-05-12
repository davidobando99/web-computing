package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

/**
 * The persistent class for the TSSC_DELIVERABLE database table.
 * 
 */
@Entity
@Table(name = "TSSC_DELIVERABLE")
@NamedQuery(name = "TsscDeliverable.findAll", query = "SELECT t FROM TsscDeliverable t")
public class TsscDeliverable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_DELIVERABLE_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_DELIVERABLE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_DELIVERABLE_ID_GENERATOR")
	private long id;

	@Column(name = "END_TIME")
	private LocalTime endTime;

	@Column(name = "N_PEOPLE")
	private Integer nPeople;

	private Integer points;

	@Column(name = "START_TIME")
	private LocalTime startTime;

	@Column(name = "USER_DESC_ACCEPTED")
	private String userDescAccepted;

	@Column(name = "USER_DESCRIPTION")
	private String userDescription;

	@Column(name = "USER_NOTES")
	private String userNotes;

	// bi-directional many-to-one association to TsscAcceptanceCriteria
	@OneToMany(mappedBy = "tsscDeliverable")
	private List<TsscAcceptanceCriteria> tsscAcceptanceCriterias;

	// bi-directional many-to-one association to TsscGroup
	@ManyToOne
	@JoinColumn(name = "TSSC_GROUP_ID")
	private TsscGroup tsscGroup;

	// bi-directional many-to-one association to TsscSprint
	@ManyToOne
	@JoinColumn(name = "TSSC_SPRINT_ID")
	private TsscSprint tsscSprint;

	// bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name = "TSSC_STATE_ID")
	private TsscState tsscState;

	// bi-directional many-to-one association to TsscStory
	@ManyToOne
	@JoinColumn(name = "TSSC_STORY_ID")
	private TsscStory tsscStory;

	public TsscDeliverable() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalTime getEndTime() {
		return this.endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Integer getNPeople() {
		return this.nPeople;
	}

	public void setNPeople(Integer nPeople) {
		this.nPeople = nPeople;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public LocalTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public String getUserDescAccepted() {
		return this.userDescAccepted;
	}

	public void setUserDescAccepted(String userDescAccepted) {
		this.userDescAccepted = userDescAccepted;
	}

	public String getUserDescription() {
		return this.userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getUserNotes() {
		return this.userNotes;
	}

	public void setUserNotes(String userNotes) {
		this.userNotes = userNotes;
	}

	public List<TsscAcceptanceCriteria> getTsscAcceptanceCriterias() {
		return this.tsscAcceptanceCriterias;
	}

	public void setTsscAcceptanceCriterias(List<TsscAcceptanceCriteria> tsscAcceptanceCriterias) {
		this.tsscAcceptanceCriterias = tsscAcceptanceCriterias;
	}

	public TsscAcceptanceCriteria addTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().add(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscDeliverable(this);

		return tsscAcceptanceCriteria;
	}

	public TsscAcceptanceCriteria removeTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().remove(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscDeliverable(null);

		return tsscAcceptanceCriteria;
	}

	public TsscGroup getTsscGroup() {
		return this.tsscGroup;
	}

	public void setTsscGroup(TsscGroup tsscGroup) {
		this.tsscGroup = tsscGroup;
	}

	public TsscSprint getTsscSprint() {
		return this.tsscSprint;
	}

	public void setTsscSprint(TsscSprint tsscSprint) {
		this.tsscSprint = tsscSprint;
	}

	public TsscState getTsscState() {
		return this.tsscState;
	}

	public void setTsscState(TsscState tsscState) {
		this.tsscState = tsscState;
	}

	public TsscStory getTsscStory() {
		return this.tsscStory;
	}

	public void setTsscStory(TsscStory tsscStory) {
		this.tsscStory = tsscStory;
	}

}