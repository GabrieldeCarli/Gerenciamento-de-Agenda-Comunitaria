/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Insercao", urlPatterns = {"/Insercao"})
public class Insercao extends HttpServlet {

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
        out.println("   <form action=\"Insercao\" method=\"post\">");
        out.println("       Titulo <input type=\"text\" name=\"titulo\" value=\"\"></input>");
        out.println("       <p>Data <input type=\"datetime-local\" name=\"data\" value=\"data\"></input>");
        out.println("       <p>Autor <input type=\"text\" name=\"idUsuario\" value=\"\" ></input>");
        out.println("       <p><input type=\"submit\" value=\"Enviar\"></input>");
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

        String titulo = request.getParameter("titulo");
        String usuario = request.getParameter("idUsuario");
        String data = request.getParameter("data");
        try {
            Conexao con = new Conexao();
            con.inserirNovaAtividade(titulo, usuario, data);
            System.out.println("Post OK");
            response.sendRedirect("Insercao");
        } catch (Exception ex) {
            System.err.println("Erro ao InserirNovaAtivadade");
            ex.getStackTrace();
            Logger.getLogger(Insercao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
