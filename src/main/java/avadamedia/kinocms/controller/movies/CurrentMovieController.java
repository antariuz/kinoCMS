package avadamedia.kinocms.controller.movies;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.CurrentMovie;
import avadamedia.kinocms.model.movies.assist.MovieInfo;
import avadamedia.kinocms.model.movies.assist.MovieType;
import avadamedia.kinocms.service.common.ImageService;
import avadamedia.kinocms.service.movie.CurrentMovieService;
import avadamedia.kinocms.service.movie.MovieTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin/movies/current")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentMovieController {

    private final CurrentMovieService currentMovieService;
    private final MovieTypeService movieTypeService;
    private final ImageService imageService;

    // Add
    @GetMapping("add")
    public String addCurrentMovie() {
        CurrentMovie currentMovie = new CurrentMovie();
        currentMovie.setMovieTypes(new HashSet<>());
        currentMovie.setImages(imageService.initImageList(4));
        currentMovie.setMovieInfo(new MovieInfo());
        currentMovie.setSeo(new SEO());
        currentMovieService.createCurrentMovie(currentMovie);
        return "redirect:/admin/movies/current/edit/" + currentMovieService.getLastId();
    }

    // Update
    @GetMapping("edit/{id}")
    public ModelAndView showEditCurrentMoviePage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/movies/current/update");
        mav.addObject("currentMovie", currentMovieService.getCurrentMovieById(id));
        mav.addObject("movieTypes", movieTypeService.getAllMovieTypes());
        return mav;
    }

    @PutMapping("update/{id}")
    public String updateCurrentMovie(@PathVariable("id") Long id,
                                     @ModelAttribute("currentMovie") CurrentMovie currentMovie,
                                     @RequestParam("movieTypes") int[] movieTypes,
                                     @RequestParam("image") MultipartFile file,
                                     @RequestParam("galleryImages") MultipartFile[] galleryImages) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "current-movies/" + currentMovie.getId();
        String galleryImagesUploadDir = "/gallery-images/";

        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            currentMovie.setMainImage(fileName);
        } else currentMovie.setMainImage(currentMovieService.getCurrentMovieById(id).getMainImage());
        Set<MovieType> movieTypeSet = new HashSet<>();
        if (movieTypes.length != 0) {
            for (int movieType : movieTypes) {
                movieTypeSet.add(movieTypeService.getMovieTypeById((long) movieType));
            }
        }
        currentMovie.setMovieTypes(movieTypeSet);
        List<Image> images = currentMovieService.getCurrentMovieById(id).getImages();
        if (galleryImages.length != 0) {
            for (int i = 0; i < galleryImages.length; i++) {
                String galleryImageName = StringUtils.cleanPath(galleryImages[i].getOriginalFilename());
                if (!galleryImageName.equals("")) {
                    FileUploadUtil.saveFile(galleryImagesUploadDir, galleryImageName, galleryImages[i]);
                    images.get(i).setName(galleryImageName);
                } else {
                    images.get(i).setName(currentMovieService.getCurrentMovieById(id).getImages().get(i).getName());
                }

            }
        }
        currentMovie.setImages(images);
        currentMovieService.updateCurrentMovie(currentMovie);
        return "redirect:/admin/movies/current/edit/" + id;
    }

    // Delete
    @GetMapping("delete/{id}")
    public String deleteCurrentMovie(@PathVariable("id") Long id) {
        currentMovieService.deleteCurrentMovieById(id);
        return "redirect:/admin/movies";
    }

}