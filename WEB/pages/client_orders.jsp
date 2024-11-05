<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.localisation}" scope="session"/>
<fmt:setBundle basename="localisation.localisedtext"/>

<!DOCTYPE html>
<html lang="${sessionScope.localisation}">
<head>
    <title><fmt:message key="title.clientorders"/></title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <h1 class="display-4"><fmt:message key="title.clientorders"/></h1>
    <hr class="my-4">

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col"><fmt:message key="ordertable.orderid"/></th>
            <th scope="col"><fmt:message key="ordertable.deliverydate"/></th>
            <th scope="col"><fmt:message key="ordertable.deliverystatus"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="order" items="${sessionScope.client_orders}">
            <tr>
                <td scope="row">${order.id}</td>
                <td>${order.deliveryDate}</td>
                <td>${order.deliveryStatus}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
