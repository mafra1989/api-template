# Definição de build para a imagem do Spring boot
FROM openjdk:17-alpine as build

# Define o diretório de trabalho atual dentro da imagem
WORKDIR /app

# Copia o executável maven para a imagem
COPY mvnw .
COPY .mvn .mvn

# Copia o arquivo pom.xml
COPY pom.xml .

# Substitui \r\n por \n no arquivo mvnw - resolve o problema do Windows
RUN dos2unix ./mvnw

# Torna o arquivo executável
RUN chmod +x ./mvnw

# Download das dependencias do pom.xml
RUN ./mvnw dependency:go-offline -B

# Copia fonte do projeto
COPY src src

# Package da aplicação
RUN ./mvnw package
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Definição de produção para a imagem do Spring boot
FROM openjdk:17-alpine as production

# Copia as dependencias para o build artifact
ARG DEPENDENCY=/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Roda a aplicação Spring boot
ENTRYPOINT ["java", "-cp", "app:app/lib/*","br.com.renner.Application"]