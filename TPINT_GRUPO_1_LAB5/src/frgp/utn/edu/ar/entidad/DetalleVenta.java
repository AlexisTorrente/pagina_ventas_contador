package frgp.utn.edu.ar.entidad;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVenta")
    private Venta venta;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;
	
    private int cantidad;
    private double totalVenta;
    private double totalCompra;
    
    
    public DetalleVenta() {
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	public Articulo getArticulo() {
		return articulo;
	}


	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getTotalVenta() {
		return totalVenta;
	}


	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}


	public double getTotalCompra() {
		return totalCompra;
	}


	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}


	
    
}
