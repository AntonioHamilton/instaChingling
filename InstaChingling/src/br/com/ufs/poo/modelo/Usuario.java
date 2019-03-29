package br.com.ufs.poo.modelo;

public class Usuario {
	
	private Integer id;
	private String nome;
	private String descricao;
	private String senha;
        private String email;
	
	public Usuario(String nome, String descricao, String email, String senha) {
		super();
		this.nome = nome;
		this.descricao = descricao;
                this.email = email;
                this.senha = senha;
	}
        
        public void setSEmail(String email) {
		this.email = email;
	}
        public String getEmail() {
		return this.email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
        public String getSenha() {
		return this.senha;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Usuario: %d %s %s",id,nome,descricao);
	}
}
