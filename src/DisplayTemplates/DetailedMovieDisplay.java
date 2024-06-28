package DisplayTemplates;
import Movie.Movie;

public class DetailedMovieDisplay extends MovieDisplayTemplate {

    @Override
    protected void displayDetails(Movie movie) {
        System.out.println("Year: " + movie.getYear());
        System.out.println("Rating: " + movie.getRating());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Genre: " + movie.getGenre());
    }

    @Override
    protected void displayFooter(Movie movie) {
        System.out.print("=====");
        for (int i = 0; i < movie.getTitle().length() + 2; i++) {
            System.out.print("=");
        }
        System.out.println("=====\n");
    }
}