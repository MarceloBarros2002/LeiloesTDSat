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
    
    public List<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connecta();
        String sql = "SELECT * FROM produtos";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<ProdutosDTO> listProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();

                produtosDTO.setId(rs.getInt("id"));
                produtosDTO.setNome(rs.getString("nome"));
                produtosDTO.setValor(rs.getString("valor"));
                produtosDTO.setStatus(rs.getString("status"));

                listProdutos.add(produtosDTO);
            }
            return listProdutos;
        } catch (Exception e) {
            return null;
        }
        }

    public int venderProduto(ProdutosDTO produtosDTO) {
        int status;
        conn = new conectaDAO().connecta();
        try {
            st = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
            st.setString(1, produtosDTO.getStatus());
            st.setInt(2, produtosDTO.getId());
            status = st.executeUpdate();
            return status;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            return e.getErrorCode();
        }
    }

    public List<ProdutosDTO> listarProdutoVendido() {
        conn = new conectaDAO().connecta();
        String sql = "SELECT * FROM produtos WHERE status LIKE 'Vendido'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            List<ProdutosDTO> listProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();

                produtosDTO.setId(rs.getInt("id"));
                produtosDTO.setNome(rs.getString("nome"));
                produtosDTO.setValor(rs.getString("valor"));
                produtosDTO.setStatus(rs.getString("status"));

                listProdutos.add(produtosDTO);
            }
            return listProdutos;
        } catch (Exception e) {
            return null;
        }
    }



}
        
   
    
    



