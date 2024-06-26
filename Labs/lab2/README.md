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

On exercise 3, I used the template given by the professor, which is in the directory _**lab2_2/geocoding**_ , in the folder _**integration**_.
The following commands were runned in the terminal:

```bash
mvn test -Dtest=AddressResolverIT
```
that executed the tests in about 7 seconds

```bash
mvn install failsafe:integration-test
```
that executed the tests in about 14 seconds
