# Lab 2 - notebook

## Lab 2.1

### Prepare a mock to substitute the remote stockmarket service (@Mock annotation)
Using @Mock, it creates a simulate object for _**IStockmarketService**_ and controls his actions during the test, instead of relying on the actual service
Using @InjectMocks, it creates a simulate object for _**StocksPortfolio**_ and injects the mock object into it.

### Using Hamcrest
[Hamcrest Guide](https://www.baeldung.com/java-junit-hamcrest-guide)

## Lab 2.2

### Which is the SuT (subject under test)? Which is the service to mock?
The SuT is the _**AdressResolverService**_ and the service to mock is the _**ISimpleHttpClient**_

## Lab 2.3

Na realização do exercício 3, foi usado o template fornecido pelo professor que se encontra no diretório _**lab2_2/geocoding**_ e na pasta de testes _**integration**_, tendo sido corrido os comandos:

```bash
mvn test -Dtest=AddressResolverIT
```
que executou os testes em cerca de 7 segundos

```bash
mvn install failsafe:integration-test
```
que executou os testes em cerca de 14 segundos