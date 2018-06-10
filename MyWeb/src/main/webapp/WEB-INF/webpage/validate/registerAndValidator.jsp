<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form  modelAttribute="user">
    <form:errors path="*" cssClass="color:red"></form:errors>
    <br>
    <form:input path="username"></form:input> <br>
    <form:input path="password"></form:input> <br>
    <input type="submit" value="register"/>
</form:form>