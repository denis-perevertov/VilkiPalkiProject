FROM tomcat:9.0
EXPOSE 8080
ADD target/vilkipalki.war vilkipalki.war
ADD target/vilkipalki.war /usr/local/tomcat/webapps
RUN sh "cp /usr/local/tomcat/webapps.dist/manager /usr/local/tomcat/webapps"
CMD catalina.sh run