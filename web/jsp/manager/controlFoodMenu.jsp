<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/mytaglib.tld" %>
<html>
<head>

    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/common.css" rel="stylesheet">

    <c:set var="language" value="${sessionScope.lang}"/>
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="property.text" var="local"/>
    <fmt:message bundle="${local}" key="page.menuControl.title" var="title" />
    <fmt:message bundle="${local}" key="page.menuControl.header" var="menuControl" />
    <fmt:message bundle="${local}" key="page.foodMenu.meal" var="meal" />
    <fmt:message bundle="${local}" key="page.foodMenu.desserts" var="desserts" />
    <fmt:message bundle="${local}" key="page.foodMenu.drinks" var="drinks" />
    <fmt:message bundle="${local}" key="page.foodMenu.elementPrice" var="elPrice" />
    <fmt:message bundle="${local}" key="page.menuControl.Name" var="name" />
    <fmt:message bundle="${local}" key="page.menuControl.Descr" var="descr" />
    <fmt:message bundle="${local}" key="page.menuControl.Price" var="price" />
    <fmt:message bundle="${local}" key="page.menuControl.Apply" var="apply" />
    <fmt:message bundle="${local}" key="page.menuControl.changePic" var="changePic" />
    <fmt:message bundle="${local}" key="page.menuControl.choose" var="choose" />
    <fmt:message bundle="${local}" key="page.menuControl.badPic" var="badPict" />
    <fmt:message bundle="${local}" key="page.menuControl.badInfo" var="badInf" />
    <fmt:message bundle="${local}" key="page.menuControl.infoSaved" var="infoSaved" />
    <fmt:message bundle="${local}" key="page.menuControl.removeFromSale" var="removeFromSale" />
    <fmt:message bundle="${local}" key="page.menuControl.returnToSale" var="returnToSale" />
    <fmt:message bundle="${local}" key="page.menuControl.outOfStock" var="outOfStock" />
    <fmt:message bundle="${local}" key="page.menuControl.emptyOutOfStock" var="emptyOutOfStock" />
    <fmt:message bundle="${local}" key="page.menuControl.outOfStockHeader" var="outHeader" />
    <fmt:message bundle="${local}" key="page.menuControl.addMealOpen" var="addMealOpen" />
    <fmt:message bundle="${local}" key="page.menuControl.addDessertOpen" var="addDessertOpen" />
    <fmt:message bundle="${local}" key="page.menuControl.addDrinkOpen" var="addDrinkOpen" />
    <fmt:message bundle="${local}" key="page.menuControl.add" var="add" />
    <fmt:message bundle="${local}" key="page.menuControl.pic" var="pic" />
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

<form action="FrontController" method="post" accept-charset="utf-8">

    <input type="hidden" id="coomId" name="command" value="">
    <c:set var="lastPage" value="/jsp/manager/controlFoodMenu.jsp" scope="session" />

    <jsp:include page="/jsp/header/headerManager.jsp"/>

    <div class="container">
        <div class="page-header">
            <h1> ${menuControl} </h1>
        </div>

        <c:if test = "${not empty requestScope.badPic}">
            <h3> <p class="bg-danger"> ${badPict}</p></h3>
        </c:if>
        <c:if test = "${not empty requestScope.badInfo}">
           <h3> <p class="bg-danger"> ${badInf}</p></h3>
        </c:if>
        <c:if test = "${not empty requestScope.saved}">
            <h3> <p class="bg-success">${infoSaved}</p></h3>
        </c:if>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#panel1">${meal}</a></li>
            <li><a data-toggle="tab" href="#panel2">${desserts}</a></li>
            <li><a data-toggle="tab" href="#panel3">${drinks}</a></li>
            <li><a data-toggle="tab" href="#panel4">${outOfStock}</a></li>
        </ul>

        <div class="tab-content">
            <div id="panel1" class="tab-pane fade in active">
                <h3>${meal}</h3>

                <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#mealAdd">
                ${addMealOpen}</button>

                <div id="mealAdd" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="form-group">
                        <h5>${name}<span style="color:red">*</span></h5>
                        <input type="text" name="newMealName" value="${requestScope.newMealName}" maxlength="35"
                               class="form-control">

                        <h5>${descr}<span style="color:red">*</span></h5>
                        <textarea  name="newMealDescr" class="form-control" rows="2" maxlength="180" >${requestScope.newMealDescr}</textarea>

                        <h5>${price}<span style="color:red">*</span></h5>
                        <input type="text" name="newMealPrice" class="form-control" maxlength="3"
                               value="${requestScope.newMealPrice}"><br>
                        <label class="btn btn-default" for="newMealPicSelector">
                            <input type="file" accept=".jpg, .jpeg, .png"
                                   id="newMealPicSelector" style="display:none"
                                   onchange="$('#newMealPicInfo').html(this.files[0].name);
                                           document.getElementById('newMealPicInfoHidd').value = (this.files[0].name);">
                            ${pic}<span style="color:red">*</span>
                        </label>
                        <input type="hidden" id="newMealPicInfoHidd" name="newMealPicInfoHidd">
                        <span class='label label-default' id="newMealPicInfo"></span>
                        </div>
                        <button input type="submit" name="newFoodType" value="Meal" onclick="setCommVal('ADD_NEW_FOOD')" class="btn">
                                 ${add}</button>

                    </div>
                </div>

                <hr>
                <c:forEach var="meal" items="${mealArr}" >
                    <div class="media">
                        <div class="media-left">
                            <img src="${meal.imgPath}" class="media-object" style="width:100px">
                        </div>

                        <div class="media-body">
                            <div class="form-group">

                                <h5>${name}</h5>
                                <input type="text" name="namemeal${meal.id}" class="form-control" maxlength="35"
                                       value="${meal.name}">
                                <h5>${descr}</h5>
                                <textarea  name="descrmeal${meal.id}" class="form-control" rows="2" maxlength="180" >${meal.descr}</textarea>
                                <h5>${price}</h5>
                                <input type="text" name="pricemeal${meal.id}" class="form-control" maxlength="3"
                                       value="${meal.price}">

                                <h5> ${changePic}</h5>
                                <label class="btn btn-default" for="fileSelectorMeal${meal.id}">
                                    <input type="file" accept=".jpg, .jpeg, .png"
                                           id="fileSelectorMeal${meal.id}" style="display:none"
                                           onchange="$('#mealFileInfo${meal.id}').html(this.files[0].name);
                                                   document.getElementById('picmeal${meal.id}').value = (this.files[0].name);">
                                        ${choose}
                                </label>
                                <input type="hidden" id="picmeal${meal.id}" name="picmeal${meal.id}" value="">
                                <span class='label label-default' id="mealFileInfo${meal.id}"></span>
                                <hr>

                            </div>

                            <button input type="submit" onclick="setCommVal('CHANGE_FOOD_INFO')"
                                    name="foodElem" value="meal|${meal.id}" class="btn btn-default">${apply}</button>

                            <button input type="submit" onclick="setCommVal('CHANGE_AVAILABLE_STATUS')"
                                    name="foodElem" value="meal|${meal.id}" class="btn btn-warning">${removeFromSale}</button>
                        </div>
                    </div>
                    <hr>
                </c:forEach>
            </div>

            <div id="panel2" class="tab-pane fade">
                <div class="container">
                    <h3>${desserts}</h3>

                    <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#dessertAdd">
                        ${addDessertOpen}</button>

                    <div id="dessertAdd" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="form-group">
                                <h5>${name}<span style="color:red">*</span></h5>
                                <input type="text" name="newDessertName" value="${requestScope.newDessertName}" maxlength="35"
                                       class="form-control">

                                <h5>${descr}<span style="color:red">*</span></h5>
                                <textarea  name="newDessertDescr" class="form-control" rows="2" maxlength="180" >${requestScope.newDessertDescr}</textarea>

                                <h5>${price}<span style="color:red">*</span></h5>
                                <input type="text" name="newDessertPrice" class="form-control" maxlength="3"
                                       value="${requestScope.newDessertPrice}"><br>
                                <label class="btn btn-default" for="newDessertPicSelector">
                                    <input type="file" accept=".jpg, .jpeg, .png"
                                           id="newDessertPicSelector" style="display:none"
                                           onchange="$('#newDessertPicInfo').html(this.files[0].name);
                                           document.getElementById('newDessertPicInfoHidd').value = (this.files[0].name);">
                                    ${pic}<span style="color:red">*</span>
                                </label>
                                <input type="hidden" id="newDessertPicInfoHidd" name="newDessertPicInfoHidd">
                                <span class='label label-default' id="newDessertPicInfo"></span>
                            </div>
                            <button input type="submit" name="newFoodType" value="Dessert" onclick="setCommVal('ADD_NEW_FOOD')" class="btn">
                                ${add}</button>

                        </div>
                    </div>

                    <hr>
                    <c:forEach var="dessert" items="${dessertArr}" >
                        <div class="media">
                            <div class="media-left">
                                <img src="${dessert.imgPath}" class="media-object" style="width:100px">
                            </div>

                            <div class="media-body">
                                <div class="form-group">

                                    <h5>${name}</h5>

                                    <input type="text" name="namedessert${dessert.id}" class="form-control" maxlength="35"
                                           value="${dessert.name}">
                                    <h5>${descr}</h5>
                                    <textarea  name="descrdessert${dessert.id}" class="form-control" rows="2" maxlength="180" >${dessert.descr}</textarea>
                                    <h5>${price}</h5>
                                    <input type="text" name="pricedessert${dessert.id}" class="form-control" maxlength="3"
                                           value="${dessert.price}">

                                    <h5> ${changePic}</h5>
                                    <label class="btn btn-default" for="fileSelectorDessert${dessert.id}">
                                        <input type="file" accept=".jpg, .jpeg, .png"
                                               id="fileSelectorDessert${dessert.id}" style="display:none"
                                               onchange="$('#dessertFileInfo${dessert.id}').html(this.files[0].name);
                                                       document.getElementById('picdessert${dessert.id}').value = (this.files[0].name);">
                                            ${choose}
                                    </label>
                                    <input type="hidden" id="picdessert${dessert.id}" name="picdessert${dessert.id}" value="">
                                    <span class='label label-default' id="dessertFileInfo${dessert.id}"></span>
                                    <hr>

                                </div>

                                <button input type="submit" onclick="setCommVal('CHANGE_FOOD_INFO')"
                                        name="foodElem" value="dessert|${dessert.id}" class="btn btn-default">${apply}</button>

                                <button input type="submit" onclick="setCommVal('CHANGE_AVAILABLE_STATUS')"
                                        name="foodElem" value="dessert|${dessert.id}" class="btn btn-warning">${removeFromSale}</button>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>

                </div>
            </div>

            <div id="panel3" class="tab-pane fade">
                <h3>${drinks}</h3>

                <button type="button" class="btn btn-default" data-toggle="collapse" data-target="#drinkAdd">
                    ${addDrinkOpen}</button>

                <div id="drinkAdd" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="form-group">
                            <h5>${name}<span style="color:red">*</span></h5>
                            <input type="text" name="newDrinkName" value="${requestScope.newDrinkName}" maxlength="35"
                                   class="form-control">

                            <h5>${descr}<span style="color:red">*</span></h5>
                            <textarea  name="newDrinkDescr" class="form-control" rows="2" maxlength="180" >${requestScope.newDrinkDescr}</textarea>

                            <h5>${price}<span style="color:red">*</span></h5>
                            <input type="text" name="newDrinkPrice" class="form-control" maxlength="3"
                                   value="${requestScope.newDrinkPrice}"><br>
                            <label class="btn btn-default" for="newDrinkPicSelector">
                                <input type="file" accept=".jpg, .jpeg, .png"
                                       id="newDrinkPicSelector" style="display:none"
                                       onchange="$('#newDrinkPicInfo').html(this.files[0].name);
                                           document.getElementById('newDrinkPicInfoHidd').value = (this.files[0].name);">
                                ${pic}<span style="color:red">*</span>
                            </label>
                            <input type="hidden" id="newDrinkPicInfoHidd" name="newDrinkPicInfoHidd">
                            <span class='label label-default' id="newDrinkPicInfo"></span>
                        </div>
                        <button input type="submit" name="newFoodType" value="Drink" onclick="setCommVal('ADD_NEW_FOOD')" class="btn">
                            ${add}</button>

                    </div>
                </div>

                <hr>
                <c:forEach var="drink" items="${drinkArr}" >
                    <div class="media">
                        <div class="media-left">
                            <img src="${drink.imgPath}" class="media-object" style="width:100px">
                        </div>
                        <div class="media-body">

                            <div class="form-group">

                                <h5>${name}</h5>

                                <input type="text" name="namedrink${drink.id}" class="form-control" maxlength="35"
                                       value="${drink.name}">
                                <h5>${descr}</h5>
                                <textarea  name="descrdrink${drink.id}" class="form-control" rows="2" maxlength="180" >${drink.descr}</textarea>
                                <h5>${price}</h5>
                                <input type="text" name="pricedrink${drink.id}" class="form-control" maxlength="3"
                                       value="${drink.price}">

                                <h5> ${changePic}</h5>
                                <label class="btn btn-default" for="fileSelectorDrink${drink.id}">
                                    <input type="file" accept=".jpg, .jpeg, .png"
                                           id="fileSelectorDrink${drink.id}" style="display:none"
                                           onchange="$('#drinkFileInfo${drink.id}').html(this.files[0].name);
                                                   document.getElementById('picdrink${drink.id}').value = (this.files[0].name);">
                                        ${choose}
                                </label>
                                <input type="hidden" id="picdrink${drink.id}" name="picdrink${drink.id}">
                                <span class='label label-default' id="drinkFileInfo${drink.id}"></span>
                                <hr>

                            </div>

                            <button input type="submit" onclick="setCommVal('CHANGE_FOOD_INFO')"
                                    name="foodElem" value="drink|${drink.id}" class="btn btn-default">${apply}</button>

                            <button input type="submit" onclick="setCommVal('CHANGE_AVAILABLE_STATUS')"
                                    name="foodElem" value="drink|${drink.id}" class="btn btn-warning">${removeFromSale}</button>
                        </div>
                    </div>
                    <hr>
                </c:forEach>
            </div>

            <div id="panel4" class="tab-pane fade">
                <h3>${outHeader}</h3>

                <c:if test="${empty outOfStockMealArr && empty outOfStockDessertArr && empty outOfStockDrinkArr  }">
                    <h4>${emptyOutOfStock}</h4>
                </c:if>
                <hr>

                <c:forEach var="outMeal" items="${outOfStockMealArr}" >
                    <div class="media">
                        <div class="media-left">
                            <img src="${outMeal.imgPath}" class="media-object" style="width:100px">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${outMeal.name}</h4>
                            <p>${outMeal.descr}</p>

                            <button input type="submit" onclick="setCommVal('CHANGE_AVAILABLE_STATUS')"
                                    name="foodElem" value="meal|${outMeal.id}" class="btn btn-default">${returnToSale}</button>

                        </div>
                    </div>
                    <hr>
                </c:forEach>

               <c:forEach var="outDessert" items="${outOfStockDessertArr}" >
                   <div class="media">
                       <div class="media-left">
                           <img src="${outDessert.imgPath}" class="media-object" style="width:100px">
                       </div>
                       <div class="media-body">
                           <h4 class="media-heading">${outDessert.name}</h4>
                           <p>${outDessert.descr}</p>

                           <button input type="submit" onclick="setCommVal('CHANGE_AVAILABLE_STATUS')"
                                   name="foodElem" value="dessert|${outDessert.id}" class="btn btn-default">${returnToSale}</button>

                       </div>
                   </div>
                   <hr>
               </c:forEach>

                <c:forEach var="outDrink" items="${outOfStockDrinkArr}" >
                    <div class="media">
                        <div class="media-left">
                            <img src="${outDrink.imgPath}" class="media-object" style="width:100px">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${outDrink.name}</h4>
                            <p>${outDrink.descr}</p>

                            <button input type="submit" onclick="setCommVal('CHANGE_AVAILABLE_STATUS')"
                                    name="foodElem" value="drink|${outDrink.id}" class="btn btn-default">${returnToSale}</button>

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
