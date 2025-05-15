package com.mack.clinica.controller;

import java.io.IOException;

import com.mack.clinica.model.Usuario;
import com.mack.clinica.model.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet responsável por processar o login do usuário.
 */
@WebServlet("/loginAction")
public class LoginActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        String realPathBase = request.getServletContext().getRealPath("/");

        Usuario usuario = UsuarioDAO.buscarUsuario(email, senha, realPathBase);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", usuario.getId());
            session.setAttribute("nome", usuario.getNome());
            session.setAttribute("tipo", usuario.getTipo());

            if ("admin".equalsIgnoreCase(usuario.getTipo())) {
                response.sendRedirect("admin_dashboard");
            } else if ("paciente".equalsIgnoreCase(usuario.getTipo())) {
                response.sendRedirect("paciente_dashboard");
            } else {
                response.sendRedirect("index.jsp?erro=tipo");
            }
        } else {
            response.sendRedirect("index.jsp?erro=login");
        }
    }
}
