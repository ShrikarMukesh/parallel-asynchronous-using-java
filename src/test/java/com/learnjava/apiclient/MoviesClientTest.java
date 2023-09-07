package com.learnjava.apiclient;


import com.learnjava.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoviesClientTest {

    WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080/movies").build();
    MoviesClient moviesClient = new MoviesClient(webClient);

    @BeforeEach
    void setUpMoviesClient() {
        var movieInfoId = 1L;
        moviesClient.retrieveMovie(movieInfoId);
    }



    @Test
    @RepeatedTest(10)
    void retrieveMovie() {
        //given
        var movieInfoId = 1L;

        //when
        var movie = moviesClient.retrieveMovie(movieInfoId);
        System.out.println(movie);

        //then
        assert movie!=null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @Test
    @RepeatedTest(10)
    void retrieveMovie_1() {
        //given
        var movieInfoId = 1L;

        //when
        var movie = moviesClient.retrieveMovie(movieInfoId);

        //then
        assert movie!=null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @Test
    @RepeatedTest(10)
    void retrieveMovie_CF() {

        //given
        var movieInfoId = 1L;

        //when
        var movie = moviesClient.retrieveMovie_CF(movieInfoId).join();

        //then
        assert movie!=null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;


    }

    @Test
    void retrieveMovieList() {
        //given
        var movieInfoIds = List.of(1L,2L, 3L , 4L, 5L, 6L, 7L);

        //when

        var movies = moviesClient.retrieveMovieList(movieInfoIds);
        System.out.println("movies : " + movies);


        //then
        assert movies.size() == 7;
    }


    @Test
    void retrieveMovieList_CF() {
        //given
        var movieInfoIds = List.of(1L,2L, 3L , 4L, 5L, 6L, 7L);

        //when

        var movies = moviesClient.retrieveMovieList_CF(movieInfoIds);

        System.out.println("movies : " + movies);

        //then
        assert movies.size() == 7;

    }

    @Test
    void retrieveMovieList_CF_approach2() {
        //given
        var movieInfoIds = List.of(1L,2L, 3L , 4L, 5L, 6L, 7L);

        //when

        var movies = moviesClient.retrieveMovieList_CF_allOf(movieInfoIds);

        System.out.println("movies : " + movies);

        //then
        assert movies.size() == 7;

    }
}