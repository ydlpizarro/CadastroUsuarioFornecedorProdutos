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
public class Endereco {
    private String rua;
    private String numero;
    private int idEndereco;
    
    public int getIdEndereco() {
        return idEndereco;
    }
    
    public void setIdEndereco(int id) {
        idEndereco = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    
}
