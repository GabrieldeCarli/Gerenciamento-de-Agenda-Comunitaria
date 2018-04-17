/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luiz-
 */
@WebServlet(urlPatterns = {"/Eventos"})
public class Eventos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean busca = false;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Veja os eventos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Veja os eventos</h1>");

            out.println("<fieldset>");
            out.println("    <legend>Buscar evento </legend>");
            out.println("   <form action=\"Eventos\" method=\"post\">");
            out.println("       Nome <input type=\"text\" name=\"busca\" value=\"\"></input>");
            out.println("       Data <input name=\"data\" value=\"data\" type=\"date\"></input>");
            out.println("       Id do user <input name=\"IdUsuario\" value=\"\" type=\"text\"></input>");
            out.println("      <input type=\"submit\" value=\"Enviar\"></input>");
            out.println("   </form>");
            out.println("</fieldset>");
            out.println("<ul>");
            EventoDAO eventoDao;
            try {
                eventoDao = new EventoDAO();
                List<Evento> listaEventos;
                busca = Boolean.parseBoolean(request.getParameter("busca"));
                
                Evento evento = new Evento();
                if (busca) {
                    evento.setNome(request.getParameter("nomeEvento"));
                    listaEventos = eventoDao.buscaEvento(evento);
                } else {
                    listaEventos = eventoDao.getListaEventos();
                }

                for (Evento eventoAtual : listaEventos) {
                    out.println("   <li>Evento: " + eventoAtual.getNome()
                            + "\t- Data do evento:" + eventoAtual.getData()
                            + "\t- ID user:" + eventoAtual.getUserId() + "</li>");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeBusca = request.getParameter("busca");
        System.out.println("Busca: " + nomeBusca);
        if (!nomeBusca.equals("")) {
            String nomeEventoParaBuscar = request.getParameter("busca");
            response.sendRedirect("Eventos?busca=" + true + "&nomeEvento=" + nomeEventoParaBuscar);
        } else if (nomeBusca.isEmpty()) {
            response.sendRedirect("Eventos?busca=" + false);
        } 
    }

    private java.util.Date castDate(String data) {
        int ano = Integer.parseInt(data.substring(0, 4));
        int mes = Integer.parseInt(data.substring(5, 7)) - 1;
        int dia = Integer.parseInt(data.substring(8, 10));
        //int hora = 0;
        //int minuto = 0;
        Calendar c = Calendar.getInstance();
        c.set(ano, mes, dia);
        java.util.Date date = c.getTime();
        return date;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
