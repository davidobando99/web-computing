package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_matxprograma database table.
 * 
 */
@Entity
@Table(name="t_matxprograma")
@NamedQuery(name="TMatxprograma.findAll", query="SELECT t FROM TMatxprograma t")
public class TMatxprograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TMatxprogramaPK id;

	//bi-directional many-to-one association to TCohorte
	@OneToMany(mappedBy="TMatxprograma")
	private List<TCohorte> TCohortes;

	//bi-directional many-to-one association to TPrograma
	@ManyToOne
	@JoinColumn(name="programa_codigo", insertable = false, updatable = false)
	private TPrograma TPrograma;

	public TMatxprograma() {
	}

	public TMatxprogramaPK getId() {
		return this.id;
	}

	public void setId(TMatxprogramaPK id) {
		this.id = id;
	}

	public List<TCohorte> getTCohortes() {
		return this.TCohortes;
	}

	public void setTCohortes(List<TCohorte> TCohortes) {
		this.TCohortes = TCohortes;
	}

	public TCohorte addTCohorte(TCohorte TCohorte) {
		getTCohortes().add(TCohorte);
		TCohorte.setTMatxprograma(this);

		return TCohorte;
	}

	public TCohorte removeTCohorte(TCohorte TCohorte) {
		getTCohortes().remove(TCohorte);
		TCohorte.setTMatxprograma(null);

		return TCohorte;
	}

	public TPrograma getTPrograma() {
		return this.TPrograma;
	}

	public void setTPrograma(TPrograma TPrograma) {
		this.TPrograma = TPrograma;
	}

}