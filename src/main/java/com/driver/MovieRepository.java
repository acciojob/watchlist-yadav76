package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Component
//@Repository
//public class MovieRepository {
//
//    private HashMap<String,Movie> movies;
//
//    private HashMap<String,Director> directors;
//
//    private HashMap<String,List<String>> directorMoviePair;  // DirectorName, List<MoiveName>
//
//    // Constructor to initialize all Three HashMaps
//    public MovieRepository() {
//        this.movies = new HashMap<String, Movie>();
//        this.directors = new HashMap<String, Director>();
//        this.directorMoviePair = new HashMap<String, List<String>>();
//    }
//
//    // Adding Movies to movie Map
//    public void addMovie(Movie m) {
//        movies.put(m.getName(),m);
//    }
//
//    // Adding Directors to director Map
//    public void addDirector(Director d) {
//        directors.put(d.getName(),d);
//    }
//
//    // Adding Director and Movies pair to Pair Map
//    public void addDirectorMoviePair(String movie,String director) {
//        if (movies.containsKey(movie) && directors.containsKey(director)) {
//            List<String> listOfMovies = new ArrayList<>();
//
//            // now check if directorMoviePair is already present in map or not
//            if (directorMoviePair.containsKey(director)) listOfMovies = directorMoviePair.get(director);
//
//            listOfMovies.add(movie);
//            directorMoviePair.put(director,listOfMovies);
//        }
//    }
//
//    // FindMovie in movie map
//    public Movie findMovie(String movieName) {
//        return movies.get(movieName);
//    }
//
//    // findDirector in directors map
//
//    public Director findDirector(String directorName) {
//        return directors.get(directorName);
//    }
//
//    // Get movies a Particular Director
//    public List<String> moviesOfDirector(String directorName) {
//        List<String> moviesList = new ArrayList<String>();
//        if (directorMoviePair.containsKey(directorName))
//            moviesList = directorMoviePair.get(directorName);
//
//        return moviesList;
//    }
//
//    // Get List of all movies added to Movie Map
//    public List<String> getAllMovies() {
//        return new ArrayList<>(movies.keySet());
//    }
//
//    // Delete Director and Its Movies from DirectorMoviePair Map, Movies Map also and From Directos map also
//    public void deleteDiretorAndItsMovies(String directorName) {
//        List<String> movie = new ArrayList<>();
//        if (directorMoviePair.containsKey(directorName))
//            movie = directorMoviePair.get(directorName);
//
//        // to Delete all movies from movies map
//        for (String m : movie) {
//            if (movies.containsKey(m))
//                movies.remove(m);
//        }
//
//        // to remove director from directorMoviePair map
//        directorMoviePair.remove(directorName);
//
//        // to remove Director from directors map
//        if (directors.containsKey(directorName))
//            directors.remove(directorName);
//    }
//
//
//    // Delete all Movies from movie Map
//
//    public void deleteAllDirectors() {
//        HashSet<String> movieSet = new HashSet<>();
//
//        for (String director : directorMoviePair.keySet())
//            for (String movie : directorMoviePair.get(director))
//                movieSet.add(movie);
//
//        for (String movie : movieSet)
//            if (movies.containsKey(movie))
//                movies.remove(movie);
//    }
//}
@Component
@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            movieMap.put(movie, movieMap.get(movie));
            directorMap.put(director, directorMap.get(director));
            List<String> currentMovies = new ArrayList<String>();
            if(directorMovieMapping.containsKey(director)) currentMovies = directorMovieMapping.get(director);
            currentMovies.add(movie);
            directorMovieMapping.put(director, currentMovies);
        }
    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)) moviesList = directorMovieMapping.get(director);
        return moviesList;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        List<String> movies = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)){
            movies = directorMovieMapping.get(director);
            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            directorMovieMapping.remove(director);
        }

        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector(){
        HashSet<String> moviesSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: directorMovieMapping.keySet()){
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
    }
}
