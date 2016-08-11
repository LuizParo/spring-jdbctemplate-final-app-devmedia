<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul>
	<c:url value="/departamento" var="formDepartamento" />
	<c:url value="/cargo" var="formCargo" />
	<c:url value="/funcionario" var="formFuncionario" />
	<li>
		<a href="${formDepartamento}" title="Departamentos">Departamentos</a>
	</li>
	<li>
		<a href="${formCargo}" title="Cargo">Cargo</a>
	</li>
	<li>
		<a href="${formFuncionario}" title="Funcionario">Funcionario</a>
	</li>
</ul>