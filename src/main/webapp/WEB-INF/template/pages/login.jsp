<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">Please, sign in</h2>
    </div>
</div>
<div class="row d-flex justify-content-center mt-3">
    <div class="col-md-6">
        <div class="card">
            <div class="card-body pl-5 pr-5">
                <form action="<%=application.getContextPath()%>/users/login" method="post">
                    <div class="md-form">
                        <i class="fa fa-user prefix grey-text"></i>
                        <input type="text" id="login" name="login" class="form-control" autocomplete="off" required>
                        <label for="login" class="font-weight-light">Your login</label>
                    </div>
                    <div class="md-form">
                        <i class="fa fa-lock prefix grey-text"></i>
                        <input type="password" id="password" name="password" class="form-control" autocomplete="off" required>
                        <label for="password" class="font-weight-light">Your password</label>
                    </div>

                    <div class="text-center py-2 mt-3">
                        <button class="btn btn-success" name="submit" type="submit">Sign in</button>
                    </div>
                    <div class="text-center py-2 mt-3">
                        Haven't got an account? <a class="text-success" href="<%=application.getContextPath()%>/users/registration">Sign up!</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
