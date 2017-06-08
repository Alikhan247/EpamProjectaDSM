<%--
  Created by IntelliJ IDEA.
  User: akmatleu
  Date: 18.05.17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<fmt:bundle basename="i18n">
    <fmt:message key="btn.Send" var="Send"/>
    <fmt:message key="menu.CreateAdverseEvent" var="adverseEvent"/>
    <fmt:message key="patient.IDcode" var="Idcode"/>
    <fmt:message key="patient.Initial" var="Initial"/>
    <fmt:message key="patient.DateOfBirthday" var="DateOfBirthDay"/>
    <fmt:message key="person.Telephone" var="Telephone"/>
    <fmt:message key="patient.Gender" var="Gender"/>
    <fmt:message key="btn.Find" var="Find"/>
    <fmt:message key="bnt.SendDrugDdministration" var="SendDrug"/>
    <fmt:message key="patient.msg" var="msgVisit"/>
    <fmt:message key="patient.msgV" var="msgV"/>
</fmt:bundle>
<c:url var="find_Patient_url" value="/do/homePage"/>
<mytag:mainPattern role="${sessionScope.role}">
    <c:if test="${role.equals('patient')}">
        <div class="bd-example" data-example-id="">
            <div class="list-group">
                <c:forEach items="${patientEvents}" var="event">
                    <a href="<c:url value="/do/event/details?eventId=${event.id}"/>"
                       class="list-group-item list-group-item-action">${msgVisit} ${sessionScope.patientDoctorSurname} ${msgV} ${event.eventDate} </a>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${role.equals('drug delivery')}">
        <form action="${find_Patient_url}" method="post">
            <div class="form-group">
                <label>${Idcode}</label>
                <input type="text" class="form-control" name="patientCode">
            </div>
            <button type="submit" class="btn btn-primary">${Find}</button>
        </form>
        <table class="table table-striped table-bordered table-hover">
            <thead class="thead-default">
            <tr>
                <th>${Idcode}</th>
                <th>${Initial}</th>
                <th>${DateOfBirthDay}</th>
                <th>${Gender}</th>
                <th>${Telephone}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${patient.patientCode}</td>
                <td>${patient.initial}</td>
                <td>${patient.dateOfBirthday}</td>
                <td>${patient.patientSex}</td>
                <td>${patient.phoneNumber}</td>
                <td>
                    <a href="<c:url value="/do/createAdministration/drug?patientCode=${patient.patientCode}"/> ">${SendDrug}</a>
                </td>
                <c:choose>
                    <c:when test="${patient.patientCode!=null}">
                        <td>
                            <a href="<c:url value="/do/createAdverseEvent/create?patientCode=${patient.patientCode}"/> ">${Send} ${adverseEvent}</a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="#"/>${Send} ${adverseEvent}</a> </td>
                    </c:otherwise>
                </c:choose>
            </tr>
            </tbody>
        </table>
    </c:if>
</mytag:mainPattern>