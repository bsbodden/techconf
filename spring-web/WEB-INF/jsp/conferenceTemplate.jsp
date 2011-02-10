<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    <title>TechConf - <tiles:getAsString name="pageTitle"/></title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
  </head>
  <body>
    <h1><a href="index.html" style="color: #E9601A">TechConf</a></h1>	
    <p id="titleblock" style="font-size: larger;"><tiles:getAsString name="pageTitle"/></p>
	<div id="header" class="borderedBlock">
	 <table width="100%">
      <tr>
        <td>
          <h1>${conference.conferenceTitle}</h1>    
          <!-- <h2><c:out value="${conference.conferenceSubtitle}"/></h2> -->
        </td>
      </tr>      
	  <tr>
	   <td>
         <!-- dates and locale -->
         <h5>
           <fmt:formatDate value="${conference.startDate}" type="date" dateStyle="full"/> - 
           <fmt:formatDate value="${conference.endDate}" type="date" dateStyle="full"/> -	
           <c:out value="${conference.venueAddressLine1}"/> ,	
           <c:out value="${conference.venueAddressLine2}"/> - 
           <c:out value="${conference.venuePhone}"/>
         </h5>
	   </td>
	  </tr>
	 </table>
	</div>
	<table width="100%">
      <tbody>
        <tr>
          <th style="width: 20%; text-align: justify;" id="left" class="borderedBlock">
          <a href="index.html">Home</a><br>
          <a href="listKeynotes.htm?id=${conference.conferenceId}">Keynotes</a><br>
          <a href="listSpeakers.htm?id=${conference.conferenceId}">Speakers</a><br>
          <a href="listSessions.htm?id=${conference.conferenceId}">Sessions</a><br>
          <a href="listBlogs.htm?id=${conference.conferenceId}">Blogs</a><br>
          Schedule<br>
          </th>
          <td id="body" class="borderedBlock"><tiles:insert attribute="body"/></td>
        </tr>
      </tbody>
	</table>
	<div id="footer" class="borderedBlock">
	 Copyright&copy; <a href="http://www.integrallis.com">Integrallis Software, LLC</a>.
	</div>
  </body>
</html>