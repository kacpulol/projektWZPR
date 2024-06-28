package DisplayTemplates;
import Movie.Movie;

public abstract class MovieDisplayTemplate {
    public final void display(Movie[] movies) {
        for (Movie movie : movies) {
            displayHeader(movie);
            displayDetails(movie);
            displayFooter(movie);
        }
    }

    public final String displayString(Movie[] movies) {
        String result = "";
        for (Movie movie : movies) {
            result += displayDetailsString(movie);
        }
        return result;
    }


    protected void displayHeader(Movie movie) {
        System.out.println("===== " + movie.getTitle() + " =====");
    };

    protected abstract void displayDetails(Movie movie);

    protected abstract void displayFooter(Movie movie);

    protected String displayDetailsString(Movie movie) {
        return  movie.getMovieDetails();
    };
}
