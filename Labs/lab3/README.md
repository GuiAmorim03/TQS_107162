# Lab 3 - notebook

## Lab 3.1

### a) Identify a couple of examples that use AssertJ expressive methods chaining.

#### A_EmployeeRepositoryTest.java
- assertThat(found).isEqualTo(alex);
- assertThat(fromDb).isNull();
- assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
- ...

#### B_EmployeeService_UnitTest.java
- assertThat(found.getName()).isEqualTo(name);
- assertThat(fromDb.getName()).isEqualTo("john");
- assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
- ...

#### C_EmployeeController_WithMockServiceTest.java

#### D_EmployeeController_WithMockMvcTest.java
- assertThat(found).extracting(Employee::getName).containsOnly("bob");

#### E_EmployeeRestControllerTemplateIT.java
- assertThat(found).extracting(Employee::getName).containsOnly("bob");
- assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
- assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");


### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

In the following code, the behavior of the repository is mocked, using Mockito (`Mochito.when()`)

```java
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```

### c) What is the difference between standard @Mock and @MockBean?
    
`@Mock` is used to create a mock object of a class/interface and it's used in unit tests, while `@MockBean` is used in integration test with SpringBoot, to add mock objects to an application context. 
    
### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
This file is a configuration file intended for integration tests in a Spring Boot application and contains properties to the integration test environment.
So, this file will be used when running integration tests in a Spring Boot application.

### e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

The mainly difference is that the tests D and E use the `@SpringBootTest` annotation, so they are integration tests, while the test C is a unit test, that focuses in the controller layer.
Also, the implementation D use `MockMvc` to simulate HTTP solicitations, while the implementation E use `TestRestTemplate` to make real HTTP solicitations.

## Lab 3.2

## Lab 3.3
