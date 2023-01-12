<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Success</title>
<style type="text/css">
    span {
        display: inline-block;
        width: 200px;
        text-align: left;
    }
</style>
</head>
<body>
    <div align="center">
        <h2>${titulo}</h2>
        <c:forEach var="messageList" items="${messageList}">
                <tr>
                    <td><b>${messageList}</b></td>
                </tr>
         </c:forEach>
    </div>
    <div align="center">
    <a href="/list">Listar Clientes</a>
    <a href="/registerForm">Registrar Clientes</a>
    </div>
</body>
</html>