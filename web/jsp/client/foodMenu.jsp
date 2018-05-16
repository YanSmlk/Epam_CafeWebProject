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
    <fmt:message bundle="${local}" key="page.foodMenu.meal" var="meal" />
    <fmt:message bundle="${local}" key="page.foodMenu.desserts" var="desserts" />
    <fmt:message bundle="${local}" key="page.foodMenu.drinks" var="drinks" />
    <fmt:message bundle="${local}" key="page.foodMenu.basket" var="basket" />
    <fmt:message bundle="${local}" key="page.foodMenu.basketTitle" var="basketTitle" />
    <fmt:message bundle="${local}" key="page.foodMenu.basketTitleEmpty" var="basketTitleEmpty" />
    <fmt:message bundle="${local}" key="page.foodMenu.totalPrice" var="totalPrice" />
    <fmt:message bundle="${local}" key="page.foodMenu.inPoints" var="inPoints" />
    <fmt:message bundle="${local}" key="page.foodMenu.elementPrice" var="elPrice" />
    <fmt:message bundle="${local}" key="page.foodMenu.selectAmount" var="selectAmount" />
    <fmt:message bundle="${local}" key="page.foodMenu.addToOrder" var="buttAddToOrder" />
    <fmt:message bundle="${local}" key="page.foodMenu.pointsWillGet" var="pointsWillGet" />
    <fmt:message bundle="${local}" key="page.clientProfile.drinksOrdered" var="drinksOrdered" />
    <fmt:message bundle="${local}" key="page.clientProfile.dessertsOrdered" var="dessertsOrdered" />
    <fmt:message bundle="${local}" key="page.clientProfile.mealsOrdered" var="mealsOrdered" />
    <fmt:message bundle="${local}" key="page.foodMenu.chooseTime" var="chooseTime" />
    <fmt:message bundle="${local}" key="page.foodMenu.chooseTimeInfo" var="chooseTimeInfo" />
    <fmt:message bundle="${local}" key="page.foodMenu.buttConfirmOrder" var="confirmOrder" />
    <fmt:message bundle="${local}" key="page.foodMenu.buttCancelOrder" var="cancelOrder" />
    <fmt:message bundle="${local}" key="page.foodMenu.choosePayType" var="chosePayType" />
    <fmt:message bundle="${local}" key="page.foodMenu.personalAcc" var="persAcc" />
    <fmt:message bundle="${local}" key="page.foodMenu.bonusPoints" var="bonus" />
    <fmt:message bundle="${local}" key="page.foodMenu.whenRec" var="whenRec" />
    <fmt:message bundle="${local}" key="page.foodMenu.badTime" var="badTimeInfo" />
    <fmt:message bundle="${local}" key="page.foodMenu.badPayType" var="badPayTypeInfo" />
    <fmt:message bundle="${local}" key="page.foodMenu.notEnoughtForPay" var="notEnoughtToPay" />
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
    <c:set var="lastPage" value="/jsp/client/foodMenu.jsp" scope="session" />
    <jsp:include page="/jsp/header/headerClient.jsp"/>

    <c:choose>
        <c:when test = "${not empty requestScope.badTime}">
            <c:set var="classTypeMeal" value="tab-pane fade" scope="page" />
            <c:set var="classTypeBasket" value="tab-pane fade in active" scope="page" />
            <c:set var="liClassMeal" value="" scope="page" />
            <c:set var="liClassBasket" value="active" scope="page" />
        </c:when>
        <c:when test = "${not empty requestScope.badPayType}">
            <c:set var="classTypeMeal" value="tab-pane fade" scope="page" />
            <c:set var="classTypeBasket" value="tab-pane fade in active" scope="page" />
            <c:set var="liClassMeal" value="" scope="page" />
            <c:set var="liClassBasket" value="active" scope="page" />
        </c:when>
        <c:when test = "${not empty requestScope.notEnought}">
            <c:set var="classTypeMeal" value="tab-pane fade" scope="page" />
            <c:set var="classTypeBasket" value="tab-pane fade in active" scope="page" />
            <c:set var="liClassMeal" value="" scope="page" />
            <c:set var="liClassBasket" value="active" scope="page" />
        </c:when>
        <c:otherwise>
            <c:set var="classTypeMeal" value="tab-pane fade in active" scope="page" />
            <c:set var="classTypeBasket" value="tab-pane fade" scope="page" />
            <c:set var="liClassMeal" value="active" scope="page" />
            <c:set var="liClassBasket" value="" scope="page" />
        </c:otherwise>
    </c:choose>

    <div class="container">
        <div class="page-header">
            <h1>
                ${ourMenuHeader}
            </h1>
        </div>

        <ul class="nav nav-tabs">
            <li class="${liClassMeal}"><a data-toggle="tab" href="#panel1">${meal}</a></li>
            <li><a data-toggle="tab" href="#panel2">${desserts}</a></li>
            <li><a data-toggle="tab" href="#panel3">${drinks}</a></li>
            <li class="${liClassBasket}"><a data-toggle="tab" href="#panel4">${basket}</a></li>
        </ul>

        <div class="tab-content">
            <div id="panel1" class=" ${classTypeMeal}">
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

                        <label>${selectAmount}</label>
                        <div class="amountInput">
                            <select name="meal|${meal.id}" class="form-control">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </select>
                        </div>
                        <button input type="submit" onclick="setCommVal('ADD_TO_ORDER')"
                                name="foodElem" value="meal|${meal.id}" class="btn btn-default">${buttAddToOrder}</button>
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

                                <label>${selectAmount}</label>
                                <div class="amountInput">
                                    <select name="dessert|${dessert.id}" class="form-control">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                    </select>
                                </div>
                                <button input type="submit" onclick="setCommVal('ADD_TO_ORDER')"
                                        name="foodElem" value="dessert|${dessert.id}" class="btn btn-default">${buttAddToOrder}</button>
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

                            <label>${selectAmount}</label>
                            <div class="amountInput">
                                <select name="drink|${drink.id}" class="form-control">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                            </div>
                            <button input type="submit" onclick="setCommVal('ADD_TO_ORDER')"
                                    name="foodElem" value="drink|${drink.id}" class="btn btn-default">${buttAddToOrder}</button>

                        </div>
                    </div>
                    <hr>
                </c:forEach>

            </div>

            <div id="panel4" class="${classTypeBasket}">

                <c:if test="${not empty sessionScope.basket}">
                    <h3>${basketTitle}</h3>
                    <dl>
                    <c:if test="${not empty sessionScope.basket.mealArr}">
                        <dt>${mealsOrdered}</dt>
                        <c:forEach var="meal" items="${sessionScope.basket.mealArr}" >
                            <dd>- ${meal.name}, ${meal.price}$</dd>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty sessionScope.basket.dessertArr}">
                        <dt>${dessertsOrdered}</dt>
                        <c:forEach var="dessert" items="${sessionScope.basket.dessertArr}" >
                            <dd>- ${dessert.name}, ${dessert.price}$</dd>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty sessionScope.basket.drinkArr}">
                        <dt>${drinksOrdered}</dt>
                        <c:forEach var="drink" items="${sessionScope.basket.drinkArr}" >
                            <dd>- ${drink.name}, ${drink.price}$</dd>
                        </c:forEach>
                    </c:if>
                        --------------------------------------------
                        <p> <strong>${totalPrice}</strong>
                        ${sessionScope.basket.totalPrice}$(${4*sessionScope.basket.totalPrice} ${inPoints})</p>

                        <fmt:setLocale value="en_US" />
                        <fmt:formatNumber var="pointsGet"  value="${sessionScope.basket.totalPrice/4}" maxFractionDigits="0" />
                        <p> <strong>${pointsWillGet}</strong> ${pointsGet}</p>
                        <fmt:setLocale value="${language}" />

                        <div class="form-group">
                            <label for="timeId" class="control-label">${chooseTime}</label>
                            <input id="timeId" type="time" name="resTime" min="9:00" max="22:00" value="${requestScope.resTime}" >
                            <span class="validity"></span><br>
                            <label for="timeId" class="control-label"><h5>- ${chooseTimeInfo}</h5></label><br>

                            <label class="control-label">${chosePayType}</label>
                            <label class="radio-inline">
                                <input type="radio" name="payType" value="personalAcc">${persAcc}
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payType" value="bonus">${bonus}
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="payType" value="whenRec">${whenRec}
                            </label><br>

                            <div class="form-group">
                                <c:if test = "${not empty requestScope.badTime}">
                                    <p class="text-danger"> ${badTimeInfo}</p>
                                </c:if>
                                <c:if test = "${not empty requestScope.badPayType}">
                                    <p class="text-danger"> ${badPayTypeInfo}</p>
                                </c:if>
                                <c:if test = "${not empty requestScope.notEnought}">
                                    <p class="text-danger">  ${notEnoughtToPay}</p>
                                </c:if>
                            </div>

                            <button input type="submit" onclick="setCommVal('CREATE_NEW_ORDER')" class="btn btn-default">${confirmOrder}</button>
                            <button input type="submit" onclick="setCommVal('CLEAR_BASKET')" class="btn btn-warning"> ${cancelOrder}</button>
                        </div>

                    </dl>
                </c:if>
                <c:if test="${empty sessionScope.basket}">
                    <h3>${basketTitleEmpty}</h3>
                </c:if>
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
