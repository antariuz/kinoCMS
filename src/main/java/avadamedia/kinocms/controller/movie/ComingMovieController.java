package avadamedia.kinocms.controller.movie;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.service.ComingMovieService;
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

    private final ComingMovieService service;

    //    Add part
    @GetMapping("add")
    public String addComingMovie(Model model) {
        model.addAttribute("comingMovie", new ComingMovie());
        return "/admin/movies/coming/add";
    }

    @PostMapping("add")
    public String addComingMovie(ComingMovie comingMovie, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        comingMovie.setImageName(fileName);
        service.createComingMovie(comingMovie);
        String uploadDir = "coming-movies/" + comingMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteComingMovie(@PathVariable("id") Long id) {
        service.deleteComingMovieById(id);
        return "redirect:/admin/movies";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateComingMovie(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/movies/coming/update", "comingMovie", service.getComingMovieById(id));
    }

    @PutMapping("update")
    public String updateComingMovie(ComingMovie comingMovie, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        comingMovie.setImageName(fileName);
        service.updateComingMovie(comingMovie);
        String uploadDir = "coming-movies/" + comingMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

}