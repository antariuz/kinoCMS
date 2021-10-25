package avadamedia.kinocms.controller.movies;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.model.movies.assist.MovieInfo;
import avadamedia.kinocms.service.movie.ComingMovieService;
import avadamedia.kinocms.service.common.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashSet;

@Controller
@RequestMapping("admin/movies/coming")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ComingMovieController {

    private final ComingMovieService comingMovieService;
    private final ImageService imageService;

    @GetMapping("add")
    public String addComingMovie() {
        ComingMovie comingMovie = new ComingMovie();
        comingMovie.setMovieTypes(new HashSet<>());
        comingMovie.setMovieInfo(new MovieInfo());
        comingMovie.setSeo(new SEO());
        comingMovieService.createComingMovie(comingMovie);
        return "redirect:/admin/movies/coming/update/" + comingMovieService.getLastId();
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteComingMovie(@PathVariable("id") Long id) {
        comingMovieService.deleteComingMovieById(id);
        return "redirect:/admin/movies";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateComingMovie(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/movies/coming/update");
        mav.addObject("comingMovie", comingMovieService.getComingMovieById(id));
        mav.addObject("movieTypes", comingMovieService.getComingMovieById(id).getMovieTypes());
        mav.addObject("movieInfo", comingMovieService.getComingMovieById(id).getMovieInfo());
        mav.addObject("seo", comingMovieService.getComingMovieById(id).getSeo());
        return mav;
    }

    @PutMapping("update")
    public String updateComingMovie(ComingMovie comingMovie, MovieInfo movieInfo, SEO seo,
                                    @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "coming-movies/" + comingMovie.getId();
        comingMovie.setMainImage(fileName);
        comingMovie.setMovieInfo(movieInfo);
        comingMovie.setSeo(seo);
        comingMovieService.updateComingMovie(comingMovie);
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

}