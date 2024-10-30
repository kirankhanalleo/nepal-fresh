# Use official Tomcat 10 image with JDK 21
FROM tomcat:10.1-jdk21

# Remove the default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file to the Tomcat webapps directory
COPY /target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
