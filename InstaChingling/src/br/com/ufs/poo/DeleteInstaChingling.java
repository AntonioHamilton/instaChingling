package br.com.ufs.poo;


import br.com.ufs.poo.jdbc.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteInstaChingling {
	
	public static void main (String[] args) throws SQLException {
		Connection connection = new ConnectionPool().getConnection();
		Statement stmt = connection.createStatement();
		boolean resultado = stmt.execute("DELETE FROM USUARIOS WHERE ID >4");
		System.out.println(resultado);
		int count = stmt.getUpdateCount();
		System.out.println( count + " linhas alteradas.");		
		connection.close();
		stmt.close();
	}

}
