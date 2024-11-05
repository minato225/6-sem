<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bsu.zlatamigas.webLab67Tomcat9.model.entity.UserStatus" %>

<fmt:setLocale value="${sessionScope.localisation}" scope="session"/>
<fmt:setBundle basename="localisation.localisedtext"/>

<!DOCTYPE html>
<html lang="${sessionScope.localisation}">
<head>
    <title><fmt:message key="title.products"/></title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

</head>
<body>

<jsp:include page="header.jsp"/>

<c:if test="${sessionScope.user.status == UserStatus.ADMIN}">
    <div class="container">
        <form action="controller" method="GET">
            <input type="hidden" name="command" value="start_add_product">
            <button type="submit" class="btn btn-primary"><fmt:message key="products.addproduct"/></button>
        </form>
    </div>
</c:if>


<div class="container">
    <div class="accordion" id="allProducts">

    <c:forEach items="${sessionScope.products}" var="product">

        <div class="card">
            <div class="card-header" id="heading${product.id}">

                <div class="row justify-content-between">
                    <div class="col">
                        <h5>${product.name}</h5>
                    </div>
                    <div class="col col-auto">
                        <button class="btn" type="button" data-toggle="collapse"
                                data-target="#collapse${product.id}" aria-expanded="true"
                                aria-controls="collapse${product.id}"><i class="fas fa-angle-down"></i></button>
                    </div>
                </div>
            </div>

            <div id="collapse${product.id}" class="collapse" aria-labelledby="heading${product.id}"
                 data-parent="#allProducts">
                <div class="card-body">
                    <h6 class="card-subtitle mb-2 text-muted">${product.price}</h6>
                    <h6 class="card-text"><fmt:message key="products.productarticul"/>${product.id}</h6>
                </div>
            </div>

        </div>

    </c:forEach>

    </div>
</div>
</body>
</html>
