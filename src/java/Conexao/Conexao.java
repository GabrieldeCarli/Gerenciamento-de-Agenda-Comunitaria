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

    public Connection getConnection() throws ClassNotFoundException {
        url = "jdbc:postgresql://localhost:5432/NomBanco";
        usuario = "postgres";
        senha = "senha";
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url,usuario,senha);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /* Conexao com Banco MySQL
    public Conexao() throws ClassNotFoundException {
        url = "jdbc:mysql://localhost/banco_sgac";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.err.println("Erro no construtor da CONEXAO.");
            throw new RuntimeException(e);
        }
    }*/

    public ResultSet executaBusca(String sql) {
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

    public void inserirNovaAtividade(String titulo, String nome, Date data) {
        String sql = "INSERT INTO evento (nomeevento, dataevento,  idusuario) VALUES (?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, titulo);
            stm.setString(2, nome);
            stm.setDate(3, data);
            stm.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.err.println("Erro ao inserirNovaAtividade na Conexao.");
            e.printStackTrace();
        }
    }

    public void inserirNovaAtividade(String titulo, String idUsuario, String data) {
        String sql = "INSERT INTO evento (nomeevento, dataevento,  idusuario) "
                + "VALUES (?,?,?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, titulo);
            stm.setString(2, data);
            stm.setString(3, idUsuario);
            stm.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.err.println("Erro ao inserirNovaAtividade na Conexao.");
            e.printStackTrace();
        }
    }
}
