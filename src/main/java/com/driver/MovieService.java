package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    // to add Movies
    public void addMovie(Movie m) {
        movieRepository.addMovie(m);
    }

    // Add director
    public void addDirector(Director d) {
        movieRepository.addDirector(d);
    }

    // Create movie director Pair
    public void movieDirectorPair(String movieName, String directorName) {
        movieRepository.addDirectorMoviePair(directorName,movieName);
    }

    // Find Movie
    public Movie findMovie(String movie) {
        return movieRepository.findMovie(movie);
    }

    // Find Director
    public Director findDirector(String director) {
        return movieRepository.findDirector(director);
    }

    // Find moives by director
    public List<String> findMoviesFromDirector(String directorName) {
        return movieRepository.moviesOfDirector(directorName);
    }

    // get list of all movies
    public List<String> allMovies() {
        return movieRepository.getAllMovies();
    }

    // Delete A Director and Its movies
    public void deleteDirector(String directorName) {
        movieRepository.deleteDiretorAndItsMovies(directorName);
    }

    // Delete AllDirectors
    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }

}
