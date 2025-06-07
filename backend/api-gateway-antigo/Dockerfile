# ---------- Build ----------
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /workspace/app

COPY . .

# Maven wrapper
RUN chmod +x ./mvnw

# Build sem testes
RUN ./mvnw clean install -DskipTests

# ---------- Final ----------
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=builder /workspace/app/target/api-gateway-*.jar app.jar

# Porta
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
