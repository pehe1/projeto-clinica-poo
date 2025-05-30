 /*
 * 
 * Classe: ConnectionFactory para conectarmos ao banco de dados
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/clinica";
    private static final String USER = "PEDRO";
    private static final String PASS = "Corinthians123";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}


/*
 * 
 * Classe: Paciente
 */
package model;

public class Paciente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    public Paciente() {}

    public Paciente(String nome, String email, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Paciente(int id, String nome, String email, String telefone, String cpf) {
        this(nome, email, telefone, cpf);
        this.id = id;
    }

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}


/*
 * Classe do pacienteDAO
 * 
 */
package dao;

import model.Paciente;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void create(Paciente p) throws Exception {
        String sql = "INSERT INTO paciente(nome, email, telefone, cpf) VALUES(?,?,?,?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getCpf());
            ps.executeUpdate();
        }
    }

    public List<Paciente> readAll() throws Exception {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER BY nome";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("cpf")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    public Paciente readById(int id) throws Exception {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("cpf")
                    );
                }
            }
        }
        return null;
    }

    public void update(Paciente p) throws Exception {
        String sql = "UPDATE paciente SET nome=?, email=?, telefone=?, cpf=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getCpf());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM paciente WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}


/*
 * Importei umas bibliotecas que provavelmente vamos usar nos outros blocos
 * 
 */
package servlet;

import dao.PacienteDAO;
import model.Paciente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet("/paciente")
public class PacienteServlet extends HttpServlet {
    private PacienteDAO dao = new PacienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                List<Paciente> pacientes = dao.readAll();
                req.setAttribute("pacientes", pacientes);
                req.getRequestDispatcher("/pacienteList.jsp").forward(req, resp);
            } else if (action.equals("new")) {
                req.getRequestDispatcher("/pacienteForm.jsp").forward(req, resp);
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Paciente p = dao.readById(id);
                req.setAttribute("paciente", p);
                req.getRequestDispatcher("/pacienteForm.jsp").forward(req, resp);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.delete(id);
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

        // validações básicas de pacientes e coleta de dados
        if (nome == null || nome.trim().isEmpty()) errors.put("nome", "Nome é obrigatório");
        if (email == null || !Pattern.matches("^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$", email)) errors.put("email", "E-mail inválido");
        if (telefone == null || telefone.trim().length() < 8) errors.put("telefone", "Telefone inválido");
        if (cpf == null || !Pattern.matches("\\d{11}", cpf)) errors.put("cpf", "CPF deve conter 11 dígitos numéricos");

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            Paciente pError = new Paciente();
            if (idParam != null && !idParam.isEmpty()) pError.setId(Integer.parseInt(idParam));
            pError.setNome(nome); pError.setEmail(email);
            pError.setTelefone(telefone); pError.setCpf(cpf);
            req.setAttribute("paciente", pError);
            req.getRequestDispatcher("/pacienteForm.jsp").forward(req, resp);
            return;
        }

        try {
            if (idParam == null || idParam.isEmpty()) {
                Paciente p = new Paciente(nome, email, telefone, cpf);
                dao.create(p);
            } else {
                Paciente p = new Paciente(Integer.parseInt(idParam), nome, email, telefone, cpf);
                dao.update(p);
            }
            resp.sendRedirect("paciente?action=list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}


/*
 * JSP: pacienteForm.jsp
 */
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Paciente</title></head>
<body>
<h2><c:choose><c:when test="${not empty paciente.id}">Editar Paciente</c:when><c:otherwise>Novo Paciente</c:otherwise></c:choose></h2>
<form action="paciente" method="post">
    <input type="hidden" name="id" value="${paciente.id}"/>
    Nome: <input type="text" name="nome" value="${paciente.nome}"/> <span style="color:red">${errors.nome}</span><br/>
    E-mail: <input type="email" name="email" value="${paciente.email}"/> <span style="color:red">${errors.email}</span><br/>
    Telefone: <input type="text" name="telefone" value="${paciente.telefone}"/> <span style="color:red">${errors.telefone}</span><br/>
    CPF: <input type="text" name="cpf" pattern="\d{11}" value="${paciente.cpf}"/> <span style="color:red">${errors.cpf}</span><br/>
    <button type="submit">Salvar</button>
    <a href="paciente?action=list">Cancelar</a>
</form>
</body>
</html>


/*
 * JSP: pacienteList.jsp
 */
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Lista de Pacientes</title></head>
<body>
<h2>Pacientes</h2>
<a href="paciente?action=new">Novo Paciente</a>
<table border="1">
    <tr><th>ID</th><th>Nome</th><th>E-mail</th><th>Telefone</th><th>CPF</th><th>Ações</th></tr>
    <c:forEach var="p" items="${pacientes}">
        <tr>
            <td>${p.id}</td>
            <td>${p.nome}</td>
            <td>${p.email}</td>
            <td>${p.telefone}</td>
            <td>${p.cpf}</td>
            <td>
                <a href="paciente?action=edit&id=${p.id}">Editar</a>
                <a href="paciente?action=delete&id=${p.id}" onclick="return confirm('Excluir este paciente?');">Excluir</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
