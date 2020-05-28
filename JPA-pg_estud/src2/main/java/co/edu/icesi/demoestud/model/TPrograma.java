package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_programas database table.
 * 
 */
@Entity
@Table(name="t_programas")
@NamedQuery(name="TPrograma.findAll", query="SELECT t FROM TPrograma t")
public class TPrograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String alias;

	private String descripcion;

	//bi-directional many-to-one association to TMatxaprobar
	@OneToMany(mappedBy="TPrograma")
	private List<TMatxaprobar> TMatxaprobars;

	//bi-directional many-to-many association to TMateria
	@ManyToMany
	@JoinTable(
		name="t_matxprograma"
		, joinColumns={
			@JoinColumn(name="programa_codigo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="materia_codigo")
			}
		)
	private List<TMateria> TMaterias;

	//bi-directional many-to-one association to TProgAlumno
	@OneToMany(mappedBy="TPrograma")
	private List<TProgAlumno> TProgAlumnos;

	//bi-directional many-to-one association to TCarrera
	@ManyToOne
	@JoinColumn(name="carrera")
	private TCarrera TCarrera;

	public TPrograma() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<TMatxaprobar> getTMatxaprobars() {
		return this.TMatxaprobars;
	}

	public void setTMatxaprobars(List<TMatxaprobar> TMatxaprobars) {
		this.TMatxaprobars = TMatxaprobars;
	}

	public TMatxaprobar addTMatxaprobar(TMatxaprobar TMatxaprobar) {
		getTMatxaprobars().add(TMatxaprobar);
		TMatxaprobar.setTPrograma(this);

		return TMatxaprobar;
	}

	public TMatxaprobar removeTMatxaprobar(TMatxaprobar TMatxaprobar) {
		getTMatxaprobars().remove(TMatxaprobar);
		TMatxaprobar.setTPrograma(null);

		return TMatxaprobar;
	}

	public List<TMateria> getTMaterias() {
		return this.TMaterias;
	}

	public void setTMaterias(List<TMateria> TMaterias) {
		this.TMaterias = TMaterias;
	}

	public List<TProgAlumno> getTProgAlumnos() {
		return this.TProgAlumnos;
	}

	public void setTProgAlumnos(List<TProgAlumno> TProgAlumnos) {
		this.TProgAlumnos = TProgAlumnos;
	}

	public TProgAlumno addTProgAlumno(TProgAlumno TProgAlumno) {
		getTProgAlumnos().add(TProgAlumno);
		TProgAlumno.setTPrograma(this);

		return TProgAlumno;
	}

	public TProgAlumno removeTProgAlumno(TProgAlumno TProgAlumno) {
		getTProgAlumnos().remove(TProgAlumno);
		TProgAlumno.setTPrograma(null);

		return TProgAlumno;
	}

	public TCarrera getTCarrera() {
		return this.TCarrera;
	}

	public void setTCarrera(TCarrera TCarrera) {
		this.TCarrera = TCarrera;
	}

}