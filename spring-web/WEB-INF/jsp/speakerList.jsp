<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <p>Speakers</p>
    <ul>
    <c:forEach var="speaker" items="${speakers}">
      <li><a href="displaySpeaker.htm?id=${speaker.presenterId}">${speaker.name}</a>
    </c:forEach>
    </ul>
