package SortStrategies;

import Movie.Movie;
import MovieCollection.MovieCollection;

import java.util.Comparator;

public class SortByTitleDescending implements SortStrategy {
    @Override
    public void sort(MovieCollection movies) {
        movies.sort(Comparator.comparing(Movie::getTitle).reversed());
    }
}
