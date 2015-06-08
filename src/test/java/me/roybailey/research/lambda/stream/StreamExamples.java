package me.roybailey.research.lambda.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExamples {

    public static void main(String[] args) {
        List<Movie> movies = Movie.getMovieList();       
        System.out.println("-Find the average of all the movie ratings-");
        System.out.println(
                movies
                .stream()
                .mapToDouble(Movie::getRating)
                .average()
                .getAsDouble());
       
        System.out.println("-Filter movies with rating > 8.5-");
        movies
        .stream()
        .filter(movie -> movie.getRating() > 8.5)
        .forEach(System.out::println);
        
        System.out.println("-What is the lowest rating-");
        System.out.println(
                movies
                .stream()
                .mapToDouble(Movie::getRating)
                .min()
                .getAsDouble());
        
        
        System.out.println("-Find movie with the lowest rating-");
        System.out.println(
                movies
                .stream()
                .min(Comparator.comparing(Movie::getRating))
                .get());        
        
        System.out.println("-Count of movies with rating > 8.5-");
        System.out.println(
                movies
                .stream()
                .mapToDouble(Movie::getRating)
                .filter(rating -> rating > 8.5)
                .count());
        
        System.out.println("-Get the summary statistics based on rating-");
        System.out.println(
                movies
                .stream()
                .mapToDouble(Movie::getRating)
                .summaryStatistics());
        
        System.out.println("-List of horror movies, comma separated-");
        System.out.println(
                movies
                .stream()
                .filter(movie -> movie.getGenre() == Genre.HORROR)
                .map(Movie::getName)
                .collect(Collectors.joining(", ")));
        
        System.out.println("-List of action movies, print using foreach-");
        movies
        .stream()
        .filter(movie -> movie.getGenre() == Genre.ACTION)
        .map(Movie::getName)
        .forEach(System.out::println);
        
        System.out.println("-List of action movies, sorted, print using foreach-");
        movies
        .stream()
        .filter(movie -> movie.getGenre() == Genre.ACTION)
        .map(Movie::getName)
        .sorted()
        .forEach(System.out::println);
                
        System.out.println(
                "-List of action movies, sorted, mapped to upper case, print using foreach-");        
        movies
        .stream()
        .filter(movie -> movie.getGenre() == Genre.ACTION)
        .map(Movie::getName)
        .map(String::toUpperCase)
        .sorted()
        .forEach(System.out::println);
        
        System.out.println("-Top 3 action movies-"); 
        movies
        .stream()
        .filter(movie -> movie.getGenre() == Genre.ACTION)
        .sorted(Comparator.comparing(Movie::getRating).reversed())
        .map(Movie::getName)
        .limit(3)
        .forEach(System.out::println);

        System.out.println("-Find the top rated movie-");
        System.out.println(
                movies
                .stream()
                .sorted(Comparator.comparing(Movie::getRating).reversed())
                .findFirst()
                .get());
        
        System.out.println("-Does the movie list have rating >= 9.0?-"
                + movies
                .stream()
                .anyMatch(movie -> movie.getRating() >= 9.0));
    }
}
