package com.mack.clinica.controller;

import com.mack.clinica.model.PacienteDAO;
import com.mack.clinica.model.Paciente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet("/paciente")
public class PacienteServlet extends HttpServlet {
    private PacienteDAO dao = new PacienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String base = getServletContext().getRealPath("/");
        try {
            if (action == null || action.equals("list")) {
                List<Paciente> pacientes = dao.readAll(base);
                req.setAttribute("pacientes", pacientes);
                req.getRequestDispatcher("/paciente_list.jsp").forward(req, resp);
            } else if (action.equals("new")) {
                req.getRequestDispatcher("/paciente_form.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Paciente p = dao.readById(id, base);
                req.setAttribute("paciente", p);
                req.getRequestDispatcher("/paciente_form.jsp").forward(req, resp);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.delete(id, base);
                resp.sendRedirect("paciente?action=list");
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
        String email = req.getParameter("email");
        String telefone = req.getParameter("telefone");
        String cpf = req.getParameter("cpf");

        //Validações
        if (nome == null || nome.trim().isEmpty()) errors.put("nome", "Nome é obrigatório");
        if (email == null || !Pattern.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) errors.put("email", "E-mail inválido");
        if (telefone == null || telefone.trim().length() < 8) errors.put("telefone", "Telefone inválido");
        if (cpf == null || !Pattern.matches("\\d{11}", cpf)) errors.put("cpf", "CPF deve conter 11 dígitos numéricos");

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            Paciente pError = new Paciente();
            if (idParam != null && !idParam.isEmpty()) pError.setId(Integer.parseInt(idParam));
            pError.setNome(nome); pError.setEmail(email);
            pError.setTelefone(telefone); pError.setCpf(cpf);
            req.setAttribute("paciente", pError);
            req.getRequestDispatcher("/paciente_form.jsp").forward(req, resp);
            return;
        }

        String base = getServletContext().getRealPath("/");
        try {
            if (idParam == null || idParam.isEmpty()) {
                Paciente p = new Paciente(nome, email, telefone, cpf);
                dao.create(p, base);
            } else {
                Paciente p = new Paciente(Integer.parseInt(idParam), nome, email, telefone, cpf);
                dao.update(p, base);
            }
            resp.sendRedirect("paciente?action=list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}