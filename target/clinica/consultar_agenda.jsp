<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mack.clinica.model.Consulta" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Consultar Agenda</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="navbar">
    <div class="nav-links">
        <a href="admin_dashboard">Home</a>
        <a href="consultarAgenda">Consultar Agenda</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>
<div class="content">
    <h1>Consultar Agenda</h1>
    <form method="get" action="consultarAgenda" class="form-container">
        <label>Paciente:</label>
        <input type="text" name="paciente" value="${param.paciente}">
        <label>Médico:</label>
        <input type="text" name="medico" value="${param.medico}">
        <label>Data:</label>
        <input type="date" name="data" value="${param.data}">
        <button type="submit">Filtrar</button>
    </form>
    <table border="1" class="table">
        <thead>
            <tr>
                <th>Paciente</th>
                <th>Médico</th>
                <th>Data/Hora</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Consulta> consultas = (List<Consulta>) request.getAttribute("consultas");
            if (consultas != null && !consultas.isEmpty()) {
                for (Consulta c : consultas) {
        %>
            <tr>
                <td><%= c.getPacienteNome() %></td>
                <td><%= c.getMedicoNome() %></td>
                <td><%= c.getDataHora() %></td>
                <td><%= c.getStatus() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="4">Nenhuma consulta encontrada.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
