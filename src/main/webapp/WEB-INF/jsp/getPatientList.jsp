<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<fmt:bundle basename="i18n">
    <fmt:message key="patient.Enrollmentdate" var="EnrollmentDate"/>
    <fmt:message key="patient.AccountDetails" var="AccountDetails"/>
    <fmt:message key="patient.IDcode" var="Idcode"/>
    <fmt:message key="patient.Initial" var="Initial"/>
    <fmt:message key="person.Password" var="Password"/>
    <fmt:message key="person.RepeatPassword" var="RepeatPassword"/>
    <fmt:message key="patient.PersonalDate" var="PersonalDate"/>
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
    <fmt:message key="patient.Doctor" var="Doctor"/>
    <fmt:message key="person.Surname" var="Surname"/>
    <fmt:message key="patient.ResearchProgress" var="ResearchProgress"/>
    <fmt:message key="patient.ResearchStatus" var="Status"/>
    <fmt:message key="btn.Edit" var="Edit"/>
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
            <th>${Localization}</th>
            <th>${Prevalence}</th>
            <th>${Doctor} ${Surname}</th>
            <th>${ResearchProgress}</th>
            <th>${Status}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${patientList}" var="patient">
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
                        <td>${diagnosis.localizationDisease}</td>
                        <td>${diagnosis.prevalence}</td>
                    </c:if>
                </c:forEach>
                <td>${patient.getDoctor().getSurname()}</td>
                <c:forEach items="${researchList}" var="research">
                    <c:if test="${patient.patientCode == research.getPatient().patientCode}">
                        <td>${research.researchProgress}</td>
                        <c:choose>
                            <c:when test="${research.activationStatus}">
                                <td class="table-active">${research.activationStatus}</td>
                            </c:when>
                            <c:otherwise>
                                <td class="table-danger">${research.activationStatus}</td>
                            </c:otherwise>
                        </c:choose>"
                    </c:if>
                </c:forEach>
                <td><a href="<c:url value="/do/patient/edit?patientCode=${patient.patientCode}"/> ">${Edit}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</mytag:mainPattern>