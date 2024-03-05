package frgp.utn.edu.ar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.PrinterLocation;
import javax.servlet.ServletConfig;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import frgp.utn.edu.ar.entidad.Articulo;
import frgp.utn.edu.ar.entidad.Cliente;
import frgp.utn.edu.ar.entidad.DetalleVenta;
import frgp.utn.edu.ar.entidad.Marca;
import frgp.utn.edu.ar.entidad.Stock;
import frgp.utn.edu.ar.entidad.TipoArticulo;
import frgp.utn.edu.ar.entidad.Venta;
import frgp.utn.edu.ar.entidad.usuario;
import frgp.utn.edu.ar.negocio.InegocioArticulo;
import frgp.utn.edu.ar.negocio.InegocioCliente;
import frgp.utn.edu.ar.negocio.InegocioDetalleVenta;
import frgp.utn.edu.ar.negocio.InegocioLogin;
import frgp.utn.edu.ar.negocio.InegocioMarca;
import frgp.utn.edu.ar.negocio.InegocioStock;
import frgp.utn.edu.ar.negocio.InegocioTipoArticulo;
import frgp.utn.edu.ar.negocio.InegocioVenta;

@Controller
public class ArticuloControladores {

	@Autowired
	public  InegocioArticulo servicio;
	@Autowired
	public  InegocioMarca servicioMarca;
	@Autowired
	public  InegocioTipoArticulo servicioTipoArt;

	@Autowired
	public InegocioStock servicioStock;
	@Autowired
	public InegocioCliente servicioCliente;
	@Autowired
	public  InegocioVenta servicioVenta;
	@Autowired
	public  InegocioDetalleVenta servicioDetalleVenta;
	
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		this.servicio = (InegocioArticulo) ctx.getBean("serviceBean");
		this.servicioMarca = (InegocioMarca) ctx.getBean("serviceBeanMarca");
		this.servicioTipoArt = (InegocioTipoArticulo) ctx.getBean("serviceBeanTipoArt");
//		this.servicioLogIn = (InegocioLogin) ctx.getBean("serviceBeanLogIn");
//		this.usuarioLogeado = (usuario) ctx.getBean("usuarioLogeado"); 
		this.servicioStock = (InegocioStock) ctx.getBean("serviceBeanStock"); 
		this.servicioVenta = (InegocioVenta) ctx.getBean("serviceBeanVenta");
		this.servicioCliente = (InegocioCliente) ctx.getBean("serviceBeanCliente");
	}
	
	@RequestMapping("redireccionar_index.html")
	public ModelAndView eventoRedireccionarIndex()
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("index");
		return MV;
	}

	@RequestMapping("redireccionar_articulosAlta.html")
	public ModelAndView eventoRedireccionarArticulosAlta()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
		MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());
		
		MV.setViewName("articulosAlta");
		return MV;
	}
	
	@RequestMapping(value ="/articulosAlta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView agregarArticulo(String nombreArticulo, String descripcionArticulo, int marcaArticulo, int tipoArticulo, double precioArticulo){
		
		ModelAndView MV = new ModelAndView();
		String Message="";
		System.out.println(nombreArticulo + " - " + descripcionArticulo + " - " + marcaArticulo + " - " + tipoArticulo + " - " + precioArticulo );
		
		if (precioArticulo <= 0) {
			Message = "Error: Precio ingresado invalido.";
			MV.addObject("Mensaje", Message);
			MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
			MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());
			MV.setViewName("articulosAlta"); 
			return MV;
		}
		Articulo art = new Articulo();
		art.setNombre(nombreArticulo);
		art.setDescripcion(descripcionArticulo);
		art.setMarca(servicioMarca.obtenerUnaMarca(marcaArticulo));
		art.setTipo(servicioTipoArt.obtenerUnTipoArticulo(tipoArticulo));
		art.setPrecio(precioArticulo);
		art.setEstado(true);
		
		
		
		try{
			
			servicio.insertarArticulo(art);
			Message = "Articulo agregado";
		}
		catch(Exception e)
		{
			Message = "No se pudo insertar el articulo - error: " + e.toString();
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message);
		MV.addObject("Mensaje", Message);
		MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
		MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());
		
		MV.setViewName("articulosAlta"); 
		return MV;
		
	}
	
	@RequestMapping("redireccionar_articulosListado.html")
	public ModelAndView eventoRedireccionarArticulosListado()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaArticulos",this.servicio.obtenerArticulos());
		MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
		MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());
		
		MV.setViewName("articulosListado");
		return MV;
	}
	
	@RequestMapping(value ="/articulosListado.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listarArticulos(int marcaArticulo, int tipoArticulo){
		
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaArticulos",this.servicio.obtenerArticulosConFiltro(marcaArticulo, tipoArticulo));
		MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
		MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());
		MV.addObject("filtroMarca", marcaArticulo);
		MV.addObject("filtroTipo", tipoArticulo);

		MV.setViewName("articulosListado");
		return MV;
		
	}
	
	@RequestMapping("redireccionar_articulosModificacion.html")
	public ModelAndView eventoRedireccionarArticulosModificacion()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaArticulos",this.servicio.obtenerArticulos());
		MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
		MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());
		
		MV.setViewName("articulosModificacion");
		return MV;
	}
	
	@RequestMapping(value ="/cargarDatosArticulo.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView cargarDatosArticulo(int ddlArticulos){
		
		ModelAndView MV = new ModelAndView();
		
		Articulo art = new Articulo();
		art = servicio.obtenerUnArticulo(ddlArticulos);
		
		
		MV.addObject("listaArticulos",this.servicio.obtenerArticulos());
		MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
		MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());	
		MV.addObject("articulo",art);	
		MV.addObject("articuloSeleccionado", ddlArticulos);
		
		MV.setViewName("articulosModificacion"); 
		return MV;
		
	}
	
	@RequestMapping(value ="/articulosModificacion.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modificacionArticulo(int idArticulo, String nombreArticulo, String descripcionArticulo, int marcaArticulo, int tipoArticulo, double precioArticulo){
		
		ModelAndView MV = new ModelAndView();
		
		Articulo art = new Articulo();
		art.setIdArticulo(idArticulo);
		art.setNombre(nombreArticulo);
		art.setDescripcion(descripcionArticulo);
		art.setMarca(servicioMarca.obtenerUnaMarca(marcaArticulo));
		art.setTipo(servicioTipoArt.obtenerUnTipoArticulo(tipoArticulo));
		art.setPrecio(precioArticulo);
		art.setEstado(true);
		
		String Message="";
		String info="";
		
		try{
			
			servicio.actualizarArticulo(art);
			Message = "Articulo actualizado";
		}
		catch(Exception e)
		{
			Message = "No se pudo actualizar el articulo";
			info = " - Mensaje de error: " + e.toString();
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message + info);
		MV.addObject("Mensaje", Message);
		MV.addObject("listaArticulos",this.servicio.obtenerArticulos());
		MV.addObject("listaMarcas", this.servicioMarca.obtenerMarcas());
		MV.addObject("listaTipos", this.servicioTipoArt.obtenerTiposArt());		
		
		MV.setViewName("articulosModificacion"); 
		return MV;
		
	}
	
	@RequestMapping(value ="/articulosBaja.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView bajaArticulo(int idArticulo){
		
		ModelAndView MV = new ModelAndView();
		/*
		Articulo art = new Articulo();
		art.setIdArticulo(idArticulo);
		*/
		String Message="";
		
		try{
			
			servicio.eliminarArticulo(idArticulo);
			Message = "Articulo dado de baja";
		}
		catch(Exception e)
		{
			Message = "No se pudo dar de baja el articulo - error: " + e.toString() ;
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message);
		MV.addObject("Mensaje", Message);
		MV.setViewName("articulosBaja"); 
		return MV;
		
	}
	
	@RequestMapping("redireccionar_articulosBaja.html")
	public ModelAndView eventoRedireccionarArticulosBaja()
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("articulosBaja");
		return MV;
	}
	
	// STOCK
	
	@RequestMapping("redireccionar_registroStock.html")
	public ModelAndView eventoRedireccionarRegistroStock()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaArticulos", this.servicio.obtenerArticulos());
		
		MV.setViewName("registroStock");
		return MV;
	}
	
	@RequestMapping("registroStock.html")
	public ModelAndView eventoRegistroStock(int idArticuloStock, String fechaIngresoArticulo, int cantidadArticulos, double precioCompra)
	{
		ModelAndView MV = new ModelAndView();
		String Message="";
		boolean existeArt = false;
		Articulo art = new Articulo();
		
		if(cantidadArticulos <= 0) {
			Message="Error: cantidad de articulos ingresada erronea.";
			MV.addObject("Mensaje", Message);
			MV.addObject("listaArticulos", this.servicio.obtenerArticulos());
		
			MV.setViewName("registroStock");
			return MV;
		}
		
		if(precioCompra <= 0) {
			Message="Error: precio de articulo ingresado es erroneo";
			MV.addObject("Mensaje", Message);
			MV.addObject("listaArticulos", this.servicio.obtenerArticulos());
		
			MV.setViewName("registroStock");
			return MV;
		}
		
		
		// Formato de la fecha recibida
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaIngresoStock = LocalDate.parse(fechaIngresoArticulo, formatter);

		// Obtener la fecha actual
		LocalDate fechaActual = LocalDate.now();

						    
						    
		if (fechaIngresoStock.isAfter(fechaActual)) {
			Message ="Error: la fecha ingresada no puede ser mayor a la actual.";
			MV.addObject("Mensaje", Message);
			MV.addObject("listaArticulos", this.servicio.obtenerArticulos());
			MV.setViewName("registroStock");
			return MV;
						
				}
		
		try{
			
			art = servicio.obtenerUnArticulo(idArticuloStock);
			
			existeArt = true;
		}
		catch(Exception e)
		{
			Message = "No se encontró el articulo con ID: " + idArticuloStock;
			System.out.println("Mensaje de prueba: " + Message);
		}
		
		if(existeArt) {
			Stock stock = new Stock();
			stock.setArticulo(art);
			stock.setCantidad(cantidadArticulos);
			stock.setFechaIngreso(fechaIngresoArticulo);
			stock.setPrecioCompra(precioCompra);
			stock.setEstado(true);
			
			try{
				
				servicioStock.insertarStock(stock);
				Message = "Se agregaron los articulos al stock con éxito";
			}
			catch(Exception e)
			{
				Message = "No se pudo agregar los articulos al stock";
			}
		}
		
		
		
		System.out.println("Mensaje de prueba: " + Message);
		MV.addObject("Mensaje", Message);
		MV.addObject("listaArticulos", this.servicio.obtenerArticulos());
	
		MV.setViewName("registroStock");
		return MV;
	}
	
	// VENTAS
	
	@RequestMapping("redireccionar_ventasAlta.html")
	public ModelAndView eventoRedireccionarVentasAlta()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
		
		List<Articulo> allArticulos = new ArrayList<>();
		List<Articulo> stockArticulos = new ArrayList<>();
		allArticulos = this.servicio.obtenerArticulos();
		for(Articulo art : allArticulos) {
			if(this.servicioStock.obtenerUnArticuloStock(art.getIdArticulo()) != null) {
				stockArticulos.add(art);
			}
		}
		MV.addObject("listaArticulos", stockArticulos);
		MV.setViewName("ventasAlta");
		return MV;
	}
	
	@RequestMapping(value ="/registroVenta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eventoRegistroVenta //(String fechaVenta, String listaClientes, String articulos, int cantidad)
	
	(@RequestParam("btnGuardar") String btnGuardar,
            @RequestParam("fechaVentaStr") String fechaVentaStr,
            @RequestParam("listaClientes") String listaClientes,
            @RequestParam Map<String, String> params)
	{
		//int cantidad = 9999;
		//String articulos = "testing";
		// System.out.println(fechaVenta + " - " + listaClientes + " - " + articulos + " - " + cantidad);
		
		/*
		 * 
		 * */
		ModelAndView MV = new ModelAndView();
		String Message="";
		
		/*SimpleDateFormat fechaVenta = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaVentaOK = new Date();
        try {
            fechaVentaOK = fechaVenta.parse(fechaVentaStr);
            System.out.println(fechaVentaOK); 
        } catch (Exception ex) {
            System.out.println("El formato de fecha no es válido");
            Message = "El formato de la fecha debe ser AÑO-MES-DIA";
    		MV.addObject("Mensaje", Message);
    		MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
    		MV.addObject("listaArticulos", this.servicio.obtenerArticulos());
    		
    		MV.setViewName("ventasAlta");
        }*/
		
		
		
		 List<String> articulos2 = new ArrayList<>();
		    List<Integer> cantidades = new ArrayList<>();
		    
		    for (Map.Entry<String, String> entry : params.entrySet()) {
		        String paramName = entry.getKey();
		        String paramValue = entry.getValue();
		        
		        // Verificar si es un campo de artículo
		        if (paramName.startsWith("articulos_")) {
		            articulos2.add(paramValue);
		        }
		        
		        // Verificar si es un campo de cantidad
		        if (paramName.startsWith("cantidad_")) {
		            int cantidad2 = Integer.parseInt(paramValue);
		            cantidades.add(cantidad2);
		        }
		    }
		    
		    List<Articulo> allArticulos = new ArrayList<>();
			List<Articulo> stockArticulos = new ArrayList<>();
			allArticulos = this.servicio.obtenerArticulos();
			for(Articulo art : allArticulos) {
				if(this.servicioStock.obtenerUnArticuloStock(art.getIdArticulo()) != null) {
					stockArticulos.add(art);
				}
			}
			
			// Formato de la fecha recibida
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaVenta= LocalDate.parse(fechaVentaStr, formatter);

			// Obtener la fecha actual
			LocalDate fechaActual = LocalDate.now();

				    
				    
			if (fechaVenta.isAfter(fechaActual)) {
				Message ="Error: la fecha ingresada no puede ser mayor a la actual.";
				MV.addObject("Mensaje", Message);
				MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
				MV.addObject("listaArticulos", stockArticulos);
				MV.setViewName("ventasAlta");
				return MV;
				
			}
			

		    //System.out.println("Fecha de venta: " + fechaVentaOK);
		    System.out.println("Lista de clientes: " + listaClientes);
		    System.out.println("Entro for");
		    
		    System.out.println("paso 2");
			Venta venta = new Venta();
			venta.setEstado(true);
			//venta.setFecha(fechaVentaOK);
			venta.setFecha(fechaVentaStr);
			
			Cliente cliente = new Cliente();
			cliente = this.servicioCliente.obtenerUnCliente(listaClientes);
			venta.setCliente(cliente);
			
			double precioTotal = 0;
			List<Articulo> articulos3 = new ArrayList<>();
			for (int i = 0; i < articulos2.size(); i++) {
				
				try
				{
			        Articulo art = new Articulo();
					art = this.servicio.obtenerUnArticulo(Integer.parseInt(articulos2.get(i)));
					articulos3.add(art);
					precioTotal += art.getPrecio() * cantidades.get(i);
					System.out.println("CANTIDAD: " + cantidades.get(i));
					if(cantidades.get(i) <= 0) {
						Message="Error: Cantidad ingresada de articulo erronea. Por favor, intentelo de nuevo.";
						MV.addObject("Mensaje", Message);
						MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
						MV.addObject("listaArticulos", stockArticulos);
						MV.setViewName("ventasAlta");
						return MV;
					}
					System.out.println("VALOR BOOLEAN: " + !this.servicioStock.hayStock(art.getIdArticulo(), cantidades.get(i)));
					if(!this.servicioStock.hayStock(art.getIdArticulo(), cantidades.get(i))) {
						Message="Error: No hay suficiente stock.";
						MV.addObject("Mensaje", Message);
						MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
						MV.addObject("listaArticulos", stockArticulos);
						MV.setViewName("ventasAlta");
						return MV;
					}
				}
				catch(Exception ex)
				{
					System.out.println("ERROR PARTE 1: " + ex.getMessage());
					Message = "No se encontró el articulo con ID: " + ex.getMessage();
				}
			}
			venta.setPrecioTotal(precioTotal);
			
			System.out.println(venta.toString());

			System.out.println("Paso al insert de venta");
			
			try {
				this.servicioVenta.insertarVenta(venta);
				int count = 0;
				
			    for (Articulo art : articulos3) {
					
			        int cantidad = cantidades.get(count);
			        
			        System.out.println("paso 1");
					//detalle carga
					try{
						DetalleVenta detVenta = new DetalleVenta();
						detVenta.setVenta(venta);
						detVenta.setArticulo(art);
						detVenta.setCantidad(cantidad);
						detVenta.setTotalVenta(art.getPrecio() * cantidad);
						detVenta.setTotalCompra(this.servicioStock.disminuirCantidadStock(art.getIdArticulo(), cantidad));
						
						this.servicioDetalleVenta.insertarDetalleVenta(detVenta);	
						Message = "Se agrego la venta con éxito.";
						
						
						
						
					}
					catch(Exception ex)
					{
						System.out.println("ERROR PARTE 3: " + ex.getMessage());
						Message = "No se pudo agregar un detalle de la venta. Error: " + ex + " - Mensaje error: " + ex.getMessage();
					}
			        
			        count ++;
			    }
			    System.out.println("Salgo for");
			    
			}
			catch(Exception e) {
				System.out.println("ERROR PARTE 2: " + e.getMessage());
				Message = "No se pudo agregar la venta. Error: " + e + " - Mensaje error: " + e.getMessage();
				
			}
		
		System.out.println("paso 3");
		System.out.println("Mensaje de prueba: " + Message);
		MV.addObject("Mensaje", Message);
		MV.addObject("listaClientes", this.servicioCliente.obtenerClientes());
		
		
		MV.addObject("listaArticulos", stockArticulos);
		
		MV.setViewName("ventasAlta");
		return MV;
	}
	
	@RequestMapping("redireccionar_ventasBaja.html")
	public ModelAndView eventoRedireccionarVentasBaja()
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("ventasBaja");
		return MV;
	}
	
	@RequestMapping(value ="/ventaBaja.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eventoBajaVenta(int idVenta){
		
		ModelAndView MV = new ModelAndView();
		/*
		Articulo art = new Articulo();
		art.setIdArticulo(idArticulo);
		*/
		String Message="";
		
		try{
			
			this.servicioVenta.eliminarVenta(idVenta);
			Message = "Venta dado de baja con éxito";
		}
		catch(Exception ex)
		{
			Message = "No se pudo dar de baja la venta - error: " + ex.getMessage() ;
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message);
		MV.addObject("Mensaje", Message);
		MV.setViewName("ventasBaja"); 
		return MV;
		
	}
	
	@RequestMapping(value = "/agregarItemVenta.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView eventoAgregarItem(int idVenta){
		
		ModelAndView MV = new ModelAndView();
		/*
		Articulo art = new Articulo();
		art.setIdArticulo(idArticulo);
		*/
		String Message="";
		
		try{
			
			this.servicioVenta.eliminarVenta(idVenta);
			Message = "Venta dado de baja con éxito";
		}
		catch(Exception ex)
		{
			Message = "No se pudo dar de baja la venta - error: " + ex.getMessage() ;
		}
		finally
		{
		
		}
		System.out.println("Mensaje de prueba: " + Message);
		MV.addObject("Mensaje", Message);
		MV.setViewName("ventasBaja"); 
		return MV;
		
	}
	
	
	@RequestMapping("redireccionar_ventasListado.html")
	public ModelAndView eventoRedireccionarVentasListado()
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaVentas", this.servicioVenta.obtenerVentas());
		
		

		MV.setViewName("ventasListado");
		return MV;
	}
	
	@RequestMapping(value ="/ventasListado.html" , method= { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listarVentas(String filtroFechaMayorQue, String filtroFechaMenorQue, String filtroPrecioMayorQue, String filtroPrecioMenorQue){
		
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaVentas",this.servicioVenta.obtenerVentasConFintro(filtroFechaMayorQue, filtroFechaMenorQue, filtroPrecioMayorQue, filtroPrecioMenorQue));
		MV.addObject("filtroFechaInicio", filtroFechaMayorQue);
		MV.addObject("filtroFechaFin", filtroFechaMenorQue);
		MV.addObject("filtroPrecioMin", filtroPrecioMayorQue);
		MV.addObject("filtroPrecioMax", filtroPrecioMenorQue);

		MV.setViewName("ventasListado");
		return MV;
		
	}
	
	@RequestMapping("redireccionar_ventasDetalleListado.html")
	public ModelAndView eventoRedireccionarVentasDetalleListado(int Nventa)
	{
		ModelAndView MV = new ModelAndView();
		MV.addObject("listaDetalleVenta", this.servicioDetalleVenta.obtenerDetalleVenta(Nventa));
		MV.addObject("venta", this.servicioVenta.obtenerVentaPorID(Nventa));
		
		
		MV.setViewName("ventasDetalleListado");
		return MV;
	}
	
	@RequestMapping("redireccionar_ventasListadoContador.html")
	public ModelAndView eventoRedireccionarVentasListadoContador()
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("contadorListadoVentas");
		return MV;
	}
	
	@RequestMapping("ventasListadoContadorFiltro.html")
	public ModelAndView eventoRedireccionarVentasListadoContadorFiltro(String filtroFechaMayorQue, String filtroFechaMenorQue)
	{
		ModelAndView MV = new ModelAndView();
		
		List<Venta> listaVenta = new ArrayList<Venta>();
		listaVenta = this.servicioVenta.obtenerVentasFiltradoFechas(filtroFechaMayorQue, filtroFechaMenorQue);
		double precioVenta=0;
		double precioCompra=0;
		ArrayList<Double> importeCompra = new ArrayList<>();
		
		for (Venta venta : listaVenta) {
			double importeDeLaCompra=0;
		    double precioTotal = venta.getPrecioTotal();
		    precioVenta += precioTotal;
		    ArrayList<DetalleVenta> listaDetalleVenta = this.servicioDetalleVenta.obtenerDetalleVenta(venta.getIdVenta());
		    for (DetalleVenta detalle : listaDetalleVenta) {
		    	//Stock stock = this.servicioStock.obtenerUnArticuloUltimoStock(detalle.getArticulo().getIdArticulo());
		    	precioCompra += detalle.getTotalCompra();
		    	importeDeLaCompra += detalle.getTotalCompra(); // OPCIONAL, ES PARA VER EL PRECIO DE LA COMPRA DE LOS PRODUCTOS
		    }
		    importeCompra.add(importeDeLaCompra);
		}
		
		double ganancia= (precioVenta-precioCompra);
		
		String Message="El precio de compra de los articulos fue: "+precioCompra+" y el de venta fue: "+precioVenta+", dando una ganancia de: "+ganancia;
		
		MV.addObject("listaVentas",this.servicioVenta.obtenerVentasFiltradoFechas(filtroFechaMayorQue, filtroFechaMenorQue));
		MV.addObject("importeCompras",importeCompra);
		MV.addObject("filtroFechaInicio", filtroFechaMayorQue);
		MV.addObject("filtroFechaFin", filtroFechaMenorQue);		
		MV.addObject("Mensaje", Message);
		MV.setViewName("contadorListadoVentas");
		return MV;
	}
	
}
