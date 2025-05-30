// Arquivo: MedicoServlet.java
package com.mack.clinica.controller;

import com.mack.clinica.model.Usuario;
import com.mack.clinica.model.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Anotação que define o caminho para acessar este servlet pela URL
@WebServlet("/medicos")
public class MedicoServlet extends HttpServlet {

    // Método que trata requisições GET (listar, editar, excluir médicos)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); // Ex: ?action=edit
        String base = getServletContext().getRealPath("/"); // Caminho para acesso ao banco

        try {
            if ("edit".equals(action)) {
                // Editar: busca médico por ID e envia para o formulário
                int id = Integer.parseInt(req.getParameter("id"));
                Usuario medico = UsuarioDAO.buscarMedicoPorId(id, base);
                req.setAttribute("medico", medico);
                req.getRequestDispatcher("medico-form.jsp").forward(req, resp);

            } else if ("delete".equals(action)) {
                // Excluir: remove médico do banco
                int id = Integer.parseInt(req.getParameter("id"));
                UsuarioDAO.excluirMedico(id, base);
                resp.sendRedirect("medicos");

            } else {
                // Listar: mostra todos os médicos cadastrados
                List<Usuario> medicos = UsuarioDAO.listarMedicos(base);
                req.setAttribute("medicos", medicos);
                req.getRequestDispatcher("medico-list.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Método que trata requisições POST (salvar novo ou editar existente)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String base = getServletContext().getRealPath("/");
        int id = req.getParameter("id") != null && !req.getParameter("id").isEmpty()
                 ? Integer.parseInt(req.getParameter("id")) : 0;
        String nome = req.getParameter("nome");

        try {
            if (id == 0) {
                // Novo médico
                UsuarioDAO.inserirMedico(nome, base);
            } else {
                // Atualização
                UsuarioDAO.atualizarMedico(id, nome, base);
            }
            resp.sendRedirect("medicos");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
