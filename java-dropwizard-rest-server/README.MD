Java Dropwizard minimum REST Server for testing purposes.

Instructions:
1. Launch App class with the following parameters: server <path-to-project>\src\main\resources\hello-world.yml
2. Try it with a simple GET request to for example http://localhost:8081/application/hello?name=toni

It should return a JSON response such as: {"content":"Hello toni!"}