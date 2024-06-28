package SortStrategies;

import Movie.Movie;
import MovieCollection.MovieCollection;

import java.util.Comparator;

public class SortByYearDescending implements SortStrategy {
    @Override
    public void sort(MovieCollection movies) {
        movies.sort(Comparator.comparing(Movie::getYear).reversed());
    }
}
