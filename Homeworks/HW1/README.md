# Desenvolvimento do Homework 1

## Criação de dados default na API

para a criação de dados default na API, foi criado um ficheiro java (`InsertData.java`) que é corrido quando a aplicação inicia e cria dados default na API, através do comando:
```bash
mvn spring-boot:run
```
Para isso, foi utilizada a anotação `@Component` para que o Spring Boot soubesse que devia correr esse componente ao inicializar a aplicação 

