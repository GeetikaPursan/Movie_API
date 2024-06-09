package com.example.API_Movie.repository

import com.example.API_Movie.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MovieRepository : JpaRepository<Movie, Long>{
     fun findByMovieYear(movieYear: Int) : List<Movie>
     fun findByMovieYearLessThan(movieYear: Int) : List<Movie>
     fun findByOrderByMovieYearDesc() : List<Movie>
     fun findByDirectorContainingIgnoreCase(director: String) : List<Movie>
     fun findByGenreContainingIgnoreCase(genre : String): List<Movie>
     fun findByMetascoreGreaterThan(metascore: Int): List<Movie>
     fun findByActorsContainingIgnoreCaseOrderByActorsDesc(actors: String): List<Movie>
     fun findByActorsContainingIgnoreCaseOrderByActorsAsc(actors: String): List<Movie>
     fun findByPlotContainingIgnoreCase(plot: String): List<Movie>
     fun findByActorsContainingIgnoreCaseAndGenreContainingIgnoreCase(actors: String, genre: String): List<Movie>

     @Query("SELECT m FROM Movie m WHERE LOWER(m.language) LIKE LOWER(concat('%', :language,'%'))")
     fun findByMovieLanguage(language: String): List<Movie>

}