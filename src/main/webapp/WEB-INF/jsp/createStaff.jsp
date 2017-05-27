
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
    <fmt:message key="btn.Registraion" var="btnCreate"/>
</fmt:bundle>

<c:url var="create_staff_url" value="/do/createStaff"/>

<mytag:mainPattern role="${sessionScope.role}">
    <div class="container col-md-6" id="createStaffForm">
            <form method="post" action="${create_staff_url}">
                <div class="form-group">
                    <label>${name}</label>
                    <input type="text" class="form-control" name="name">
                </div>
                <div class="form-group">
                    <label>${surname}</label>
                    <input type="text" class="form-control" name="surname">
                </div>
                <div class="form-group row">
                    <label>${telephone}</label>
                    <input  type="tel" value="7-(___)-___-____" name="phone">
                </div>
                <div class="form-group">
                    <label>${password}</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <div class="form-group">
                    <label>${repeatPassword}</label>
                    <input type="password" class="form-control" name="confirmedPassword">
                </div>
                <div class="form-group">
                    <label>${position}</label>
                    <select class="form-control" name="role">
                        <option>coordinator</option>
                        <option>doctor</option>
                        <option>drug delivery</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">${btnCreate}</button>
            </form>
    </div>
</mytag:mainPattern>