package com.example.API_Movie.controller

import com.example.API_Movie.dto.MultiSearchDTO
import com.example.API_Movie.dto.SearchDTO
import com.example.API_Movie.entity.Movie
import com.example.API_Movie.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
//@CrossOrigin("*")
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
class MovieController(private val movieService: MovieService) {

    @GetMapping("/movie")
    fun getAllMovie() : List<Movie> = movieService.getAllMovies()

    @GetMapping("/testing")
    fun test() = println("Hello World")

    @PostMapping("/addMovie")
    fun addMovie(@RequestBody movie: Movie): ResponseEntity<*> {
        try {
            var createdMovie = movieService.addMovie(movie)
            return ResponseEntity.ok(createdMovie)
        } catch (e : Exception){
            return ResponseEntity.internalServerError().body("Invalid date")
        }
    }

    @GetMapping("/{id}")
    fun getMovieById(@PathVariable("id") id: Long): Movie? {
       return movieService.getMovieById(id)
    }


    @GetMapping("/year/{movieYear}")
    fun getMovieByYear(@PathVariable("movieYear") movieYear: Int) : List<Movie> {
        return movieService.getMovieByYear(movieYear)
    }

    @GetMapping("/movieByGenre/{genre}")
    fun getMovieByGenre(@PathVariable("genre") genre: String) : List<Movie> {
        return movieService.getMovieByGenre(genre)
    }

    @GetMapping("/lessYear/{movieYear}")
    fun getMovieLessThan(@PathVariable("movieYear") movieYear: Int) : List<Movie> {
        return movieService.getMovieLessThan(movieYear)
    }

    @GetMapping("/movieOrderInDesc")
    fun getMovieOrderByMovieYear() :List<Movie> {
        return movieService.getMovieOrderByMovieYear()
    }

    @GetMapping("/movieByDirector/{director}")
    fun getMovieContainingDirector(@PathVariable("director") director: String) :List<Movie> {
        return movieService.getMovieByDirector(director)
    }

    @PostMapping("/movieByActor")
    fun getMovieByActor(@RequestBody searchDTO: SearchDTO) :List<Movie> {
        if (searchDTO.searchBy == "actors"){
            if (searchDTO.sortBy == "desc"){
                return movieService.getMovieByActorDesc(searchDTO.search ?: "")
            } else{
                return movieService.getMovieByActorAsc(searchDTO.search ?: "")
            }
        }
        return listOf()
    }

    @PostMapping("/movieByPlot")
    fun getMovieByPlot(@RequestBody searchDTO: SearchDTO): List<Movie> {
        if(searchDTO.searchBy == "plot"){
            return movieService.getMovieByPlot(searchDTO.search?: "")
        }
        return listOf()
    }

    @PostMapping("/search")
    fun searchMovie(@RequestBody multiSearchDTO: MultiSearchDTO) : List<Movie>{
        return movieService.searchMovie(multiSearchDTO)
    }




    @GetMapping("/movieMetaScoreGreaterThan/{metascore}")
    fun getMovieMetaScoreGreaterThan(@PathVariable("metascore") metascore: Int) :List<Movie> {
        return movieService.getMovieMetaScoreGreaterThan(metascore)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteMovieById(@PathVariable("id") id: Long) = movieService.deleteMovieById(id)


    @PutMapping("/update")
    fun updateMovieById(@RequestBody payload: Movie): Movie =
        movieService.updateMovie(payload)
}