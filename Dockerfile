FROM openjdk:8-jre-slim

# Add the jar with all the dependencies
# Maven creates selenium-test.jar in the target folder of my workspace.
# We need this in some location - say - /usr/share/tag folder of the container
ADD  target/selenium-test.jar /usr/share/tag/selenium-test.jar
ADD  target/libs /usr/share/tag/libs

# Command line to execute the test
ENTRYPOINT ["/usr/bin/java", "-cp", "/usr/share/tag/selenium-test.jar:/usr/share/tag/libs/*", "org.testng.TestNG", "-testclass", "com.schogini.container.test.GoogleTest"]