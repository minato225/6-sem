<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.localisation}" scope="session"/>
<fmt:setBundle basename="localisation.localisedtext"/>

<!DOCTYPE html>
<html lang="${sessionScope.localisation}">
<head>
    <title><fmt:message key="title.addproduct"/></title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">

    <form action="controller" method="POST">

        <input type="hidden" name="command" value="finish_add_product">

        <div class="form-group">
            <div class="text-danger">
                <c:if test="${requestScope.form_invalid.product_name!=null}">
                    <fmt:message key="${requestScope.form_invalid.product_name}"/>
                </c:if>
            </div>
            <label for="productName"><fmt:message key="addproduct.label.productname"/></label>
            <input name="product_name" type="text" class="form-control" id="productName" value="${param.product_name}">
        </div>

        <div class="form-group">
            <div class="text-danger">
                <c:if test="${requestScope.form_invalid.product_cost!=null}">
                    <fmt:message key="${requestScope.form_invalid.product_cost}"/>
                </c:if>
            </div>
            <label for="productCost"><fmt:message key="addproduct.label.productcost"/></label>
            <input name="product_cost" type="number" class="form-control" id="productCost" value="${param.product_cost}" step="0.01">
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="addproduct.saveproduct"/></button>
    </form>

    <hr>
</div>

</body>
</html>
