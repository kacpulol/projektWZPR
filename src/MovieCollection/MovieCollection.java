package MovieCollection;

import Movie.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieCollection {
    private List<Movie> movies;

    public MovieCollection() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Added movie: " + movie);
    }

    public void addMovieFromInput() {
        System.out.println("\n--- Adding movie from input --- \n");
        Movie movie = new Movie();
        movie.inputMovieDetails();
        movies.add(movie);
        System.out.println("Added movie: " + movie);
    }

    public void removeMovie(String title) {
        movies.removeIf(movie -> movie.getTitle().equals(title));
        System.out.println("Removed movie: " + title);
    }

    public void displayMovies() {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public Movie[] getMovies() {
        Movie[] movieArray = new Movie[movies.size()];
        for (int i = 0; i < movies.size(); i++) {
            movieArray[i] = movies.get(i);
        }
        return movieArray;
    }

    public List<Movie> getMoviesList() {
        return movies;
    }

    public void sort(Comparator<Movie> comparing) {
        movies.sort(comparing);
    }
}
