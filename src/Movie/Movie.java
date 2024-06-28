package Movie;

import java.util.Scanner;

public class Movie {
    private String title;
    private int year;
    private double rating;
    private String director;
    private String genre;

    public Movie() {}

    public Movie(String title, int year, double rating, String director, String genre) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.director = director;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setYear(int newYear) {
        this.year = newYear;
    }

    public void setRating(double newRating) {
        this.rating = newRating;
    }

    public void setDirector(String newDirector) {
        this.director = newDirector;
    }

    public void setGenre(String newGenre) {
        this.genre = newGenre;
    }

    public String getMovieDetails() {
        return "Title: " + title + ", Year: " + year + ", Rating: " + rating + ", Director: " + director + ", Genre: " + genre;
    }
    
    @Override
    public String toString() {
        return title + " (" + year + ") - Rating: " + rating;
    }
    public void inputMovieDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the movie title: ");
        this.title = scanner.nextLine();
        while (this.title.isEmpty()) {
            System.out.println("Title cannot be empty. Please enter the movie title: ");
            this.title = scanner.nextLine();
        }

        System.out.println("Enter the movie year: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid year: ");
            scanner.next();
        }
        this.year = scanner.nextInt();
        while (this.year < 1880 || this.year > java.time.Year.now().getValue()) {
            System.out.println("Invalid year. Please enter a year between 1880 and the current year: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid year: ");
                scanner.next();
            }
            this.year = scanner.nextInt();
        }
        scanner.nextLine();

        System.out.println("Enter the movie rating separated with comma: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter rating separated with comma: ");
            scanner.next();
        }
        this.rating = scanner.nextDouble();
        while (this.rating < 0 || this.rating > 10) {
            System.out.println("Invalid rating. Please enter a rating between 0,0 and 10,0: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter rating separated with comma: ");
                scanner.next();
            }
            this.rating = scanner.nextDouble();
        }
        scanner.nextLine();

        System.out.println("Enter the movie director: ");
        this.director = scanner.nextLine();
        while (this.director.isEmpty()) {
            System.out.println("Director cannot be empty. Please enter the movie director: ");
            this.director = scanner.nextLine();
        }

        System.out.println("Enter the movie genre: ");
        this.genre = scanner.nextLine();
        while (this.genre.isEmpty()) {
            System.out.println("Genre cannot be empty. Please enter the movie genre: ");
            this.genre = scanner.nextLine();
        }
    }
}