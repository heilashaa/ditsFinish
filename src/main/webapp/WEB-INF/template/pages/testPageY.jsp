<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="/nextTestPage" method="get">
    <h4>
        <div>${questions}</div>
    </h4>
    <br>
    <c:forEach items="${answers}" var="answer">
        <input type="radio"
               name="choosenAns"
               value="${answer.id}">
        ${answer.description}<br>
    </c:forEach>
    <br>
    <div>
        <button class="btn btn-success" name="submit" type="submit">next</button>
    </div>
</form>
<form action="/main" method="get">
    <div>
        <button class="btn btn-success" name="submit" type="submit">exit</button>
    </div>
</form>
<%--<a href="<c:url value="/logout"/>">Exit</a>--%>