package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_matxprograma database table.
 * 
 */
@Embeddable
public class TMatxprogramaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="materia_codigo", insertable=false, updatable=false)
	private String materiaCodigo;

	@Column(name="programa_codigo", insertable=false, updatable=false)
	private String programaCodigo;

	public TMatxprogramaPK() {
	}
	public String getMateriaCodigo() {
		return this.materiaCodigo;
	}
	public void setMateriaCodigo(String materiaCodigo) {
		this.materiaCodigo = materiaCodigo;
	}
	public String getProgramaCodigo() {
		return this.programaCodigo;
	}
	public void setProgramaCodigo(String programaCodigo) {
		this.programaCodigo = programaCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TMatxprogramaPK)) {
			return false;
		}
		TMatxprogramaPK castOther = (TMatxprogramaPK)other;
		return 
			this.materiaCodigo.equals(castOther.materiaCodigo)
			&& this.programaCodigo.equals(castOther.programaCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.materiaCodigo.hashCode();
		hash = hash * prime + this.programaCodigo.hashCode();
		
		return hash;
	}
}