package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the TSSC_SPRINT database table.
 * 
 */
@Entity
@Table(name="TSSC_SPRINT")
@NamedQuery(name="TsscSprint.findAll", query="SELECT t FROM TsscSprint t")
public class TsscSprint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TSSC_SPRINT_ID_GENERATOR", allocationSize = 1, sequenceName="TSSC_SPRINT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TSSC_SPRINT_ID_GENERATOR")
	private long id;

	@Column(name="SP_NUMBER")
	private BigDecimal number;

	//bi-directional many-to-one association to TsscDeliverable
	@OneToMany(mappedBy="tsscSprint")
	private List<TsscDeliverable> tsscDeliverables;

	//bi-directional many-to-one association to TsscGame
	@ManyToOne
	@JoinColumn(name="TSSC_GAME_ID")
	private TsscGame tsscGame;

	//bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name="TSSC_STATE_ID")
	private TsscState tsscState;

	public TsscSprint() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getNumber() {
		return this.number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public List<TsscDeliverable> getTsscDeliverables() {
		return this.tsscDeliverables;
	}

	public void setTsscDeliverables(List<TsscDeliverable> tsscDeliverables) {
		this.tsscDeliverables = tsscDeliverables;
	}

	public TsscDeliverable addTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().add(tsscDeliverable);
		tsscDeliverable.setTsscSprint(this);

		return tsscDeliverable;
	}

	public TsscDeliverable removeTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().remove(tsscDeliverable);
		tsscDeliverable.setTsscSprint(null);

		return tsscDeliverable;
	}

	public TsscGame getTsscGame() {
		return this.tsscGame;
	}

	public void setTsscGame(TsscGame tsscGame) {
		this.tsscGame = tsscGame;
	}

	public TsscState getTsscState() {
		return this.tsscState;
	}

	public void setTsscState(TsscState tsscState) {
		this.tsscState = tsscState;
	}

}