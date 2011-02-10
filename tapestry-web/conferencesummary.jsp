<%@ page contentType="text/html; charset=iso-8859-1" language="java" 
         import="org.dynadto.*,java.util.*,javax.naming.*,com.integrallis.techconf.ejb.*,com.integrallis.techconf.dto.*,com.integrallis.techconf.service.*" 
         errorPage="" %>
<%!
  private ConferenceService conferenceService = null;
  private ScheduleService scheduleService = null;
  

  public void jspInit () {
    try {
      InitialContext ctx = new InitialContext();
      conferenceService = (ConferenceService) ctx.lookup(
                  ConferenceService.class.getName());
      scheduleService = (ScheduleService) ctx.lookup(
                  ScheduleService.class.getName());  

    } catch (Exception e) {
      e.printStackTrace ();
    }
  }


%>
<!--
private ScheduleService scheduleService = null;



-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>TechConf - Conference Summary DTO Test Page</title>
</head>

<body>
<h1>Conference Summary DynaDTO Test Page</h1>
<h2>Contents:</h2>
<table width="100%"  border="0" summary="Name/Value Pairs in [dtoname] DTO">
  <caption>
  DTO Contents
  </caption>
  <tr>
    <th width="25%">Name</th>
    <th width="75%">Value</th>
  </tr>
<%
  Integer scheduleEntryId = new Integer(1);
  Date dateTime = new Date(new Date().getTime() + 60000);
  String message = "Hey Dude, You Got Reminded";

  scheduleService.createReminder(scheduleEntryId, dateTime, message);
  ConferenceSummary conferenceSummary = conferenceService.getConferenceSummary(1);
  MapDTO mapDTO = conferenceSummary.getMap();
  for (Iterator i = mapDTO.keySet().iterator(); i.hasNext();) {
    String key = (String) i.next();
	Object value = mapDTO.get(key);
%>
  <tr>
    <td nowrap="nowrap"><%=key%></td>
    <td><%=value%></td>
  </tr>
<%
  }
%>
</table>
<p>&nbsp;</p>
<table width="100%"  border="0" summary="Name/Value Pairs in [dtoname] DTO">
  <caption>
  DTO Contents
  </caption>
  <tr>
    <th width="25%">Name</th>
    <th width="75%">Value</th>
  </tr>
<%
  List blogEntries = conferenceService.getBlogEntriesForPresenter(3);
  for (Iterator i = blogEntries.iterator(); i.hasNext();) {
    BlogEntry blogEntry = (BlogEntry) i.next();
    String title = blogEntry.getTitle();
	String link = blogEntry.getLink();
    String description = blogEntry.getDescription();
%>
  <tr>
    <td nowrap="nowrap"><a href="<%=link%>"><%=title%></a></td>
    <td><%=description%></td>
  </tr>
<%
  }
%>
</table>

</table>
<p>&nbsp;</p>
<table width="100%"  border="0" summary="Name/Value Pairs in [dtoname] DTO">
  <caption>
  DTO Contents
  </caption>
  <tr>
    <th width="25%">Name</th>
    <th width="75%">Value</th>
  </tr>
<%
  List presenters = conferenceService.getPresentersSummaryList(1);
  for (Iterator i = presenters.iterator(); i.hasNext();) {
    PresenterSummary presenterSummary = (PresenterSummary) i.next();
    int id = presenterSummary.getPresenterId().intValue();
	String name = presenterSummary.getName();
%>
  <tr>
    <td nowrap="nowrap"><%=id%></td>
    <td><%=name%></td>
  </tr>
<%
  }
%>
</table>


</body>
</html>
