<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">Please, sign up</h2>
    </div>
</div>
<div class="row d-flex justify-content-center mt-3">
    <div class="col-md-6">
        <div class="card">
            <div class="card-body pl-5 pr-5">
                <form:form modelAttribute="user" method="post" action="/users/registration" >
                    <div class="md-form">
                        <i class="fa fa-user prefix grey-text"></i>
                        <form:input path="firstName" id="firstName" class="form-control" autocomplete="off"/>
                        <label for="firstName" class="font-weight-light">Your name</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="firstName"/></div>
                    </div>
                    <div class="md-form">
                        <i class="fa fa-user prefix grey-text"></i>
                        <form:input path="middleName" id="middleName" class="form-control" autocomplete="off"/>
                        <label for="middleName" class="font-weight-light">Your middle name</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="middleName"/></div>
                    </div>
                    <div class="md-form">
                        <i class="fa fa-user prefix grey-text"></i>
                        <form:input path="lastName" id="lastName" class="form-control" autocomplete="off"/>
                        <label for="lastName" class="font-weight-light">Your surname</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="lastName"/></div>
                    </div>
                    <div class="md-form">
                        <i class="fa fa-user prefix grey-text"></i>
                        <form:input path="login" id="login" class="form-control" autocomplete="off"/>
                        <label for="login" class="font-weight-light">Your login</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="login"/></div>
                        <div class="text-danger font-weight-light font-small">${loginError}</div>
                    </div>
                    <div class="md-form">
                        <i class="fas fa-envelope prefix grey-text"></i>
                        <form:input path="email" id="email" class="form-control" autocomplete="off"/>
                        <label for="email" class="font-weight-light">Your email</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="email"/></div>
                    </div>
                    <div class="md-form">
                        <i class="fa fa-lock prefix grey-text"></i>
                        <form:input path="password" type="password" id="password" class="form-control" autocomplete="off"/>
                        <label for="password" class="font-weight-light">Your password</label>
                        <div class="text-danger font-weight-light font-small"><form:errors path="password"/></div>
                    </div>
                    <div class="text-center py-2 mt-3">
                        <button class="btn btn-success" name="submit" type="submit">Sign up</button>
                    </div>
                    <div class="text-center py-2 mt-3">
                        Already have an account? <a class="text-success" href="<%=application.getContextPath()%>/users/login">Sign in!</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
