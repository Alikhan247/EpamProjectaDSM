<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="role" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:url var="createStaff_url" value="/do/createStaff"/>
<c:url var="createPatient_url" value="/do/createPatient"/>
<c:url var="staffList_url" value="/do/getStaffList"/>
<c:url var="patientList_url" value="/do/getPatientList"/>
<c:url var="schedule_url" value="/do/getSchedule"/>
<c:url var="adverseList_url" value="/do/getAdverseList"/>
<c:url var="signOut_url" value="/do/signOut"/>
<c:url var="imageLogo" value="/../../img/CareLogo.jpg"/>
<c:url var="ruLogo" value="/../../img/ruLogo.png"/>
<c:url var="engLogo" value="/../../img/engLogo.png"/>
<c:url var="createDrug_Admin_url" value="/do/createDrugAdministration"/>
<c:url var="createAdverse_url" value="/do/createAdverse"/>
<c:url var="createAdverseEvent_url" value="/do/createAdverseEvent/create?patientCode=${sessionScope.patientCode}"/>
<c:url var="home_url" value="/do/homePage"/>
<c:url var="patient_home_url" value="/do/patientHomePage"/>
<c:url var="doctor_patients_url" value="/do/getMyPatients"/>
<c:url var="doctor_schedule_url" value="/do/doctorSchedule"/>
<c:url var="doctor_adverse_url" value="/do/getMyPatientsAdverse"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <fmt:bundle basename="i18n">
        <fmt:message key="menu.createStaff" var="CreateStaff"/>
        <fmt:message key="menu.CreatePatient" var="CreatePatient"/>
        <fmt:message key="menu.ListOfEmployees" var="ListOfEmployees"/>
        <fmt:message key="menu.ListOfPatients" var="ListOfPatients"/>
        <fmt:message key="menu.SignOut" var="SignOut"/>
        <fmt:message key="menu.Home" var="Home"/>
        <fmt:message key="menu.Hello" var="Hello"/>
        <fmt:message key="menu.MySchedule" var="MySchedule"/>
        <fmt:message key="menu.ListOfAdverseEvents" var="ListOfAdverseEvents"/>
        <fmt:message key="menu.CreateAdverseEvent" var="CreateAdverseEvent"/>
    </fmt:bundle>


    <meta charset="UTF-8"/>
    <!--TO DO JS SCRRIPS HERE-->

    <!--*********************-->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <style>
        <jsp:directive.include file="/WEB-INF/css/mainStyle.css" />
    </style>
    <title>aDSM</title>

</head>
<body>
<div class="container">
    <div class="row" id="header">
        <div class="span12">
            <img src="${imageLogo}" class="img-fluid" alt="logo">
        </div>
    </div>
    <div class="row" id="content">
        <div class="span3 sideBar">
            <c:if test="${role.equals('coordinator')}">
                <div class="dropdown-menu" style="display: block; position: static">
                    <a class="dropdown-item" href="${createStaff_url}">${CreateStaff}</a>
                    <a class="dropdown-item" href="${createPatient_url}">${CreatePatient}</a>
                    <a class="dropdown-item" href="${staffList_url}">${ListOfEmployees}</a>
                    <a class="dropdown-item" href="${patientList_url}">${ListOfPatients}</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">${Hello}, ${sessionScope.name}!</a>
                    <a class="dropdown-item" href="set-languageLocale?lang=en"><img src="${engLogo}"
                                                                                     width="45" height="50"
                                                                                     alt="engLogo"></a>
                    <a class="dropdown-item" href="set-languageLocale?lang=ru"><img src="${ruLogo}"
                                                                                    width="45" height="50" alt="ruLogo"></a>
                    <form action="${signOut_url}" method="post">
                        <button type="submit" class="btn btn-primary">${SignOut}</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${role.equals('patient')}">
                <div class="dropdown-menu" style="display: block; position: static">
                    <a class="dropdown-item" href="#">${Hello}, ${sessionScope.patientCode}!</a>
                    <a class="dropdown-item" href="${patient_home_url}">${Home}</a>
                    <a class="dropdown-item" href="${createAdverseEvent_url}">${CreateAdverseEvent}</a>
                    <a class="dropdown-item" href="set-languageLocale?lang=en"><img src="${engLogo}"
                                                                                    width="45" height="50"
                                                                                    alt="engLogo"></a>
                    <a class="dropdown-item" href="set-languageLocale?lang=ru"><img src="${ruLogo}"
                                                                                    width="45" height="50" alt="ruLogo"></a>
                    <form action="${signOut_url}" method="post">
                        <button type="submit" class="btn btn-primary">${SignOut}</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${role.equals('drug delivery')}">
                <div class="dropdown-menu" style="display: block; position: static">
                    <a class="dropdown-item" href="#">${Hello}, ${sessionScope.name}!</a>
                    <a class="dropdown-item" href="${home_url}">${Home}</a>
                    <a class="dropdown-item" href="set-languageLocale?lang=en"><img src="${engLogo}"
                                                                                    width="45" height="50"
                                                                                    alt="engLogo"></a>
                    <a class="dropdown-item" href="set-languageLocale?lang=ru"><img src="${ruLogo}"
                                                                                    width="45" height="50" alt="ruLogo"></a>
                    <form action="${signOut_url}" method="post">
                        <button type="submit" class="btn btn-primary">${SignOut}</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${role.equals('doctor')}">
                <div class="dropdown-menu" style="display: block; position: static">
                    <a class="dropdown-item" href="#">${Hello}, ${sessionScope.name}!</a>
                    <a class="dropdown-item" href="${home_url}">${Home}</a>
                    <a class="dropdown-item" href="${doctor_schedule_url}">${MySchedule}</a>
                    <a class="dropdown-item" href="${doctor_patients_url}">${ListOfPatients}</a>
                    <a class="dropdown-item" href="${doctor_adverse_url}">${ListOfAdverseEvents}</a>
                    <a class="dropdown-item" href="set-languageLocale?lang=en"><img src="${engLogo}"
                                                                                    width="45" height="50"
                                                                                    alt="engLogo"></a>
                    <a class="dropdown-item" href="set-languageLocale?lang=ru"><img src="${ruLogo}"
                                                                                    width="45" height="50" alt="ruLogo"></a>
                    <form action="${signOut_url}" method="post">
                        <button type="submit" class="btn btn-primary">${SignOut}</button>
                    </form>
                </div>
            </c:if>
        </div>
        <div class="span9">
            <jsp:doBody/>
        </div>
    </div>
    <div class="container-fluid text-center bg-lightgray" id="footer">
        <div class="span12">
            <p>Epam Lab21 by Tleuzhan</p>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
        integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
        crossorigin="anonymous"></script>

</body>
</html>