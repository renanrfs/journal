# Etapa 1: Build da aplicação
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copie o arquivo pom.xml e instale as dependências do Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o restante do código fonte da aplicação e compile o projeto
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final do contêiner
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR gerado na etapa anterior
COPY --from=build /app/target/journal-1.0.0.jar /app/journal.jar

# Expõe a porta que a aplicação Spring Boot irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/journal.jar"]
