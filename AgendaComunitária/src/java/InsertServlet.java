/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author White
 */
@WebServlet(urlPatterns = {"/InsertServlet"})
public class InsertServlet extends HttpServlet {

    boolean busca = false;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet InserirConteudo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("   <h1>Inserir Conteudo Web</h1>");

        out.println("<fieldset>");
        out.println("    <legend>Buscar evento </legend>");
        out.println("   <form action=\"InsertServlet\" method=\"post\">");
        out.println("       Nome <input type=\"text\" name=\"busca\" value=\"\"></input>");
        out.println("      <input type=\"submit\" value=\"Enviar\"></input>");
        out.println("   </form>");
        out.println("</fieldset>");

        out.println("   <form action=\"InsertServlet\" method=\"post\">");
        out.println("       Titulo <input type=\"text\" name=\"nomeEvento\" value=\"\"></input>");
        out.println("       <p>Data <input type=\"date\" name=\"data\" value=\"data\"></input>");
        out.println("       <p>Autor <input type=\"text\" name=\"IdUsuario\" value=\"\" ></input>");
        out.println("       <p><input type=\"submit\" value=\"Enviar\"></input>");
        out.println("   <ul>");
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
                        + "\tID user:" + eventoAtual.getUserId() + "</li>");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.println("   </ul>");
        out.println("   </form>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeBusca = request.getParameter("busca");
        System.out.println("Busca: " + nomeBusca);
        if (!nomeBusca.equals("")) {
            String nomeEventoParaBuscar = request.getParameter("busca");
            response.sendRedirect("InsertServlet?busca=" + true + "&nomeEvento=" + nomeEventoParaBuscar);
        }else if(nomeBusca.isEmpty()){
            response.sendRedirect("InsertServlet?busca=" + false);
        }else {
            String nomeEvento = request.getParameter("nomeEvento");
            int idUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
            String dataForm = request.getParameter("data");
            System.out.println("Data: " + dataForm);
            Calendar data = castDate(dataForm);

            Evento evento = new Evento(nomeEvento, data, idUsuario);
            try {
                EventoDAO eventoDao = new EventoDAO();
                eventoDao.adicionaEvento(evento);
                response.sendRedirect("InsertServlet");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Calendar castDate(String data) {
        int ano = Integer.parseInt(data.substring(0, 4));
        int mes = Integer.parseInt(data.substring(5, 7));
        int dia = Integer.parseInt(data.substring(8, 10));
        /*int hora = Integer.parseInt(data.substring(11, 13));
        int minuto = Integer.parseInt(data.substring(14, 16));*/

        return new GregorianCalendar(ano, mes, dia);
        //return new GregorianCalendar(ano, mes, dia, hora, minuto);
    }

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
