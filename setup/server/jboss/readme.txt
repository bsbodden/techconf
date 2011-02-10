=========
Downloads
=========

Download JBoss version jboss-4.0.3RC1, use the Installer version for Windows to simplifyt the install

The root directory of the install is %JBOSS_HOME% 

=============
Server Config
=============

//
// Libraries
//

Copy the file 
  mysql-connector-java-3.0.14-production-bin.jar* 
  *included in the setup/server/jboss directory
To
  %JBOSS_HOME%/server/default/lib directory

Deploy 
  DynaDTO Service Archive (dynadto-1.0.sar) **
To
  %JBOSS_HOME%/server/default/deploy

** This might be added to a deploy task soon!


//
// Data Source
//

Copy the
Change the <connection-url> element in the provided file mysql-ds.xml
Copy the file mysql-ds.xml to the JBoss deploy directory***
***This assumes you have created the MySQL database, see the setup/db/mysql directory for instructions

=================
Deploying the EAR
=================
TechConf EAR is Deployed to a JBoss-4.0.3RC1 Instance
Using the 'default' server configuration
That is, to deploy the EAR (techconf.ear) copy it to the 
%JBOSS_HOME%/server/default/deploy

//
// application log file
//
Techconf uses Commons Logging for all of its logging. In JBoss,
commons-logging defaults to using Log4J. Add the following entries
to the file log4j.xml located in the %JBOSS_HOME%/server/default/conf
Change the priority value to the DEBUG level desired.

   <appender name="TechConfLog" class="org.apache.log4j.FileAppender">
      <errorHandler class="org.jboss.logging.util.OnlyOnceErrorHandler"/>
      <param name="Append" value="false"/>
      <param name="File" value="${jboss.server.home.dir}/log/techconf.log"/>
      <layout class="org.apache.log4j.PatternLayout">
         <param name="ConversionPattern" value="%d %-5p [%c] %m%n"/>
      </layout>
   </appender>
   
   <category name="com.integrallis.techconf" additivity="false">
      <appender-ref ref="TechConfLog"/>
      <priority value="TRACE"/>
   </category>

//
// JBoss mail service
//

Below is a sample configuration for the JBoss 
%JBOSS_HOME%/server/default/deploymail-service.xml 
The example below works with a POP3/SMTP enabled gmail account. 
Modify to use your own settings

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE server>
<server>
  <!-- ==================================================================== -->
  <!-- Mail Connection Factory                                              -->
  <!-- ==================================================================== -->
  <mbean code="org.jboss.mail.MailService"
         name="jboss:service=Mail">
    <attribute name="JNDIName">java:/Mail</attribute>
    <attribute name="User">youremail@gmail.com</attribute>
    <attribute name="Password">YOURPASSWORD</attribute>
    <attribute name="Configuration">
       <configuration>
          <property name="mail.store.protocol" value="pop3"/>
          <property name="mail.transport.protocol" value="smtp"/>
          <property name="mail.user" value="techconf@gmail.com"/>
          <property name="mail.pop3.host" value="pop.gmail.com"/>
          <property name="mail.smtp.host" value="smtp.gmail.com"/>
          <property name="mail.smtp.port" value="465"/>
          <property name="mail.smtp.user" value="youremail@gmail.com"/>
          <property name="mail.smtp.password" value="YOURPASSWORD"/>
          <property name="mail.smtp.auth" value="true"/>
          <property name="mail.smtp.starttls.enable" value="true" />
          <property name="mail.from" value="noreply@techconf.org"/>
          <property name="mail.debug" value="true"/>
          <property name="mail.smtp.socketFactory.class" value="javax.net.ssl.SSLSocketFactory" />
          <property name="mail.smtp.socketFactory.port" value="465" />
       </configuration>
       <depends>jboss:service=Naming</depends>
    </attribute>
  </mbean>
</server>

