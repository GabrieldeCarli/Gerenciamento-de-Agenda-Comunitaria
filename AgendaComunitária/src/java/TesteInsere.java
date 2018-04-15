
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luiz-
 */
public class TesteInsere {
    public static void main(String[] args) throws SQLException {
        
        EventoDAO e = new EventoDAO();
        
        List<Evento> eventos = e.getListaEventos();
        
        for (Evento evento : eventos){
            System.out.println("Nome do Evento: " + evento.getNome());
            System.out.println("Id do Criador: " + evento.getUserId());
        }
        
    }
}
