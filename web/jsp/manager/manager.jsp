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
    <fmt:message bundle="${local}" key="page.clientPorfile.hello" var="hello" />
    <fmt:message bundle="${local}" key="page.adminPage.title" var="title" />
    <fmt:message bundle="${local}" key="page.adminPage.profile" var="profile" />
    <fmt:message bundle="${local}" key="page.adminPage.ordersControl" var="orderControl" />
    <fmt:message bundle="${local}" key="page.adminPage.currentOrders" var="current" />
    <fmt:message bundle="${local}" key="page.adminPage.currentOrdersManagment" var="currManage" />
    <fmt:message bundle="${local}" key="page.adminPage.noNewOrders" var="noNewOrders" />
    <fmt:message bundle="${local}" key="page.adminPage.finishedOrders" var="finished" />
    <fmt:message bundle="${local}" key="page.adminPage.finishedOrdersHistory" var="finishHist" />
    <fmt:message bundle="${local}" key="page.adminPage.cancekedOrders" var="canceled" />
    <fmt:message bundle="${local}" key="page.adminPage.cancekedOrdersHistory" var="canceledHist" />
    <fmt:message bundle="${local}" key="page.adminPage.buttConfirmOrder" var="buttConfirm" />
    <fmt:message bundle="${local}" key="page.adminPage.buttCancel" var="buttCancel" />
    <fmt:message bundle="${local}" key="page.adminPage.orderNum" var="orderNum" />
    <fmt:message bundle="${local}" key="page.adminPage.orderClient" var="orderClient" />
    <fmt:message bundle="${local}" key="page.adminPage.orderPrice" var="orderPrice" />
    <fmt:message bundle="${local}" key="page.adminPage.orderTime" var="orderTime" />
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
    <c:set var="lastPage" value="/jsp/manager/manager.jsp" scope="session" />

    <jsp:include page="/jsp/header/headerManager.jsp"/>

    <div class="container">
        <div class="page-header">
            <h1>${profile}</h1>
        </div>
        <h3>  <p class="bg-info"> ${hello}${sessionScope.login}</p></h3>
        <h3> <p>${orderControl}</p></h3>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#panel1">${current}</a></li>
            <li><a data-toggle="tab" href="#panel2">${finished}</a></li>
            <li><a data-toggle="tab" href="#panel3">${canceled}</a></li>
        </ul>

        <div class="tab-content">
            <div id="panel1" class="tab-pane fade in active">
                <h3>${currManage}</h3>

                <c:if test="${empty ordersCurrent }">
                    <h4>${noNewOrders}</h4>
                </c:if>

                <c:forEach var="currOrder" items="${ordersCurrent}" >
                    <div class="panel panel-warning">
                        <div class="panel-heading"> <p> <strong>${orderNum}</strong>${currOrder.id} </p></div>
                        <div class="panel-body">
                            <p> <strong>${orderClient}</strong>  ${currOrder.clientName}</p>
                            <p> <strong>${orderPrice}</strong>  ${currOrder.totalPrice}</p>
                            <p> <strong>${orderTime}</strong>  ${currOrder.gettingTime}</p>


                            <button input type="submit" value="${currOrder.id}|${currOrder.totalPrice}|${currOrder.clientName}"
                                    onclick="setCommVal('CONFIRM_ORDER')" name="orderInfo" class="btn btn-default">
                                     ${buttConfirm}</button>

                            <button input type="submit" value="${currOrder.id}|${currOrder.totalPrice}|${currOrder.clientName}"
                                    onclick="setCommVal('CANCEL_ORDER')" name="orderInfo" class="btn btn-warning">
                                    ${buttCancel}</button>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="panel2" class="tab-pane fade">
                <div class="container">
                    <h3>${finishHist}</h3>
                    <c:forEach var="finishedOrder" items="${ordersFinished}" >
                        <div class="panel panel-success">
                            <div class="panel-heading"> <p> <strong>${orderNum}</strong>${finishedOrder.id} </p></div>
                            <div class="panel-body">
                                <p> <strong>${orderClient}</strong>  ${finishedOrder.clientName}</p>
                                <p> <strong>${orderPrice}</strong>  ${finishedOrder.totalPrice}</p>
                                <p> <strong>${orderTime}</strong>  ${finishedOrder.gettingTime}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div id="panel3" class="tab-pane fade">
                <h3>${canceledHist}</h3>
                <c:forEach var="canceledOrder" items="${ordersCanceled}" >
                    <div class="panel panel-danger">
                        <div class="panel-heading"> <p> <strong>${orderNum}</strong>${canceledOrder.id} </p></div>
                        <div class="panel-body">
                            <p> <strong>${orderClient}</strong>  ${canceledOrder.clientName}</p>
                            <p> <strong>${orderPrice}</strong>  ${canceledOrder.totalPrice}</p>
                            <p> <strong>${orderTime}</strong>  ${canceledOrder.gettingTime}</p>
                        </div>
                    </div>
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