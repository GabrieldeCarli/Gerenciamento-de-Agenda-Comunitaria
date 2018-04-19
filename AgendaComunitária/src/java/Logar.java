/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gabri
 */
@WebServlet(urlPatterns = {"/Logar"})
public class Logar extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Veja os eventos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<fieldset>");
            out.println("<legend>LOGAR</legend>");
            out.println("<form action=\"logar\" method=\"post\">");
            out.println("Nome: <input type=\"text\" name=\"name_user\" /><br />");
            out.println("Senha: <input type=\"password\" name=\"senha\" /><br />");
            out.println("<input type=\"submit\" value=\"Login\" />");
            out.println("</form>");
            out.println("</fieldset>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String name_user = request.getParameter("name_user");
        String senha = request.getParameter("senha");
        List<Usuario> user = new ArrayList();
        user = null;
        
        try {
            
            UsuarioDAO usuario = new UsuarioDAO();
            user = usuario.validar(name_user, senha);
            
            if (user == null) {
                out.println("<html><body>Usuário ou senha inválida</body></html>");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user.logado", usuario);
                out.println("<html><body>Usuário logado: " + name_user + "</body></html>");

                response.sendRedirect("/Eventos");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Logar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
