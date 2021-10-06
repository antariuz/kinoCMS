package avadamedia.kinocms.controller.movies;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.service.ComingMovieService;
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
@RequestMapping("admin/movies/coming")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ComingMovieController {

    private final ComingMovieService comingMovieService;
    private final MovieTypeService movieTypeService;

    //    Add part
    @GetMapping("add")
    public String addComingMovie(Model model) {
        model.addAttribute("comingMovie", new ComingMovie());
        model.addAttribute("movieTypes", movieTypeService.getAllMovieTypes());
        model.addAttribute("seo", new SEO());
        return "/admin/movies/coming/add";
    }

    @PostMapping("add")
    public String addComingMovie(ComingMovie comingMovie, @RequestParam("mainImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        comingMovie.setMainImage(fileName);
        comingMovieService.createComingMovie(comingMovie);
        String uploadDir = "coming-movies/" + comingMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
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
        mav.addObject("movieTypes", movieTypeService.getAllMovieTypes());
        mav.addObject("seo", new SEO());
        return mav;
    }

    @PutMapping("update")
    public String updateComingMovie(ComingMovie comingMovie, @RequestParam("mainImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        comingMovie.setMainImage(fileName);
        comingMovieService.updateComingMovie(comingMovie);
        String uploadDir = "coming-movies/" + comingMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

}