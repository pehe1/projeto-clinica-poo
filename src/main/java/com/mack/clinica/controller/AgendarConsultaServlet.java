package com.mack.clinica.controller;

import java.io.IOException;
import java.util.List;

import com.mack.clinica.model.AgendarConsultaDAO;
import com.mack.clinica.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/agendarConsulta")
public class AgendarConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtém o caminho real do projeto
        String realPathBase = request.getServletContext().getRealPath("/");
        // Instancia o DAO passando o caminho
        AgendarConsultaDAO dao = new AgendarConsultaDAO(realPathBase);
        // Busca a lista de médicos
        List<Usuario> medicos = dao.listarMedicos();
        System.out.println("Médicos encontrados: " + (medicos != null ? medicos.size() : 0));
        // Atribui a lista no request para ser usada no JSP
        request.setAttribute("medicos", medicos);
        // Encaminha para a página de agendamento
        request.getRequestDispatcher("/agendar_consulta.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Pega dados do formulário
            int profissionalId = Integer.parseInt(request.getParameter("profissionalId"));
            String dataHora = request.getParameter("dataHora");

            // Pega o paciente_id da sessão
            Integer pacienteIdObj = (Integer) request.getSession().getAttribute("id");
            if (pacienteIdObj == null) {
                System.out.println("Paciente não autenticado. Redirecionando para login.");
                response.sendRedirect("index.jsp");
                return;
            }
            int pacienteId = pacienteIdObj;

            // Conecta no banco
            String realPathBase = request.getServletContext().getRealPath("/");

            System.out.println("Paciente ID: " + pacienteId);
            System.out.println("Profissional ID: " + profissionalId);
            System.out.println("Data e Hora: " + dataHora);

            AgendarConsultaDAO dao = new AgendarConsultaDAO(realPathBase);

            // Agenda a consulta
            boolean sucesso = dao.agendarConsulta(pacienteId, profissionalId, dataHora);
            System.out.println("Sucesso: " + sucesso);
            if (sucesso) {
                // apresenta o pop-up de sucesso e mensagem_sucesso.jsp redireciona para o painel do paciente
                response.sendRedirect("/mensagem_sucesso.jsp");

            } else {
                response.sendRedirect("index.jsp?erro=agendar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paciente_dashboard.jsp?msg=erro");
        }
    }

}
