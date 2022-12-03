package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("movies")               // to fix "movies" in my API Endpoint
public class MovieController {

    @Autowired
    MovieService movieService;

//     for Adding Movie
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie m) {

        movieService.addMovie(m);
        return new ResponseEntity("New movie added successfully", HttpStatus.CREATED);
    }


    // for Adding Director
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director d) {

        movieService.addDirector(d);
        return new ResponseEntity<>("New director added successfully",HttpStatus.CREATED);
    }


    // for adding movie Director pair
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie,
                                                       @RequestParam("director")String director) {

        movieService.movieDirectorPair(movie,director);
        return new ResponseEntity<>("New movie-director pair added successfully",HttpStatus.CREATED);
    }



    // get movie by name
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name) {

        Movie movie = movieService.findMovie(name);
        return new ResponseEntity<>(movie,HttpStatus.CREATED);
    }


    // Search Director by Name
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name")String name) {

        Director director = movieService.findDirector(name);
        return new ResponseEntity<>(director,HttpStatus.CREATED);
    }


    // Get all movies of a Director
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String directorName) {

        List<String> listOfMovies = movieService.findMoviesFromDirector(directorName);
       return new ResponseEntity<>(listOfMovies,HttpStatus.CREATED);
    }

    // get all movies added to movies map
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {

        List<String> movies = movieService.allMovies();
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }

    // Delete a Driector from all records
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName) {

        movieService.deleteDirector(directorName);
        return new ResponseEntity<>(directorName + "removed successfully",HttpStatus.CREATED);
    }


    // Delete all Directors
    @DeleteMapping("delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {

        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors deleted successfully",HttpStatus.CREATED);
    }

//    @PostMapping("/add-movie")
//    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
//        movieService.addMovie(movie);
//        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
//    }
//
//    @PostMapping("/add-director")
//    public ResponseEntity<String> addDirector(@RequestBody Director director){
//        movieService.addDirector(director);
//        return new ResponseEntity<>("New director added successfully", HttpStatus.CREATED);
//    }
//
//    @PutMapping("/add-movie-director-pair")
//    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie, @RequestParam String director){
//        movieService.movieDirectorPair(movie, director);
//        return new ResponseEntity<>("New movie-director pair added successfully", HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-movie-by-name/{name}")
//    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
//        Movie movie = movieService.findMovie(name);
//        return new ResponseEntity<>(movie, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-director-by-name/{name}")
//    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
//        Director director = movieService.findDirector(name);
//        return new ResponseEntity<>(director, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-movies-by-director-name/{director}")
//    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
//        List<String> movies = movieService.findMoviesFromDirector(director);
//        return new ResponseEntity<>(movies, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-all-movies")
//    public ResponseEntity<List<String>> findAllMovies(){
//        List<String> movies = movieService.allMovies();
//        return new ResponseEntity<>(movies, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete-director-by-name")
//    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
//        movieService.deleteDirector(director);
//        return new ResponseEntity<>(director + " removed successfully", HttpStatus.CREATED);
//    }
//    @DeleteMapping("/delete-all-directors")
//    public ResponseEntity<String> deleteAllDirectors(){
//        movieService.deleteAllDirectors();
//        return new ResponseEntity<>("All directors deleted successfully", HttpStatus.CREATED);
//    }
}
