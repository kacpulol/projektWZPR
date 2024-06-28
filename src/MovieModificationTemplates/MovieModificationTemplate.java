package MovieModificationTemplates;

import Movie.Movie;

public abstract class MovieModificationTemplate {

    public void modifyMovie(Movie movie) {
        showInfo(movie);
        retrieveData();
        if (validateData()) {
            modifyData(movie);
        }
    }
    public void modifyMovieValue(Movie movie, String newValue) {
            modifyDataValue(movie, newValue);
    }

    protected void showInfo(Movie movie) {
        System.out.println("Modifying movie: " + movie.getTitle());
    }

    protected abstract void retrieveData();

    protected abstract boolean validateData();

    protected abstract void modifyData(Movie movie);

    protected abstract void modifyDataValue(Movie movie, String newValue);
}