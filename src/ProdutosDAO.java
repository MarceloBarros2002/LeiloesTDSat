/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    
    public int cadastrarProduto (ProdutosDTO produto) {
        
        int status;
        conn = new conectaDAO().connecta();
        
        try
        {
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setInt(1, produto.getId());
            st.setString(2, produto.getNome());
            st.setString(3, produto.getValor());
            st.setString(4, produto.getStatus());
            status = st.executeUpdate();
            return status;
            
        }
        
        catch(SQLException ex)
        {
            System.out.println("Erro de conexão: " + ex.getMessage());
            return ex.getErrorCode();
        }
        
        
        
    }
    
    public List<ProdutosDTO> listarProdutos(String filtro){
        String sql = "select * from produtos";
        if(!filtro.isEmpty())
        {
            sql = sql + "where nome like?";
        }
        try
        {
            st = conn.prepareStatement(sql);
            
            if(!filtro.isEmpty())
            {
                st.setString(1, "%" + filtro + "%");
            }
            rs = st.executeQuery();
            
            List<ProdutosDTO>  listarProdutos = new ArrayList<>();
            
            while(rs.next())
            {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getString("valor"));
                produto.setStatus(rs.getString("status"));
                listarProdutos.add(produto);
            }
            
            return listarProdutos;
        }
            
        catch(SQLException ex)
        {
            System.out.println("Erro de conexão: " + ex.getMessage());
            return null;
        }
        }}
        
   
    
    



