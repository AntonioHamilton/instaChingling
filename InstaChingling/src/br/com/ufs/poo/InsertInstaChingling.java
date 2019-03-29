package br.com.ufs.poo;

import br.com.ufs.poo.jdbc.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;


import br.com.ufs.poo.dao.UsuarioDAO;
import br.com.ufs.poo.modelo.Usuario;

public class InsertInstaChingling {

	public static void main(String[] args) throws SQLException {
		try (Connection con = new ConnectionPool().getConnection()) {
			Usuario novoUsuario = new Usuario("Corora", "A outra calopsita","email@email","123");
			UsuarioDAO dao = new UsuarioDAO(con);
			dao.salvar(novoUsuario);	
		}
	}
}
