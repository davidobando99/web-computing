package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the t_cohortes database table.
 * 
 */
@Entity
@Table(name = "t_cohortes")
@NamedQuery(name = "TCohorte.findAll", query = "SELECT t FROM TCohorte t")
public class TCohorte implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TCohortePK id;

	private String semestre;

	// bi-directional many-to-one association to TMatxprograma
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "materia_codigo", referencedColumnName = "materia_codigo", insertable = false, updatable = false),
			@JoinColumn(name = "programa_codigo", referencedColumnName = "programa_codigo", insertable = false, updatable = false) })
	private TMatxprograma TMatxprograma;

	// bi-directional many-to-many association to TMateria
	@ManyToMany(mappedBy = "TCohortes")
	private List<TMateria> TMaterias;

	public TCohorte() {
	}

	public TCohortePK getId() {
		return this.id;
	}

	public void setId(TCohortePK id) {
		this.id = id;
	}

	public String getSemestre() {
		return this.semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public TMatxprograma getTMatxprograma() {
		return this.TMatxprograma;
	}

	public void setTMatxprograma(TMatxprograma TMatxprograma) {
		this.TMatxprograma = TMatxprograma;
	}

	public List<TMateria> getTMaterias() {
		return this.TMaterias;
	}

	public void setTMaterias(List<TMateria> TMaterias) {
		this.TMaterias = TMaterias;
	}

}