package com.mack.clinica.controller;

import com.mack.clinica.model.Consulta;
import com.mack.clinica.model.ConsultaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/consultarAgenda")
public class ConsultarAgendaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String paciente = req.getParameter("paciente");
        String medico = req.getParameter("medico");
        String data = req.getParameter("data");
        String realPathBase = req.getServletContext().getRealPath("/");

        List<Consulta> consultas = ConsultaDAO.buscarConsultasFiltradas(paciente, medico, data, realPathBase);
        req.setAttribute("consultas", consultas);
        req.getRequestDispatcher("/consultar_agenda.jsp").forward(req, resp);
    }
}