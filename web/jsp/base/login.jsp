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
    <fmt:message bundle="${local}" key="page.title.login" var="title" />
    <fmt:message bundle="${local}" key="page.login" var="loginPageTitle" />
    <fmt:message bundle="${local}" key="page.login.login" var="login" />
    <fmt:message bundle="${local}" key="page.login.password" var="password" />
    <fmt:message bundle="${local}" key="page.login.userNotFound" var="errNotFound" />
    <fmt:message bundle="${local}" key="page.login.badLoginData" var="errBadData" />
    <fmt:message bundle="${local}" key="page.login.buttonLogin" var="buttLogIn" />

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

    <input type="hidden" id="coomId" name="command" value="LOGIN_USER">
    <c:set var="lastPage" value="/jsp/base/login.jsp" scope="session" />

    <jsp:include page="/jsp/header/headerBase.jsp"/>

    <div class="container">
        <div class="page-header">
            <h1>
                ${loginPageTitle}
            </h1>
        </div>

        <div class="dataInput">

        <div class="form-group">
            <label for="login" class="control-label">${login}<span style="color:red">*</span></label>
            <input type="text" name="login" class="form-control"  maxlength="15" id="login" placeholder=${login}  >
        </div>
        <div class="form-group">
            <label for="password" class="control-label">${password}<span style="color:red">*</span></label>
            <input type="password" name="password" class="form-control"  maxlength="20" id="password" placeholder=${password} >
        </div>
        <div class="form-group">
            <c:if test = "${not empty requestScope.noUser}">
                <p class="text-danger"> ${errNotFound}</p>
            </c:if>
            <c:if test = "${not empty requestScope.badLoginData}">
                <p class="text-danger"> ${errBadData}</p>
            </c:if>
        </div>
        <div class="form-group">
            <button input type="submit" onclick="setCommVal('LOGIN_USER')" class="btn btn-success"> ${buttLogIn}</button>
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
