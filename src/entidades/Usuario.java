/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Gonosuke
 */
public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private int nivel; // 1 - Administrador   2 - Comum
    private int idUsuario;
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int id) {
        idUsuario = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    @Override
    public String toString(){
        return nome;
    }    
}
