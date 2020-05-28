package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_cohortes database table.
 * 
 */
@Embeddable
public class TCohortePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="programa_codigo", insertable=false, updatable=false)
	private String programaCodigo;

	private String cohorte;

	@Column(name="materia_codigo", insertable=false, updatable=false)
	private String materiaCodigo;

	public TCohortePK() {
	}
	public String getProgramaCodigo() {
		return this.programaCodigo;
	}
	public void setProgramaCodigo(String programaCodigo) {
		this.programaCodigo = programaCodigo;
	}
	public String getCohorte() {
		return this.cohorte;
	}
	public void setCohorte(String cohorte) {
		this.cohorte = cohorte;
	}
	public String getMateriaCodigo() {
		return this.materiaCodigo;
	}
	public void setMateriaCodigo(String materiaCodigo) {
		this.materiaCodigo = materiaCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCohortePK)) {
			return false;
		}
		TCohortePK castOther = (TCohortePK)other;
		return 
			this.programaCodigo.equals(castOther.programaCodigo)
			&& this.cohorte.equals(castOther.cohorte)
			&& this.materiaCodigo.equals(castOther.materiaCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.programaCodigo.hashCode();
		hash = hash * prime + this.cohorte.hashCode();
		hash = hash * prime + this.materiaCodigo.hashCode();
		
		return hash;
	}
}