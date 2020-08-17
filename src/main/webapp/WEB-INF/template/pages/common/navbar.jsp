<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<nav class="navbar navbar-expand-lg navbar-dark special-color-dark">
    <a class="navbar-brand" href="<%=application.getContextPath()%>/"><img src="/img/logo.png" height="60" alt="logo"></a>
    <sec:authorize access="isAuthenticated()">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-1"
            aria-controls="navbarSupportedContent-4" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent-1">
        <ul class="navbar-nav ml-auto h5">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-00" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Statistics </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-00">
                    <sec:authorize access="hasAnyAuthority('ADMIN', 'TUTOR')">
                        <a class="dropdown-item" href="<%=application.getContextPath()%>/statistic/by-tests">By test</a>
                        <a class="dropdown-item" href="<%=application.getContextPath()%>/statistic/by-questions">By questions</a>
                        <a class="dropdown-item" href="<%=application.getContextPath()%>/statistic/by-users">By users</a>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('USER')">
                        <a class="dropdown-item" href="<%=application.getContextPath()%>/statistic/personal">Personal</a>
                    </sec:authorize>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Tests </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-01">
                    <sec:authorize access="hasAnyAuthority('ADMIN', 'TUTOR')">
                        <a class="dropdown-item" href="/tests">All tests</a>
                        <a class="dropdown-item" href="/tests/create">Create</a>
                        <a class="dropdown-item" href="/tests/edit">Edit</a>
                    </sec:authorize>
                    <sec:authorize access="hasAnyAuthority('USER')">
                        <a class="dropdown-item" href="<%=application.getContextPath()%>/choose/chooseTest">Pass</a>
                    </sec:authorize>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=application.getContextPath()%>/topics"> Topics </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Profile </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-03">
                    <a class="dropdown-item" href="/users/profile">My account</a>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <a class="dropdown-item" href="<%=application.getContextPath()%>/users">All accounts</a>
                        <a class="dropdown-item" href="<%=application.getContextPath()%>/users/create">Create</a>
                    </sec:authorize>
                    <a class="dropdown-item" href="<%=application.getContextPath()%>/users/logout">Log out</a>
                </div>
            </li>
            <sec:authorize access="hasAuthority('ADMIN')">
                <c:if test="${countBlockedUsers > 0}">
                    <li class="nav-item text-success">
                        <a href="<%=application.getContextPath()%>/users" class="nav-link waves-effect waves-light text-success">
                            ${countBlockedUsers}
                            <i class="fas fa-envelope"></i>
                        </a>
                    </li>
                </c:if>
            </sec:authorize>
        </ul>
    </div>
    </sec:authorize>
</nav>
