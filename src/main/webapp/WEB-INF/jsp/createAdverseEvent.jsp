
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<c:url var="create_drugAdministration_url" value="/do/createAdministration/create"/>

<fmt:bundle basename="i18n">
    <fmt:message key="adverse.AdverseEventComment" var="AdverseEventComment"/>
    <fmt:message key="adverse.AdverseEventName" var="AdverseEventName"/>
    <fmt:message key="adverse.Alcohol" var="Alcohol"/>
    <fmt:message key="adverse.Drug" var="Drug"/>
    <fmt:message key="btn.Send" var="Send"/>
</fmt:bundle>


<mytag:mainPattern role="${sessionScope.role}">
    <form action="" method="post">
        <div class="form-group row">
            <label for="adverse-Date" class="col-2 col-form-label">Date</label>
            <div class="col-10">
                <jsp:useBean id="today" class="java.util.Date" scope="page" />
                <input class="form-control" type="date" name="adverseEventDate" value="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />" id="adverse-Date">
            </div>
        </div>
        <div class="form-group">
            <label>${AdverseEventName}</label>
            <input type="text" class="form-control" name="adverseEventName" >
        </div>
        <div class="form-group">
            <label>${AdverseEventComment}</label>
            <input type="text" class="form-control" name="adverseEventComment">
        </div>
        <div class="form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" value="${true}" name="isAlcohol">
                ${Alcohol}
            </label>
        </div>
        <div class="form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" value="${true}" name="isDrug">
                ${Drug}
            </label>
        </div>
        <button type="submit" class="btn btn-primary">${Send}</button>
    </form>
</mytag:mainPattern>