
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
public class EventoDAO {
    private Connection connection;
    
    
    public EventoDAO() throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adicionaEvento(Evento evento){
        String sql = "insert into evento (name_event, date, id_user)" 
                + " values (?, ?, ?)";
        
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
            
            ps.setString(1, evento.getNome());
            ps.setTimestamp(2, new java.sql.Timestamp (evento.getData().getTime()));
            ps.setLong(3, evento.getUserId());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e){
            throw new RuntimeException (e);
        }
    }
    
    public List<Evento> getListaEventos(){
        try {
            List<Evento> eventos = new ArrayList<Evento>();
            
            PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement("select * from evento");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Evento event = new Evento();
                
                event.setNome(rs.getString("name_event"));
                event.setUserId(rs.getInt("id_user"));
                event.setData(rs.getDate("date"));
                eventos.add(event);
            }
            ps.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }      
    }
    public List<Evento> buscaEvento(Evento evento){
        String sql = "select * from evento WHERE name_event LIKE ?";
        try {
            List<Evento> eventos = new ArrayList();
            PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement(sql);
            ps.setString(1, "%"+evento.getNome()+"%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Evento event = new Evento();                
                event.setNome(rs.getString("name_event"));
                event.setData(rs.getDate("date"));
                event.setUserId(rs.getInt("id_user"));
                eventos.add(event);
            }
            ps.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }      
    }
}
