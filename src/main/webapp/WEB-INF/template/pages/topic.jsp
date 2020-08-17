<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">Please, <c:if test="${!empty topic.id}">update</c:if><c:if test="${empty topic.id}">create</c:if> topic</h2>
    </div>
</div>
<div class="row d-flex justify-content-center mt-3">
    <div class="col-md-12">
        <div class="card">
            <div class="card-body pl-5 pr-5">
                <form:form modelAttribute="topic" method="post" action="/topics">
                    <form:input path="id" id="id" type="hidden"/>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="md-form">
                                <form:input path="name" id="name"  class="form-control" autocomplete="off"/>
                                <label for="name" class="font-weight-light">Name</label>
                                <div class="text-danger font-weight-light font-small"><form:errors path="name"/></div>
                                <div class="text-danger font-weight-light font-small">${topicNameError}</div>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="md-form">
                                <form:input path="description" id="description" class="form-control" autocomplete="off"/>
                                <label for="description" class="font-weight-light">Description</label>
                                <div class="text-danger font-weight-light font-small"><form:errors path="description"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 ">
                            <c:if test="${!empty topic.id}">
                                <div class="text-left py-2">
                                    <c:if test="${topic.testAmount > 0}">
                                        This topic contains
                                        <span class="badge badge-success badge-pill">${topic.testAmount}</span>
                                        test(s) that
                                        <c:if test="${topic.amountPassedTests > 0}">
                                            have been passed <span class="badge badge-success badge-pill">${topic.amountPassedTests}</span> times
                                        </c:if>
                                        <c:if test="${topic.amountPassedTests == 0}">
                                            have never been passed
                                        </c:if>
                                    </c:if>
                                    <c:if test="${topic.testAmount == 0}">
                                        There are no tests for this topic
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                        <div class="col-md-6">
                            <div class="text-right py-2">
                                <button class="btn btn-success" name="submit" type="submit"><c:if test="${!empty topic.id}">Update</c:if><c:if test="${empty topic.id}">Create</c:if></button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="row mt-5">
    <div class="col">
        <h2 class="title h2 text-center">List of Topics</h2>
    </div>
</div>
<c:if test="${!empty listTopics}">
    <div class="row">
        <div class="col">
            <table class="table table-hover table-sm">
                <thead class="special-color-dark white-text">
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th class="text-center">Passed tests</th>
                    <th class="text-center">Tests</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listTopics}" var="topic">
                    <tr>
                        <td>${topic.name}</td>
                        <td>${topic.description}</td>
                        <td class="text-center">
                            <c:if test="${topic.amountPassedTests > 0}">
                                <span class="badge badge-success badge-pill">${topic.amountPassedTests}</span>
                            </c:if>
                        </td>
                        <td class="text-center">
                            <c:if test="${topic.testAmount > 0}">
                                <span class="badge badge-success badge-pill">${topic.testAmount}</span>
                            </c:if>
                        </td>
                        <td><a href="<%=application.getContextPath()%>/topics/edit/${topic.id}"><i class="fas fa-edit"></i></a></td>
                        <td><a href="<%=application.getContextPath()%>/topics/delete/${topic.id}"><i class="fas fa-trash-alt"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
<c:if test="${empty listTopics}">
    <div class="row mt-3">
        <div class="col">
            <h4 class="title h4-responsive text-center text-success">Sorry! Topic list is empty</h4>
        </div>
    </div>
</c:if>
<div class="mt-3 text-right">
    <a href="<%=application.getContextPath()%>/" class="text-success">Back to main page</a>
</div>
