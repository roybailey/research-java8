package me.roybailey.research.sparkjava;

import me.roybailey.research.lambda.collection.Gender;
import me.roybailey.research.lambda.collection.Person;

import static spark.Spark.*;


public class SparkJsonApp {

    public static void main(String[] args) {

        port(4545);
        staticFileLocation("/webapp");

        get("/hello", (request, response) -> {
            return "Hello World!";
        });

        get("/hello-json", "application/json", (request, response) -> {
            return new Person.Builder().givenName("Roy").gender(Gender.MALE).age(40).build();
        }, new JsonTransformer());
    }
}
