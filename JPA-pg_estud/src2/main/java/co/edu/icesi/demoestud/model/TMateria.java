package co.edu.icesi.demoestud.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the t_materias database table.
 * 
 */
@Entity
@Table(name="t_materias")
@NamedQuery(name="TMateria.findAll", query="SELECT t FROM TMateria t")
public class TMateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private BigDecimal cupo;

	private BigDecimal intensidad;

	private String nombre;

	//bi-directional many-to-one association to TCurso
	@OneToMany(mappedBy="TMateria")
	private List<TCurso> TCursos;

	//bi-directional many-to-one association to TMatxaprobar
	@OneToMany(mappedBy="TMateria")
	private List<TMatxaprobar> TMatxaprobars;

	//bi-directional many-to-many association to TPrograma
	@ManyToMany(mappedBy="TMaterias")
	private List<TPrograma> TProgramas;

	//bi-directional many-to-many association to TCohorte
	@ManyToMany
	@JoinTable(
		name="t_prereq_mat"
		, joinColumns={
			@JoinColumn(name="prereq_codigo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cohorte", referencedColumnName="cohorte"),
			@JoinColumn(name="materia_codigo", referencedColumnName="materia_codigo"),
			@JoinColumn(name="programa_codigo", referencedColumnName="programa_codigo")
			}
		)
	private List<TCohorte> TCohortes;

	public TMateria() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getCupo() {
		return this.cupo;
	}

	public void setCupo(BigDecimal cupo) {
		this.cupo = cupo;
	}

	public BigDecimal getIntensidad() {
		return this.intensidad;
	}

	public void setIntensidad(BigDecimal intensidad) {
		this.intensidad = intensidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TCurso> getTCursos() {
		return this.TCursos;
	}

	public void setTCursos(List<TCurso> TCursos) {
		this.TCursos = TCursos;
	}

	public TCurso addTCurso(TCurso TCurso) {
		getTCursos().add(TCurso);
		TCurso.setTMateria(this);

		return TCurso;
	}

	public TCurso removeTCurso(TCurso TCurso) {
		getTCursos().remove(TCurso);
		TCurso.setTMateria(null);

		return TCurso;
	}

	public List<TMatxaprobar> getTMatxaprobars() {
		return this.TMatxaprobars;
	}

	public void setTMatxaprobars(List<TMatxaprobar> TMatxaprobars) {
		this.TMatxaprobars = TMatxaprobars;
	}

	public TMatxaprobar addTMatxaprobar(TMatxaprobar TMatxaprobar) {
		getTMatxaprobars().add(TMatxaprobar);
		TMatxaprobar.setTMateria(this);

		return TMatxaprobar;
	}

	public TMatxaprobar removeTMatxaprobar(TMatxaprobar TMatxaprobar) {
		getTMatxaprobars().remove(TMatxaprobar);
		TMatxaprobar.setTMateria(null);

		return TMatxaprobar;
	}

	public List<TPrograma> getTProgramas() {
		return this.TProgramas;
	}

	public void setTProgramas(List<TPrograma> TProgramas) {
		this.TProgramas = TProgramas;
	}

	public List<TCohorte> getTCohortes() {
		return this.TCohortes;
	}

	public void setTCohortes(List<TCohorte> TCohortes) {
		this.TCohortes = TCohortes;
	}

}