package com.example.brokus;



import java.sql.Date;

public class BRPublicacion {
	private Integer id;
	private String titulo;
	private String contenido;
	private String anexo;
	private Date fechaCaducidad;
	private Integer idUsuario;
	private String nombreUsuario;
	private String fechaString;
	
	private String extension;
	
	public void setNombreUsuario(String nombreUsuario){
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getNombreUsuario(){
		return this.nombreUsuario;
	}
	
	public String getExtension(){
		return this.extension;
	}
	
	public void setExtension(String extension){
		this.extension = extension;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public BRPublicacion() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo; 
	} 
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	public void setFechaString(String fechaString){
		this.fechaString = fechaString;
	}
	
	public String getFechaString(){
		return this.fechaString;
	}
	
	
	
	
}
