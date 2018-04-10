package Conexao;

import java.sql.*;

/**
 *
 * @author gabriel de carli
 */
public class Conexao {
    private String url;
    private String usuario;
    private String senha;
    private Connection con;
    
    Conexao(){
        url = "jdbc:postgresql://localhost:5432/NomBanco";
        usuario = "postgres";
        senha = "senha";
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url,usuario,senha);
            System.out.println("conexao realizada com sucesso");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
   
    public ResultSet executaBusca(String sql){
        try {
           Statement stm = con.createStatement();
           ResultSet rs = stm.executeQuery(sql);
           con.close();
           return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
