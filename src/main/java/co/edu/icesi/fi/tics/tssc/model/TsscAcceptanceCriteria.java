package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TSSC_ACCEPTANCE_CRITERIA database table.
 * 
 */
@Entity
@Table(name = "TSSC_ACCEPTANCE_CRITERIA")
@NamedQuery(name = "TsscAcceptanceCriteria.findAll", query = "SELECT t FROM TsscAcceptanceCriteria t")
public class TsscAcceptanceCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_ACCEPTANCE_CRITERIA_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_ACCEPTANCE_CRITERIA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_ACCEPTANCE_CRITERIA_ID_GENERATOR")
	private long id;

	private String description;

	@Column(name = "INITIALLY_VISIBLE")
	private String initiallyVisible;

	// bi-directional many-to-one association to TsscDeliverable
	@ManyToOne
	@JoinColumn(name = "TSSC_DELIVERABLE_ID")
	private TsscDeliverable tsscDeliverable;

	// bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name = "TSSC_STATE_ID")
	private TsscState tsscState;

	// bi-directional many-to-one association to TsscStory
	@ManyToOne
	@JoinColumn(name = "TSSC_STORY_ID")
	private TsscStory tsscStory;

	public TsscAcceptanceCriteria() {
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

	public String getInitiallyVisible() {
		return this.initiallyVisible;
	}

	public void setInitiallyVisible(String initiallyVisible) {
		this.initiallyVisible = initiallyVisible;
	}

	public TsscDeliverable getTsscDeliverable() {
		return this.tsscDeliverable;
	}

	public void setTsscDeliverable(TsscDeliverable tsscDeliverable) {
		this.tsscDeliverable = tsscDeliverable;
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