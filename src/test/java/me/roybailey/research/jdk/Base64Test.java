package me.roybailey.research.jdk;

import java.util.Base64;

/**
 * Testing base64 encoding/decoding
 */
public class Base64Test {

    public static void main(String[] args) {
        String toconvert = "test";
        if (args.length >= 1) {
            toconvert = args[0];
        }
        System.out.println("encoding " + toconvert);
        String base64login = new String(Base64.getEncoder().encode(toconvert.getBytes()));
        System.out.println("encoded  " + base64login);

        String jira = "curl -D- -X GET -H \"Authorization: Basic " + base64login + "\" -H \"Content-Type: application/json\" \"http://localhost:9090/rest/api/2/issue/RES-23\"";
        System.out.println(jira);
    }
}
