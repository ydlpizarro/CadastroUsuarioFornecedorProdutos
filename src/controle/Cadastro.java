/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Cliente;
import entidades.Endereco;
import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FBS
 */
public class Cadastro {
    private Conexao con = new Conexao();
    
    public Usuario consultarUsuario(String login) {
        Usuario usuario = null;
        PreparedStatement st;
        ResultSet rs;
        int i = 1;
        
        try {
            st = con.getConexao().prepareStatement("select idUsuario, nome, login, senha, nivel from usuario where login = ?");
            st.setString(i++, login);
            rs = st.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNivel(rs.getInt("nivel"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                
            }
        } catch(SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    public Usuario consultarUsuario(int id) {
        Usuario usuario = null;
        PreparedStatement st;
        ResultSet rs;
        int i = 1;
        
        try {
            st = con.getConexao().prepareStatement("select idUsuario, nome, login, senha, nivel from usuario where idUsuario = ?");
            st.setInt(i++, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNivel(rs.getInt("nivel"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    public LinkedList consultarTodosClientes() {
        PreparedStatement st;
        ResultSet rs;
        LinkedList lista = new LinkedList();
        Cliente cliente;
        Endereco endereco;
        
        try {
            st = con.getConexao().prepareStatement("select en.*, cl.* from cliente cl, endereco en where cl.endereco = en.idEndereco");
            rs = st.executeQuery();
            
            while (rs.next()) {
                cliente = new Cliente();
                endereco = new Endereco();
                
                endereco.setNumero(rs.getString("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setIdEndereco(rs.getInt("idEndereco"));
                
                cliente.setUsuario(consultarUsuario(rs.getInt("usuario")));
                cliente.setEndereco(endereco);
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                
                lista.add(cliente);
            }
        } catch(SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public void salvarEndereco(Endereco endereco) {
        PreparedStatement st;
        ResultSet rs;
        int i = 1;
        
        try {
            st = con.getConexao().prepareStatement("insert into endereco (rua, numero) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(i++, endereco.getRua());
            st.setString(i++, endereco.getNumero());
            st.execute();
            rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                endereco.setIdEndereco(rs.getInt(1));
            }
        } catch(SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvarCliente(Cliente cliente) {
        PreparedStatement st;
        ResultSet rs;
        int i = 1;
        
        try {
            st = con.getConexao().prepareStatement("insert into cliente (nome, cpf, endereco, usuario) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(i++, cliente.getNome());
            st.setString(i++, cliente.getCpf());
            st.setInt(i++, cliente.getEndereco().getIdEndereco());
            st.setInt(i++, cliente.getUsuario().getIdUsuario());
            st.execute();
            rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt(1));
            }
        } catch(SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
