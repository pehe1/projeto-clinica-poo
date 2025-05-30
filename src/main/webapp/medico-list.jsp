<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Médicos</title></head>
<body>
<h2>Lista de Médicos</h2>

<a href="medico-form.jsp">Novo Médico</a>

<!-- Tabela com a listagem dos médicos -->
<table border="1">
    <tr><th>ID</th><th>Nome</th><th>Ações</th></tr>
    <!-- Laço de repetição para exibir médicos -->
    <c:forEach var="medico" items="${medicos}">
        <tr>
            <td>${medico.id}</td>
            <td>${medico.nome}</td>
            <td>
                <a href="medicos?action=edit&id=${medico.id}">Editar</a>
                <a href="medicos?action=delete&id=${medico.id}">Excluir</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
