# Lab 4 - notebook

## Lab 4.1

Running maven, the tests were not being runned
```bash
Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
```
While, running them, through VS Code, the tests were executed with success

To solve this problem, it was necessary to do a wrapper of the maven version 3.9.6
```bash
mvn wrapper:wrapper -Dmaven=3.9.6
```
After that, the tests were executed with success, using the command `./mvnw test`

