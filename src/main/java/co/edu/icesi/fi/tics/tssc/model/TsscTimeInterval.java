package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the TSSC_TIME_INTERVAL database table.
 * 
 */
@Entity
@Table(name = "TSSC_TIME_INTERVAL")
@NamedQuery(name = "TsscTimeInterval.findAll", query = "SELECT t FROM TsscTimeInterval t")
public class TsscTimeInterval implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_TIME_INTERVAL_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_TIME_INTERVAL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_TIME_INTERVAL_ID_GENERATOR")
	private long id;

	private Integer duration;

	private String name;

	@Column(name = "TI_ORDER")
	private Integer order;

	private String sound;

	// bi-directional many-to-one association to TsscGame
	@ManyToOne
	@JoinColumn(name = "TSSC_TIMECONTROL_ID")
	private TsscTimecontrol tsscTimecontrol;

	public TsscTimeInterval() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getSound() {
		return this.sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public TsscTimecontrol getTsscTimecontrol() {
		return this.tsscTimecontrol;
	}

	public void setTsscTimecontrol(TsscTimecontrol tsscTimecontrol) {
		this.tsscTimecontrol = tsscTimecontrol;
	}
}