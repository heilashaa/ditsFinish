<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">List of Users</h2>
    </div>
</div>
<c:if test="${!empty listUsers}">
<div class="row">
    <div class="col">
        <table class="table table-hover table-sm">
            <thead class="special-color-dark white-text">
            <tr>
                <th>Full name</th>
                <th>Login</th>
                <th>Email</th>
                <th>Block</th>
                <th>Authorities</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.fullName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:if test="${!user.enabled}">
                            <span class="badge badge-danger badge-pill">BLOCK</span>
                        </c:if>
                    </td>
                    <td>
                        <c:forEach items="${user.roles}" var="role">
                            <span class="badge badge-success badge-pill"><c:out value="${role}" /></span>
                        </c:forEach>
                    </td>
                    <td><a href="<%=application.getContextPath()%>/users/edit/${user.id}"><i class="fas fa-edit"></i></a></td>
                    <td><a href="<%=application.getContextPath()%>/users/re-block/${user.id}"><i class="fas fa-key"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</c:if>
<c:if test="${empty listUsers}">
    <div class="row mt-3">
        <div class="col">
            <h4 class="title h4-responsive text-center text-success">Sorry! User list is empty</h4>
        </div>
    </div>
</c:if>
<div class="mt-3 text-right">
    <a href="<%=application.getContextPath()%>/users/create" class="btn btn-success">Create user</a>
</div>
<div class="mt-3 text-right pr-2">
    <a href="<%=application.getContextPath()%>/" class="text-success">Back to main page</a>
</div>
