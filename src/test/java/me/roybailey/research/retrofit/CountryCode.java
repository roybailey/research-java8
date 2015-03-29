package me.roybailey.research.retrofit;

/**
 * Created by roybailey on 29/03/2015.
 */
public class CountryCode {

    private String code;
    private String name;

    public CountryCode(String code, String name) {
        this.code = code;
        this.name = name;
    }


    @Override
    public String toString() {
        return "CountryCode{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
