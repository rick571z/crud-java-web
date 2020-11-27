package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.Producto;



public class ProductoDAO {

	//DAO --> es un patron de diseño, en java
	//se encarga de la persistencia de los datos en una tabla
	//lista las operaciones crud  de una tabla
	
	private Connection conection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	public boolean guardar(Producto producto) throws SQLException {
		
		String sql;
		estadoOperacion = false;
		//conexion al pool
		conection = obtenerConexion();
		
		//pasos para establecer la persistencia
		
		try {
			//desde aqui comienza la transaccion, completa
			//para parametrizar la consulta --> PreparedStatement
			conection.setAutoCommit(false);
			sql = "INSERT INTO productos (id, nombre, cantidad, precio, fecha_creacion, fecha_actualizar) "
					+ "					 VALUES(?, ?, ?, ?, ?, ?)";
			
			//crear el statment
			statement = conection.prepareStatement(sql);
			
			//se pasa los parametros
			statement.setString(1, null);
			statement.setString(2, producto.getNombre());
			statement.setDouble(3, producto.getCantidad());
			statement.setDouble(4, producto.getPrecio());
			statement.setDate(5, producto.getFecha_creacion());
			statement.setDate(6, producto.getFecha_actualizar());
			
			//enviar la sentecia para que se ejecute en la bd
			estadoOperacion = statement.executeUpdate() > 0;
			
			conection.commit();
			statement.close();
			conection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	public boolean editar(Producto producto) throws SQLException {
		
		String sql;
		estadoOperacion = false;
		//conexion al pool
		conection = obtenerConexion();
		
		//pasos para establecer la persistencia
		
				try {
					//desde aqui comienza la transaccion, completa
					//para parametrizar la consulta --> PreparedStatement
					conection.setAutoCommit(false);
					sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
					
					statement = conection.prepareStatement(sql);
					
					//se pasa los parametros
					statement.setString(1, producto.getNombre());
					statement.setDouble(2, producto.getCantidad());
					statement.setDouble(3, producto.getPrecio());
					statement.setDate(4, producto.getFecha_actualizar());
					statement.setInt(5,  producto.getId());
					
					estadoOperacion = statement.executeUpdate() > 0;
					
					conection.commit();
					statement.close();
					conection.close();//devuelve la conexion al pool
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					conection.rollback();//en caso de error rollback()
					e.printStackTrace();
				}
				
		return estadoOperacion;
	}
	
	public boolean eliminar(int idProducto) throws SQLException {
		
		String sql;
		estadoOperacion = false;
		//conexion al pool
		conection = obtenerConexion();
		
		//pasos para establecer la persistencia
		
				try {
					//desde aqui comienza la transaccion, completa
					//para parametrizar la consulta --> PreparedStatement
					conection.setAutoCommit(false);
					sql = "DELETE FROM productos WHERE id=?";
					
					statement = conection.prepareStatement(sql);
					
					statement.setInt(1, idProducto);
					
					estadoOperacion = statement.executeUpdate() > 0;
					
					conection.commit();
					statement.close();
					conection.close();//devuelve la conexion al pool
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					conection.rollback();//en caso de error rollback()
					e.printStackTrace();
				}
		
		return estadoOperacion;
	}
	
	public List<Producto> obtenerProductos() throws SQLException {
		
		ResultSet res = null;//obtiene todos los registros que se hace a la bd
		String sql;
		List<Producto> listaProductos = new ArrayList<>();
		
		estadoOperacion = false;
		//conexion al pool
		conection = obtenerConexion();
		
		//pasos para establecer la persistencia
		
				try {
					
					sql = "SELECT *FROM productos";
					//creacion del canal
					statement = conection.prepareStatement(sql);
					//es resultado esta en forma de matriz
					res = statement.executeQuery(sql);
					
					//cada objeto se pondra en una lista
					while(res.next()) {
						//mientras haya registros
						//se obtendra cada registro del resultset
						//para luego almacenar en la lista
						Producto p = new Producto();
						p.setId(res.getInt(1));
						p.setNombre(res.getString(2));
						p.setCantidad(res.getDouble(3));
						p.setPrecio(res.getDouble(4));
						p.setFecha_creacion(res.getDate(5));
						p.setFecha_actualizar(res.getDate(6));
						
						listaProductos.add(p);								
					}	
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
		return listaProductos;
	}
	
	//obtener el producto
	public Producto obtenerProductos(int idProducto) throws SQLException {
		ResultSet res = null;//obtiene todos los registros que se hace a la bd
		String sql;
		Producto p = new Producto();
		
		estadoOperacion = false;
		//conexion al pool
		conection = obtenerConexion();
		
		//pasos para establecer la persistencia
		
				try {
					
					sql = "SELECT *FROM productos WHERE id=?";
					statement = conection.prepareStatement(sql);
					statement.setInt(1, idProducto);
					//es resultado esta en forma de matriz
					res = statement.executeQuery();
					
					//solo se recorre un solo registro
					if(res.next()) {
						//mientras haya registros
						//se obtendra cada registro del resultset
						p.setId(res.getInt(1));
						p.setNombre(res.getString(2));
						p.setCantidad(res.getDouble(3));
						p.setPrecio(res.getDouble(4));
						p.setFecha_creacion(res.getDate(5));
						p.setFecha_actualizar(res.getDate(6));						
					}	
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
		return p;
	}
	
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
}
