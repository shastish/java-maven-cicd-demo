# Java Maven CI/CD Demo

A small Java 17 and Maven project used to demonstrate Jenkins continuous integration.

## Build locally

```sh
mvn --batch-mode clean verify
```

## Jenkins

Configure the `java-maven-ci` Jenkins job as **Pipeline script from SCM**, select Git, and use `https://github.com/shastish/java-maven-cicd-demo.git`, branch `*/main`, and script path `Jenkinsfile`. The pipeline checks out source, runs Maven verification and unit tests, publishes JUnit results, and archives the JAR. It does not deploy. The Jenkins build agent needs Java 17 and Maven, or the repository can add a Maven Wrapper.
