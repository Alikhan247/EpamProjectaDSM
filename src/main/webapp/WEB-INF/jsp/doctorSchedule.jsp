<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<fmt:bundle basename="i18n">
    <fmt:message key="person.Date" var="Date"/>
    <fmt:message key="btn.Edit" var="Edit"/>
    <fmt:message key="patient.IDcode" var="Idcode"/>
    <fmt:message key="patient.Initial" var="Initial"/>
    <fmt:message key="person.Telephone" var="Telephone"/>
</fmt:bundle>
<mytag:mainPattern role="${sessionScope.role}">
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-default">
        <tr>
            <th>${Idcode}</th>
            <th>${Initial}</th>
            <th>${Telephone}</th>
            <th>${Date}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${patientList}" var="patient">
            <tr>
                <td>${patient.patientCode}</td>
                <td>${patient.initial}</td>
                <td>${patient.phoneNumber}</td>
                <td>${patient.getEventDay().getEventDate()}</td>
                <td><a href="<c:url value="/do/event/details?eventId=${patient.getEventDay().getId()}"/> ">${Edit}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</mytag:mainPattern>