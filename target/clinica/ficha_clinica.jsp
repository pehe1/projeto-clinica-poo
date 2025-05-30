<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Ficha Clínica</title>
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
    <h1>Ficha Clínica</h1>
    <form method="post" action="fichaClinica" class="form-container">
        <label for="anotacoes">Anotações:</label>
        <textarea id="anotacoes" name="anotacoes" rows="5" cols="50"></textarea>
        <label for="prescricoes">Prescrições:</label>
        <textarea id="prescricoes" name="prescricoes" rows="5" cols="50"></textarea>
        <button type="submit">Salvar</button>
    </form>
</div>
</body>
</html>
