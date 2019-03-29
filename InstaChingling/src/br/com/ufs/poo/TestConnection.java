package br.com.ufs.poo;

import br.com.ufs.poo.jdbc.ConnectionPool;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String[] args) throws SQLException {
		ConnectionPool database = new ConnectionPool();
		Connection connection = database.getConnection();

		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("SELECT * FROM USUARIOS");
		ResultSet resultSet = statement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			String nome = resultSet.getString("NOME");
			String descricao = resultSet.getString("DESCRICAO");
                        String senha = resultSet.getString("SENHA");
			System.out.println(id);
			System.out.println(nome);
			System.out.println(descricao);
                        System.out.println(senha);
		}
		statement.close();
		resultSet.close();
		connection.close();
	}
}
