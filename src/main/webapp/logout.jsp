<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Logout</title>
    <meta http-equiv="refresh" content="2;URL=index.jsp">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            text-align: center;
            padding-top: 100px;
        }

        h2 {
            color: #333;
        }
    </style>
</head>
<body>

<%
    // Invalida a sessão atual
    session.invalidate();
%>

<h2>Você foi desconectado com sucesso!</h2>
<p>Redirecionando para a página inicial...</p>

</body>
</html>

