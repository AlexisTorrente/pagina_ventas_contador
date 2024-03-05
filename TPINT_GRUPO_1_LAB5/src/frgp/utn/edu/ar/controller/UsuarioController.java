package frgp.utn.edu.ar.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;


import frgp.utn.edu.ar.entidad.Articulo;
import frgp.utn.edu.ar.entidad.TipoUsuario;
import frgp.utn.edu.ar.entidad.usuario;
import frgp.utn.edu.ar.negocio.InegocioTipoUsuario;
import frgp.utn.edu.ar.negocio.InegocioUsuario;

@Controller
public class UsuarioController {
	
	@Autowired
	public InegocioUsuario serviciousuario;
	@Autowired
	public InegocioTipoUsuario servicioTipoUsuario;
	
	@RequestMapping(value ="/redireccionar_usuario_alta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eventoRedireccionar_usuarioAlta()
	{
		ModelAndView MV = new ModelAndView();
		
		MV.addObject("listaTipos", this.servicioTipoUsuario.obtenerTiposUsuario());
		MV.setViewName("usuarioAlta");
		return MV;
	}
	
	
	@RequestMapping(value ="/redireccionar_usuario_alta_Contador.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eventoRedireccionar_usuarioAlta_Contador()
	{
		ModelAndView MV = new ModelAndView();
		List<TipoUsuario> listaTipos = new ArrayList<>();
		listaTipos.add( this.servicioTipoUsuario.obtenerUnTipoUsuario(2));
		MV.addObject("listaTipos", listaTipos); //1 = Vendedor, 2 = Contador
		MV.setViewName("usuarioAlta");
		return MV;
	}
	

	@RequestMapping(value ="/usuario_alta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView agregarusuario(String usuarioTxt, String contraseniaTxt, String contraConf, int IDtipoUsuarioAregistrar, int tipoUsuario){
		
		ModelAndView MV = new ModelAndView();
		String Message = "";
		usuario usuario = new usuario(usuarioTxt, contraseniaTxt);
		System.out.println(tipoUsuario);
		if(contraseniaTxt.equals(contraConf)) {
			
			usuario.setTipoUsuario(servicioTipoUsuario.obtenerUnTipoUsuario(IDtipoUsuarioAregistrar));
			usuario.setEstado(true);
		}
		else {
			Message = "Las contraseñas ingresadas no coinciden";
			MV.addObject("Mensaje",Message);
			List<TipoUsuario> listaTipos = new ArrayList<>();
			listaTipos.add( this.servicioTipoUsuario.obtenerUnTipoUsuario(2));
			MV.addObject("listaTipos", listaTipos); //1 = Vendedor, 2 = Contador
			MV.setViewName("usuarioAlta");
			return MV;
		
		}
		
		String info="";
		
		try{
			
			serviciousuario.insertarUsuario(usuario);
			Message = "usuario agregado";
		}
		catch(Exception e)
		{
			Message = "No se pudo agregar el usuario";
			info = " - Mensaje de error: " + e.toString();
		}
		finally
		{
			//Comprueba el tipo de usuario de quien ingresa, para saber que DDL devolver
			
			//1 = Vendedor, 2 = Contador
			if (tipoUsuario == 1) {
				MV.addObject("listaTipos", this.servicioTipoUsuario.obtenerTiposUsuario());
			}
			else {
				List<TipoUsuario> listaTipos = new ArrayList<>();
				listaTipos.add( this.servicioTipoUsuario.obtenerUnTipoUsuario(2));
				MV.addObject("listaTipos", listaTipos);
			}
			
		}
		
		
		
		MV.addObject("Mensaje",Message);
		MV.setViewName("usuarioAlta");
		return MV;
	}
	
	
}
