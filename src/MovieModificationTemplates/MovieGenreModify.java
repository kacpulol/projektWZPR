package MovieModificationTemplates;

import java.util.Scanner;
import Movie.Movie;

public class MovieGenreModify extends MovieModificationTemplate{

        private String newGenre;

        @Override
        protected void retrieveData() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter new genre: ");
            newGenre = scanner.nextLine();

            while (newGenre == null || newGenre.trim().isEmpty()) {
                System.out.println("Invalid genre. Please enter a valid genre: ");
                newGenre = scanner.nextLine();
            }
        }

        @Override
        protected boolean validateData() {
            return newGenre != null && !newGenre.isEmpty();
        }

        @Override
        protected void modifyData(Movie movie) {
            movie.setGenre(newGenre);
        }

        @Override
        protected void modifyDataValue(Movie movie, String newValue) {
            movie.setGenre(newValue);
        }
}
