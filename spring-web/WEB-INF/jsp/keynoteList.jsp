<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <p>Currently Available Conferences</p>
    <ul>
    <c:forEach var="conference" items="${conferences}">
      <li><a href="displayConference.htm?id=${conference.conferenceId}">${conference.conferenceTitle}</a>
    </c:forEach>
    </ul>
