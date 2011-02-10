<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <table width="100%">
      <tr>
        <td>
          <h4>Abstract Submissions</h4>
        </td>
      </tr> 
      <tr>
        <td>
          Abstracts accepted from <fmt:formatDate value="${conference.abstractSubmissionStartDate}" type="date" dateStyle="full"/> until <fmt:formatDate value="${conference.abstractSubmissionEndDate}" type="date" dateStyle="full"/>
        </td>
      </tr> 
      <tr>
        <td>
          <h4>Tracks</h4>
        </td>
      </tr> 
      <tr>
        <td>
          <ul>
            <c:forEach var="track" items="${conference.tracks}">
              <li><a href="displayTrack.htm?id=${track.id}">${track.title}</a></li>
            </c:forEach>
          </ul>
        </td>
      </tr> 
    </table>
