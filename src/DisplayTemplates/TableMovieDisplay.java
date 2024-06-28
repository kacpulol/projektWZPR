package DisplayTemplates;
import Movie.Movie;

public class TableMovieDisplay extends MovieDisplayTemplate{

        @Override
        protected void displayHeader(Movie movie) {}

        @Override
        protected void displayDetails(Movie movie) {
            String data = "| Year: " + movie.getYear() + " | Rating: " + movie.getRating() + " | Director: " + movie.getDirector() + " | Genre: " + movie.getGenre() + " |";
            String titleLine = "+" + "-".repeat((((data.length()) - movie.getTitle().length())/2) - 2) + " " + movie.getTitle() + " " + "-".repeat( (((data.length()) - movie.getTitle().length())/2 - 2)) + "+";
            String bottomLine = "+" + "-".repeat(data.length() - 2) + "+" ;
            System.out.println(titleLine + "\n" + data + "\n" + bottomLine + "\n");
        }

        @Override
        protected void displayFooter(Movie movie) {}

}
