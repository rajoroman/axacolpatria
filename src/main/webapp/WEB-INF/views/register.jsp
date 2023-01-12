<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
        
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        
<style type="text/css">
    label {
        display: inline-block;
        width: 200px;
        margin: 5px;
        text-align: left;
    }
    input[type=text], input[type=password], select {
        width: 200px;  
    }
    input[type=radio] {
        display: inline-block;
        margin-left: 45px;
    }
    input[type=checkbox] {
        display: inline-block;
        margin-right: 190px;
    }  
     
    button {
        padding: 10px;
        margin: 10px;
    }
</style>
<meta charset="UTF-8">
<title>Customer Registration Form</title>
</head>
<body>
    <c:if test="${hasError == 1}">
	    <script type="text/javascript">
	            alert("${messageList}");
	            window.location.href = "/registerForm?hasError=0"
		</script> 
    </c:if>
   <c:if test="${hasError == 0}">
	    <div align="left">
	    	<form:form action="list" method="get" modelAttribute="customer">
	           <form:button class="btn btn-danger">Customers List</form:button>
	        </form:form>
	     </div>
	    <div align="center">
	        <h2>Customer Registration</h2>
	        <form:form action="register" method="get" modelAttribute="customer">
	            
	            <form:label path="customerId">ID Customer:</form:label>
	            <form:input required="required" path="customerId" type="text" pattern="[0-9]{5,10}"  title="El Id de Cliente debe contener digitos de 0 a 9. Ejemplo 0147852"/><br/>
	            
	            <form:label path="name">Full name:</form:label>
	            <form:input required="required" path="name" type="text" title="El nombre debe contener solo letras. ejemplo John"/><br/>
	             
	            <form:label path="phoneNumber">Phone Number:</form:label>
	            <form:input required="required" path="phoneNumber" pattern="[0-9]{6,15}" title="El número de teléfono debe contener digitos de 0 a 9. Ejemplo 310647102020"/><br/>      
	 
	            <form:label path="age">Age:</form:label>
	            <form:input required="required" path="age" pattern="[0-9]{1,3}" min="1" max="110" title="La edad debe ser número entre 0 y 9, entre 1 y 110"/><br/>
	             
	            <form:label path="address">Address:</form:label>
	            <form:input type="text" required="required" path="address" patter="(a-z){1,}" title="La dirección debe tener el formato XXXX 23 # 23XXX – 23XXX"/><br/>
	            <form:button class="btn btn-primary">Register</form:button>
	        </form:form>
	        </div>
	    </div>
    </c:if>
</body>
</html>