<%@ page
	info="Desenvolvido por Marcelo Gadelha"
	language="java"
	session="true"
	buffer="10kb"
	autoFlush="true"
	isErrorPage="false"
	errorPage="paginas/erros/erro.jsp"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!-- JSTL -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sucesso: JSP Standard Tag Library e Expression Language</title>
</head>
<body>

	<!-- JSTL - JSP Standard Tag Library & EL - EXPRESSION LANGUAGE -->
	sucesso
	<c:out value="${objeto.nome}"/><br/>
	<c:out value="${nomear}"/><br/>
	
	<c:forEach var="item" items="${lista}">
		<c:out value="NUMERO:"/><c:out value="${item.numero}"/>
		<c:out value="NOME:"/><c:out value="${item.nome}"/>
		<c:out value="DATA:"/><fmt:formatDate value="${item.data}" type="both" pattern="dd/MM/yyyy"/>
		<c:out value="DATA:"/><c:out value="${item.data}"/>
		
		<fmt:formatDate value="${item.data}" type="both" pattern="dd/MM/yyyy"/>
		
		<a href="ServletControle?escolha=excluir&numero=${item.numero}">Excluir</a><br/>
	</c:forEach>
	
	<c:if test="${fn:length(lista) > 0}">
		Existem ${fn:length(lista)} nomes!
	</c:if>
	
	<br>
	<a href="index.jsp">index</a>

</body>
</html>