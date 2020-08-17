<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row mt-5">
    <div class="col">
        <h2 class="title h2 text-center">Current test result</h2>
    </div>
</div>
<div class="row">
    <div class="col">
        <table class="table table-hover table-sm">
            <thead class="special-color-dark white-text">
            <tr>
                <th class="text-center"> Question</th>
                <th class="text-center"> Correct</th>
                <th class="text-center"> Recommend literature</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${statistic}" var="stat">
                <tr>
                    <td>${stat.getQuestion().getDescription()}</td>
                    <td class="text-center">
                        <c:if test="${stat.isCorrect()}">
                            <i class="fas fa-thumbs-up text-success"></i>
                        </c:if>
                        <c:if test="${!stat.isCorrect()}">
                            <i class="fas fa-thumbs-down text-danger"></i>
                        </c:if>
                    </td>
                    <td>
                        <ul>
                            <c:forEach items="${stat.getQuestion().getLiterature()}" var="literature">
                                <c:forEach items="${literature.getLinks()}" var="link">
                                    <li class="list-unstyled"><a class="text-success" href="${link.getLink()}">${literature.getDescription()}</a></li>
                                </c:forEach>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="mt-3 text-right pr-2">
    <a href="<%=application.getContextPath()%>/" class="text-success">Back to main page</a>
</div>
