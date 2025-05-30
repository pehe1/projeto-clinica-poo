package com.mack.clinica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/fichaClinica")
public class FichaClinicaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/ficha_clinica.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Aqui você pode salvar no banco se desejar
        String anotacoes = req.getParameter("anotacoes");
        String prescricoes = req.getParameter("prescricoes");
        // Salvar no banco (implementar no DAO futuramente)
        // Redireciona ou mostra mensagem de sucesso
        resp.sendRedirect("admin_dashboard?msg=ficha_salva");
    }
}