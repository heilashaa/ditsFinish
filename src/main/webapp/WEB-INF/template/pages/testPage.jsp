<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>




<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">Answer the question</h2>
    </div>
</div>
<div class="row d-flex justify-content-center mt-3">
    <div class="col-md-12">
        <div class="card">
            <div class="card-body pl-5 pt-5 pr-5">
                <form action="<%=application.getContextPath()%>/nextTestPage" method="get">
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
            </div>
        </div>
    </div>
</div>
<div class="mt-3 text-right pr-2">
    <a href="<%=application.getContextPath()%>/" class="text-success">Back to main page</a>
</div>