package MovieModificationTemplates;

import java.util.Scanner;
import Movie.Movie;

public class MovieRatingModify extends MovieModificationTemplate{

        private double newRating;

        @Override
        protected void retrieveData() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter new rating with comma (X,X): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid rating: ");
                scanner.next();
            }
            newRating = scanner.nextDouble();
            while (newRating < 0 || newRating > 10) {
                System.out.println("Invalid rating. Please enter a rating between 0 and 10: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid rating: ");
                    scanner.next();
                }
                newRating = scanner.nextDouble();
            }
        }

        @Override
        protected boolean validateData() {
            return newRating >= 0 && newRating <= 10;
        }

        @Override
        protected void modifyData(Movie movie) {
            movie.setRating(newRating);
        }

        @Override
        protected void modifyDataValue(Movie movie, String newValue) {
            movie.setRating(Double.parseDouble(newValue));
        }
}
