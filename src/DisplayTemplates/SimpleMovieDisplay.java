package DisplayTemplates;
import Movie.Movie;

public class SimpleMovieDisplay extends MovieDisplayTemplate {

    @Override
    protected void displayHeader(Movie movie) {
        System.out.print(movie.getTitle());
    }

    @Override
    protected void displayDetails(Movie movie) {
        System.out.println(" (" + movie.getYear() + ") - Rating: " + movie.getRating());
    }

    protected void displayFooter(Movie movie) {
    }

}
