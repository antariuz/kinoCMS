package avadamedia.kinocms.controller.movies;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.CurrentMovie;
import avadamedia.kinocms.model.movies.assist.MovieInfo;
import avadamedia.kinocms.service.movie.CurrentMovieService;
import avadamedia.kinocms.service.common.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("admin/movies/current")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentMovieController {

    private final CurrentMovieService currentMovieService;
    private final ImageService imageService;

    @GetMapping("add")
    public String addCurrentMovie() {
        CurrentMovie currentMovie = new CurrentMovie();
        currentMovie.setMovieTypes(new HashSet<>());
//        currentMovie.setImageList(createImageList());
        currentMovie.setMovieInfo(new MovieInfo());
        currentMovie.setSeo(new SEO());
        currentMovieService.createCurrentMovie(currentMovie);
        return "redirect:/admin/movies/current/update/" + currentMovieService.getLastId();
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
        mav.addObject("movieTypes", currentMovieService.getCurrentMovieById(id).getMovieTypes());
//        mav.addObject("imageList", currentMovieService.getCurrentMovieById(id).getImageList());
        mav.addObject("movieInfo",currentMovieService.getCurrentMovieById(id).getMovieInfo());
        mav.addObject("seo", currentMovieService.getCurrentMovieById(id).getSeo());
        return mav;
    }

    @PutMapping("update")
    public String updateCurrentMovie(CurrentMovie currentMovie, MovieInfo movieInfo, SEO seo,
                                     @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "current-movies/" + currentMovie.getId();
        currentMovie.setMainImage(fileName);
        currentMovie.setMovieInfo(movieInfo);
        currentMovie.setSeo(seo);
        currentMovieService.updateCurrentMovie(currentMovie);
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/movies";
    }

    private List<Image> createImageList(){
        List<Image> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Image image = imageService.createImage(new Image("1"));
            list.add(image);
        }
        return list;
    }

}