package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_matxaprobar database table.
 * 
 */
@Embeddable
public class TMatxaprobarPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String alumno;

	@Column(insertable=false, updatable=false)
	private String programa;

	@Column(insertable=false, updatable=false)
	private String materia;

	public TMatxaprobarPK() {
	}
	public String getAlumno() {
		return this.alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public String getPrograma() {
		return this.programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getMateria() {
		return this.materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TMatxaprobarPK)) {
			return false;
		}
		TMatxaprobarPK castOther = (TMatxaprobarPK)other;
		return 
			this.alumno.equals(castOther.alumno)
			&& this.programa.equals(castOther.programa)
			&& this.materia.equals(castOther.materia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.alumno.hashCode();
		hash = hash * prime + this.programa.hashCode();
		hash = hash * prime + this.materia.hashCode();
		
		return hash;
	}
}