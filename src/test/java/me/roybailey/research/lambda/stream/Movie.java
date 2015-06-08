package me.roybailey.research.lambda.stream;

import java.util.Arrays;
import java.util.List;

public class Movie {
    private String name;
    private Genre genre;
    private double rating;

    Movie(String name, Genre genre, double rating) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }

    public String toString() {
        return name + "(" + genre + ", " + rating + ")";
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }
    
    public static List<Movie> getMovieList(){
        return Arrays.asList(movies);
    }
        
    private static final Movie[] movies = new Movie[] { m("Green Mile", Genre.DRAMA, 8.5),
            m("Forrest Gump", Genre.DRAMA, 8.8),
            m("A Beautiful Mind", Genre.DRAMA, 8.2),
            m("The Notebook", Genre.ROMANTIC, 7.9),
            m("The Titanic", Genre.ROMANTIC, 7.7),
            m("Pretty Woman", Genre.ROMANTIC, 6.9),
            m("Notting Hill", Genre.ROMANTIC, 7),
            m("Inception", Genre.THRILLER, 8.8),
            m("The Game", Genre.THRILLER, 7.8),
            m("Seven", Genre.THRILLER, 8.7),
            m("The Dark Knight", Genre.THRILLER, 9),
            m("The Exorcist", Genre.HORROR, 8),
            m("The Shinning", Genre.HORROR, 8.5),
            m("The Cabin in the Woods", Genre.HORROR, 7),
            m("Insidious", Genre.HORROR, 6.8),
            m("The Avengers", Genre.ACTION, 8.2),
            m("Die Hard", Genre.ACTION, 8.3),
            m("Casino Royale", Genre.ACTION, 8),
            m("Star Wars", Genre.ACTION, 8.7), m("Thor", Genre.ACTION, 7) };
    
    private static Movie m(String name, Genre genre, double rating) {
        return new Movie(name, genre, rating);
    }
}
