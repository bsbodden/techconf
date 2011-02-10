<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <p>Sessions</p>
    <ul>
    <c:forEach var="session" items="${sessions}">
      <span style="text-decoration: underline; font-weight: bold;"><a href="displaySession.htm?id=${session.sessionId}">${session.presentation.title}</a></span>
      <br/>
      ${session.presentation.presenterName}<br/>
      <span font-weight: bold;">Time: <span> 
      <fmt:formatDate value="${session.dateTimeBegin}" type="date" dateStyle="full"/> - 
      <fmt:formatDate value="${session.dateTimeEnd}" type="date" dateStyle="full"/><br/>
      <span font-weight: bold;">Location: <span> 
      ${session.roomName}<br/>
      <span font-weight: bold;">Track: <span>  
      ${session.presentation.track}<br/><br/> 
    </c:forEach>
    </ul>