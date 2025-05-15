package com.mack.clinica.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Mapeia para "/admin_dashboard" sem expor .jsp
@WebServlet("/admin_dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Faz o forward para o JSP interno
        request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
    }
}

