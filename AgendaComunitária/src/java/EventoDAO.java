
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    
    public EventoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adicionaEvento(Evento evento){
        String sql = "insert into evento " + "(name_event, date, id_user)" + " values (?, ?, ?)";
        
        
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
            
            ps.setString(1, evento.getNome());
            ps.setLong(3, evento.getUserId());
            
            // setando a data
            
            java.sql.Date dataParaGravar = new java.sql.Date(
            Calendar.getInstance().getTimeInMillis());
            ps.setDate(2, dataParaGravar);
            
            ps.execute();
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
                eventos.add(event);
            }
            ps.close();
            return eventos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }      
    }
}
