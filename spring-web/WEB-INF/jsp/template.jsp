<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>
  <head>
    <title>TechConf - <tiles:getAsString name="pageTitle"/></title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
  </head>
  <body>
    <h1><a href="index.html" style="color: #E9601A">TechConf</a></h1>	
    <p id="titleblock" style="font-size: larger;"><tiles:getAsString name="pageTitle"/></p>
    <tiles:insert attribute="body"/>
  </body>
</html>