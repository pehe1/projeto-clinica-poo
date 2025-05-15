<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mack.clinica.model.Usuario" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Agendar Consulta</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <!-- Menu de Navegação -->
    <div class="navbar">
        <div class="nav-links">
            <a href="paciente_dashboard">Home</a>
            <a href="agendarConsulta">Agendamento de Consultas</a>
            <a href="#">Minha Agenda</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

<!-- Conteúdo centralizado -->
<div class="content">
    <h1>Agendar Consulta</h1>
    <form method="post" action="agendarConsulta" class="form-container">
        <label for="profissionalId">Profissional:</label>
        <select id="profissionalId" name="profissionalId" required>
            <option value="">Selecione o médico</option>
            <%
                List<Usuario> medicos = (List<Usuario>) request.getAttribute("medicos");
                if (medicos != null) {
                    for (Usuario medico : medicos) {
            %>
                        <option value="<%= medico.getId() %>"><%= medico.getNome() %></option>
            <%
                    }
                }
            %>
        </select>

        <label for="dataHora">Data e Hora:</label>
        <input type="datetime-local" id="dataHora" name="dataHora" required>

        <button type="submit" class="button">Agendar</button>
    </form>
</div>

</body>
</html>