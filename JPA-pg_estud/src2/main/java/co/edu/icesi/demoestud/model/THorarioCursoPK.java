package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_horario_cursos database table.
 * 
 */
@Embeddable
public class THorarioCursoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="periodo_acad", insertable=false, updatable=false)
	private String periodoAcad;

	@Column(name="materia_codigo", insertable=false, updatable=false)
	private String materiaCodigo;

	@Column(insertable=false, updatable=false)
	private long grupo;

	@Column(name="cedula_prof", insertable=false, updatable=false)
	private String cedulaProf;

	private String dia;

	private String salon;

	public THorarioCursoPK() {
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
	public String getCedulaProf() {
		return this.cedulaProf;
	}
	public void setCedulaProf(String cedulaProf) {
		this.cedulaProf = cedulaProf;
	}
	public String getDia() {
		return this.dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getSalon() {
		return this.salon;
	}
	public void setSalon(String salon) {
		this.salon = salon;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof THorarioCursoPK)) {
			return false;
		}
		THorarioCursoPK castOther = (THorarioCursoPK)other;
		return 
			this.periodoAcad.equals(castOther.periodoAcad)
			&& this.materiaCodigo.equals(castOther.materiaCodigo)
			&& (this.grupo == castOther.grupo)
			&& this.cedulaProf.equals(castOther.cedulaProf)
			&& this.dia.equals(castOther.dia)
			&& this.salon.equals(castOther.salon);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.periodoAcad.hashCode();
		hash = hash * prime + this.materiaCodigo.hashCode();
		hash = hash * prime + ((int) (this.grupo ^ (this.grupo >>> 32)));
		hash = hash * prime + this.cedulaProf.hashCode();
		hash = hash * prime + this.dia.hashCode();
		hash = hash * prime + this.salon.hashCode();
		
		return hash;
	}
}