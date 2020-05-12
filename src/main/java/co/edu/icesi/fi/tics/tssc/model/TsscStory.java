package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import co.icesi.fi.tics.tssc.validations.ValidationStory;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the TSSC_STORY database table.
 * 
 */
@Entity
@Table(name = "TSSC_STORY")
@NamedQuery(name = "TsscStory.findAll", query = "SELECT t FROM TsscStory t")
public class TsscStory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_STORY_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_STORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_STORY_ID_GENERATOR")
	private long id;

	@Column(name = "ALT_DESC_SHOWN")
	private String altDescShown;

	@Column(name = "ALT_DESCRIPTON")
	private String altDescripton;

	@Min(value = 1, message ="El valor debe ser mayor que cero", groups=ValidationStory.class)
	@NotNull(message = "Ingresa un valor para el valor de negocio", groups=ValidationStory.class)
	@Column(name = "BUSINESS_VALUE")
	private BigDecimal businessValue;

	@NotBlank(message = "Ingresa una descripción", groups=ValidationStory.class)
	private String description;

	@Min(value = 1, message ="El valor debe ser mayor que cero", groups=ValidationStory.class)
	@NotNull(message = "Ingresa un valor para el Sprint Inicial", groups=ValidationStory.class)
	@Column(name = "INITIAL_SPRINT")
	private BigDecimal initialSprint;

	@Column(name = "ST_NUMBER")
	private BigDecimal number;

	@Min(value = 1, message ="El valor debe ser mayor que cero", groups=ValidationStory.class)
	@NotNull(message = "Ingresa un valor de prioridad", groups=ValidationStory.class)
	private BigDecimal priority;

	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	// bi-directional many-to-one association to TsscAcceptanceCriteria
	@OneToMany(mappedBy = "tsscStory")
	private List<TsscAcceptanceCriteria> tsscAcceptanceCriterias;

	// bi-directional many-to-one association to TsscDeliverable
	@OneToMany(mappedBy = "tsscStory")
	private List<TsscDeliverable> tsscDeliverables;

	// bi-directional many-to-one association to TsscGame
	@NotNull(message = "Debes elegir un juego obligatoriamente", groups=ValidationStory.class)
	@ManyToOne
	@JoinColumn(name = "TSSC_GAME_ID")
	private TsscGame tsscGame;
	
	//bi-directional many-to-one association to TsscTopic
	@ManyToOne
	@JoinColumn(name="TSSC_TOPIC_ID")
	private TsscTopic tsscTopic;


	// bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name = "TSSC_STATE_ID")
	private TsscState tsscState;

	public TsscStory() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAltDescShown() {
		return this.altDescShown;
	}

	public void setAltDescShown(String altDescShown) {
		this.altDescShown = altDescShown;
	}

	public String getAltDescripton() {
		return this.altDescripton;
	}

	public void setAltDescripton(String altDescripton) {
		this.altDescripton = altDescripton;
	}

	public BigDecimal getBusinessValue() {
		return this.businessValue;
	}

	public void setBusinessValue(BigDecimal businessValue) {
		this.businessValue = businessValue;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getInitialSprint() {
		return this.initialSprint;
	}

	public void setInitialSprint(BigDecimal initialSprint) {
		this.initialSprint = initialSprint;
	}

	public BigDecimal getNumber() {
		return this.number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public List<TsscAcceptanceCriteria> getTsscAcceptanceCriterias() {
		return this.tsscAcceptanceCriterias;
	}

	public void setTsscAcceptanceCriterias(List<TsscAcceptanceCriteria> tsscAcceptanceCriterias) {
		this.tsscAcceptanceCriterias = tsscAcceptanceCriterias;
	}

	public TsscAcceptanceCriteria addTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().add(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscStory(this);

		return tsscAcceptanceCriteria;
	}

	public TsscAcceptanceCriteria removeTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().remove(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscStory(null);

		return tsscAcceptanceCriteria;
	}

	public List<TsscDeliverable> getTsscDeliverables() {
		return this.tsscDeliverables;
	}

	public void setTsscDeliverables(List<TsscDeliverable> tsscDeliverables) {
		this.tsscDeliverables = tsscDeliverables;
	}

	public TsscDeliverable addTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().add(tsscDeliverable);
		tsscDeliverable.setTsscStory(this);

		return tsscDeliverable;
	}

	public TsscDeliverable removeTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().remove(tsscDeliverable);
		tsscDeliverable.setTsscStory(null);

		return tsscDeliverable;
	}

	public TsscGame getTsscGame() {
		return this.tsscGame;
	}

	public void setTsscGame(TsscGame tsscGame) {
		this.tsscGame = tsscGame;
	}
	
	public TsscTopic getTsscTopic() {
		return this.tsscTopic;
	}

	public void setTsscTopic(TsscTopic tsscTopic) {
		this.tsscTopic = tsscTopic;
	}

	public TsscState getTsscState() {
		return this.tsscState;
	}

	public void setTsscState(TsscState tsscState) {
		this.tsscState = tsscState;
	}

}