package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TSSC_GAME_ADMIN database table.
 * 
 */
@Entity
@Table(name="TSSC_GAME_ADMIN")
@NamedQuery(name="TsscGameAdmin.findAll", query="SELECT t FROM TsscGameAdmin t")
public class TsscGameAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TSSC_GAME_ADMIN_ID_GENERATOR",allocationSize = 1,  sequenceName="TSSC_GAME_ADMIN_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TSSC_GAME_ADMIN_ID_GENERATOR")
	private long id;

	//bi-directional many-to-one association to TsscAdmin
	@ManyToOne
	@JoinColumn(name="TSSC_ADMIN_ID")
	private TsscAdmin tsscAdmin;

	//bi-directional many-to-one association to TsscGame
	@ManyToOne
	@JoinColumn(name="TSSC_GAME_ID")
	private TsscGame tsscGame;

	public TsscGameAdmin() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TsscAdmin getTsscAdmin() {
		return this.tsscAdmin;
	}

	public void setTsscAdmin(TsscAdmin tsscAdmin) {
		this.tsscAdmin = tsscAdmin;
	}

	public TsscGame getTsscGame() {
		return this.tsscGame;
	}

	public void setTsscGame(TsscGame tsscGame) {
		this.tsscGame = tsscGame;
	}

}