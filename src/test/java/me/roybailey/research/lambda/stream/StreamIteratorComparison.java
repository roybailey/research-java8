package me.roybailey.research.lambda.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StreamIteratorComparison {

    public static void main(String[] args) {
        List<Movie> movies = Movie.getMovieList();
        System.out.println("-Find top 3 action movies using iterator-");
        List<Movie> actionMovies = new ArrayList<Movie>();
        for (Movie movie : movies) {
            if (movie.getGenre() != Genre.ACTION) {
                continue;
            }
            actionMovies.add(movie);
        }
        Collections.sort(actionMovies, new Comparator<Movie>() {

            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getRating(), m1.getRating());
            }
        });
        for (int i = 0; i < 3; i++) {
            System.out.println(actionMovies.get(i).getName());
        }

        System.out.println("-Top 3 action movies using stream-");
        movies.stream()
              .filter(movie -> movie.getGenre() == Genre.ACTION)
              .sorted(Comparator.comparing(Movie::getRating).reversed())
              .limit(3)
              .map(Movie::getName)
              .forEach(System.out::println);
    }
}
