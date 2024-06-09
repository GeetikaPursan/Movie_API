package com.example.API_Movie.service

import com.example.API_Movie.dto.MultiSearchDTO
import com.example.API_Movie.dto.SearchDTO
import com.example.API_Movie.entity.Movie
import com.example.API_Movie.repository.MovieRepository
import jakarta.annotation.PostConstruct
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.Year
import java.util.*

@Service
class MovieService(private val movieRepository: MovieRepository) {
    fun getAllMovies(): List<Movie> = movieRepository.findAll()

    fun getMovieById(id: Long): Movie? = movieRepository.findById(id).orElse(null)

    fun getMovieByYear(movieYear: Int): List<Movie> = movieRepository.findByMovieYear(movieYear)

    fun getMovieLessThan(movieYear: Int): List<Movie> = movieRepository.findByMovieYearLessThan(movieYear)

    fun getMovieOrderByMovieYear(): List<Movie> = movieRepository.findByOrderByMovieYearDesc()

    fun getMovieByDirector(director: String) : List<Movie> = movieRepository.findByDirectorContainingIgnoreCase(director)

    fun getMovieByGenre(genre : String): List<Movie> = movieRepository.findByGenreContainingIgnoreCase(genre)

    fun getMovieMetaScoreGreaterThan(metascore : Int): List<Movie> = movieRepository.findByMetascoreGreaterThan(metascore)

    fun getMovieByActorDesc(actors : String): List<Movie> = movieRepository.findByActorsContainingIgnoreCaseOrderByActorsDesc(actors)

    fun getMovieByActorAsc(actors : String): List<Movie> = movieRepository.findByActorsContainingIgnoreCaseOrderByActorsAsc(actors)

    fun getMovieByPlot(plot: String): List<Movie> = movieRepository.findByPlotContainingIgnoreCase(plot)

    //fun getMovieByActorAndGenre(actors: String, genre: String): List<Movie> = movieRepository.findByActorsAndGenreContainingIgnoreCase(actors, genre)

    fun searchMovie(multiSearchDTO: MultiSearchDTO): List<Movie>{
        if (multiSearchDTO.search.isNullOrEmpty() || multiSearchDTO.searchBy.isNullOrEmpty()) {
            return listOf()
        }
        if (multiSearchDTO.search.size == multiSearchDTO.searchBy.size) {
            if (multiSearchDTO.searchBy.size == 1) {
                if (multiSearchDTO.searchBy[0] == "plot") {
                    return movieRepository.findByPlotContainingIgnoreCase(multiSearchDTO.search[0])
                }
                if(multiSearchDTO.searchBy[0] == "language") {
                    return movieRepository.findByMovieLanguage(multiSearchDTO.search[0])
                }
            }
            if (multiSearchDTO.searchBy.size == 2) {
                if (multiSearchDTO.searchBy[0] == "actors" && multiSearchDTO.searchBy[1] == "genre") {
                    return movieRepository.findByActorsContainingIgnoreCaseAndGenreContainingIgnoreCase(multiSearchDTO.search[0], multiSearchDTO.search[1])
                }

            }
            return listOf()
        }
        return listOf()
    }

    fun addMovie(movie: Movie): Movie {
        return movieRepository.save(movie)
    }

    fun deleteMovieById(id: Long) {
        return movieRepository.deleteById(id)
    }

    fun updateMovie(movie: Movie): Movie {
        return movieRepository.save(
            Movie(
                id = movie.id,
                title = movie.title,
                rated = movie.rated,
                released = movie.released,
                genre = movie.genre,
                director = movie.director,
                writer = movie.writer,
                actors = movie.actors,
                plot = movie.plot,
                language = movie.language,
                country = movie.country,
                awards = movie.awards,
                poster = movie.poster,
                metascore = movie.metascore,
                imdbRating = movie.imdbRating,
                imdbVotes = movie.imdbVotes,
                imdbID = movie.imdbID,
                type = movie.type,
                dvd = movie.dvd,
                boxOffice = movie.boxOffice,
                production = movie.production,
                movieYear = movie.movieYear,
                runtime = movie.runtime
            )
        )
    }

    @PostConstruct
    fun sampleData() {
        val movie = Movie(
            id = null, // This value depends on your database setup, you can assign it as needed
            title = "They Shall Not Grow Old",
            movieYear = 2018,
            runtime = "99 min",
            rated = "R",
            released = "01 Feb 2019",
            genre = "Documentary, History, War",
            director = "Peter Jackson",
            writer = "Peter Jackson",
            actors = "Mark Kermode, Peter Jackson",
            plot = "A documentary about World War I with never-before-seen footage to commemorate the centennial of the end of the war.",
            language = "English",
            country = "UK, New Zealand",
            awards = "Nominated for 1 BAFTA Film Award. Another 4 wins & 10 nominations.",
            poster = "https://m.media-amazon.com/images/M/MV5BZWI3ZThmYzUtNDJhOC00ZWY4LThiNmMtZDgxNjE3Yzk4NDU1XkEyXkFqcGdeQXVyNTk5Nzg1NjQ@._V1_SX300.jpg",
            metascore = 91,
            imdbRating = 8.3,
            imdbVotes = 21722,
            imdbID = "tt7905466",
            type = "movie",
            dvd = "N/A",
            boxOffice = "N/A",
            production = "Warner Bros. Pictures"
        )
        movieRepository.save(movie)

        val movie3 =Movie(
            id = null,
            title = "Midnight Family",
            movieYear = 2019,
            runtime = "81 min",
            rated = "N/A",
            released = "06 Dec 2019",
            genre = "Documentary, Action, Crime, Drama",
            director = "Luke Lorentzen",
            writer = "Luke Lorentzen",
            actors = "Fer Ochoa, Josue Ochoa, Juan Ochoa",
            plot = "In Mexico City's wealthiest neighborhoods, the Ochoa family runs a private ambulance, competing with other for-profit EMTs for patients in need of urgent help.",
            language = "Spanish",
            country = "Mexico",
            awards = "24 wins & 20 nominations.",
            poster = "https://m.media-amazon.com/images/M/MV5BMGYyZTk5MjYtNGY2ZS00NzRhLTgwMWMtZjhmMmQ4OGFkNTNiXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg",
            metascore = 84,
            imdbRating = 7.7,
            imdbVotes = 330,
            imdbID = "tt6010976",
            type = "movie",
            dvd = "N/A",
            boxOffice = "N/A",
            production = "1091",
        )
        movieRepository.save(movie3)

        val movie4 = Movie(
            id = null,
            title = "Pain & Gain",
            movieYear = 2013,
            runtime = "129 min",
            rated = "R",
            released = "26 Apr 2013",
            genre = "Action, Comedy, Crime, Drama",
            director = "Michael Bay",
            writer = "Christopher Markus (screenplay), Stephen McFeely (screenplay), Pete Collins (based on the magazine articles by)",
            actors = "Mark Wahlberg, Dwayne Johnson, Anthony Mackie, Tony Shalhoub",
            plot = "A trio of bodybuilders in Florida get caught up in an extortion ring and a kidnapping scheme that goes terribly wrong.",
            language = "English",
            country = "USA",
            awards = "4 nominations.",
            poster = "https://m.media-amazon.com/images/M/MV5BMTU0NDE5NTU0OV5BMl5BanBnXkFtZTcwMzI1OTMzOQ@@._V1_SX300.jpg",
            metascore = 45,
            imdbRating = 6.4,
            imdbVotes = 191388,
            imdbID = "tt1980209",
            type = "movie",
            dvd = "27 Aug 2013",
            boxOffice = "$49,300,000",
            production = "Paramount Studios",
        )
        movieRepository.save(movie4)

        val movie5 = Movie(
            id = null,
            title = "The Irishman",
            movieYear = 2019,
            runtime = "209 min",
            rated = "R",
            released = "27 Nov 2019",
            genre = "Biography, Crime, Drama",
            director = "Martin Scorsese",
            writer = "Steven Zaillian (screenplay), Charles Brandt (book)",
            actors = "Robert De Niro, Al Pacino, Joe Pesci, Harvey Keitel",
            plot = "A mob hitman recalls his possible involvement with the slaying of Jimmy Hoffa.",
            language = "English, Italian, Latin, Spanish",
            country = "USA",
            awards = "Nominated for 5 Golden Globes. Another 35 wins & 159 nominations.",
            poster = "https://m.media-amazon.com/images/M/MV5BMGUyM2ZiZmUtMWY0OC00NTQ4LThkOGUtNjY2NjkzMDJiMWMwXkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_SX300.jpg",
            metascore = 94,
            imdbRating = 8.2,
            imdbVotes = 144536,
            imdbID = "tt1302006",
            type = "movie",
            dvd = "27 Nov 2019",
            boxOffice = "N/A",
            production = "Netflix",
        )
        movieRepository.save(movie5)


        val movie6 = Movie(
            id = null,
            title = "Once Upon a Time... in Hollywood",
            movieYear = 2019,
            runtime = "161 min",
            rated = "R",
            released = "26 Jul 2019",
            genre = "Comedy, Drama",
            director = "Quentin Tarantino",
            writer = "Quentin Tarantino",
            actors = "Leonardo DiCaprio, Brad Pitt, Margot Robbie, Emile Hirsch",
            plot = "A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.",
            language = "English, Italian, Spanish",
            country = "USA, UK, China",
            awards = "Won 3 Golden Globes. Another 70 wins & 242 nominations.",
            poster = "https://m.media-amazon.com/images/M/MV5BOTg4ZTNkZmUtMzNlZi00YmFjLTk1MmUtNWQwNTM0YjcyNTNkXkEyXkFqcGdeQXVyNjg2NjQwMDQ@._V1_SX300.jpg",
            metascore = 83,
            imdbRating = 7.8,
            imdbVotes = 318841,
            imdbID = "tt7131622",
            type = "movie",
            dvd = "22 Nov 2019",
            boxOffice = "N/A",
            production = "Columbia Pictures",
        )
        movieRepository.save(movie6)
    }
}