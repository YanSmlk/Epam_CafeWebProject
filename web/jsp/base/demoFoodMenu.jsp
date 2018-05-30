<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/mytaglib.tld" %>
<html>
<head>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/common.css" rel="stylesheet">

    <c:set var="language" value="${sessionScope.lang}"/>
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="property.text" var="local"/>
    <fmt:message bundle="${local}" key="page.foodMenu.title" var="title" />
    <fmt:message bundle="${local}" key="page.foodMenu.ourMenu" var="ourMenuHeader" />
    <fmt:message bundle="${local}" key="page.demoFood.warn" var="warn" />
    <fmt:message bundle="${local}" key="page.foodMenu.meal" var="meal" />
    <fmt:message bundle="${local}" key="page.foodMenu.desserts" var="desserts" />
    <fmt:message bundle="${local}" key="page.foodMenu.drinks" var="drinks" />
    <fmt:message bundle="${local}" key="page.foodMenu.elementPrice" var="elPrice" />

    <title>${title}</title>

    <script>
        function setCommVal(val){
            document.getElementById('coomId').value = val;
            return true;
        }
    </script>
</head>

<body>

<form action="FrontController" method="post">

    <input type="hidden" id="coomId" name="command" value="">
    <c:set var="lastPage" value="/jsp/base/demoFoodMenu.jsp" scope="session" />

    <jsp:include page="/jsp/header/headerBase.jsp"/>

    <div class="container">
        <div class="page-header">
            <h1> ${ourMenuHeader} </h1>
        </div>

        <div class="alert alert-warning">
            <p >${warn}</p>
        </div>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#panel1">${meal}</a></li>
            <li><a data-toggle="tab" href="#panel2">${desserts}</a></li>
            <li><a data-toggle="tab" href="#panel3">${drinks}</a></li>
        </ul>

        <div class="tab-content">
            <div id="panel1" class="tab-pane fade in active">
                <h3>${meal}</h3>

                <c:forEach var="meal" items="${mealArr}" >
                    <div class="media">
                        <div class="media-left">
                            <img src="${meal.imgPath}" class="media-object" style="width:100px">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${meal.name}</h4>
                            <p>${meal.descr}</p>
                            <p> <strong>${elPrice}</strong>${meal.price}$ </p>
                        </div>
                    </div>
                    <hr>
                </c:forEach>

            </div>

            <div id="panel2" class="tab-pane fade">
                <div class="container">
                    <h3>${desserts}</h3>

                    <c:forEach var="dessert" items="${dessertArr}" >
                        <div class="media">
                            <div class="media-left">
                                <img src="${dessert.imgPath}" class="media-object" style="width:100px">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">${dessert.name}</h4>
                                <p>${dessert.descr}</p>
                                <p> <strong>${elPrice}</strong>${dessert.price}$ </p>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>

                </div>
            </div>

            <div id="panel3" class="tab-pane fade">
                <h3>${drinks}</h3>

                <c:forEach var="drink" items="${drinkArr}" >
                    <div class="media">
                        <div class="media-left">
                            <img src="${drink.imgPath}" class="media-object" style="width:100px">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${drink.name}</h4>
                            <p>${drink.descr}</p>
                            <p> <strong>${elPrice}</strong>${drink.price}$ </p>
                        </div>
                    </div>
                    <hr>
                </c:forEach>

            </div>
        </div>
    </div>

    <footer class="footer">
        <p class="text-muted"><mytag:getinfo/></p>
    </footer>

    <script src="static/js/jquery-3.2.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>

</form>

</body>
</html>