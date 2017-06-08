<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<c:url var="edit_staff_url" value="/do/getMyPatientsAdverse"/>
<fmt:bundle basename="i18n">
    <fmt:message key="menu.CreateAdverseEvent" var="adverseEvent"/>
    <fmt:message key="patient.Status" var="Status"/>
    <fmt:message key="btn.Edit" var="Edit"/>
    <fmt:message key="adverse.AdverseEventDate" var="adverseEventDate"/>
    <fmt:message key="adverse.Alcohol" var="alchohol"/>
    <fmt:message key="adverse.Drug" var="drug"/>
</fmt:bundle>
<mytag:mainPattern role="${sessionScope.role}">
    <c:forEach items="${adverseEvents}" var="adverse">
        <div class="container col-md-9">
            <div class="card" style="width: 500px;">
                <div class="card-block">
                    <h4 class="card-title">${adverse.adverseName}</h4>
                    <p class="card-text">${adverse.patient.getPatientCode()}</p>
                    <p class="card-text">${adverse.adverseComment}</p>
                    <p class="card-text">${adverseEventDate}</p>
                    <p class="card-text">${adverse.adverseDate}</p>
                    <p class="card-text">${alchohol} ${adverse.adverseAlcohol}</p>
                    <p class="card-text">${drug} ${adverse.adverseDrug}</p>
                </div>
                <form method="post" action="${edit_staff_url}">
                    <div class="form-group">
                        <div class="form-group row">
                            <label>${adverseEvent} ${Status}</label>
                            <input type="hidden" name="adverseEventId" value="${adverse.id}">
                            <select class="form-control form-control-lg" name="adverseStatus">
                                <option disabled></option>
                                <c:forEach items="${adverseStatuses}" var="adverseStatus">
                                    <option>${adverseStatus}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">${Edit}</button>
                </form>
            </div>
        </div>
    </c:forEach>
</mytag:mainPattern>