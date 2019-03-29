package br.com.ufs.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufs.poo.modelo.Comentario;

public class ComentarioDAO {

	private static Connection con;

	public ComentarioDAO(Connection con) {
		this.con = con;
		// TODO Auto-generated constructor stub
	}

	public void salvar(Comentario comentario) throws SQLException {
		String sql = "INSERT INTO COMENTARIOS (NOME, COMENTARIO, FEED_ID) VALUES (?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, comentario.getNome());
			stmt.setString(2, comentario.getComentario());
			stmt.setInt(3, comentario.getFeed_id());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					comentario.setId(id);
				}
			}
		}
	}

	public List<Comentario> lista(int id) throws SQLException {
		// TODO Auto-generated method stub
		List<Comentario> comentarios = new ArrayList<>();
		String sql = "SELECT * FROM COMENTARIOS " + "WHERE FEED_ID = ? ORDER BY ID DESC";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					Comentario comentario = new Comentario();
					comentario.setId(rs.getInt("ID"));
					comentario.setNome(rs.getString("nome"));
					comentario.setComentario(rs.getString("comentario"));
					comentario.setFeed_id(rs.getInt("feed_id"));
					comentarios.add(comentario);
				}
			}
		}
		return comentarios;
	}

}
