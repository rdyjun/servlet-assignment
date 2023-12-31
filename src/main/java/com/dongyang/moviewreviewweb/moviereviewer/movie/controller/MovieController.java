package com.dongyang.moviewreviewweb.moviereviewer.movie.controller;

import com.dongyang.moviewreviewweb.moviereviewer.member.service.MemberService;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.Movie;
import com.dongyang.moviewreviewweb.moviereviewer.movie.entity.MovieList;
import com.dongyang.moviewreviewweb.moviereviewer.movie.movieapi.MovieAPI;
import com.dongyang.moviewreviewweb.moviereviewer.movie.service.MovieServiceImpl;
import com.dongyang.moviewreviewweb.moviereviewer.review.entity.Review;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewLikeService;
import com.dongyang.moviewreviewweb.moviereviewer.review.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieServiceImpl movieService;
    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;
    private final MemberService memberService;
    @PostMapping("/test")
    public void test () {
        System.out.println("");
    }
    @RequestMapping("/movies")
    public String getMovies (@RequestParam(value="sort", required = false) String sort, Model model) throws IOException {
        List<MovieList> mdto = movieService.getMovies(sort);
        Map<String, Double> moviesVote = reviewService.getMovieVote(mdto);
        model.addAttribute("movies", mdto);
        model.addAttribute("vote", moviesVote);
        return "movies";
    }
    @RequestMapping("/mbti-movies")
    public String getMovies (Model model, String mbti) throws IOException {
        if (mbti == null)
            mbti = "INFP"; // 기본
        String moviesJson = movieService.getMovieByMBTI(mbti);
        List<MovieList> mdto = MovieAPI.jsonConvertToMovieList(moviesJson);
        Map<String, Double> moviesVote = reviewService.getMovieVote(mdto);
        model.addAttribute("movies", mdto);
        model.addAttribute("vote", moviesVote);
        return "mbtiMovies";
    }
    @RequestMapping("/search")
    public String getSearchMovies (Model model, String searchValue) throws IOException {
        List<MovieList> movieList = movieService.getMovieByKeyword(searchValue);
        Map<String, Double> moviesVote = reviewService.getMovieVote(movieList);
        model.addAttribute("movies", movieList);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("vote", moviesVote);
        return "search";
    }
    @RequestMapping("/movies/{id}")
    public String getMovieInfo (@PathVariable String id, HttpSession session, Model model) throws IOException {
        String memberId = (String) session.getAttribute("memberId");
        Movie movie = movieService.getTargetMovie(id);
        List<Review> reviewList = reviewService.getReviewByMovie(id);
        Collections.reverse(reviewList);
        Map<Long, Integer> likeCount = reviewLikeService.getLikeCount(id);
        Set<Long> reviewLikeList = reviewLikeService.getReviewLikeListByMovieIdAndMemberId(id, memberId);
        List<String> reviewMemberName = memberService.getMemberNameByReview(reviewList);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviewList);
        model.addAttribute("like", reviewLikeList);
        model.addAttribute("likeCount", likeCount);
        model.addAttribute("reviewName", reviewMemberName);
        return "/movieReview";
    }
    @RequestMapping("/")
    public String root (Model model) throws JsonProcessingException {
        List<MovieList> movieList = movieService.getTopThreeMovie();
        List<Movie> movies = movieService.getMovieDataList(movieList);
        model.addAttribute("movies", movies);
        return "index";
    }
}
