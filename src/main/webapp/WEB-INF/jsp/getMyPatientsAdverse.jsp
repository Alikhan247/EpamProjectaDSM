<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<c:url var="edit_staff_url" value="/do/getMyPatientsAdverse"/>

<fmt:bundle basename="i18n">
    <fmt:message key="menu.CreateAdverseEvent" var="AdverseEvent"/>
    <fmt:message key="patient.Status" var="Status"/>
    <fmt:message key="btn.Edit" var="Edit"/>
</fmt:bundle>


<mytag:mainPattern role="${sessionScope.role}">
    <c:forEach items="${adverseEvents}" var="adverse">
        <div class="container col-md-9">
            <div class="card" style="width: 500px;">
                <div class="card-block">
                    <h4 class="card-title">${adverse.adverseName}</h4>
                    <p class="card-text">${adverse.patient.getPatientCode()}</p>
                    <p class="card-text">${adverse.adverseComment}</p>
                    <p class="card-text">Date Of Adverse Event:</p>
                    <p class="card-text">${adverse.adverseDate}</p>
                    <p class="card-text">${adverse.adverseAlcohol}</p>
                    <p class="card-text">${adverse.adverseDrug}</p>
                </div>
                <form method="post" action="${edit_staff_url}">
                    <div class="form-group">
                        <div class="form-group row">
                            <label>${AdverseEvent} ${Status}</label>
                            <input type="hidden" name="adverseEventId" value="${adverse.id}">
                            <select class="form-control form-control-lg" name="adverseStatus">
                                <option disabled></option>
                                <option>Разрешено</option>
                                <option>Отложено</option>
                                <option>Не разрешено</option>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">${Edit}</button>
                </form>
            </div>
        </div>
    </c:forEach>
    </div>
</mytag:mainPattern>