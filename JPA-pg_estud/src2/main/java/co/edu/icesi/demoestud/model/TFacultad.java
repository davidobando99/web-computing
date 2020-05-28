package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_facultad database table.
 * 
 */
@Entity
@Table(name="t_facultad")
@NamedQuery(name="TFacultad.findAll", query="SELECT t FROM TFacultad t")
public class TFacultad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String descripcion;

	//bi-directional many-to-one association to TCarrera
	@OneToMany(mappedBy="TFacultad")
	private List<TCarrera> TCarreras;

	public TFacultad() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<TCarrera> getTCarreras() {
		return this.TCarreras;
	}

	public void setTCarreras(List<TCarrera> TCarreras) {
		this.TCarreras = TCarreras;
	}

	public TCarrera addTCarrera(TCarrera TCarrera) {
		getTCarreras().add(TCarrera);
		TCarrera.setTFacultad(this);

		return TCarrera;
	}

	public TCarrera removeTCarrera(TCarrera TCarrera) {
		getTCarreras().remove(TCarrera);
		TCarrera.setTFacultad(null);

		return TCarrera;
	}

}