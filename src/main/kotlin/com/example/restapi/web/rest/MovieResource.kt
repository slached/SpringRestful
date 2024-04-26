package com.example.restapi.web.rest

import com.example.restapi.dto.MovieDTO
import com.example.restapi.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieResource(
    //injections
    private val movieService: MovieService,
) {

    @PostMapping("/api/createMovie")
    fun createMovie(@RequestBody body: MovieDTO): ResponseEntity<Any> {
        val movie = movieService.createMovie(body)
        val returnObject = ReturnObject("id ${movie.id} created successfully.", HttpStatus.CREATED.value())
        return ResponseEntity(returnObject, HttpStatus.CREATED)
    }

    @GetMapping("/api/getAllMovies")
    fun getAllMovie(): ResponseEntity<List<MovieDTO>> =
        ResponseEntity(movieService.getAllMovie(), HttpStatus.OK)


    @GetMapping("/api/getMovie/{id}")
    fun getOneMovie(@PathVariable id: Int): ResponseEntity<MovieDTO> =
        ResponseEntity(movieService.getOneMovie(id), HttpStatus.OK)


    @DeleteMapping("/api/deleteOneMovie/{id}")
    fun deleteMovieById(@PathVariable id: Int): ResponseEntity<ReturnObject> {
        val deleteTrigger = movieService.deleteMovieById(id)
        val returnObject =
            ReturnObject("id ${deleteTrigger.id} has deleted successfully.", HttpStatus.NO_CONTENT.value())
        return ResponseEntity(returnObject, HttpStatus.NO_CONTENT)
    }

    @PutMapping("/api/changeMovieInfo")
    fun changeMovieInfo(@RequestBody body: MovieDTO): ResponseEntity<ReturnObject> {
        val movie = movieService.changeMovie(body)
        val returnObject = ReturnObject("id ${movie.id} has altered successfully.", HttpStatus.CREATED.value())
        return ResponseEntity(returnObject, HttpStatus.CREATED)
    }
}