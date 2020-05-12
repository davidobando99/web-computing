package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

/**
 * The persistent class for the TSSC_TIMECONTROL database table.
 * 
 */
@Entity
@Table(name = "TSSC_TIMECONTROL")
@NamedQuery(name = "TsscTimecontrol.findAll", query = "SELECT t FROM TsscTimecontrol t")
public class TsscTimecontrol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_TIMECONTROL_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_TIMECONTROL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_TIMECONTROL_ID_GENERATOR")
	private long id;

	private String autostart;

	@Column(name = "INTERVAL_RUNNING")
	private BigDecimal intervalRunning;

	@Column(name = "LAST_PLAY_TIME")
	private LocalTime lastPlayTime;

	private String name;

	@Column(name = "TC_ORDER")
	private BigDecimal order;

	@Column(name = "TC_STATE")
	private String state;

	@Column(name = "TIME_INTERVAL")
	private BigDecimal timeInterval;

	@Column(name = "TC_TYPE")
	private String type;

	// bi-directional many-to-one association to TsscTimeInterval
	@OneToMany(mappedBy = "tsscTimecontrol")
	private List<TsscTimeInterval> tsscTimeIntervals;

	// bi-directional many-to-one association to TsscGame
	@ManyToOne
	@JoinColumn(name = "TSSC_GAME_ID")
	private TsscGame tsscGame;

	// bi-directional many-to-one association to TsscTopic
	@ManyToOne
	@JoinColumn(name = "TSSC_TOPIC_ID")
	private TsscTopic tsscTopic;

	public TsscTimecontrol() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAutostart() {
		return this.autostart;
	}

	public void setAutostart(String autostart) {
		this.autostart = autostart;
	}

	public BigDecimal getIntervalRunning() {
		return this.intervalRunning;
	}

	public void setIntervalRunning(BigDecimal intervalRunning) {
		this.intervalRunning = intervalRunning;
	}

	public LocalTime getLastPlayTime() {
		return this.lastPlayTime;
	}

	public void setLastPlayTime(LocalTime lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getOrder() {
		return this.order;
	}

	public void setOrder(BigDecimal order) {
		this.order = order;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getTimeInterval() {
		return this.timeInterval;
	}

	public void setTimeInterval(BigDecimal timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TsscTimeInterval> getTsscTimeIntervals() {
		return this.tsscTimeIntervals;
	}

	public void setTsscTimeIntervals(List<TsscTimeInterval> tsscTimeIntervals) {
		this.tsscTimeIntervals = tsscTimeIntervals;
	}

	public TsscTimeInterval addTsscTimeInterval(TsscTimeInterval tsscTimeInterval) {
		getTsscTimeIntervals().add(tsscTimeInterval);
		tsscTimeInterval.setTsscTimecontrol(this);

		return tsscTimeInterval;
	}

	public TsscTimeInterval removeTsscTimeInterval(TsscTimeInterval tsscTimeInterval) {
		getTsscTimeIntervals().remove(tsscTimeInterval);
		tsscTimeInterval.setTsscTimecontrol(null);

		return tsscTimeInterval;
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
}