package MovieModificationTemplates;

import java.util.Scanner;
import Movie.Movie;

public class MovieDirectorModify extends MovieModificationTemplate{

        private String newDirector;

        @Override
        protected void retrieveData() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter new director: ");
            newDirector = scanner.nextLine();

            while (newDirector == null || newDirector.trim().isEmpty()) {
                System.out.println("Invalid director. Please enter a valid director: ");
                newDirector = scanner.nextLine();
            }
        }

        @Override
        protected boolean validateData() {
            return newDirector != null && !newDirector.isEmpty();
        }

        @Override
        protected void modifyData(Movie movie) {
            movie.setDirector(newDirector);
        }

        @Override
        protected void modifyDataValue(Movie movie, String newValue) {
            movie.setDirector(newValue);
        }
}
