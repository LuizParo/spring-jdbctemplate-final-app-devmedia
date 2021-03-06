<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" >
		<title>Funcionário</title>
		
		<script type="text/javascript" src="<c:url value="/js/functions.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css" />" >
	</head>
	<body>
		<c:import url="menu.jsp" />
	
		<fieldset class="master">
			<c:url value="/funcionario" var="save" />
			<form:form action="${save}" modelAttribute="funcionario" method="post">
				<form:errors cssClass="errorblock" path="*" element="div" />
				<form:hidden path="id"/>
				
				<fieldset class="grupo">
					<legend>Funcionário</legend>
					<div class="campo">
						<form:label path="nome">Nome</form:label><br/>
						<form:input path="nome" id="nome" type="text" size="40" />
					</div>
					<div class="campo">
						<form:label path="salario">Salario</form:label><br/>
						<form:input path="salario" type="text" size="20" />
					</div>
					<div class="campo">
						<form:label path="dataEntrada">Data de Entrada</form:label><br/>
						<form:input type="date" path="dataEntrada" />
					</div>
					<div class="campo">
						<form:label path="dataSaida">Data de Saída</form:label><br/>
						<form:input path="dataSaida" type="date" />
					</div>
					<input type="button" onclick="localizarPorNome();" value="Localizar" />
				</fieldset>
					
				<fieldset class="grupo">
					<legend>Cargo</legend>
					<div class="campo">
						<form:label path="cargo">Cargo</form:label>
						<br/>
						<form:select id="cargo" path="cargo">
							<form:option value="" label="--- Select ---" />
							<form:options items="${cargos}" itemValue="id" itemLabel="cargo" />
						</form:select>
						<input type="button" onclick="localizarPorCargo();" value="Localizar" />
					</div>
				</fieldset>
				
				<br/>
				<fieldset class="grupo">
					<legend>Endereço</legend>
					<form:hidden path="endereco.id"/>
					<div class="campo">
						<form:label path="endereco.logradouro">Logradouro</form:label><br/>
						<form:input path="endereco.logradouro" type="text" size="30" />
						<form:errors cssClass="error" path="endereco.logradouro" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.numero">Número</form:label><br/>
						<form:input path="endereco.numero" type="text" size="30" />
						<form:errors cssClass="error" path="endereco.numero" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.complemento">Complemento</form:label><br/>
						<form:input path="endereco.complemento" type="text" size="30" />
						<div>&nbsp;</div>
					</div>
					<div class="campo">
						<form:label path="endereco.bairro">Bairro</form:label><br/>
						<form:input path="endereco.bairro" type="text" size="30" />
						<form:errors cssClass="error" path="endereco.bairro" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.cidade">Cidade</form:label><br/>
						<form:input path="endereco.cidade" type="text" size="30" />
						<form:errors cssClass="error" path="endereco.cidade" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.estado">Estado</form:label><br/>
						<form:input path="endereco.estado" type="text" size="30" />
						<form:errors cssClass="error" path="endereco.estado" element="div" />
					</div>
				</fieldset>
				
				<br/>
				<div>
					<input type="submit" value="Salvar" />
					<input type="reset" value="Limpar" />
				</div>
			</form:form>
		</fieldset>
		
		<fieldset class="master">
			<legend>Funcionarios</legend>
			<table style="width: 960px;">
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Salário</th>
					<th>Data de Entrada</th>
					<th>Data de Saída</th>
					<th>Cargo</th>
					<th>Ação</th>
				</tr>
				<c:forEach items="${funcionarios}" var="funcionario" varStatus="i">
					<tr bgcolor="${i.count % 2 == 0 ? '#f1f1f1' : 'white'}">
						<td>${funcionario.id}</td>
						<td>${funcionario.nome}</td>
						<td>
							<fmt:formatNumber value="${funcionario.salario}" currencySymbol="R$"
								maxFractionDigits="2" type="currency" />
						</td>
						<td>
							<fmt:parseDate value="${funcionario.dataEntrada}" var="dataEntrada" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${dataEntrada}" dateStyle="medium"/>
						</td>
						<td>
							<fmt:parseDate value="${funcionario.dataSaida}" var="dataSaida" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${dataSaida}" dateStyle="medium"/>
						</td>
						<td>${funcionario.cargo.cargo}</td>
						<td>
							<c:url value="/funcionario/update/${funcionario.id}" var="update" />
							<c:url value="/funcionario/remove/${funcionario.id}" var="remove" />
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