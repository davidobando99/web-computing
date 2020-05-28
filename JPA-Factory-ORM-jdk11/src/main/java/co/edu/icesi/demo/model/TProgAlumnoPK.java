package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_prog_alumnos database table.
 * 
 */
@Embeddable
public class TProgAlumnoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="periodo_acad")
	private String periodoAcad;

	@Column(name="alumno_codigo", insertable=false, updatable=false)
	private String alumnoCodigo;

	@Column(name="programa_codigo", insertable=false, updatable=false)
	private String programaCodigo;

	private String principal;

	public TProgAlumnoPK() {
	}
	public String getPeriodoAcad() {
		return this.periodoAcad;
	}
	public void setPeriodoAcad(String periodoAcad) {
		this.periodoAcad = periodoAcad;
	}
	public String getAlumnoCodigo() {
		return this.alumnoCodigo;
	}
	public void setAlumnoCodigo(String alumnoCodigo) {
		this.alumnoCodigo = alumnoCodigo;
	}
	public String getProgramaCodigo() {
		return this.programaCodigo;
	}
	public void setProgramaCodigo(String programaCodigo) {
		this.programaCodigo = programaCodigo;
	}
	public String getPrincipal() {
		return this.principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TProgAlumnoPK)) {
			return false;
		}
		TProgAlumnoPK castOther = (TProgAlumnoPK)other;
		return 
			this.periodoAcad.equals(castOther.periodoAcad)
			&& this.alumnoCodigo.equals(castOther.alumnoCodigo)
			&& this.programaCodigo.equals(castOther.programaCodigo)
			&& this.principal.equals(castOther.principal);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.periodoAcad.hashCode();
		hash = hash * prime + this.alumnoCodigo.hashCode();
		hash = hash * prime + this.programaCodigo.hashCode();
		hash = hash * prime + this.principal.hashCode();
		
		return hash;
	}
}