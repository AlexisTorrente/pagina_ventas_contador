package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Venta")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;

    private boolean estado;
    private String fecha;
    private double precioTotal;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;


    public Venta() {
    }
    
    //setters and getters
    public int getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    /*
     * public Cliente getCliente() { return cliente; } public void
     * setCliente(Cliente cliente) { this.cliente = cliente; }
     */


	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fechaVenta) {
		this.fecha = fechaVenta;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", estado=" + estado + ", fecha=" + fecha + ", precioTotal=" + precioTotal
				+ ", cliente=" + cliente + "]";
	}

	

	
    
    
}