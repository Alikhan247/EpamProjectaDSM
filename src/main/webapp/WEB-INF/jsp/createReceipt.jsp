<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>


<c:url var="createReceipt_url" value="/do/createReceipt"/>

<mytag:mainPattern role="${sessionScope.role}">
    <div class="container col-md-6" id="createPatientFrom">
        <form method="post" action="">
            <h3>Create Receipt</h3>
            <p>${patient.patientCode}</p>
            <div class="form-group row">
                <label>Drug name</label>
                <select  name="drug_id" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${drugs}" var="drug">
                        <option value="${drug.id}">${drug.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group row">
                <label>Drug doze</label>
                <input type="text" class="form-control" name="drug_doze">
            </div>
            <button type="submit" class="btn btn-primary">Safe</button>
        </form>
    </div>
</mytag:mainPattern>