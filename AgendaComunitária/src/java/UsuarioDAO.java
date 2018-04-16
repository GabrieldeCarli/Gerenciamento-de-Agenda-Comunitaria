
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luiz-
 */
public class UsuarioDAO {
    private Connection connection;
    
    public UsuarioDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adicionaUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario (name_user, password)" 
                + " VALUES (?, ?)";
        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

            // seta os valores
            stmt.setString(1,usuario.getUserName());
            stmt.setString(2,usuario.getPassword());

            // executa a query
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public List<Usuario> getListaUsuarios(){
        try {
            List<Usuario> contatos = new ArrayList<Usuario>();
            PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement("select * from usuario");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Usuario user = new Usuario();                
                user.setUserName(rs.getString("name_user"));                
                contatos.add(user);
            }
            rs.close();
            ps.close();
            return contatos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
