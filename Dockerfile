FROM tomcat:10.1
EXPOSE 8080
ADD target/vilkipalki.war vilkipalki.war
ADD target/vilkipalki.war /usr/local/tomcat/webapps
CMD catalina.sh run