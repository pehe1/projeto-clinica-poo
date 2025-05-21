package com.mack.clinica.controller;

import java.io.IOException;
import java.util.List;

import com.mack.clinica.model.Consulta;
import com.mack.clinica.model.ConsultaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/minhaAgenda") // Mapeia o Servlet para a URL "/minhaAgenda"
public class MinhaAgendaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém o ID do paciente da sessão
        Integer pacienteId = (Integer) request.getSession().getAttribute("id");
        if (pacienteId == null) { // Verifica se o paciente está autenticado
            response.sendRedirect("index.jsp"); // Redireciona para a página de login, caso não esteja
            return;
        }

        // Obtém o caminho real do projeto
        String realPathBase = request.getServletContext().getRealPath("/");

        // Busca as consultas do paciente no banco de dados
        List<Consulta> consultas = ConsultaDAO.buscarConsultasPorPaciente(pacienteId, realPathBase);

        // Atribui a lista de consultas ao request
        request.setAttribute("consultas", consultas);

        // Encaminha para o JSP "minha_agenda.jsp"
        request.getRequestDispatcher("/minha_agenda.jsp").forward(request, response);
    }
}