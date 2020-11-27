package conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;



import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;


public class Conexion {

	//PARA EL POOL DE CONEXIONES
	
	private static BasicDataSource dataSource = null;
	
	private static DataSource getDataSource() {
		if(dataSource == null) {
			//se pondra los parametros de conexiones
			dataSource = new BasicDataSource();
			//para la conexion
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			//pasa los parametros
			dataSource.setUsername("wilmer78");
			dataSource.setPassword("kira35as12FI");
			dataSource.setUrl("jdbc:mysql://localhost:3306/crud");
			//con cuantas conexiones va iniciar el pool
			dataSource.setInitialSize(20);
			//para saber cuantas conexiones pueden estar activas
			dataSource.setMaxIdle(15);
			//para el total de conexiones
			dataSource.setMaxTotal(20);
			//tiempo que estara activo la conexion
			dataSource.setMaxWaitMillis(5000);
			
		}
		
		return dataSource;
	}
	
	//para retornar la conexion
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
