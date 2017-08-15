<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<c:url var="createPatient_url" value="/do/createPatient"/>
<fmt:bundle basename="i18n">
    <fmt:message key="patient.Enrollmentdate" var="EnrollmentDate"/>
    <fmt:message key="patient.AccountDetails" var="AccountDetails"/>
    <fmt:message key="patient.IDcode" var="Idcode"/>
    <fmt:message key="patient.Initial" var="Initial"/>
    <fmt:message key="person.Password" var="Password"/>
    <fmt:message key="person.RepeatPassword" var="RepeatPassword"/>
    <fmt:message key="person.Email" var="Email"/>
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
    <fmt:message key="btn.Registraion" var="Registraion"/>
    <fmt:message key="error.null" var="errorNullFields"/>
    <fmt:message key="error.password" var="errorPasswords"/>
    <fmt:message key="error.phone" var="errorPhone"/>
    <fmt:message key="error.nullDoctors" var="errorDoctors"/>
</fmt:bundle>
<mytag:mainPattern role="${sessionScope.role}">
    <div class="container col-md-6" id="createPatientFrom">
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                <c:choose>
                    <c:when test="${error.equals('errorNull')}">
                        <strong>${errorNullFields}</strong>
                    </c:when>
                    <c:when test="${error.equals('errorPhoneExist')}">
                        <strong>${errorPhone}</strong>
                    </c:when>
                    <c:when test="${error.equals('errorNullDoctors')}">
                        <strong>${errorDoctors}</strong>
                    </c:when>
                    <c:otherwise>
                        <strong>${errorPasswords}</strong>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
        <form method="post" action="${createPatient_url}">
            <h3>${EnrollmentDate}</h3>
            <div class="form-group row">
                <label for="example-date-input" class="col-2 col-form-label">${EnrollmentDate}</label>
                <div class="col-10">
                    <input class="form-control" type="date" value="2011-08-19" id="enrollmentDate"
                           name="enrollmentDate">
                </div>
            </div>
            <h3>${AccountDetails}</h3>
            <div class="form-group">
                <label>${Idcode}</label>
                <input type="text" class="form-control" name="patientCode">
            </div>
            <div class="form-group">
                <label>${Initial}</label>
                <input type="text" class="form-control" name="initial">
            </div>
            <div class="form-group">
                <label>${Password}</label>
                <input type="password" class="form-control" name="password">
            </div>
            <div class="form-group">
                <label>${RepeatPassword}</label>
                <input type="password" class="form-control" name="confirmedPassword">
            </div>
            <h3>${PersonalDate}</h3>
            <div class="form-group row">
                <label>${Email}</label>
                <input type="email" name="email">
            </div>
            <div class="form-group row">
                <label for="example-date-input" class="col-2 col-form-label">${DateOfBirthDay}</label>
                <div class="col-10">
                    <input class="form-control" type="date" value="2011-08-19" id="example-date-input"
                           name="dateBirthday">
                </div>
            </div>
            <div class="form-group row">
                <label>${Telephone}</label>
                <input type="tel" value="" name="phone">
            </div>
            <div class="form-group row">
                <label>${RiskFactor}</label>
                <select id="risk_factors" name="riskfactor" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${riskFactors}" var="riskFactor">
                        <option>${riskFactor}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label>${Gender}</label>
                <select class="form-control form-control-lg" name="gender">
                    <option disabled></option>
                    <c:forEach items="${genders}" var="gender">
                        <option>${gender}</option>
                    </c:forEach>
                </select>
            </div>
            <h3>${DiagnosisPatient}</h3>
            <div class="form-group row">
                <label>${Localization}</label>
                <select name="localization" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${localizatoins}" var="localization">
                        <option>${localization}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label>${Prevalence}</label>
                <select name="prevalence" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${prevalences}" var="prevalence">
                        <option>${prevalence}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label>${ClinicalForm}</label>
                <select id="clinical_form" name="clinicalForm" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${clinicalForms}" var="clinicalForm">
                        <option>${clinicalForm}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label>${MBT}</label>
                <select name="mbtStatus" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${mbtStatuses}" var="mbtStatus">
                        <option>${mbtStatus}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label>${TypePatient}</label>
                <select name="patientType" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${patientTypes}" var="patientType">
                        <option>${patientType}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label>${DST}</label>
                <select id="dsts_status" name="dstStatus" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${dstsStatus}" var="dstStatus">
                        <option>${dstStatus}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label> ${Doctor} </label>
                <select id="staff_id" name="staff_id" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${doctors}" var="doctor">
                        <option value="${doctor.id}">${doctor.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">${Registraion}</button>
        </form>
    </div>
</mytag:mainPattern>