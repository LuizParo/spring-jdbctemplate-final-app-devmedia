<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Departamento</title>
	</head>
	<body>
		<c:import url="menu.jsp" />
	
		<c:url value="/departamento" var="save" />
		<form:form action="${save}" modelAttribute="departamento" method="post">
			<form:hidden path="id"/>
			<fieldset style="width: 300px; margin: 0 auto;">
				<legend>Departamento</legend>
				<div>
					<form:label path="departamento">Departamento</form:label>
					<form:input path="departamento" type="text" required="true" />
				</div>
				<br/>
				<div>
					<input type="submit" value="Salvar" />
					<input type="reset" value="Limpar" />
				</div>
			</fieldset>
		</form:form>
		
		<fieldset style="width: 300px; margin: 0 auto;">
			<legend>Departamentos</legend>
			<table style="width: 300px;">
				<tr>
					<th>Código</th>
					<th>Descrição</th>
					<th>Ação</th>
				</tr>
				<c:forEach items="${departamentos}" var="departamento" varStatus="i">
					<tr bgcolor="${i.count % 2 == 0 ? '#f1f1f1' : 'white'}">
						<td>${departamento.id}</td>
						<td>${departamento.departamento}</td>
						<td>
							<c:url value="/departamento/update/${departamento.id}" var="update" />
							<c:url value="/departamento/remove/${departamento.id}" var="remove" />
							<a href="${update}" title="Ver/Editar">&#9445;</a>
							|
							<a href="${remove}" title="Remover">&#9447;</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</body>
</html>