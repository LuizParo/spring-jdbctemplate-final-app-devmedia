<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form:form action="" modelAttribute="" method="post">
			<fieldset style="width: 300px; margin: 0 auto;">
				<legend>Departamento</legend>
				<div>
					<form:label path="departamento">Departamento</form:label>
					<form:input path="departamento" type="text" />
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
				<c:forEach items="departamentos" var="departamento" varStatus="i">
					<tr>
						<td>${departamento.id}</td>
						<td>${departamento.departamento}</td>
						<td>
							<a href="#" title="Ver/Editar">&#9445;</a>
							|
							<a href="#" title="Remover">&#9447;</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</body>
</html>