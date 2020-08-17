<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>--%>

<form action="/goTest" >
    <select id="themes" name="themes" class="browser-default custom-select">
        <c:forEach items="${topics}" var="topic">
            <option value="" disabled selected hidden> Choose topic </option>
            <option>${topic.name}</option>
        </c:forEach>
    </select>
    <br>
    <select id="tests" name="testName" class="browser-default custom-select">
        <option value="" disabled selected hidden> Choose topic </option>
    </select>
    <br>
    <div class="text-center py-2 mt-3">
        <button class="btn btn-success" name="submit" type="submit">take the
            test</button>
    </div>
    <%--<input type="submit" value="пройти тестирование" class="btn aqua-gradient">--%>
</form>
