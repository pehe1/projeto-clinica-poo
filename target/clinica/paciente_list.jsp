<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Lista de Pacientes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <div class="nav-links">
        <a href="admin_dashboard">Home</a>
        <a href="paciente?action=new">Novo Paciente</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>
<div class="content">
    <h2>Pacientes</h2>
    <table class="table">
        <tr>
            <th>ID</th><th>Nome</th><th>E-mail</th><th>Telefone</th><th>CPF</th><th>Ações</th>
        </tr>
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
</div>
</body>
</html>