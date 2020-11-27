package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductoDAO;
import model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/producto" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String opcion = request.getParameter("opcion");
		
		if(opcion.equals("crear")) {
			System.out.println("ha presionado opcion crear.");
			
			//redireccionar a la pagina (crear)
			RequestDispatcher requestD = request.getRequestDispatcher("/views/crear.jsp");
			//lo envia hacia la pagina crear
			requestD.forward(request, response);
		
		}else if(opcion.equals("listar")) {
			
			ProductoDAO pdo = new ProductoDAO();
			//lista
			List<Producto> lista = new ArrayList<>();
			try {
				lista = pdo.obtenerProductos();
				
				//comporbar los productos
				for(Producto pd : lista) {
					System.out.println(pd);
				}
				
				//pasar la (lista de productos) a la vista listar.jsp
				request.setAttribute("lista", lista);
	
				//enviar a la vista
				RequestDispatcher requestD = request.getRequestDispatcher("/views/listar.jsp");
				//lo envia hacia la pagina listar
				requestD.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
			System.out.println("ha presionado opcion listar");
		
		}else if(opcion.equals("meditar")) {
			//id, es el nombre que esta la pagina jsp listar.jsp
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: "+id);
			
			ProductoDAO pdo = new ProductoDAO();
			//objeto para recibir
			Producto p = new Producto();
			try {
				p = pdo.obtenerProductos(id);
				System.out.println("producto: "+p);
				
				//pasar el producto a la nueva vista
				request.setAttribute("producto", p);
				
				//se redirecciona hacia la nueva pagina
				RequestDispatcher requestD = request.getRequestDispatcher("/views/editar.jsp");
				//lo envia hacia la pagina listar
				requestD.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
		}else if(opcion.equals("eliminar")) {
			ProductoDAO pdao = new ProductoDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				pdao.eliminar(id);
				
				System.out.println("Registro eliminado correctamente....");
				//redireccionar a la pagina crear
				RequestDispatcher requestD = request.getRequestDispatcher("/index.jsp");
				//lo envia hacia la pagina index
				requestD.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String opcion = request.getParameter("opcion");
		
		//obtener la fecha actual
		Date fechaActual = new Date();
		
		
		
		//GUARDAR
		if(opcion.equals("guardar")) {
			
			ProductoDAO pdao = new ProductoDAO();
			Producto pro = new Producto();

			pro.setNombre(request.getParameter("nombre"));
			pro.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			pro.setPrecio(Double.parseDouble(request.getParameter("precio")));
			//fecha actual
			pro.setFecha_creacion(new java.sql.Date(fechaActual.getTime()));
			
			//persistencia
			try {
				pdao.guardar(pro);
				System.out.println("Registro guardado correctamente....");
				//redireccionar a la pagina crear
				RequestDispatcher requestD = request.getRequestDispatcher("/index.jsp");
				//lo envia hacia la pagina crear
				requestD.forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}else if(opcion.equals("editar")) {
			
			ProductoDAO pdao = new ProductoDAO();
			Producto pro = new Producto();
			
			pro.setId(Integer.parseInt(request.getParameter("id")));
			pro.setNombre(request.getParameter("nombre"));
			pro.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			pro.setPrecio(Double.parseDouble(request.getParameter("precio")));
			//fecha actual
			pro.setFecha_actualizar(new java.sql.Date(fechaActual.getTime()));
			
			try {
				pdao.editar(pro);
				System.out.println("Registro editado correctamente....");
				//redireccionar a la pagina crear
				RequestDispatcher requestD = request.getRequestDispatcher("/index.jsp");
				//lo envia hacia la pagina index
				requestD.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
		}
		
		
	}

}
