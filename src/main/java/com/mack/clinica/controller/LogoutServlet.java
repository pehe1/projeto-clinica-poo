package com.mack.clinica.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Mapeia para "/logout" de forma limpa
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalida a sessão
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Redireciona para página inicial (login)
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
