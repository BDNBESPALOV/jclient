.DEFAULT_GOAL := run
run:build
	java -jar target/jclient-10.11.20-jar-with-dependencies.jar
build:
	mvn clean package
clean:
	rm target/jclient-10.11.20-jar-with-dependencies.jar
install:
	mvn install

