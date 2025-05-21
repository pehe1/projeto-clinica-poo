package com.mack.clinica.controller;

import java.io.IOException;

import com.mack.clinica.model.Usuario;
import com.mack.clinica.model.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/meuCadastro") //Mapeia o Servlet para a URL "/meuCadastro"
public class MeuCadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtém o ID do paciente da sessão
        Integer pacienteId = (Integer) request.getSession().getAttribute("id");
        if (pacienteId == null) { //Verifica se o paciente está autenticado
            response.sendRedirect("index.jsp"); //Redireciona para a página de login, caso não esteja
            return;
        }

        //Obtém o caminho real do projeto (necessário para acessar o banco de dados)
        String realPathBase = request.getServletContext().getRealPath("/");

        //Busca os dados do paciente no banco de dados
        Usuario paciente = UsuarioDAO.buscarPacientePorId(pacienteId, realPathBase);

        //Atribui os dados do paciente ao request para serem usados no JSP
        request.setAttribute("paciente", paciente);

        //Encaminha para o JSP "meu_cadastro.jsp"
        request.getRequestDispatcher("/meu_cadastro.jsp").forward(request, response);
    }
}