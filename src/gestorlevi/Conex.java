package gestorlevi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;


public class Conex
{
	private String Url = "D:\\Programacion\\Sqlite\\geslevi.db";
	private Connection conex;
	private ResultSet querry;
	
	public Conex()
	{
		try
		{
			conex = DriverManager.getConnection("jdbc:sqlite:" + Url);
			
			if(conex != null)
				System.out.println("Conectado");
			
		}
		catch (SQLException e){System.err.println("No se ha podido conectar¡¡");}
	}
	
	public void close()
	{
		try
		{
			conex.close();
			System.out.println("Desconectado");
		}
		catch (SQLException ex){System.err.println("No se ha podido cerrar¡¡");}
	}
	
	public void CargaTablaColores(DefaultTableModel tabla)
	{
		try
		{
			PreparedStatement st = conex.prepareStatement("select * from colores");
	        querry = st.executeQuery();
	        
	        while (querry.next())
	        {
				Object[] nuevafila = {querry.getInt("id"), querry.getString("referencia"), querry.getString("nombre")};
				tabla.addRow(nuevafila);
	        }
	        
		}
		catch(SQLException ex){System.err.println(ex.getMessage());}
	}
	
	public void CargaTablaOjetes(DefaultTableModel tabla)
	{
		
	}
	
	public void InsertColores(String ref, String nombre)
	{
		try
		{
			PreparedStatement st = conex.prepareStatement("insert into colores (referencia, nombre) values (?,?)");
			st.setString(1, ref);
			st.setString(2, nombre);
	        st.execute();
		}
		catch(SQLException ex){System.err.println(ex.getMessage());}
	}
	
	public void BorrarColor(String ref)
	{
		try
		{
			PreparedStatement st = conex.prepareStatement("delete from colores where referencia = '" + ref + "';");
	        st.execute();
		}
		catch(SQLException ex){System.err.println(ex.getMessage());}
		
	}
	
	public void updateColores(String id, String ref, String nombre)
	{
		
		try
		{
			PreparedStatement st = conex.prepareStatement("update colores set referencia = '" + ref + "', nombre = '" + nombre + "' where id =" + id + ";" );
			st.execute();
			
		} catch (SQLException e){e.printStackTrace();}
		
		
		/*UPDATE table_name
		SET column1 = value1, column2 = value2...., columnN = valueN
		WHERE [condition];*/
	}
}
