package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
@Repository
public class MovieRepository {

    private HashMap<String,Movie> movies;

    private HashMap<String,Director> directors;

    private HashMap<String,List<String>> directorMoviePair;  // DirectorName, List<MoiveName>

    // Constructor to initialize all Three HashMaps
    public MovieRepository() {
        this.movies = new HashMap<>();
        this.directors = new HashMap<>();
        this.directorMoviePair = new HashMap<>();
    }

    // Adding Movies to movie Map
    public void addMovie(Movie m) {
        movies.put(m.getName(),m);
    }

    // Adding Directors to director Map
    public void addDirector(Director d) {
        directors.put(d.getName(),d);
    }

    // Adding Director and Movies pair to Pair Map
    public void addDirectorMoviePair(String movie,String director) {
        if (movies.containsKey(movie) && directors.containsKey(director)) {
            List<String> listOfMovies = new ArrayList<>();

            // now check if directorMoviePair is already present in map or not
            if (directorMoviePair.containsKey(director)) listOfMovies = directorMoviePair.get(director);

            listOfMovies.add(movie);
            directorMoviePair.put(director,listOfMovies);
        }
    }

    // FindMovie in movie map
    public Movie findMovie(String movieName) {
        return movies.get(movieName);
    }

    // findDirector in directors map

    public Director findDirector(String directorName) {
        return directors.get(directorName);
    }

    // Get movies a Particular Director
    public List<String> moviesOfDirector(String directorName) {
        if (directorMoviePair.containsKey(directorName))
            return directorMoviePair.get(directorName);

        return null;
    }

    // Get List of all movies added to Movie Map
    public List<String> getAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    // Delete Director and Its Movies from DirectorMoviePair Map, Movies Map also and From Directos map also
    public void deleteDiretorAndItsMovies(String directorName) {
        List<String> movie = new ArrayList<>();
        if (directorMoviePair.containsKey(directorName))
            movie = directorMoviePair.get(directorName);

        // to Delete all movies from movies map
        for (String m : movie) {
            if (movies.containsKey(m))
                movies.remove(m);
        }

        // to remove director from directorMoviePair map
        directorMoviePair.remove(directorName);

        // to remove Director from directors map
        if (directors.containsKey(directorName))
            directors.remove(directorName);
    }


    // Delete all Movies from movie Map

    public void deleteAllDirectors() {
        HashSet<String> movieSet = new HashSet<>();

        for (String director : directorMoviePair.keySet())
            for (String movie : directorMoviePair.get(director))
                movieSet.add(movie);

        for (String movie : movieSet)
            if (movies.containsKey(movie))
                movies.remove(movie);
    }
}
