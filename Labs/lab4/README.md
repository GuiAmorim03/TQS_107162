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

## Lab 4.2

In this exercise, the same problem occured, so it was necessary to do the same wrapper of the maven version 3.9.6

## Lab 4.3

For this exercise, I recorded a new test (Alternative), in order to increase the difficulty of the exercise