<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<fmt:bundle basename="i18n">
    <fmt:message key="patient.IDcode" var="Idcode"/>
    <fmt:message key="patient.Initial" var="Initial"/>
    <fmt:message key="patient.DateOfBirthday" var="DateOfBirthDay"/>
    <fmt:message key="person.Telephone" var="Telephone"/>
    <fmt:message key="patient.RiskFactors" var="RiskFactor"/>
    <fmt:message key="patient.Typepatient" var="TypePatient"/>
    <fmt:message key="patient.Prevalence" var="Prevalence"/>
    <fmt:message key="patient.MBTStatus" var="MBT"/>
    <fmt:message key="patient.Localization" var="Localization"/>
    <fmt:message key="patient.DSTStatus" var="DST"/>
    <fmt:message key="patient.Gender" var="Gender"/>
    <fmt:message key="patient.DiagnosisPatient" var="DiagnosisPatient"/>
    <fmt:message key="patient.ClinicalForm" var="ClinicalForm"/>
</fmt:bundle>
<mytag:mainPattern role="${sessionScope.role}">
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-default">
        <tr>
            <th>${Idcode}</th>
            <th>${Initial}</th>
            <th>${DateOfBirthDay}</th>
            <th>${Gender}</th>
            <th>${Telephone}</th>
            <th>${RiskFactor}</th>
            <th>${TypePatient}</th>
            <th>${ClinicalForm}</th>
            <th>${MBT}</th>
            <th>${DST}</th>
            <th>${Localization}</th>
            <th>${Prevalence}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${patientList}" var="patient" varStatus="status">
            <tr>
                <td>${patient.patientCode}</td>
                <td>${patient.initial}</td>
                <td>${patient.dateOfBirthday}</td>
                <td>${patient.patientSex}</td>
                <td>${patient.phoneNumber}</td>
                <c:forEach items="${diagnosisList}" var="diagnosis">
                    <c:if test="${patient.patientCode == diagnosis.getPatient().patientCode}">
                        <td>${diagnosis.riskFactor}</td>
                        <td>${diagnosis.patientType}</td>
                        <td>${diagnosis.clinicalForm}</td>
                        <td>${diagnosis.mbtStatus}</td>
                        <td>${diagnosis.dstStatus}</td>
                        <td>${diagnosis.localizationDisease}</td>
                        <td>${diagnosis.prevalence}</td>
                    </c:if>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</mytag:mainPattern>