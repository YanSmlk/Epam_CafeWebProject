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
    <fmt:message bundle="${local}" key="page.clientProfile.title" var="title" />
    <fmt:message bundle="${local}" key="page.clientProfile.profile" var="profile" />
    <fmt:message bundle="${local}" key="page.clientPorfile.hello" var="hello" />
    <fmt:message bundle="${local}" key="page.clientProfile.myOrders" var="myOrders" />
    <fmt:message bundle="${local}" key="page.clientProfile.balance" var="balance" />
    <fmt:message bundle="${local}" key="page.clientProfile.settings" var="settiings" />
    <fmt:message bundle="${local}" key="page.clientProfile.settingsTitle" var="settiingsTitle" />
    <fmt:message bundle="${local}" key="page.clientProfile.myOrdersTitle" var="myOrdersTitle" />
    <fmt:message bundle="${local}" key="page.clientProfile.balanceTitle" var="balanceTitle" />
    <fmt:message bundle="${local}" key="page.clientProfile.orderNum" var="orderNum" />
    <fmt:message bundle="${local}" key="page.clientProfile.gettingTime" var="gettingTime" />
    <fmt:message bundle="${local}" key="page.clientProfile.totalPrice" var="totalPrice" />
    <fmt:message bundle="${local}" key="page.clientProfile.status" var="status" />
    <fmt:message bundle="${local}" key="page.clientProfile.details" var="details" />
    <fmt:message bundle="${local}" key="page.clientProfile.drinksOrdered" var="drinksOrdered" />
    <fmt:message bundle="${local}" key="page.clientProfile.dessertsOrdered" var="dessertsOrdered" />
    <fmt:message bundle="${local}" key="page.clientProfile.mealsOrdered" var="mealsOrdered" />
    <fmt:message bundle="${local}" key="page.clientProfile.balancePoints" var="balancePoints" />
    <fmt:message bundle="${local}" key="page.clientProfile.balanceMoney" var="balanceMoney" />
    <fmt:message bundle="${local}" key="page.clientProfile.buttonMoneyMore" var="buttMoneyMore" />
    <title>${title}</title>
    <script>
        function setCommVal(val)
        {
            document.getElementById('coomId').value = val;
            return true;
        }
    </script>
</head>
<body>

<form action="FrontController" method="post">

    <input type="hidden" id="coomId" name="command" value="">
    <c:set var="lastPage" value="/jsp/client/client.jsp" scope="session" />
    <jsp:include page="/jsp/header/headerClient.jsp"/>

    <div class="container">
        <div class="page-header">
            <h1>${profile}</h1>
        </div>
        <h3>  <p class="bg-success"> ${hello}${sessionScope.login}</p></h3>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#panel1">${myOrders}</a></li>
            <li><a data-toggle="tab" href="#panel2">${balance}</a></li>
        </ul>

        <div class="tab-content">
            <div id="panel1" class="tab-pane fade in active">
                <h3>${myOrdersTitle}</h3>

                <div class="container">
                    <div class="panel-group" id="accordion">
                        <c:forEach var="order" items="${orders}" >
                            <div class="panel panel-default">
                            <div class="panel-heading">
                                <h5 class="panel-title">
                                    <c:choose>
                                        <c:when test = "${order.status== 'Current'}">
                                            <p class="bg-warning"><strong>${status}</strong> ${order.status}</p>
                                        </c:when>
                                        <c:when test = "${order.status== 'Finished'}">
                                            <p class="bg-success"> <strong>${status}</strong> ${order.status}</p>
                                        </c:when>
                                        <c:when test = "${order.status== 'Canceled'}">
                                            <p class="bg-danger"><strong>${status}</strong> ${order.status}</p>
                                        </c:when>
                                    </c:choose>
                                    <p> <strong>${orderNum}</strong>${order.id}</p>
                                    <p> <strong>${gettingTime}</strong> ${order.gettingTime}</p>
                                    <p> <strong>${totalPrice}</strong>  ${order.totalPrice}$</p>
                                    <p> <a data-toggle="collapse" data-parent="#accordion" href="#${order.id}" class="text-primary">${details}</a></p>
                                </h5>
                            </div>
                            <div id="${order.id}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <dl>
                                        <c:if test="${not empty order.drinkArr}">
                                            <dt>${drinksOrdered}</dt>
                                            <c:forEach var="drink" items="${order.drinkArr}" >
                                                <dd>- ${drink.name}, ${drink.price}$</dd>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty order.dessertArr}">
                                            <dt>${dessertsOrdered}</dt>
                                            <c:forEach var="dessert" items="${order.dessertArr}" >
                                                <dd>- ${dessert.name}, ${dessert.price}$</dd>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty order.mealArr}">
                                            <dt>${mealsOrdered}</dt>
                                            <c:forEach var="meal" items="${order.mealArr}" >
                                                <dd>- ${meal.name}, ${meal.price}$</dd>
                                            </c:forEach>
                                        </c:if>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div id="panel2" class="tab-pane fade">
                <div class="container">
                   <h3>${balanceTitle}</h3>
                   <p> <strong>${balancePoints}</strong>  ${requestScope.points}</p>
                   <p> <strong>${balanceMoney}</strong>  ${requestScope.money}$</p>
                    <hr>
                   <button input type="submit" onclick="setCommVal('ADD_MONEY')" class="btn btn-default">${buttMoneyMore}</button>
                </div>
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
