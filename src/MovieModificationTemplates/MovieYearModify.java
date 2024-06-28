package MovieModificationTemplates;

import java.util.Scanner;
import Movie.Movie;

public class MovieYearModify  extends MovieModificationTemplate{

    private int newYear;

    public void modifyYear(Movie movie) {
        retrieveData();
        if (validateData()) {
            modifyData(movie);
        }
    }

    protected void retrieveData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new year: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid year: ");
            scanner.next();
        }
        newYear = scanner.nextInt();

        while (newYear < 1880 || newYear > java.time.Year.now().getValue()) {
            System.out.println("Invalid year. Please enter a year between 1880 and the current year: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid year: ");
                scanner.next();
            }
            newYear = scanner.nextInt();
        }
    }

    protected boolean validateData() {
        return newYear >= 1880 && newYear <= java.time.Year.now().getValue();
    }

    protected void modifyData(Movie movie) {
        movie.setYear(newYear);
    }

    @Override
    protected void modifyDataValue(Movie movie, String newValue) {
        movie.setYear(Integer.parseInt(newValue));
    }
}
