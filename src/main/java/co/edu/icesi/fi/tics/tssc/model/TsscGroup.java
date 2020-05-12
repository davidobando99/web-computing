package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the TSSC_GROUP database table.
 * 
 */
@Entity
@Table(name="TSSC_GROUP")
@NamedQuery(name="TsscGroup.findAll", query="SELECT t FROM TsscGroup t")
public class TsscGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TSSC_GROUP_ID_GENERATOR", allocationSize = 1, sequenceName="TSSC_GROUP_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TSSC_GROUP_ID_GENERATOR")
	private long id;

	@Lob
	private byte[] avatar;

	private String name;

	@Column(name="GP_POSITION")
	private BigDecimal gpPosition;

	@Column(name="QR_PASSWORD")
	private String qrPassword;

	//bi-directional many-to-one association to TsscDeliverable
	@OneToMany(mappedBy="tsscGroup")
	private List<TsscDeliverable> tsscDeliverables;

	//bi-directional many-to-one association to TsscGame
	@ManyToOne
	@JoinColumn(name="TSSC_GAME_ID")
	private TsscGame tsscGame;

	//bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name="TSSC_STATE_ID")
	private TsscState tsscState;

	public TsscGroup() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getAvatar() {
		return this.avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getGpPosition() {
		return this.gpPosition;
	}

	public void setGpPosition(BigDecimal position) {
		this.gpPosition = position;
	}

	public String getQrPassword() {
		return this.qrPassword;
	}

	public void setQrPassword(String qrPassword) {
		this.qrPassword = qrPassword;
	}

	public List<TsscDeliverable> getTsscDeliverables() {
		return this.tsscDeliverables;
	}

	public void setTsscDeliverables(List<TsscDeliverable> tsscDeliverables) {
		this.tsscDeliverables = tsscDeliverables;
	}

	public TsscDeliverable addTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().add(tsscDeliverable);
		tsscDeliverable.setTsscGroup(this);

		return tsscDeliverable;
	}

	public TsscDeliverable removeTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().remove(tsscDeliverable);
		tsscDeliverable.setTsscGroup(null);

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