import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.Arrays;
import java.util.List;

import Movie.Movie;
import MovieCollection.MovieCollection;
import MovieModificationTemplates.*;
import SortStrategies.*;
import DisplayTemplates.*;

public class Main {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel tablePanel;

    private JTextField titleField;
    private JTextField yearField;
    private JTextField ratingField;
    private JTextField directorField;
    private JTextField genreField;

    private JButton addButton;
    private JButton sortButton;
    private JButton displayButton;
    private JButton modifyButton;
    private JButton removeButton;

    private JTable movieTable;
    private DefaultTableModel tableModel;

    private MovieCollection movieCollection;

    public Main() {
        movieCollection = new MovieCollection();

        // Initialize GUI components
        frame = new JFrame("Movie Collection Manager");
        mainPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new GridLayout(5, 2));
        buttonPanel = new JPanel(new FlowLayout());
        tablePanel = new JPanel(new BorderLayout());

        // Input fields
        titleField = new JTextField();
        yearField = new JTextField();
        ratingField = new JTextField();
        directorField = new JTextField();
        genreField = new JTextField();

        // Buttons
        addButton = new JButton("Add Movie");
        sortButton = new JButton("Sort");
        displayButton = new JButton("Display Movie Details");
        modifyButton = new JButton("Modify Movie");
        removeButton = new JButton("Remove Movie");

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Title", "Year", "Rating", "Director", "Genre"}, 0);
        movieTable = new JTable(tableModel);

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearField);
        inputPanel.add(new JLabel("Rating:"));
        inputPanel.add(ratingField);
        inputPanel.add(new JLabel("Director:"));
        inputPanel.add(directorField);
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genreField);

        buttonPanel.add(addButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(removeButton);

        JScrollPane scrollPane = new JScrollPane(movieTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        contentPane.add(mainPanel);
        frame.setContentPane(contentPane);
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = titleField.getText();
                    if (title.isEmpty()) {
                        throw new IllegalArgumentException("Title cannot be empty");
                    }

                    int year;
                    try {
                        year = Integer.parseInt(yearField.getText());
                        if (year < 1880 || year > java.time.Year.now().getValue()) {
                            throw new IllegalArgumentException("Year must be between 1880 and the current year");
                        }
                    } catch (NumberFormatException ex) {
                        throw new NumberFormatException("Invalid year. Please enter a valid year.");
                    }

                    double rating;
                    try {
                        rating = Double.parseDouble(ratingField.getText().replace(',', '.'));
                        if (rating < 0 || rating > 10) {
                            throw new IllegalArgumentException("Rating must be between 0.0 and 10.0");
                        }
                    } catch (NumberFormatException ex) {
                        throw new NumberFormatException("Invalid rating. Please enter a rating between 0.0 and 10.0");
                    }

                    String director = directorField.getText();
                    if (director.isEmpty()) {
                        throw new IllegalArgumentException("Director cannot be empty");
                    }

                    String genre = genreField.getText();
                    if (genre.isEmpty()) {
                        throw new IllegalArgumentException("Genre cannot be empty");
                    }

                    Movie movie = new Movie(title, year, rating, director, genre);
                    movieCollection.addMovie(movie);
                    updateTableModel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] sortOptions = {"Title A-Z", "Title Z-A", "Year Ascending", "Year Descending", "Rating Ascending", "Rating Descending"};
                int selectedOption = JOptionPane.showOptionDialog(
                        frame,
                        "Choose Sorting Criteria",
                        "Sort Movies",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        sortOptions,
                        sortOptions[0]
                );

                if (selectedOption != JOptionPane.CLOSED_OPTION) {
                    switch (selectedOption) {
                        case 0:
                            new Context(new SortByTitleAscending()).sort(movieCollection);
                            break;
                        case 1:
                            new Context(new SortByTitleDescending()).sort(movieCollection);
                            break;
                        case 2:
                            new Context(new SortByYearAscending()).sort(movieCollection);
                            break;
                        case 3:
                            new Context(new SortByYearDescending()).sort(movieCollection);
                            break;
                        case 4:
                            new Context(new SortByRatingAscending()).sort(movieCollection);
                            break;
                        case 5:
                            new Context(new SortByRatingDescending()).sort(movieCollection);
                            break;
                    }
                    updateTableModel();
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieList = getMovieList();
                String indexString = JOptionPane.showInputDialog(frame, "Movies:\n" + movieList + "\nEnter movie index to display:");

                try {
                    int index = Integer.parseInt(indexString);
                    if (index < 0 || index >= movieCollection.getMoviesList().size()) {
                        throw new IllegalArgumentException("Invalid index.");
                    }

                    Movie movie = movieCollection.getMoviesList().get(index);
                    Movie[] movies = {movie};
                    MovieDisplayTemplate displayer = new DetailedMovieDisplay();
                    String movieDetails = displayer.displayString(movies);

                    JOptionPane.showMessageDialog(frame, "Movie Details:\n" + movieDetails);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid index.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieList = getMovieList();
                List<String> modifyingOptions = Arrays.asList("0: Title", "1: Year", "2: Rating", "3: Director", "4: Genre");
                String indexString = JOptionPane.showInputDialog(frame, "Movies:\n" + movieList + "\nEnter movie index to modify:");

                try {
                    int index = Integer.parseInt(indexString);
                    if (index < 0 || index >= movieCollection.getMoviesList().size()) {
                        throw new IllegalArgumentException("Invalid index.");
                    }
                    Movie movieToModify = movieCollection.getMoviesList().get(index);
                    String indexString2 = JOptionPane.showInputDialog(frame, "Options:\n" + modifyingOptions + "\nEnter option index to modify:");
                    int optionIndex = Integer.parseInt(indexString2);
                    if (optionIndex < 0 || optionIndex >= modifyingOptions.size()) {
                        throw new IllegalArgumentException("Invalid option index.");
                    }

                    String newValue = "";

                    try {newValue = JOptionPane.showInputDialog(frame, "Enter new value:");
                    } catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Value cannot be empty.");}
                    
                    switch (optionIndex) {
                        case 0:
                        MovieModificationTemplate modifier = new MovieTitleModify();
                        modifier.modifyMovieValue(movieToModify, newValue);
                            break;
                        case 1:
                        MovieModificationTemplate modifier2 = new MovieYearModify();  
                        int newYear;
                        try {
                            newYear = Integer.parseInt(newValue);
                            if (newYear < 1880 || newYear > java.time.Year.now().getValue()) {
                                throw new IllegalArgumentException("Year must be between 1880 and the current year");
                            }
                            modifier2.modifyMovieValue(movieToModify, newValue);
                        } catch (NumberFormatException ex) {
                            throw new NumberFormatException("Invalid year. Please enter a valid year.");
                        }
                            break;
                        case 2:
                            MovieModificationTemplate modifier3 = new MovieRatingModify();
                            try {
                                double newRating;
                                newRating = Double.parseDouble(newValue);
                                if (newRating < 0 || newRating > 10) {
                                    throw new IllegalArgumentException("Rating must be between 0.0 and 10.0");
                                }
                                modifier3.modifyMovieValue(movieToModify, newValue);
                            } catch (NumberFormatException ex) {
                                throw new NumberFormatException("Invalid rating. Please enter a valid rating.");
                            }
                            break;
                        case 3:
                            MovieModificationTemplate modifier4 = new MovieDirectorModify();
                            modifier4 = new MovieDirectorModify();
                            modifier4.modifyMovieValue(movieToModify, newValue);
                            break;
                        case 4:
                            MovieModificationTemplate modifier5= new MovieGenreModify();
                            modifier5 = new MovieGenreModify();
                            modifier5.modifyMovieValue(movieToModify, newValue);
                            break;
                    }
                    updateTableModel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid index.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieList = getMovieList();
                String indexString = JOptionPane.showInputDialog(frame, "Movies:\n" + movieList + "\nEnter movie index to remove:");

                try {
                    int index = Integer.parseInt(indexString);
                    if (index < 0 || index >= movieCollection.getMoviesList().size()) {
                        throw new IllegalArgumentException("Invalid index.");
                    }

                    movieCollection.removeMovie(movieCollection.getMoviesList().get(index).getTitle());
                    updateTableModel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid index.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sample data
        movieCollection.addMovie(new Movie("Inception", 2010, 8.8, "Christopher Nolan", "Sci-Fi"));
        movieCollection.addMovie(new Movie("The Matrix", 1999, 8.7, "Wachowski's", "Sci-Fi"));
        movieCollection.addMovie(new Movie("Interstellar", 2014, 8.6, "Christopher Nolan", "Sci-Fi"));
        movieCollection.addMovie(new Movie("The Dark Knight", 2008, 9.0, "Christopher Nolan", "Action"));
        movieCollection.addMovie(new Movie("The Dark Knight Rises", 2012, 8.4, "Christopher Nolan", "Action"));
        movieCollection.addMovie(new Movie("The Shawshank Redemption", 1994, 9.3, "Frank Darabont", "Drama"));
        movieCollection.addMovie(new Movie("The Godfather", 1972, 9.2, "Francis Ford Coppola", "Crime"));
        movieCollection.addMovie(new Movie("The Godfather - Part II", 1974, 9.0, "Francis Ford Coppola", "Crime"));
        movieCollection.addMovie(new Movie("Forrest Gump", 1994, 8.8, "Robert Zemecki's", "Drama"));
        movieCollection.addMovie(new Movie("Avengers - Endgame", 2019, 8.4, "Russo Brothers", "Action"));
        movieCollection.addMovie(new Movie("Avengers - Infinity War", 2018, 8.4, "Russo Brothers", "Action"));


        updateTableModel();
    }

    private void updateTableModel() {
        tableModel.setRowCount(0); // Clear existing rows
        for (Movie movie : movieCollection.getMovies()) {
            tableModel.addRow(new Object[]{movie.getTitle(), movie.getYear(), movie.getRating(), movie.getDirector(), movie.getGenre()});
        }
    }

    private String getMovieList() {
        StringBuilder movieList = new StringBuilder();
        for (int i = 0; i < movieCollection.getMoviesList().size(); i++) {
            Movie movie = movieCollection.getMoviesList().get(i);
            movieList.append(i).append(": ").append(movie.getTitle()).append("\n");
        }
        return movieList.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
