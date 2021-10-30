package avadamedia.kinocms.controller.movies;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.movies.ComingMovie;
import avadamedia.kinocms.model.movies.assist.MovieInfo;
import avadamedia.kinocms.model.movies.assist.MovieType;
import avadamedia.kinocms.service.common.ImageService;
import avadamedia.kinocms.service.movie.ComingMovieService;
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
@RequestMapping("admin/movies/coming")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ComingMovieController {

    private final ComingMovieService comingMovieService;
    private final MovieTypeService movieTypeService;
    private final ImageService imageService;

    // Add
    @GetMapping("add")
    public String addComingMovie() {
        ComingMovie comingMovie = new ComingMovie();
        comingMovie.setMovieTypes(new HashSet<>());
        comingMovie.setImages(imageService.initImageList(4));
        comingMovie.setMovieInfo(new MovieInfo());
        comingMovie.setSeo(new SEO());
        comingMovieService.createComingMovie(comingMovie);
        return "redirect:/admin/movies/coming/edit/" + comingMovieService.getLastId();
    }

    // Update
    @GetMapping("edit/{id}")
    public ModelAndView updateComingMovie(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/movies/coming/update");
        mav.addObject("comingMovie", comingMovieService.getComingMovieById(id));
        mav.addObject("movieTypes", movieTypeService.getAllMovieTypes());
        return mav;
    }

    @PutMapping("update/{id}")
    public String updateCurrentMovie(@PathVariable("id") Long id,
                                     @ModelAttribute("comingMovie") ComingMovie comingMovie,
                                     @RequestParam("movieTypes") int[] movieTypes,
                                     @RequestParam("image") MultipartFile file,
                                     @RequestParam("galleryImages") MultipartFile[] galleryImages) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "coming-movies/" + comingMovie.getId();
        String galleryImagesUploadDir = "/gallery-images/";

        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            comingMovie.setMainImage(fileName);
        } else comingMovie.setMainImage(comingMovieService.getComingMovieById(id).getMainImage());
        Set<MovieType> movieTypeSet = new HashSet<>();
        if (movieTypes.length != 0) {
            for (int movieType : movieTypes) {
                movieTypeSet.add(movieTypeService.getMovieTypeById((long) movieType));
            }
        }
        comingMovie.setMovieTypes(movieTypeSet);
        List<Image> images = comingMovieService.getComingMovieById(id).getImages();
        if (galleryImages.length != 0) {
            for (int i = 0; i < galleryImages.length; i++) {
                String galleryImageName = StringUtils.cleanPath(galleryImages[i].getOriginalFilename());
                if (!galleryImageName.equals("")) {
                    FileUploadUtil.saveFile(galleryImagesUploadDir, galleryImageName, galleryImages[i]);
                    images.get(i).setName(galleryImageName);
                } else {
                    images.get(i).setName(comingMovieService.getComingMovieById(id).getImages().get(i).getName());
                }

            }
        }
        comingMovie.setImages(images);
        comingMovieService.updateComingMovie(comingMovie);
        return "redirect:/admin/movies/coming/edit/" + id;
    }

    // Delete
    @GetMapping("delete/{id}")
    public String deleteComingMovie(@PathVariable("id") Long id) {
        comingMovieService.deleteComingMovieById(id);
        return "redirect:/admin/movies";
    }

}