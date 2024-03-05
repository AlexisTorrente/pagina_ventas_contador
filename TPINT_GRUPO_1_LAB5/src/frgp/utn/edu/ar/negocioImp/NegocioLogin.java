package frgp.utn.edu.ar.negocioImp;

import frgp.utn.edu.ar.negocio.InegocioLogin;
import frgp.utn.edu.ar.dao.daoLogin;
import frgp.utn.edu.ar.entidad.TipoUsuario;
import frgp.utn.edu.ar.entidad.usuario;

public class NegocioLogin implements InegocioLogin{
	
	private daoLogin dataAccess = null;
	
	public void setDataAccess(daoLogin dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public usuario inicioSesionUsuario(usuario usuarioIngresado) {
		
		usuario UsuarioEncontrado = dataAccess.inicioSesionUsuario(usuarioIngresado);
		
		if (UsuarioEncontrado.getContrasenia().equals(usuarioIngresado.getContrasenia()) && UsuarioEncontrado.isEstado()) {
			return UsuarioEncontrado;
		}
		else{
			TipoUsuario aux = new TipoUsuario();
			aux.setId(-1);
			usuarioIngresado.setTipoUsuario(aux);;
			return usuarioIngresado;
		}
	}

}
