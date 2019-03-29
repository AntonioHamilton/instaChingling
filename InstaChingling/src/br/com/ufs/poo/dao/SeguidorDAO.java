package br.com.ufs.poo.dao;

import br.com.ufs.poo.modelo.Seguidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SeguidorDAO {

	private static Connection con;

	public SeguidorDAO(Connection con) {
		this.con = con;
		// TODO Auto-generated constructor stub
	}

	public void salvar(Seguidor seguidor) throws SQLException {
		String sql = "INSERT INTO SEGUIDORES (USUARIO_ID, SEGUINDO_ID) VALUES (?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, seguidor.getUsuario_Id());
			stmt.setInt(2, seguidor.getSeguindo_Id());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					seguidor.setId(id);
				}
			}
		}
	}

	public List<String> listaSeguidores(int id) throws SQLException {
		// TODO Auto-generated method stub
		List<String> seguidores = new ArrayList<>();
		String sql = "SELECT * FROM USUARIOS AS U" +
                             " INNER JOIN SEGUIDORES AS S ON U.ID = S.USUARIO_ID" +
                             " WHERE S.SEGUINDO_ID = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    stmt.setInt(1,id);
                    stmt.execute();
                    try (ResultSet rs = stmt.getResultSet()) {
                        while (rs.next()) {
                            String nome = rs.getString("nome");
                            seguidores.add(nome);
                        }
                    }
		}
		return seguidores;
	}
        public List<String> listaSeguindo(int id) throws SQLException {
		// TODO Auto-generated method stub
		List<String> seguidores = new ArrayList<>();
		String sql = "SELECT * FROM USUARIOS AS U" +
                            " INNER JOIN SEGUIDORES AS S ON U.ID = S.SEGUINDO_ID" +
                            " WHERE S.USUARIO_ID = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    stmt.setInt(1,id);
                    stmt.execute();
                    try (ResultSet rs = stmt.getResultSet()) {
                        while (rs.next()) {
                            String nome = rs.getString("nome");
                            seguidores.add(nome);
                        }
                    }
		}
		return seguidores;
	}

}
