package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_cursos database table.
 * 
 */
@Embeddable
public class TCursoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="periodo_acad")
	private String periodoAcad;

	@Column(name="materia_codigo", insertable=false, updatable=false)
	private String materiaCodigo;

	private long grupo;

	public TCursoPK() {
	}
	public String getPeriodoAcad() {
		return this.periodoAcad;
	}
	public void setPeriodoAcad(String periodoAcad) {
		this.periodoAcad = periodoAcad;
	}
	public String getMateriaCodigo() {
		return this.materiaCodigo;
	}
	public void setMateriaCodigo(String materiaCodigo) {
		this.materiaCodigo = materiaCodigo;
	}
	public long getGrupo() {
		return this.grupo;
	}
	public void setGrupo(long grupo) {
		this.grupo = grupo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCursoPK)) {
			return false;
		}
		TCursoPK castOther = (TCursoPK)other;
		return 
			this.periodoAcad.equals(castOther.periodoAcad)
			&& this.materiaCodigo.equals(castOther.materiaCodigo)
			&& (this.grupo == castOther.grupo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.periodoAcad.hashCode();
		hash = hash * prime + this.materiaCodigo.hashCode();
		hash = hash * prime + ((int) (this.grupo ^ (this.grupo >>> 32)));
		
		return hash;
	}
}