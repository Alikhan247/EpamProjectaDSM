<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<c:url var="edit_staff_url" value="/do/staff/edit"/>
<fmt:bundle basename="i18n">
    <fmt:message key="person.Telephone" var="Telephone"/>
    <fmt:message key="person.Activity" var="StatusActiv"/>
    <fmt:message key="person.Text" var="EditPhoneOrStatus"/>
    <fmt:message key="btn.Edit" var="Edit"/>
</fmt:bundle>
<mytag:mainPattern role="${sessionScope.role}">
    <div class="container col-md-9" id="editStaffForm">
        <div class="card" style="width: 500px;">
            <div class="card-block">
                <h4 class="card-title">${staff.name} ${staff.surname}</h4>
                <p class="card-text">${Telephone} ${staff.phoneNumber}</p>
                <p class="card-text">${EditPhoneOrStatus}:</p>
                <p class="card-text">${staff.activity_status}</p>
            </div>
            <form method="post" action="">
                <div class="form-group">
                    <div class="form-group row">
                        <input type="tel" value="${staff.phoneNumber}" name="phone">
                    </div>
                    <div class="form-group row">
                        <label>${StatusActiv}</label>
                        <select class="form-control form-control-lg" name="activity_status">
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
    </div>
</mytag:mainPattern>