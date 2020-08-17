<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row justify-content-center mt-5">
    <div class="col-md-12 text-center">
        <span class="display-1 d-block text-success">404</span>
        <div class="mb-4 lead">The page you are looking for was not found.</div>
        <a href="<%=application.getContextPath()%>/" class="btn btn-link">Back to Home</a>
    </div>
</div>
