<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">List of Tests</h2>
    </div>
</div>
<div class="row d-flex justify-content-center mt-3">
    <div class="col-md-12">
        <div class="card">
            <div class="card-body pl-5 pt-5 pr-5">
                <%--FIRST ACCORDING START!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                <c:if test="${!empty listTopicsWithTests}">
                    <div class="accordion" id="accordion01">
                        <c:forEach items="${listTopicsWithTests}" var="topicWithTests">
                            <div class="card z-depth-0 bordered">
                                <div class="card-header p-2 bg-white" id="heading${topicWithTests.id}">
                                    <div class="row p-0 m-0">
                                        <div class="col-9 p-0 m-0">
                                            <button class="btn btn-link collapsed p-0 m-o" type="button" data-toggle="collapse"
                                                    data-target="#collapse${topicWithTests.id}" aria-expanded="false" aria-controls="collapse${topicWithTests.id}">
                                                <h5 class="p-0 m-0">${topicWithTests.name}</h5>
                                            </button>
                                        </div>
                                        <div class="col-3  p-0 m-0 text-right h5">
                                            <c:if test="${topicWithTests.testAmount > 0}">
                                                <span class="badge badge-success badge-pill">${topicWithTests.testAmount} tests</span>
                                            </c:if>
                                            <c:if test="${topicWithTests.testAmount == 0}">
                                                <span class="badge badge-danger badge-pill">no tests</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse${topicWithTests.id}" class="collapse" aria-labelledby="heading${topicWithTests.id}" data-parent="#accordion01">
                                    <div class="card-body border border-0">
                                        <%--SECOND ACCORDING START!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                                        <c:if test="${!empty topicWithTests.tests}">
                                            <div class="accordion" id="accordion02">
                                                <c:forEach items="${topicWithTests.tests}" var="test">
                                                    <div class="card z-depth-0">
                                                        <div class="card-header p-2 bg-white" id="headingTest${test.id}">
                                                            <div class="row p-0 m-0">
                                                                <div class="col-9 p-0 m-0">
                                                                    <button class="btn btn-link collapsed p-0 m-o" type="button" data-toggle="collapse"
                                                                            data-target="#collapseTest${test.id}" aria-expanded="false" aria-controls="collapseTest${test.id}">
                                                                        <h5 class="p-0 m-0 text-black-50">${test.name} <span class="text-lowercase">(${test.description})</span></h5>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div id="collapseTest${test.id}" class="collapse" aria-labelledby="headingTest${test.id}" data-parent="#accordion02">
                                                            <div class="card-body border border-0">
                                                                <%--SECOND ACCORDING BODY START!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                                                                <c:if test="${!empty test.questions}">
                                                                    <ol>
                                                                        <c:forEach items="${test.questions}" var="question">
                                                                            <li><h5>${question.description}</h5></li>
                                                                            <ul>
                                                                                <c:forEach items="${question.answers}" var="answer">
                                                                                    <li class="list-unstyled">
                                                                                        <c:if test="${answer.correct}">
                                                                                            <i class="far fa-check-circle"></i>
                                                                                        </c:if>
                                                                                        <c:if test="${!answer.correct}">
                                                                                            <i class="far fa-circle"></i>
                                                                                        </c:if>
                                                                                            ${answer.description}
                                                                                    </li>
                                                                                </c:forEach>
                                                                            </ul>
                                                                            <h5>Literature:</h5>
                                                                            <c:if test="${empty question.literature}">
                                                                                none
                                                                            </c:if>
                                                                            <c:if test="${!empty question.literature}">
                                                                                <ul>
                                                                                    <c:forEach items="${question.literature}" var="literature">
                                                                                        <c:forEach items="${literature.links}" var="link">
                                                                                            <li class="list-unstyled"><a class="text-success" href="${link.link}">${literature.description}</a></li>
                                                                                        </c:forEach>
                                                                                    </c:forEach>
                                                                                </ul>
                                                                            </c:if>
                                                                            <hr>
                                                                        </c:forEach>
                                                                    </ol>
                                                                </c:if>
                                                                <c:if test="${empty test.questions}">
                                                                    <h6>There are no questions for this test</h6>
                                                                </c:if>
                                                                <%--SECOND ACCORDING BODY FINISH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                        <c:if test="${empty topicWithTests.tests}">
                                            <h6>There are no tests for this topic</h6>
                                        </c:if>
                                        <%--SECOND ACCORDING FINISH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <%--FIRST ACCORDING FINISH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
                <c:if test="${empty listTopicsWithTests}">
                    <div class="row mt-3">
                        <div class="col">
                            <h4 class="title h4-responsive text-center text-success">Sorry! Topic & test list is empty</h4>
                        </div>
                    </div>
                </c:if>
                <div class="mt-3 text-right">
                    <a href="<%=application.getContextPath()%>/tests/create" class="btn btn-success">Create test</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="mt-3 text-right pr-2">
    <a href="<%=application.getContextPath()%>/" class="text-success">Back to main page</a>
</div>
