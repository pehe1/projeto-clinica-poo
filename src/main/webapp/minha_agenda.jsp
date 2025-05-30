<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mack.clinica.model.Consulta" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Minha Agenda</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <!-- Menu de Navegação -->
    <div class="navbar">
        <div class="nav-links">
            <a href="paciente_dashboard">Home</a>
            <a href="agendarConsulta">Agendar Consulta</a>
            <a href="minhaAgenda">Minha Agenda</a>
            <a href="meuCadastro">Meu Cadastro</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

    <!-- Conteúdo principal -->
    <div class="content">
        <h1>Minha Agenda</h1>
        <table class="styled-table">
            <thead>
                <tr>
                    <th>Data e Hora</th>
                    <th>Médico</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Obtém a lista de consultas do request
                    List<Consulta> consultas = (List<Consulta>) request.getAttribute("consultas");
                    if (consultas != null && !consultas.isEmpty()) {
                        for (Consulta consulta : consultas) {
                %>
                            <tr>
                                <td><%= consulta.getDataHora() %></td> <!-- Exibe a data e hora da consulta -->
                                <td><%= consulta.getMedicoNome() %></td> <!-- Exibe o nome do médico -->
                                <td><%= consulta.getStatus() %></td> <!-- Exibe o status da consulta -->
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="3">Nenhuma consulta marcada.</td> <!-- Mensagem caso não haja consultas -->
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

</body>
</html>