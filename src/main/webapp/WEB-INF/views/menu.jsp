<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul style="list-style-type: none;">
	<li>
		<c:url value="/departamento" var="formDepartamento" />
		<img alt="SnowAngel" src="<c:url value="/files/snowangel.jpg" />" width="15px" height="15px">
		<a href="${formDepartamento}" title="Departamentos">Departamentos</a>
	</li>
	<li>
		<c:url value="/cargo" var="formCargo" />
		<img alt="SnowAngel" src="<c:url value="/img/snowangel.jpg" />" width="15px" height="15px">
		<a href="${formCargo}" title="Cargo">Cargo</a>
	</li>
	<li>
		<c:url value="/funcionario" var="formFuncionario" />
		<img alt="SnowAngel" src="<c:url value="/images/snowangel.jpg" />" width="15px" height="15px">
		<a href="${formFuncionario}" title="Funcionario">Funcionario</a>
	</li>
</ul>