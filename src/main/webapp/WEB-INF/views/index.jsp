<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Welcome to Cognito test Calendar)))" scope="request"/>
<jsp:include page="./includes/header.jsp"/>

<br/>
<pre><c:out value="${note}" /></pre>

<jsp:include page="./includes/footer.jsp"/>