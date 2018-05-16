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
    <fmt:message bundle="${local}" key="button.Main" var="OnMain" />
    <fmt:message bundle="${local}" key="button.login" var="login" />
    <fmt:message bundle="${local}" key="button.logOut" var="logout" />
    <fmt:message bundle="${local}" key="button.registration" var="registration" />
    <fmt:message bundle="${local}" key="button.ourMenu" var="ourMenu"/>
    <fmt:message bundle="${local}" key="page.adminPage.menuControl" var="menuManage"/>
    <fmt:message bundle="${local}" key="button.profile" var="myProfile"/>
    <fmt:message bundle="${local}" key="main.cafe" var="cafe" />
    <fmt:message bundle="${local}" key="page.title.error" var="title" />
    <fmt:message bundle="${local}" key="page.errorCode" var="errorCode" />
    <fmt:message bundle="${local}" key="page.errorInfo" var="errorInfo" />
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

    <input type="hidden" id="coomId" name="command" value="MAIN_FORWARD">

    <c:set var="lastPage" value="/jsp/error/errorInfo.jsp" scope="session" />

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">

            <div class="navbar-header">
                <a class="navbar-brand" href="#">${cafe}</a>
            </div>
            <ul class="nav navbar-nav navbar-left">

                <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                             value="main" class="btn btn-default navbar-btn">${OnMain}</button></li>

                <c:choose>
                    <c:when test="${sessionScope.isLogin == false}">

                        <c:if test="${sessionScope.role =='VISITOR'}">
                            <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                                         value="demoFoodMenu" class="btn btn-default navbar-btn">${ourMenu}</button> </li>
                        </c:if>

                        <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                                     value="logination" class="btn btn-default navbar-btn">${login}</button> </li>

                        <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                                     value="registration" class="btn btn-default navbar-btn">${registration}</button> </li>

                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${sessionScope.role =='CLIENT'}">
                                <li> <button input type="submit" onclick="setCommVal('MENU_SHOW')" name="action"
                                             class="btn btn-default navbar-btn">${ourMenu}</button> </li>
                                <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                                             value="profileClient" class="btn btn-default navbar-btn">${myProfile}</button> </li>
                            </c:when>
                            <c:when test="${sessionScope.role =='MANAGER'}">
                                <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                                             value="controlFoodMenu" class="btn btn-default navbar-btn">${menuManage}</button> </li>
                                <li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"
                                             value="profileManager" class="btn btn-default navbar-btn">${myProfile}</button> </li>
                            </c:when>
                        </c:choose>
                        <li> <button input type="submit" onclick="setCommVal('LOGOUT')" name="action"
                                     value="logout" class="btn btn-default navbar-btn">${logout}</button> </li>
                    </c:otherwise>
                </c:choose>
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

    <div class="container">
        <h2>  <p class="bg-danger"> ${errorCode} ${pageContext.errorData.statusCode}</p></h2>
        <c:if test="${not empty pageContext.exception.message}">
            <h3>${errorInfo} ${pageContext.exception.message}</h3>
        </c:if>
    </div>>

    <footer class="footer">
        <p class="text-muted"><mytag:getinfo/></p>
    </footer>

    <script src="static/js/jquery-3.2.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>

</form>

</body>
</html>

