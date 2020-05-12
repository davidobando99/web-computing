package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.icesi.fi.tics.tssc.validations.ValidationGame;
import co.icesi.fi.tics.tssc.validations.ValidationStory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The persistent class for the TSSC_GAME database table.
 * 
 */
@Entity
@Table(name = "TSSC_GAME")
@NamedQuery(name = "TsscGame.findAll", query = "SELECT t FROM TsscGame t")
public class TsscGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_GAME_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_GAME_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_GAME_ID_GENERATOR")
	private long id;

	@NotBlank(message = "Se requiere una contraseña", groups=ValidationGame.class)
	@Column(name = "ADMIN_PASSWORD")
	private String adminPassword;

	@NotBlank(message = "Se requiere una contraseña", groups=ValidationGame.class)
	@Column(name = "GUEST_PASSWORD")
	private String guestPassword;

	@Min(value = 1, message = "Groups deben ser mayores a 0", groups=ValidationGame.class)
	@Column(name = "N_GROUPS")
	private Integer nGroups = 4;

	@Min(value = 1, message = "Sprints deben ser mayores a 0", groups=ValidationGame.class)
	@Column(name = "N_SPRINTS")
	private Integer nSprints = 4;

	@NotBlank(message = "El juego debe tener un nombre", groups=ValidationGame.class)
	private String name;

	@Column(name = "PAUSE_SECONDS")
	private Long pauseSeconds = 0L;

	@NotNull(message = "Debes  elegir una fecha", groups=ValidationGame.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SCHEDULED_DATE")
	private LocalDate scheduledDate;

	@DateTimeFormat(iso = ISO.TIME)
	@NotNull(message  = "Debes elegir una hora", groups=ValidationGame.class)
	@Column(name = "SCHEDULED_TIME")
	private LocalTime scheduledTime;

	@Column(name = "START_TIME")
	private LocalTime startTime;

	@Column(name = "TYPEGAME_ID")
	private BigDecimal typegameId;

	@NotBlank(message = "Se requiere una contraseña", groups=ValidationGame.class)
	@Column(name = "USER_PASSWORD")
	private String userPassword;

	// bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name = "TSSC_STATE_ID")
	private TsscState tsscState;

	// bi-directional many-to-one association to TsscGameAdmin
	@OneToMany(mappedBy = "tsscGame")
	private List<TsscGameAdmin> tsscGameAdmins;

	// bi-directional many-to-one association to TsscGroup
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tsscGame")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<TsscGroup> tsscGroups;

	// bi-directional many-to-one association to TsscSprint
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tsscGame")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<TsscSprint> tsscSprints;

	// bi-directional many-to-one association to TsscStory
	@OneToMany(mappedBy = "tsscGame")
	@JsonIgnore
	private List<TsscStory> tsscStories;
	
	//bi-directional many-to-one association to TsscTimecontrol
	@OneToMany(mappedBy="tsscGame")
	private List<TsscTimecontrol> tsscTimecontrols;

	//bi-directional many-to-one association to TssTopic
	@ManyToOne
	@JoinColumn(name="TSSC_TOPIC_ID")
	private TsscTopic tsscTopic;

	public TsscGame() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getGuestPassword() {
		return this.guestPassword;
	}

	public void setGuestPassword(String guestPassword) {
		this.guestPassword = guestPassword;
	}

	public Integer getNGroups() {
		return this.nGroups;
	}

	public void setNGroups(Integer nGroups) {
		this.nGroups = nGroups;
	}

	public Integer getNSprints() {
		return this.nSprints;
	}

	public void setNSprints(Integer nSprints) {
		this.nSprints = nSprints;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPauseSeconds() {
		return this.pauseSeconds;
	}

	public void setPauseSeconds(Long pauseSeconds) {
		this.pauseSeconds = pauseSeconds;
	}

	public LocalDate getScheduledDate() {
		return this.scheduledDate;
	}

	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public LocalTime getScheduledTime() {
		return this.scheduledTime;
	}

	public void setScheduledTime(LocalTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public LocalTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getTypegameId() {
		return this.typegameId;
	}

	public void setTypegameId(BigDecimal typegameId) {
		this.typegameId = typegameId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public TsscState getTsscState() {
		return this.tsscState;
	}

	public void setTsscState(TsscState tsscState) {
		this.tsscState = tsscState;
	}

	public List<TsscGameAdmin> getTsscGameAdmins() {
		return this.tsscGameAdmins;
	}

	public void setTsscGameAdmins(List<TsscGameAdmin> tsscGameAdmins) {
		this.tsscGameAdmins = tsscGameAdmins;
	}

	public TsscGameAdmin addTsscGameAdmin(TsscGameAdmin tsscGameAdmin) {
		getTsscGameAdmins().add(tsscGameAdmin);
		tsscGameAdmin.setTsscGame(this);

		return tsscGameAdmin;
	}

	public TsscGameAdmin removeTsscGameAdmin(TsscGameAdmin tsscGameAdmin) {
		getTsscGameAdmins().remove(tsscGameAdmin);
		tsscGameAdmin.setTsscGame(null);

		return tsscGameAdmin;
	}

	public List<TsscGroup> getTsscGroups() {
		return this.tsscGroups;
	}

	public void setTsscGroups(List<TsscGroup> tsscGroups) {
		this.tsscGroups = tsscGroups;
	}

	public TsscGroup addTsscGroup(TsscGroup tsscGroup) {
		getTsscGroups().add(tsscGroup);
		tsscGroup.setTsscGame(this);

		return tsscGroup;
	}

	public TsscGroup removeTsscGroup(TsscGroup tsscGroup) {
		getTsscGroups().remove(tsscGroup);
		tsscGroup.setTsscGame(null);

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
		tsscSprint.setTsscGame(this);

		return tsscSprint;
	}

	public TsscSprint removeTsscSprint(TsscSprint tsscSprint) {
		getTsscSprints().remove(tsscSprint);
		tsscSprint.setTsscGame(null);

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
		tsscStory.setTsscGame(this);

		return tsscStory;
	}

	public TsscStory removeTsscStory(TsscStory tsscStory) {
		getTsscStories().remove(tsscStory);
		tsscStory.setTsscGame(null);

		return tsscStory;
	}

	public List<TsscTimecontrol> getTsscTimecontrols() {
		return this.tsscTimecontrols;
	}

	public void setTsscTimecontrol(List<TsscTimecontrol> tsscTimecontrols) {
		this.tsscTimecontrols = tsscTimecontrols;
	}

	public TsscTimecontrol addTsscTimecontrol(TsscTimecontrol tsscTimecontrol) {
		getTsscTimecontrols().add(tsscTimecontrol);
		tsscTimecontrol.setTsscGame(this);

		return tsscTimecontrol;
	}

	public TsscTimecontrol removeTsscTimecontrol(TsscTimecontrol tsscTimecontrol) {
		getTsscTimecontrols().remove(tsscTimecontrol);
		tsscTimecontrol.setTsscGame(null);

		return tsscTimecontrol;
	}
	
	public TsscTopic getTsscTopic() {
		return this.tsscTopic;
	}

	public void setTsscTopic(TsscTopic tsscTopic) {
		this.tsscTopic = tsscTopic;
	}
	
}