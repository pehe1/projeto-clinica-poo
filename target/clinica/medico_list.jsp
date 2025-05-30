<!-- filepath: src/main/webapp/medico_list.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Lista de Médicos</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="navbar">
    <div class="nav-links">
        <a href="admin_dashboard">Home</a>
        <a href="medico?action=new">Novo Medico</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>
<div class="content">
    <h2>Medicos</h2>
    <table class="table">
        <tr>
            <th>ID</th><th>Nome</th><th>Especialidade</th><th>Ajustes</th>
        </tr>
        <c:forEach var="m" items="${medicos}">
            <tr>
                <td>${m.id}</td>
                <td>${m.nome}</td>
                <td>${m.especialidade}</td>
                <td>
                    <a href="medico?action=edit&id=${m.id}">Editar</a>
                    <a href="medico?action=delete&id=${m.id}" onclick="return confirm('Excluir este medico?');">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>