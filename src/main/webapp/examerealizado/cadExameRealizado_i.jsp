<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="Cadastrado de Exames Realizados"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<s:form action="/novoExamesRealizados.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosExamesRealizados" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Exames Realizados</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Novo Exame Realizado</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-1 col-form-label text-center">
								ID
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="exameRealizadoVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="idfuncionario" class="col-sm-1 col-form-label text-center">
								ID Funcionario
							</label>	
								
							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="idfuncionario" name="exameRealizadoVo.rowidFuncionario"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="idexame" class="col-sm-1 col-form-label text-center">
								ID Exame
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="idexame" name="exameRealizadoVo.rowidExame"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="dtrealizacao" class="col-sm-1 col-form-label text-center">
								Data de Realização
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="dtrealizacao" name="exameRealizadoVo.dataRealizacao"/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>