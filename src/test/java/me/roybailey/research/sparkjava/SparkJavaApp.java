package me.roybailey.research.sparkjava;

import static spark.Spark.*;

public class SparkJavaApp {
    public static void main(String[] args) {

        get("/hello", (request, response) -> {
            return "Hello World!";
        });

    }
}
