package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_carreras database table.
 * 
 */
@Entity
@Table(name="t_carreras")
@NamedQuery(name="TCarrera.findAll", query="SELECT t FROM TCarrera t")
public class TCarrera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String descripcion;

	//bi-directional many-to-one association to TFacultad
	@ManyToOne
	@JoinColumn(name="facultad")
	private TFacultad TFacultad;

	//bi-directional many-to-one association to TPrograma
	@OneToMany(mappedBy="TCarrera")
	private List<TPrograma> TProgramas;

	public TCarrera() {
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

	public TFacultad getTFacultad() {
		return this.TFacultad;
	}

	public void setTFacultad(TFacultad TFacultad) {
		this.TFacultad = TFacultad;
	}

	public List<TPrograma> getTProgramas() {
		return this.TProgramas;
	}

	public void setTProgramas(List<TPrograma> TProgramas) {
		this.TProgramas = TProgramas;
	}

	public TPrograma addTPrograma(TPrograma TPrograma) {
		getTProgramas().add(TPrograma);
		TPrograma.setTCarrera(this);

		return TPrograma;
	}

	public TPrograma removeTPrograma(TPrograma TPrograma) {
		getTProgramas().remove(TPrograma);
		TPrograma.setTCarrera(null);

		return TPrograma;
	}

}