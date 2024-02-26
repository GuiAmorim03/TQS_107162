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