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
public class Seguidor {
    
    private int id;
    private int usuario_Id;
    private int seguindo_Id;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    public void setUsuario_Id(int usuario_Id){
        this.usuario_Id = usuario_Id;
    }
    
    public int getUsuario_Id(){
        return this.usuario_Id;
    }
    
    public void setSeguindo_Id(int seguindo_Id){
        this.seguindo_Id = seguindo_Id;
    }
    
    public int getSeguindo_Id(){
        return this.seguindo_Id;
    }
    
}
