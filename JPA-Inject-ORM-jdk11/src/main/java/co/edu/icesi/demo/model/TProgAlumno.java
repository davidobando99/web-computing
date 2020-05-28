package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_prog_alumnos database table.
 * 
 */
@Entity
@Table(name="t_prog_alumnos")
@NamedQuery(name="TProgAlumno.findAll", query="SELECT t FROM TProgAlumno t")
public class TProgAlumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TProgAlumnoPK id;

	private String cohorte;

	private String semestre;

	//bi-directional many-to-one association to TAlumno
	@ManyToOne
	@JoinColumn(name="alumno_codigo", insertable = false, updatable = false )
	private TAlumno TAlumno;

	//bi-directional many-to-one association to TPrograma
	@ManyToOne
	@JoinColumn(name="programa_codigo", insertable = false, updatable = false)
	private TPrograma TPrograma;

	public TProgAlumno() {
	}

	public TProgAlumnoPK getId() {
		return this.id;
	}

	public void setId(TProgAlumnoPK id) {
		this.id = id;
	}

	public String getCohorte() {
		return this.cohorte;
	}

	public void setCohorte(String cohorte) {
		this.cohorte = cohorte;
	}

	public String getSemestre() {
		return this.semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public TAlumno getTAlumno() {
		return this.TAlumno;
	}

	public void setTAlumno(TAlumno TAlumno) {
		this.TAlumno = TAlumno;
	}

	public TPrograma getTPrograma() {
		return this.TPrograma;
	}

	public void setTPrograma(TPrograma TPrograma) {
		this.TPrograma = TPrograma;
	}

}