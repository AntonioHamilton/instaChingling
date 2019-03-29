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
public class Feed {
    private int id;
    private String imagem;
    private String texto;
    private int idUsuario;
    private String nomeUsuario;
    
    public String getNomeUsuario(){
        return this.nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getImagem(){
        return this.imagem;
    }
    
    public void setImagem(String imagem){
        this.imagem = imagem;
    }
    
    public String getTexto(){
        return this.texto;
    }
   
    public void setTexto(String texto){
        this.texto = texto;
    }
    public int getIdUsuario(){
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
}
