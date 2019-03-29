package br.com.ufs.poo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import br.com.ufs.poo.jdbc.ConnectionPool;
import br.com.ufs.poo.dao.UsuarioDAO;
import br.com.ufs.poo.modelo.Usuario;

public class TesteInsercaoBuscatUsuario {

	public static void main(String[] args) throws SQLException {
		Usuario usuario = new Usuario("Pedro", "Quem sabe ser� o pr�ximo","email@email","123");
		try (Connection con = new ConnectionPool().getConnection()) {
			UsuarioDAO dao = new UsuarioDAO(con);
			dao.salvar(usuario);
			
			List<Usuario> usuarios = dao.lista(0);
			for (Usuario usuario2 : usuarios) {
				System.out.println(usuario2);
			}
			
		}
	}
}
