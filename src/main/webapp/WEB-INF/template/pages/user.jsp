<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">Please, <c:if test="${!empty user.id}">update</c:if><c:if test="${empty user.id}">create</c:if> user</h2>
    </div>
</div>
<div class="row d-flex justify-content-center mt-3">
    <div class="col-md-6">
        <div class="card">
            <div class="card-body pl-5 pr-5">
                <form:form modelAttribute="user" method="post" action="/users/create">
                    <form:input path="id" id="id" type="hidden"/>
                    <div class="md-form">
                        <form:input path="firstName" id="firstName"  class="form-control" autocomplete="off"/>
                        <label for="firstName" class="font-weight-light">Name</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="firstName"/></div>
                    </div>
                    <div class="md-form">
                        <form:input path="middleName" id="middleName" class="form-control" autocomplete="off"/>
                        <label for="middleName" class="font-weight-light">Middle name</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="middleName"/></div>
                    </div>
                    <div class="md-form">
                        <form:input path="lastName" id="lastName" class="form-control" autocomplete="off"/>
                        <label for="lastName" class="font-weight-light">Surname</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="lastName"/></div>
                    </div>
                    <div class="md-form">
                        <form:input path="login" id="login" class="form-control" autocomplete="off"/>
                        <label for="login" class="font-weight-light">Login</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="login"/></div>
                        <div class="text-danger font-weight-light font-small">${loginError}</div>
                    </div>
                    <div class="md-form">
                        <form:input path="email" id="email" class="form-control" autocomplete="off"/>
                        <label for="email" class="font-weight-light">Email</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="email"/></div>
                    </div>
                    <div class="md-form">
                        <form:input path="password" type="password" id="password" class="form-control" autocomplete="off"/>
                        <label for="password" class="font-weight-light">Password</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="password"/></div>
                    </div>
                    <p class="text-center h4 mt-4">Choose authorities:</p>
                    <div class="mt-3 text-center">
                        <c:forEach items="${roleList}" var="role">
                            <div class="custom-control custom-checkbox custom-control-inline">
                                <form:checkbox path="roles" value="${role.name}" class="custom-control-input" id="${role.name}"/>
                                <label class="custom-control-label" for="${role.name}">${fn:substring(role.name, 0, 1)}${fn:toLowerCase(fn:substring(role.name, 1, -1))}</label>
                            </div>
                        </c:forEach>
                        <div class="text-danger font-weight-light font-small"><form:errors path="roles"/></div>
                    </div>
                    <p class="text-center h4 mt-3">Also you can block the user:</p>
                    <div class="custom-control custom-switch mt-3 text-center">
                        <form:checkbox path="enabled" class="custom-control-input" id="enabled"/>
                        <label class="custom-control-label" for="enabled">User unblock</label>
                    </div>
                    <div class="text-center py-2 mt-3">
                        <button class="btn btn-success" name="submit" type="submit"><c:if test="${!empty user.id}">Update</c:if><c:if test="${empty user.id}">Create</c:if></button>
                    </div>
                    <div class="mt-3 text-right">
                        <a href="<%=application.getContextPath()%>/users" class="text-success">Back to list of users</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
