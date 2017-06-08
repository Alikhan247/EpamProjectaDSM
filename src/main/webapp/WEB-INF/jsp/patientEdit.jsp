<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<fmt:bundle basename="i18n">
    <fmt:message key="person.Telephone" var="Telephone"/>
    <fmt:message key="person.Activity" var="StatusActiv"/>
    <fmt:message key="person.Text" var="EditPhoneOrStatus"/>
    <fmt:message key="btn.Edit" var="Edit"/>
</fmt:bundle>

<mytag:mainPattern role="${sessionScope.role}">
    <div class="container col-md-9">
        <div class="card" style="width: 600px;">
            <div class="card-block">
                <h4 class="card-title">${patient.patientCode}</h4>
                <p class="card-text">${Telephone} ${patient.phoneNumber}</p>
                <p class="card-text">${EditPhoneOrStatus}:</p>
                <p class="card-text">${research.activationStatus}</p>
            </div>
            <form method="post" action="">
                <div class="form-group">
                    <div class="form-group row">
                        <input type="tel" value="${patient.phoneNumber}" name="phone">
                    </div>
                    <div class="form-group row">
                        <label>${StatusActiv}</label>
                        <select class="form-control form-control-lg" name="activationStatus">
                            <option disabled></option>
                            <option value="${true}">${activaionStatuses[0]}</option>
                            <option value="${false}">${activaionStatuses[1]}</option>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">${Edit}</button>
            </form>
        </div>
    </div>
</mytag:mainPattern>