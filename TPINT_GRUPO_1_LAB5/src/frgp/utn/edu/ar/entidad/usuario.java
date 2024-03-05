package frgp.utn.edu.ar.entidad;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Usuario")
public class usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nombre;
    private String contrasenia;
    @ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name = "tipoUsuario_id")
    private TipoUsuario tipoUsuario; //1 = Vendedor, 2 = Contador
    private boolean estado;

    public usuario() {
        this.nombre = "vacio";
        this.contrasenia = "vacio";
        this.tipoUsuario = null;
        estado = true;
    }
    
    public usuario(String nombre, String contrasenia) {
    	this.nombre = nombre;
    	this.contrasenia = contrasenia;
    }

    public usuario(String nombre, String contrasenia, TipoUsuario tipoUsuario, boolean estado) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "usuario [nombre=" + nombre + ", contrasenia=" + contrasenia + ", tipoUsuario="
                + tipoUsuario + ", estado=" + estado + "]";
    }

    
    
    
}
