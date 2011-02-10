<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <p>Speaker Blogs</p>
    <ul>
    <c:forEach var="blog" items="${blogs}">
      <li><a href="${blog.link}">${blog.title}</a>
    </c:forEach>
    </ul>
