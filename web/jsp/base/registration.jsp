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
    <fmt:message bundle="${local}" key="page.title.reg" var="title" />
    <fmt:message bundle="${local}" key="page.reg" var="registration" />
    <fmt:message bundle="${local}" key="page.reg.Login" var="Login" />
    <fmt:message bundle="${local}" key="page.reg.LoginInfo" var="LoginInfo" />
    <fmt:message bundle="${local}" key="page.reg.Password" var="Password" />
    <fmt:message bundle="${local}" key="page.reg.PasswordInfo" var="PasswordInfo" />
    <fmt:message bundle="${local}" key="page.reg.RepeatPassword" var="repeatPassword" />
    <fmt:message bundle="${local}" key="page.reg.Email" var="Email" />
    <fmt:message bundle="${local}" key="page.reg.buttReg" var="buttReg" />
    <fmt:message bundle="${local}" key="page.reg.badLogin" var="badLogin" />
    <fmt:message bundle="${local}" key="page.reg.badPassword" var="badPassword" />
    <fmt:message bundle="${local}" key="page.reg.notEqualsPass" var="notEqualsPass" />
    <fmt:message bundle="${local}" key="page.reg.badEmail" var="badEmail" />
    <fmt:message bundle="${local}" key="page.reg.LoginUsed" var="loginUsed" />
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

    <input type="hidden" id="coomId" name="command" value="REGISTR_CLIENT">
    <c:set var="lastPage" value="/jsp/base/registration.jsp" scope="session" />

    <jsp:include page="/jsp/header/headerBase.jsp"/>

<div class="container">
    <div class="page-header">
        <h1>
           ${registration}
        </h1>
    </div>

    <div class="dataInput">
        <div class="form-group">
            <label for="login" class="control-label">${Login}<span style="color:red">*</span></label>
            <label for="login" class="control-label" ><h5>${LoginInfo}</h5></label>
            <input type="text" name="login" value="${requestScope.login}"  maxlength="15" class="form-control"
                   id="login" placeholder=${Login} >
        </div>
        <div class="form-group">
            <label for="password" class="control-label">${Password}<span style="color:red">*</span></label>
            <label for="password" class="control-label"><h5>${PasswordInfo}</h5></label>
            <input type="password" name="password" value="${requestScope.password}"  maxlength="20" class="form-control"
                   id="password" placeholder=${Password} >
        </div>
        <div class="form-group">
            <label for="confPass" class="control-label">${repeatPassword}<span style="color:red">*</span></label>
            <input type="password" name="passwordConfirm" value="${requestScope.passwordConfirm}" maxlength="20"
                   class="form-control" id="confPass" placeholder=${Password} >
        </div>
        <div class="form-group">
            <label for="mail" class="control-label">${Email}<span style="color:red">*</span></label>
            <input type="text" name="email" value="${requestScope.email}"  maxlength="45" class="form-control"
                   id="mail" placeholder=${Email} >
        </div>
        <div class="form-group">
            <c:if test = "${not empty requestScope.badLogin}">
                <p class="text-danger"> ${badLogin}</p>
            </c:if>
            <c:if test = "${not empty requestScope.badPassword}">
                <p class="text-danger"> ${badPassword}</p>
            </c:if>
            <c:if test = "${not empty requestScope.notEqualsPass}">
                <p class="text-danger">  ${notEqualsPass}</p>
            </c:if>
            <c:if test = "${not empty requestScope.badEmail}">
                <p class="text-danger">  ${badEmail}</p>
            </c:if>
            <c:if test = "${not empty requestScope.loginUsed}">
                <p class="text-danger"> ${loginUsed} </p>
            </c:if>
       </div>

    <div class="form-group">
        <button input type="submit" onclick="setCommVal('REGISTR_CLIENT')" class="btn btn-success"> ${buttReg}</button>
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