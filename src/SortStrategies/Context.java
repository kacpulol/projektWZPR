package SortStrategies;
import MovieCollection.MovieCollection;

public class Context {
    private SortStrategy sortStrategy;

    public Context(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort(MovieCollection movies) {
        sortStrategy.sort(movies);
    }
}
