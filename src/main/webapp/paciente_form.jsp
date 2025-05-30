<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Paciente</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="navbar">
    <div class="nav-links">
        <a href="admin_dashboard">Home</a>
        <a href="paciente?action=list">Listar Pacientes</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
</div>
<div class="content">
    <h2>
        <c:choose>
            <c:when test="${not empty paciente.id}">Editar Paciente</c:when>
            <c:otherwise>Novo Paciente</c:otherwise>
        </c:choose>
    </h2>
    <form action="paciente" method="post" class="form-container">
        <input type="hidden" name="id" value="${paciente.id}"/>
        <label>Nome:</label>
        <input type="text" name="nome" value="${paciente.nome}" required>
        <span style="color:red">${errors.nome}</span><br/>

        <label>E-mail:</label>
        <input type="email" name="email" value="${paciente.email}" required>
        <span style="color:red">${errors.email}</span><br/>

        <label>Telefone:</label>
        <input type="text" name="telefone" value="${paciente.telefone}" required>
        <span style="color:red">${errors.telefone}</span><br/>

        <label>CPF:</label>
        <input type="text" name="cpf" pattern="\d{11}" value="${paciente.cpf}" required>
        <span style="color:red">${errors.cpf}</span><br/>

        <button type="submit">Salvar</button>
        <a href="paciente?action=list">Cancelar</a>
    </form>
</div>
</body>
</html>
