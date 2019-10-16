/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Cliente;
import entidades.Usuario;
import java.util.LinkedList;

/**
 *
 * @author FBS
 */
public class Controlador {
    private Cadastro cad = new Cadastro();
    
    public Usuario validaUsuario(Usuario usuario) {
        Usuario local = cad.consultarUsuario(usuario.getLogin());
        
        if (local != null && usuario.getLogin().equals(local.getLogin()) && usuario.getSenha().equals(local.getSenha())) {
            return local;
        }
        
        return null;
    }
    
    public LinkedList consultarTodosClientes() {
        return cad.consultarTodosClientes();
    }
    
    public void salvarCliente(Cliente cliente) {
        cad.salvarEndereco(cliente.getEndereco());
        cad.salvarCliente(cliente);
    }
}
