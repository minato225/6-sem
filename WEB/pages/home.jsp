<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bsu.zlatamigas.webLab67Tomcat9.model.entity.UserStatus" %>

<fmt:setLocale value="${sessionScope.localisation}" scope="session"/>
<fmt:setBundle basename="localisation.localisedtext"/>

<!DOCTYPE html>
<html lang="${sessionScope.localisation}">
<head>
    <title><fmt:message key="title.homepage"/></title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <c:choose>
        <c:when test="${sessionScope.user.status == UserStatus.ADMIN}">
            <div class="container">
                <form class="form-inline mt-2 mt-lg-0" action="controller">
                    <input type="hidden" name="command" value="find_delayed_orders">
                    <label class="mr-sm-2"><fmt:message key="home.admin.finddelayedorders"/></label>
                    <input type="submit" name="submit" class="btn btn-sm btn-outline-info my-2 my-sm-0" value="&#10140;">
                </form>
                <form class="form-inline mt-2 mt-lg-0" action="controller">
                    <input type="hidden" name="command" value="find_delivery_before_date_orders">
                    <label class="mr-sm-3"><fmt:message key="home.admin.finddeliverybeforedateorders"/></label>
                    <input class="form-control mr-sm-3" type="date" name="before_date" value="2022-04-18">
                    <input type="submit" name="submit" class="btn btn-sm btn-outline-info my-3 my-sm-0" value="&#10140;">
                </form>
            </div>
        </c:when>
        <c:when test="${sessionScope.user.status == UserStatus.CLIENT}">
            <div class="container">
                <form class="form-inline mt-2 mt-lg-0" action="controller">
                    <input type="hidden" name="command" value="find_client_orders">
                    <label class="mr-sm-2"><fmt:message key="home.admin.findclientorders"/></label>
                    <input type="submit" name="submit" class="btn btn-sm btn-outline-info my-2 my-sm-0" value="&#10140;">
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container-fluid text-center">
                <h1><fmt:message key="home.guest.welcome"/></h1>
            </div>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>
