<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/mytaglib.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/common.css" rel="stylesheet">

    <c:set var="language" value="${sessionScope.lang}"/>
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="property.text" var="local"/>
    <fmt:message bundle="${local}" key="main.cafe" var="cafe" />
    <fmt:message bundle="${local}" key="button.Main" var="OnMain" />
    <fmt:message bundle="${local}" key="button.ourMenu" var="ourMenu"/>
    <fmt:message bundle="${local}" key="button.login" var="loginbutt" />
    <fmt:message bundle="${local}" key="button.registration" var="registrationButt" />

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" href="#">${cafe}</a>
        </div>
        <ul class="nav navbar-nav navbar-left">

            <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                         value="main" class="btn btn-default navbar-btn">${OnMain}</button></li>
            <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                         value="demoFoodMenu" class="btn btn-default navbar-btn">${ourMenu}</button> </li>
            <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                         value="logination" class="btn btn-default navbar-btn">${loginbutt}</button> </li>
            <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                         value="registration" class="btn btn-default navbar-btn">${registrationButt}</button> </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <div class="btn-group btn-group-sm" role="group" aria-label="...">

                    <button type="submit" onclick="setCommVal('CHANGE_LANG')" name="lang"
                            value="en" class="btn btn-default navbar-btn">EN</button>

                    <button type="submit" onclick="setCommVal('CHANGE_LANG')" name="lang"
                            value="rus" class="btn btn-default navbar-btn">RU</button>
                </div>
            </li>
        </ul>
    </div>
</nav>

</body>
</html>
