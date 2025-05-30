package com.mack.clinica.controller;

import com.mack.clinica.model.MedicoDAO;
import com.mack.clinica.model.Medico;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet("/medico")
public class MedicoServlet extends HttpServlet {
    private MedicoDAO dao = new MedicoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String base = getServletContext().getRealPath("/");
        try {
            if (action == null || action.equals("list")) {
                List<Medico> medicos = dao.readAll(base);
                req.setAttribute("medicos", medicos);
                req.getRequestDispatcher("/medico_list.jsp").forward(req, resp);
            } else if (action.equals("new")) {
                req.getRequestDispatcher("/medico_form.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Medico m = dao.readById(id, base);
                req.setAttribute("medico", m);
                req.getRequestDispatcher("/medico_form.jsp").forward(req, resp);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.delete(id, base);
                resp.sendRedirect("medico?action=list");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        String idParam = req.getParameter("id");
        String nome = req.getParameter("nome");
        String especialidade = req.getParameter("especialidade");

        // Validação básica
        if (nome == null || nome.trim().isEmpty()) errors.put("nome", "Nome é obrigatório");
        if (especialidade == null || especialidade.trim().isEmpty()) errors.put("especialidade", "Especialidade é obrigatória");

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            Medico mError = new Medico();
            if (idParam != null && !idParam.isEmpty()) mError.setId(Integer.parseInt(idParam));
            mError.setNome(nome);
            mError.setEspecialidade(especialidade);
            req.setAttribute("medico", mError);
            req.getRequestDispatcher("/medico_form.jsp").forward(req, resp);
            return;
        }

        String base = getServletContext().getRealPath("/");
        try {
            if (idParam == null || idParam.isEmpty()) {
                Medico m = new Medico(nome, especialidade);
                dao.create(m, base);
            } else {
                Medico m = new Medico(Integer.parseInt(idParam), nome, especialidade);
                dao.update(m, base);
            }
            resp.sendRedirect("medico?action=list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}