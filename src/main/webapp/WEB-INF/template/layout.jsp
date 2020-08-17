<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><tiles:getAsString name="title" /></title>
    <link rel="icon" href="/img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/mdb.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/addons/datatables.min.css">
</head>
<body>
    <div id="hellopreloader">
        <div id="hellopreloader_preload"></div>
    </div>
    <tiles:insertAttribute name="navbar" />
    <div class="container">
        <c:if test="${!empty report}">
        <div class="row mt-3">
            <div class="col">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong><i class="fas fa-info-circle"></i></strong>
                    ${report}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
        </c:if>
        <tiles:insertAttribute name="content" />
    </div>
        <tiles:insertAttribute name="footer" />
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/popper.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/mdb.min.js"></script>
    <script src="/js/addons/datatables.min.js"></script>
    <script src="/js/script.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#dtBasicExample').DataTable();
            $('.dataTables_length').addClass('bs-select');
        });
    </script>
</body>
</html>
