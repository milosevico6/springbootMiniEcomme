# Jednostavan Dockerfile
FROM eclipse-temurin:21-jre

# Postavljanje radnog direktorijuma
WORKDIR /app

# Kopiranje JAR fajla (već build-ovan lokalno)
COPY target/mini_e_commerce-1.0.0-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Pokretanje aplikacije
CMD ["java", "-jar", "app.jar"]

