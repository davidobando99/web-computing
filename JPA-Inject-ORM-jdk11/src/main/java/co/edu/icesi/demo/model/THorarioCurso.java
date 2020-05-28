package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the t_horario_cursos database table.
 * 
 */
@Entity
@Table(name = "t_horario_cursos")
@NamedQuery(name = "THorarioCurso.findAll", query = "SELECT t FROM THorarioCurso t")
public class THorarioCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private THorarioCursoPK id;

	@Column(name = "hora_fin")
	private BigDecimal horaFin;

	@Column(name = "hora_inicio")
	private BigDecimal horaInicio;

	// bi-directional many-to-one association to TCurso
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "grupo", referencedColumnName = "grupo", insertable = false, updatable = false),
			@JoinColumn(name = "materia_codigo", referencedColumnName = "materia_codigo", insertable = false, updatable = false),
			@JoinColumn(name = "periodo_acad", referencedColumnName = "periodo_acad", insertable = false, updatable = false) })
	private TCurso TCurso;

	// bi-directional many-to-one association to TProfesore
	@ManyToOne
	@JoinColumn(name = "cedula_prof", insertable = false, updatable = false )
	private TProfesore TProfesore;

	public THorarioCurso() {
	}

	public THorarioCursoPK getId() {
		return this.id;
	}

	public void setId(THorarioCursoPK id) {
		this.id = id;
	}

	public BigDecimal getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(BigDecimal horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(BigDecimal horaInicio) {
		this.horaInicio = horaInicio;
	}

	public TCurso getTCurso() {
		return this.TCurso;
	}

	public void setTCurso(TCurso TCurso) {
		this.TCurso = TCurso;
	}

	public TProfesore getTProfesore() {
		return this.TProfesore;
	}

	public void setTProfesore(TProfesore TProfesore) {
		this.TProfesore = TProfesore;
	}

}