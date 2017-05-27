
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<c:url var="event_detail_url" value="/do/event/details"/>


<fmt:bundle basename="i18n">
    <fmt:message key="btn.Send" var="Send"/>
</fmt:bundle>


<mytag:mainPattern role="${sessionScope.role}">

   <c:if test="${role.equals('patient')}">
    <div class="bd-example" data-example-id="">
        <ul class="list-group">
         <c:forEach items="${tasksName}" var="task" varStatus="progress">
                <c:choose>
                <c:when test="${tasksProgress[progress.index]==100}">
                    <li class="list-group-item justify-content-between list-group-item-success">
                        <c:out value="${task}" />
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="list-group-item justify-content-between list-group-item-">
                        <c:out value="${task}" />
                    </li>
                </c:otherwise>
                </c:choose>
        </c:forEach>
        </ul>
    </div>
   </c:if>
    <c:if test="${role.equals('doctor')}">

            <c:forEach items="${tasksName}" var="task" varStatus="progress">
                    <form method="post" action="">
                        <div class="card w-75">
                            <div class="card-block">
                                <input type="hidden" name="eventId" value="${eventId}">
                                <input type="hidden" name="taskId" value="${tasksId[progress.index]}">
                                <h3 class="card-title">${task}</h3>
                                <button type="submit" class="btn btn-primary">${Send}</button>
                            </div>
                        </div>
                    </form>

            </c:forEach>

    </c:if>

</mytag:mainPattern>
