package frgp.utn.edu.ar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Articulo;
import frgp.utn.edu.ar.entidad.Cliente;
import frgp.utn.edu.ar.negocio.InegocioCliente;
import frgp.utn.edu.ar.negocio.InegocioGenero;
import frgp.utn.edu.ar.negocio.InegocioLocalidad;

@Controller
public class ClienteController {

	@Autowired
	public InegocioCliente servicioCliente;
	@Autowired
	public InegocioGenero servicioGenero;	
	@Autowired
	public InegocioLocalidad servicioLocalidad;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.servicioCliente = (InegocioCliente) ctx.getBean("serviceBeanCliente");
		this.servicioGenero = (InegocioGenero) ctx.getBean("serviceBeanGenero");
		this.servicioLocalidad = (InegocioLocalidad) ctx.getBean("serviceBeanLocalidad");
	}
	
	@RequestMapping("redireccionar_clientesAlta.html")
	public ModelAndView eventoRedireccionarClientesAlta()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
		MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());		
		
		MV.setViewName("clientesAlta");
		return MV;
	}
	
	@RequestMapping(value ="/clientesAlta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView agregarCliente(String dniCliente, String nombreCliente, String apellidoCliente, int generoCliente, String fechaNacimientoCliente, String direccionCliente, int localidadCliente, String correoCliente, String telefonoCliente){
		
		ModelAndView MV = new ModelAndView();
		String Message="";
		
		// Formato de la fecha recibida
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCliente, formatter);

		// Obtener la fecha actual
		LocalDate fechaActual = LocalDate.now();

	    
	    
	    if (fechaNacimiento.isAfter(fechaActual)) {

	    	Message="Error: La fecha ingresada no puede ser mayor a la actual. Intentelo nuevamente.";
	    	MV.addObject("Mensaje", Message);
			MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
			MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());		
			
			MV.setViewName("clientesAlta");
			return MV;
	    }
		
		System.out.println(dniCliente + " - " + nombreCliente + " - " + apellidoCliente + " - " + generoCliente + " - " + fechaNacimientoCliente + " - " + direccionCliente + " - " + localidadCliente + " - " + correoCliente+ " - " + telefonoCliente);
		Cliente cliente = new Cliente();
		cliente.setDni(dniCliente);
		cliente.setNombre(nombreCliente);
		cliente.setApellido(apellidoCliente);
		cliente.setGenero(servicioGenero.obtenerUnGenero(generoCliente));
		cliente.setFechaNac(fechaNacimientoCliente);
		cliente.setDireccion(direccionCliente);
		cliente.setLocalidad(servicioLocalidad.obtenerUnaLocalidad(localidadCliente));
		cliente.setCorreo(correoCliente);
		cliente.setTelefono(telefonoCliente);
		cliente.setEstado(true);
		
		
		String info="";
		
		try{
			
			servicioCliente.insertarCliente(cliente);
			Message = "Cliente agregado";
		}
		catch(Exception e)
		{
			Message = "No se pudo agregar el cliente";
			info = " - Mensaje de error: " + e.toString();
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message + info);
		MV.addObject("Mensaje", Message);
		MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
		MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());		
		
		MV.setViewName("clientesAlta");
		return MV;
	}
	
	@RequestMapping("redireccionar_clientesListado.html")
	public ModelAndView eventoRedireccionarClientesListado()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
		MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());		
		MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
		
		
		MV.setViewName("clientesListado");
		return MV;
	}
	
	@RequestMapping(value ="/clientesListado.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listarClientes(int generoCliente, int localidadCliente, String fechaMayorQue, String fechaMenorQue){
		
		ModelAndView MV = new ModelAndView();	
		MV.addObject("listaClientes", this.servicioCliente.obtenerClientesConFiltro(generoCliente, localidadCliente, fechaMayorQue, fechaMenorQue));
		MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
		MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());	
		MV.addObject("filtroGenero", generoCliente);
		MV.addObject("filtroLocalidad", localidadCliente);
		MV.addObject("filtroFechaInicio", fechaMayorQue);
		MV.addObject("filtroFechaFin", fechaMenorQue);

		//System.out.println("Filtro frcha 1: " + fechaMayorQue + " - Filtro fecha 2: " + fechaMenorQue);
		
		MV.setViewName("clientesListado");
		return MV;
		
	}
	
	@RequestMapping(value ="/cargarDatosCliente.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView cargarDatosCliente(String ddlClientes){
		
		ModelAndView MV = new ModelAndView();
		
		Cliente cliente = new Cliente();
		cliente = servicioCliente.obtenerUnCliente(ddlClientes);
		
		
		MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());	
		MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
		MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());
		MV.addObject("cliente",cliente);	
		MV.addObject("clienteSeleccionado", ddlClientes);
		
		MV.setViewName("clientesModificacion"); 
		return MV;
		
	}
	
	@RequestMapping("redireccionar_clientesModificacion.html")
	public ModelAndView eventoRedireccionarClientesModificacion()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
		MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());			
		MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());	
		
		MV.setViewName("clientesModificacion");
		return MV;
	}
	
	@RequestMapping(value ="/clientesModificacion.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modificacionCliente(String dniCliente, String nombreCliente, String apellidoCliente, int generoCliente, String fechaNacimientoCliente, 
			String direccionCliente, int localidadCliente, String correoCliente, String telefonoCliente){
		
		ModelAndView MV = new ModelAndView();
		System.out.println(dniCliente + " - " + nombreCliente + " - " + apellidoCliente + " - " + generoCliente + " - " + fechaNacimientoCliente + " - " + direccionCliente + " - " + localidadCliente + " - " + correoCliente+ " - " + telefonoCliente);
		
		Cliente cliente = new Cliente();
		cliente.setDni(dniCliente);
		cliente.setNombre(nombreCliente);
		cliente.setApellido(apellidoCliente);
		cliente.setGenero(servicioGenero.obtenerUnGenero(generoCliente));
		cliente.setFechaNac(fechaNacimientoCliente);
		cliente.setDireccion(direccionCliente);
		cliente.setLocalidad(servicioLocalidad.obtenerUnaLocalidad(localidadCliente));
		cliente.setCorreo(correoCliente);
		cliente.setTelefono(telefonoCliente);
		cliente.setEstado(true);
		
		String Message="";
		String info="";
		
		try{
			
			servicioCliente.actualizarCliente(cliente);
			Message = "Cliente actualizado";
		}
		catch(Exception e)
		{
			Message = "No se pudo actualizar el cliente";
			info = " - Mensaje de error: " + e.toString();
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message + info);
		MV.addObject("Mensaje", Message);
		MV.addObject("listaGeneros", this.servicioGenero.obtenerGeneros());
		MV.addObject("listaLocalidades", this.servicioLocalidad.obtenerLocalidades());			
		MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
		
		MV.setViewName("clientesModificacion"); 
		return MV;
		
	}
	
	@RequestMapping("redireccionar_clientesBaja.html")
	public ModelAndView eventoRedireccionarClientesBaja()
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("clientesBaja");
		return MV;
	}
	
	@RequestMapping(value ="/clientesBaja.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modificacionCliente(String dniCliente){
		
		ModelAndView MV = new ModelAndView();
		String Message="";
		String info="";
		
		try{
			
			servicioCliente.eliminarCliente(dniCliente);
			Message = "Cliente dado de baja.";
		}
		catch(Exception e)
		{
			Message = "No se pudo dar de baja el cliente";
			info = " - Mensaje de error: " + e.toString();
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message + info);
		MV.addObject("Mensaje", Message);		
		
		MV.setViewName("clientesBaja"); 
		return MV;
		
	}
	
	
	
}
