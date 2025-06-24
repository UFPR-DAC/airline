# Etapa de build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /workspace/app

# Copia tudo pro contexto de build
COPY . .

# Dá permissão de execução para o wrapper do Maven (só por segurança)
RUN chmod +x ./mvnw

# Executa build com skip dos testes
RUN ./mvnw clean install -DskipTests

# Etapa final
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o .jar gerado da etapa anterior para a imagem final
COPY --from=builder /workspace/app/target/*-SNAPSHOT.jar app.jar

# Define o comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
 