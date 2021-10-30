package avadamedia.kinocms.controller;

import avadamedia.kinocms.service.movie.ComingMovieService;
import avadamedia.kinocms.service.movie.CurrentMovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/movies")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MoviesController {

    private final CurrentMovieService currentMovieService;
    private final ComingMovieService comingMovieService;

    //    Show all Movies
    @GetMapping({"/", ""})
    public ModelAndView showAllMovies() {
        ModelAndView mav = new ModelAndView("admin/movies/index");
        mav.addObject("currentMovies", currentMovieService.getAllCurrentMovies());
        mav.addObject("comingMovies", comingMovieService.getAllComingMovies());
        return mav;
    }


}
