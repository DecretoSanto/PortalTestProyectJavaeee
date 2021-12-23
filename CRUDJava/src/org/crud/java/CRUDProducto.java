package org.crud.java;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//CRUD: CREATE REAL UPDATE DELETE
public class CRUDProducto {
	
	
	static Connection connection;
	static String driver="oracle.jdbc.driver.OracleDriver";
	static String URL="jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectDataBaseOracle() throws IOException, SQLException{
		try {
			 Class.forName(driver).newInstance();
			 System.out.println("CARGO DRIVER: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("Exeption driver:"+ e.getMessage());
		}
		try {
			connection=DriverManager.getConnection(URL,"System","root");
			System.out.println("CONEXION EXITOSA: Oracle11g");
		} catch (Exception e) {
			System.out.println("Exeption conexion: "+ e.getMessage());
		}
	}
	
	public static void agregarProducto(int id, String nombre, double precio) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String sql="INSERT INTO PRODUCTO (ID,NOM_PRO,PRECIOU_PRO) VALUES (?,?,?)";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setDouble(3, precio);
			ps.executeQuery();
			System.out.println("AGREGO:"+id+","+nombre+","+precio);
		} catch (Exception e) {
			
			System.out.println("Exception agregar:"+ e.getMessage());
		}
	}
	
	public static void actualizarProducto(String nombre, double precio, int id) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String sql="UPDATE PRODUCTO SET NOM_PRO= ?, PRECIOU_PRO= ? WHERE ID = ?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setDouble(2, precio);
			ps.setInt(3, id);
			ps.executeQuery();
			System.out.println("ACTUALIZO:"+nombre+","+precio+","+id);
		} catch (Exception e) {
			
			System.out.println("Exception actualizar:"+ e.getMessage());
		}
	}
	
	public static void eliminarProducto(int id) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String sql="DELETE FROM PRODUCTO WHERE ID= ?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			System.out.println("ELIMINO:"+id);
		} catch (Exception e) {
			
			System.out.println("Exception eliminar:"+ e.getMessage());
		}
	}
	public static void consultaIndividualProducto(int id) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String sql="SELECT * FROM PRODUCTO WHERE ID = ? ";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			ResultSet resultSet= ps.executeQuery();
				if(resultSet.next()) {
					System.out.println(resultSet.getInt("id")+","+ resultSet.getString("nom_pro")
				+","+resultSet.getDouble("preciou_pro"));
				}
			
		} catch (Exception e) {
			
			System.out.println("Exception consulta individual:"+ e.getMessage());
		}
	}
	
	public static void consultaGeneralProducto() throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String sql="SELECT * FROM PRODUCTO";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet resultSet= ps.executeQuery();
				while(resultSet.next()) {
					System.out.println(resultSet.getInt("id")+","+ resultSet.getString("nom_pro")
				+","+resultSet.getDouble("preciou_pro"));
				}
			
		} catch (Exception e) {
			
			System.out.println("Exception consulta general:"+ e.getMessage());
		}
	}
	
	public static void invocaProcedure(int id, String name) throws IOException, SQLException{
		try {
			connectDataBaseOracle();
			String sql="CALL proc(?,?)";
			CallableStatement cs=connection.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			
			System.out.println("Execute Stored Procedure:Proc");
			
		} catch (Exception e) {
			
			System.out.println("Exception procedure:"+ e.getMessage());
		}
	}
	
	
	public static void main(String[] args)throws IOException, SQLException {
		//agregarProducto(101, "MOUSE HP", 25.0);
		//actualizarProducto("MOUSE DELL", 35.0, 101);
		//eliminarProducto(101);
		//consultaIndividualProducto(1);
		//consultaGeneralProducto();
		invocaProcedure(55, "SINALOA");
	}
	
}
