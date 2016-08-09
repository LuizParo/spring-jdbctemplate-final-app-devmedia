<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Cargo</title>
	</head>
	<body>
		<c:import url="menu.jsp" />
	
		<c:url value="/cargo" var="save" />
		<form:form action="${save}" modelAttribute="cargo" method="post">
			<form:hidden path="id"/>
			<fieldset style="width: 500px; margin: 0 auto;">
				<legend>Cargos</legend>
				<div>
					<form:label path="cargo">Cargo</form:label>
					<form:input path="cargo" type="text" required="true" />
				</div>
				<br/>
				<div>
					<form:label path="departamento">Departamento</form:label>
					<br/>
					<form:select path="departamento" required="true">
						<form:option value="" label="--- Select ---" />
						<form:options items="${departamentos}" itemValue="id" itemLabel="departamento" />
					</form:select>
				</div>
				<br/>
				<div>
					<input type="submit" value="Salvar" />
					<input type="reset" value="Limpar" />
				</div>
			</fieldset>
		</form:form>
		
		<fieldset style="width: 490px; margin: 0 auto;">
			<legend>Cargos</legend>
			<table style="width: 300px;">
				<tr>
					<th>Código</th>
					<th>Descrição</th>
					<th>Departamento</th>
					<th>Ação</th>
				</tr>
				<c:forEach items="${cargos}" var="cargo" varStatus="i">
					<tr bgcolor="${i.count % 2 == 0 ? '#f1f1f1' : 'white'}">
						<td>${cargo.id}</td>
						<td>${cargo.cargo}</td>
						<td>${cargo.departamento.departamento}</td>
						<td>
							<c:url value="/cargo/update/${cargo.id}" var="update" />
							<c:url value="/cargo/remove/${cargo.id}" var="remove" />
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