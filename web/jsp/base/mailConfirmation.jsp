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
    <fmt:message bundle="${local}" key="page.mConf.title" var="title" />
    <fmt:message bundle="${local}" key="page.mConf.mailConf" var="mailConf" />
    <fmt:message bundle="${local}" key="page.mConf.Info" var="info" />
    <fmt:message bundle="${local}" key="page.mconf.InputCode" var="inputCode" />
    <fmt:message bundle="${local}" key="button.ok" var="ok" />
    <fmt:message bundle="${local}" key="page.mconf.badCode" var="badCode" />
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

    <input type="hidden" id="coomId" name="command" value="MAIL_CONFIRM">
    <c:set var="lastPage" value="/jsp/base/mailConfirmation.jsp" scope="session" />

    <jsp:include page="/jsp/header/headerBase.jsp"/>

    <div class="container">
        <div class="page-header">
            <h1>
                ${mailConf}
            </h1>
        </div>


            <p class="text">${info}</p>
            <div class="form-group">
                <label for="mailCode" class="control-label">${inputCode}</label>
                <div class="mailCodeInput">
                    <input type="text" name="mailCode" class="form-control" id="mailCode"  maxlength="7">
                </div>
            </div>
            <div class="form-group">
                <c:if test = "${not empty requestScope.badCode}">
                    <p class="text-danger"> ${ badCode} </p>
                </c:if>
            </div>
            <div class="form-group">
                <button input type="submit" onclick="setCommVal('MAIL_CONFIRM')" class="btn btn-success"> ${ok}</button>
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

