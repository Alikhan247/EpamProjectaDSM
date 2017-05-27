
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>


<fmt:bundle basename="i18n">
    <fmt:message key="person.Name" var="name"/>
    <fmt:message key="person.Password" var="password"/>
    <fmt:message key="person.Position" var="position"/>
    <fmt:message key="person.RepeatPassword" var="repeatPassword"/>
    <fmt:message key="person.Surname" var="surname"/>
    <fmt:message key="person.Telephone" var="telephone"/>
    <fmt:message key="btn.Create" var="btnCreate"/>
    <fmt:message key="patient.Status" var="Status"/>
    <fmt:message key="btn.Edit" var="Edit"/>
</fmt:bundle>


<c:url var="staffList_url" value="/do/getStaffList"/>

<mytag:mainPattern role="${sessionScope.role}">
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-default">
        <tr>
            <th>${name}</th>
            <th>${surname}</th>
            <th>${telephone}</th>
            <th>${position}</th>
            <th>${Status}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${staffList}" var="staff">
            <tr>
                <td>${staff.name}</td>
                <td>${staff.surname}</td>
                <td>${staff.phoneNumber}</td>
                <td>${staff.role}</td>
                <c:choose>
                    <c:when test="${staff.activity_status}">
                        <td class="table-active">${staff.activity_status}</td>
                    </c:when>
                    <c:otherwise>
                        <td class="table-danger">${staff.activity_status}</td>
                    </c:otherwise>
                </c:choose>"
                <td><a href="<c:url value="/do/staff/edit?id=${staff.id}"/>">${Edit}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</mytag:mainPattern>