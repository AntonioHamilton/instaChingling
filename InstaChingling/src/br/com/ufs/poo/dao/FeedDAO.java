/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufs.poo.dao;

import br.com.ufs.poo.modelo.Feed;
import br.com.ufs.poo.modelo.Seguidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luiz Souza
 */
public class FeedDAO {
    
    private static Connection con;

	public FeedDAO(Connection con) {
		this.con = con;
		// TODO Auto-generated constructor stub
	}

	public void salvar(Feed feed) throws SQLException {
		String sql = "INSERT INTO FEED (IMAGEM, TEXTO,IDUSUARIO) VALUES (?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, feed.getImagem());
			stmt.setString(2, feed.getTexto());
                        stmt.setInt(3, feed.getIdUsuario());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					feed.setId(id);
				}
			}
		}
	}
        public List<Feed> listaFeed(int id) throws SQLException {
		// TODO Auto-generated method stub
		List<Feed> feed = new ArrayList<>();
		String sql = "SELECT F.*, U.NOME FROM FEED AS F " +
                            "INNER JOIN USUARIOS AS U ON U.ID = F.IDUSUARIO " +
                            "WHERE IDUSUARIO = ? " +
                            "OR IDUSUARIO IN(SELECT DISTINCT(SEGUINDO_ID) FROM SEGUIDORES " +
                            "WHERE USUARIO_ID = ?) " +
                            "ORDER BY ID DESC";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    stmt.setInt(1,id);
                    stmt.setInt(2,id);
                    stmt.execute();
                    try (ResultSet rs = stmt.getResultSet()) {
                        while (rs.next()) {
                            Feed feedAux = new Feed();
                            feedAux.setId(rs.getInt("ID"));
                            feedAux.setIdUsuario(rs.getInt("IDUSUARIO"));
                            feedAux.setImagem(rs.getString("IMAGEM"));
                            feedAux.setTexto(rs.getString("TEXTO"));
                            feedAux.setNomeUsuario(rs.getString("NOME"));
                            feed.add(feedAux);
                        }
                    }
		}
		return feed;
	}
}
