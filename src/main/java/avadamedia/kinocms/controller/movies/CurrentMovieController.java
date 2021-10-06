package avadamedia.kinocms.controller.movies;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.CurrentMovie;
import avadamedia.kinocms.model.movies.assist.MovieType;
import avadamedia.kinocms.service.CurrentMovieService;
import avadamedia.kinocms.service.MovieTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("admin/movies/current")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentMovieController {

    private final CurrentMovieService currentMovieService;
    private final MovieTypeService movieTypeService;

    //    Add part

//    @PostMapping("add")
//    public ModelAndView addCurrentMovie(Model model) {
//        ModelAndView mav = new ModelAndView("/admin/movies/current/add");
//        List<MovieType> movieTypes = (List<MovieType>) movieTypeService.getAllMovieTypes();
//        mav.addObject("currentMovie", new CurrentMovie());
//        mav.addObject("movieTypes", movieTypes);
//        return mav;
//    }

    @GetMapping("add")
    public String addCurrentMovie(Model model) {
        model.addAttribute("currentMovie", new CurrentMovie());
        model.addAttribute("movieTypes", movieTypeService.getAllMovieTypes());
        model.addAttribute("seo", new SEO());
        return "/admin/movies/current/add";
    }

    @PostMapping("add")
    public String addCurrentMovie(CurrentMovie currentMovie, MovieType movieType,
                                  @RequestParam("mainImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        currentMovie.setMainImage(fileName);
        currentMovieService.createCurrentMovie(currentMovie);
//        movieTypeService.createMovieType(movieType);
        String uploadDir = "current-movies/" + currentMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteCurrentMovie(@PathVariable("id") Long id) {
        currentMovieService.deleteCurrentMovieById(id);
        return "redirect:/admin/movies";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateCurrentMovie(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/movies/current/update");
        mav.addObject("currentMovie", currentMovieService.getCurrentMovieById(id));
        mav.addObject("movieTypes", movieTypeService.getAllMovieTypes());
        mav.addObject("seo", new SEO());
        return mav;
    }

    @PutMapping("update")
    public String updateCurrentMovie(CurrentMovie currentMovie, MovieType movieType,
                                     @RequestParam("mainImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        currentMovie.setMainImage(fileName);
        currentMovieService.updateCurrentMovie(currentMovie);
        movieTypeService.updateMovieType(movieType);
        String uploadDir = "current-movies/" + currentMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

}