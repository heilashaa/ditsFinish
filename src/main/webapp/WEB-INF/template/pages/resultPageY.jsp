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
                <th> Question</th>
                <th> Correct</th>
                <th> Recommend literature</th>
                <th> Recommend literature link</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${statistic}" var="stat">
                <tr>
                    <td> ${stat.getQuestion().getDescription()}</td>
                    <td> ${stat.isCorrect()}</td>
                    <td>
                        <table class="table table-hover table-sm">
                            <tbody>
                            <c:forEach
                                    items="${stat.getQuestion().getLiterature()}"
                                    var="literature">
                                <tr>
                                    <th>
                                            ${literature.getDescription()}
                                    </th>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <table class="table table-hover table-sm">
                            <tbody>
                            <c:forEach
                                    items="${stat.getQuestion().getLiterature()}"
                                    var="literature">
                                <c:forEach
                                        items="${literature.getLinks()}"
                                        var="link">
                                    <tr>
                                        <th>
                                                ${link.getLink()}
                                        </th>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                        <%--<td> ${stat.getQuestionY().getLiteratureY().getDescription()}</td>
                    <td> ${stat.getQuestionY().getLiteratureY().getLinkY().getLink()}</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<form action="/main">
    <%--<input type="submit" value="Home page">--%>
    <div>
        <button class="btn btn-success" name="submit" type="submit">home
            page</button>
    </div>
</form>