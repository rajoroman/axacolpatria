<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
        
        <title>Customers</title>
    </head>
    <body>
    	<div class="container-fluid">
    	<div class="table-responsive">
		        <h1>Customers List</h1> 
		        <form:form action="/list" method="get" modelAttribute="filter">
			        <form:label path="nameFilter" >Full name:</form:label>
	                <form:input required="required" id="nameFilter" path="nameFilter" type="text" title="El nombre debe contener solo letras. ejemplo John"/>
	                <form:button class="btn btn-danger">Filter Name</form:button>
	                <a class="btn btn-success" href="/list" role="button">Clear Filter</a>
	                <a class="btn btn-primary" href="/registerForm?hasError=0" role="button">Register Customer</a>
		        </form:form>
				        <table class="table table-dark table-hover">
				            <tr>
				                <th style="width:  50px;">Id</th>
				                <th style="width: 100px;"><a href="/order?orderParam=customerId"/>Customer Id</a></th>
				                <th style="width: 300px;"><a href="/order?orderParam=name"/> Name</a></th>
				                <th style="width: 150px;">Phone Number</th>
				                <th style="width: 300px;">Address</th>
				                <th style="width: 100px;">Accion</th>
				            </tr>
				            <c:forEach var="customer" items="${customersList}">
				                <tr>
				                    <td><b>${customer.id}</b></td>
				                    <td>${customer.customerId}</td>
				                    <td>${customer.name}</td>
				                    <td>${customer.phoneNumber}</td>
				                    <td>${customer.address}</td>
				                    <td><a class="btn btn-danger" href="/delete?id=${customer.id}" role="button">Delete</a></td>
				                </tr>
				            </c:forEach>
				        </table>
				</div>
    	</div>
    </body>
</html>