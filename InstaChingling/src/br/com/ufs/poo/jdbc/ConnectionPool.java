package br.com.ufs.poo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {
	
	private DataSource dataSource;
	
	public ConnectionPool(){
		JDBCPool pool = new JDBCPool();
		pool.setURL("jdbc:hsqldb:hsql://localhost/instaChingling");
		pool.setUser("SA");
		pool.setPassword("");
		this.dataSource = pool;	
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}
}
