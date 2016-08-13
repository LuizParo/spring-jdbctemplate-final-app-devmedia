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
		<style>
			.master {
				width: 960px; margin: 0 auto;
			}
			
			.campo {
				margin-bottom: 1em;
			}
			
			.campo input:FOCUS, .campo select:FOCUS {
				background: #f8f8f8;
			}
			
			fieldset.grupo .campo {
				float: left;
				margin-right: 2em;
			}
		</style>
	</head>
	<body>
		<c:import url="menu.jsp" />
	
		<fieldset class="master">
			<c:url value="/funcionario" var="save" />
			<form:form action="${save}" modelAttribute="funcionario" method="post">
				<form:hidden path="id"/>
				<fieldset class="grupo">
					<legend>Funcionário</legend>
					<div class="campo">
						<form:label path="nome">Nome</form:label><br/>
						<form:input path="nome" type="text" size="40" />
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
					
					<fieldset class="grupo">
						<legend>Cargo</legend>
						<div class="campo">
							<form:label path="cargo">Cargo</form:label>
							<br/>
							<form:select path="cargo" required="true">
								<form:option value="" label="--- Select ---" />
								<form:options items="${cargos}" itemValue="id" itemLabel="cargo" />
							</form:select>
						</div>
					</fieldset>
					
					<br/>
					<fieldset class="grupo">
						<legend>Endereço</legend>
						<form:hidden path="endereco.id"/>
						<div class="campo">
							<form:label path="endereco.logradouro">Logradouro</form:label><br/>
							<form:input path="endereco.logradouro" type="text" size="30" />
						</div>
						<div class="campo">
							<form:label path="endereco.numero">Número</form:label><br/>
							<form:input path="endereco.numero" type="text" size="30" />
						</div>
						<div class="campo">
							<form:label path="endereco.complemento">Complemento</form:label><br/>
							<form:input path="endereco.complemento" type="text" size="30" />
						</div>
						<div class="campo">
							<form:label path="endereco.bairro">Bairro</form:label><br/>
							<form:input path="endereco.bairro" type="text" size="30" />
						</div>
						<div class="campo">
							<form:label path="endereco.cidade">Cidade</form:label><br/>
							<form:input path="endereco.cidade" type="text" size="30" />
						</div>
						<div class="campo">
							<form:label path="endereco.estado">Estado</form:label><br/>
							<form:input path="endereco.estado" type="text" size="30" />
						</div>
					</fieldset>
					
					<br/>
					<div>
						<input type="submit" value="Salvar" />
						<input type="reset" value="Limpar" />
					</div>
				</fieldset>
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
								maxFractionDigits="2" pattern="###,###.00" />
						</td>
						<td>
							<fmt:parseDate value="${funcionario.dataEntrada}" var="dataEntrada" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${dataEntrada}"/>
						</td>
							<fmt:parseDate value="${funcionario.dataSaida}" var="dataSaida" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${dataSaida}"/>
						<td>
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