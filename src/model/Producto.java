package model;

import java.sql.Date;

//MODELO

public class Producto {

	private int id;
	private String nombre;
	private double cantidad;
	private double precio;
	private Date fecha_creacion;
	private Date fecha_actualizar;
	
	public Producto() {
		
	}
	
	public Producto(int id, String nombre, double cantidad, double precio, Date fecha_creacion, Date fecha_actualizar) {
	
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fecha_creacion = fecha_creacion;
		this.fecha_actualizar = fecha_actualizar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_actualizar() {
		return fecha_actualizar;
	}

	public void setFecha_actualizar(Date fecha_actualizar) {
		this.fecha_actualizar = fecha_actualizar;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", fecha_creacion=" + fecha_creacion + ", fecha_actualizar=" + fecha_actualizar + "]";
	}
	
	
}
