<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<c:url var="create_drugAdministration_url" value="/do/createAdministration/drug"/>

<fmt:bundle basename="i18n">
    <fmt:message key="receipt.Receipts" var="receipts_fmt"/>
    <fmt:message key="receipt.DozeForDrug" var="receiptDozeForDrug"/>
</fmt:bundle>
<mytag:mainPattern role="${sessionScope.role}">
    <div class="container col-md-6">
        <h4>${receipts_fmt}</h4>
        <c:forEach items="${drugs}" var="drug">
            <c:forEach items="${receipts}" var="receipt">
                <c:if test="${drug.id == receipt.drug.id}">
                    <form method="post" action="">
                        <div class="card w-75">
                            <div class="card-block">
                                <input type="hidden" name="receiptId" value="${receipt.id}">
                                <h3 class="card-title">${drug.name}</h3>
                                <p class="card-text">${receiptDozeForDrug} ${receipt.drugDoze}</p>
                                <select name="drugStatus" class="form-control input-md">
                                    <option disabled></option>
                                    <c:forEach items="${administrationOptions}" var="administrationOption">
                                        <option>${administrationOption}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" class="btn btn-primary">Send</button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </c:forEach>
        </c:forEach>
    </div>
</mytag:mainPattern>