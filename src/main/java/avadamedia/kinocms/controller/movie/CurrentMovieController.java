package avadamedia.kinocms.controller.movie;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.movies.CurrentMovie;
import avadamedia.kinocms.service.CurrentMovieService;
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

    private final CurrentMovieService service;

    //    Add part
    @GetMapping("add")
    public String addCurrentMovie(Model model) {
        model.addAttribute("currentMovie", new CurrentMovie());
        return "/admin/movies/current/add";
    }

    @PostMapping("add")
    public String addCurrentMovie(CurrentMovie currentMovie, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        currentMovie.setImageName(fileName);
        service.createCurrentMovie(currentMovie);
        String uploadDir = "current-movies/" + currentMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteCurrentMovie(@PathVariable("id") Long id) {
        service.deleteCurrentMovieById(id);
        return "redirect:/admin/movies";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateCurrentMovie(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/movies/current/update", "currentMovie", service.getCurrentMovieById(id));
    }

    @PutMapping("update")
    public String updateCurrentMovie(CurrentMovie currentMovie, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        currentMovie.setImageName(fileName);
        service.updateCurrentMovie(currentMovie);
        String uploadDir = "current-movies/" + currentMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

}