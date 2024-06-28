package MovieModificationTemplates;

import java.util.Scanner;
import Movie.Movie;

public class MovieTitleModify extends MovieModificationTemplate {

    private String newTitle;

    @Override
    protected void retrieveData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new title: ");
        newTitle = scanner.nextLine();

        while (newTitle == null || newTitle.trim().isEmpty()) {
            System.out.println("Invalid title. Please enter a valid title: ");
            newTitle = scanner.nextLine();
        }
    }

    @Override
    protected boolean validateData() {
        return newTitle != null && !newTitle.isEmpty();
    }

    @Override
    protected void modifyData(Movie movie) {
        movie.setTitle(newTitle);
    }

    @Override
    protected void modifyDataValue(Movie movie, String newValue) {
        movie.setTitle(newValue);
    }
}