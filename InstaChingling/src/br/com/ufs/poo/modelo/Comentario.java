/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufs.poo.modelo;

/**
 *
 * @author Luiz Souza
 */
public class Comentario {

	private Integer id;
	private String nome;
	private String comentario;
	private Integer feed_id;

	public Integer getId() {
		return id;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getFeed_id() {
		return feed_id;
	}

	public void setFeed_id(Integer feed_id) {
		this.feed_id = feed_id;
	}

}
