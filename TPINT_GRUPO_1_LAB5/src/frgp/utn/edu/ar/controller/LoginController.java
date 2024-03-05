package frgp.utn.edu.ar.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.negocio.InegocioArticulo;
import frgp.utn.edu.ar.negocio.InegocioLogin;
import frgp.utn.edu.ar.negocio.InegocioMarca;
import frgp.utn.edu.ar.negocio.InegocioTipoArticulo;
import frgp.utn.edu.ar.entidad.TipoUsuario;
import frgp.utn.edu.ar.entidad.usuario;



@Controller
public class LoginController {

	@Autowired
	public  InegocioLogin serviceBeanLogIn;
	@Autowired
	public  InegocioArticulo servicio;
	
	
	
	@Autowired
	public  InegocioMarca servicioMarca;
	@Autowired
	public  InegocioTipoArticulo servicioTipoArt;

	
	@RequestMapping(value ="/inicio_sesion.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validarLogIn(String usuarioTxt, String contraseniaTxt, HttpServletRequest request){
		
		usuario usuarioLogeado = new usuario();
		ModelAndView MV = new ModelAndView();
		usuario usuario = new usuario(usuarioTxt, contraseniaTxt);
		
		
		String Message="";
		
		try{
			
			usuario respuestaUsuario = new usuario();
			TipoUsuario aux = new TipoUsuario();
			aux.setId(-1);
			respuestaUsuario.setTipoUsuario(aux);
			
			respuestaUsuario = serviceBeanLogIn.inicioSesionUsuario(usuario);
			if(respuestaUsuario.getTipoUsuario().getId() == -1) { 
				Message = "Usuario o contrasenia incorrecta";
				MV.addObject("mensajeLogIn", Message);
				MV.setViewName("index");
				
				
			}
				
			else { 
				Message = "Autenticación realizada con éxito";
				
				usuarioLogeado = respuestaUsuario;
				HttpSession session = request.getSession();
				session.setAttribute("nombreUsuario", usuarioLogeado.getNombre());
				session.setAttribute("tipoUsuario", usuarioLogeado.getTipoUsuario().getId());
				//System.out.println(usuarioLogeado.toString());
				//Si el usuario es contador, lo manda a ventasListado. Si es vendedor, a articulosListado.
				//1 = Vendedor, 2 = Contador
					if(usuarioLogeado.getTipoUsuario().getId() == 2) 
					{
						
						MV.setViewName("contadorListadoVentas");
					}
					else {
					
						MV.addObject("listaArticulos",this.servicio.obtenerArticulos());
						MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
						MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());
					MV.setViewName("articulosListado");
						 }
				
				}
			}
		catch(Exception e)
		{
			Message = "Experimentamos un error: " + e.toString();
		}

		MV.addObject("Mensaje", Message); 
		return MV;

		
		
	}
	
}