<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row justify-content-center mt-5">
    <div class="col-md-12 text-center">
        <span class="display-1 d-block text-success">500</span>
        <div class="mb-4 lead">Sorry, internal server error.</div>
        <a href="<%=application.getContextPath()%>/" class="btn btn-link">Back to Home</a>
    </div>
</div>
<div class="accordion" id="accordion01">
    <div class="card z-depth-0 bordered">
        <div class="card-header p-2 bg-white" id="heading01">
            <div class="row p-0 m-0">
                <div class="col-9 p-0 m-0">
                    <button class="btn btn-link collapsed p-0 m-o" type="button" data-toggle="collapse"
                            data-target="#collapse01" aria-expanded="false" aria-controls="collapse01">
                        <h5 class="p-0 m-0">Stack Trace</h5>
                    </button>
                </div>
            </div>
        </div>
        <div id="collapse01" class="collapse" aria-labelledby="heading01" data-parent="#accordion01">
            <div class="card-body border border-0 h6-responsive">
                <c:forEach items="${exception.getStackTrace()}" var="stackTraceElement">
                    ${stackTraceElement.toString()}
                </c:forEach>
            </div>
        </div>
    </div>
</div>


