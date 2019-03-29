package br.com.ufs.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufs.poo.modelo.Usuario;

public class UsuarioDAO {

	private static Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
		// TODO Auto-generated constructor stub
	}

	public void salvar(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO USUARIOS (NOME, DESCRICAO, SENHA, EMAIL) VALUES (?,?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getDescricao());
                        stmt.setString(3, usuario.getSenha());
                        stmt.setString(4, usuario.getEmail());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					usuario.setId(id);
				}
			}
		}
	}
	
	public int login(String email, String senha) throws SQLException {
		String sql = "SELECT * FROM USUARIOS WHERE EMAIL = ? AND SENHA = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, email);
			stmt.setString(2, senha);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
                                    int id = rs.getInt("id");
                                    String emailRs = rs.getString("email");
                                    String senhaRs = rs.getString("senha");
                                    if( (email.equals(emailRs)) && (senhaRs.equals(senhaRs)) ) {
					return id;
                                    }
				}
			}
		}
		return 0;
	}

	public List<Usuario> lista(int idUsuario) throws SQLException {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM USUARIOS WHERE ID <> ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
                        stmt.setInt(1, idUsuario);
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String descricao = rs.getString("descricao");
                                        String email = rs.getString("email");
					String senha = rs.getString("senha");
					Usuario usuario  = new Usuario(nome, descricao, email, senha);
					usuario.setId(id);
					usuarios.add(usuario);
				}
			}
		}
		return usuarios;
	}
        
        public Usuario buscaUsuarioId(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM USUARIOS WHERE ID = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
                        stmt.setInt(1, usuario.getId());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String descricao = rs.getString("descricao");
                                        usuario.setId(id);
                                        usuario.setNome(nome);
                                        usuario.setDescricao(descricao);
				}
			}
		}
		return usuario;
	}
        public Usuario buscaUsuarioNome(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM USUARIOS WHERE NOME = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
                        stmt.setString(1, usuario.getNome());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String descricao = rs.getString("descricao");
                                        usuario.setId(id);
                                        usuario.setNome(nome);
                                        usuario.setDescricao(descricao);
				}
			}
		}
		return usuario;
	}

}
