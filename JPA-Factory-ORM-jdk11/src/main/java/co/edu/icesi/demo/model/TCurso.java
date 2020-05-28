package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the t_cursos database table.
 * 
 */
@Entity
@Table(name="t_cursos")
@NamedQuery(name="TCurso.findAll", query="SELECT t FROM TCurso t")
public class TCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TCursoPK id;

	//bi-directional many-to-one association to TMateria
	@ManyToOne
	@JoinColumn(name="materia_codigo", insertable = false, updatable = false)
	private TMateria TMateria;

	//bi-directional many-to-one association to THorarioCurso
	@OneToMany(mappedBy="TCurso")
	private List<THorarioCurso> THorarioCursos;

	public TCurso() {
	}

	public TCursoPK getId() {
		return this.id;
	}

	public void setId(TCursoPK id) {
		this.id = id;
	}

	public TMateria getTMateria() {
		return this.TMateria;
	}

	public void setTMateria(TMateria TMateria) {
		this.TMateria = TMateria;
	}

	public List<THorarioCurso> getTHorarioCursos() {
		return this.THorarioCursos;
	}

	public void setTHorarioCursos(List<THorarioCurso> THorarioCursos) {
		this.THorarioCursos = THorarioCursos;
	}

	public THorarioCurso addTHorarioCurso(THorarioCurso THorarioCurso) {
		getTHorarioCursos().add(THorarioCurso);
		THorarioCurso.setTCurso(this);

		return THorarioCurso;
	}

	public THorarioCurso removeTHorarioCurso(THorarioCurso THorarioCurso) {
		getTHorarioCursos().remove(THorarioCurso);
		THorarioCurso.setTCurso(null);

		return THorarioCurso;
	}

}