# Desenvolvimento do Homework 1

## Criação de dados default na API

para a criação de dados default na API, foi criado um ficheiro java (`InsertData.java`) que é corrido quando a aplicação inicia e cria dados default na API, através do comando:
```bash
mvn spring-boot:run
```
Para isso, foi utilizada a anotação `@Component` para que o Spring Boot soubesse que devia correr esse componente ao inicializar a aplicação 

## Correr a aplicação

### Frontend
Para correr a aplicação, primeiro inicia-se o frontend, no diretório `\frontend` com o comando:
```bash
python3 -m http.server
```
E o website estará disponível em `http://localhost:8000/`

### Backend
De seguida, inicia-se o backend, no diretório `\backend` com o comando:
```bash
mvn spring-boot:run
```

## SonarQube

### Prepare a local instance of SonarQube
```bash
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
```

O Sonar está disponível em [http://localhost:9000](http://localhost:9000)
As credenciais default são: admin/admin

### Criar o projeto no SonarQube

Primeiramente, é necessário adicionar as dependências do Jacoco ao `pom.xml` do backend:

De seguida, no repositório `\backend`, correr o seguinte comando:
```bash
mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=homework-tqs -Dsonar.login=admin -Dsonar.password=tqsHW
```

## Documentação da API

A documentação da API está disponível em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Foi necessário adicionar a dependência do Swagger ao `pom.xml` do backend:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```