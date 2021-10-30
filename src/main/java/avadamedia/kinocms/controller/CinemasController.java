package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.service.cinema.CinemaHallService;
import avadamedia.kinocms.service.cinema.CinemaService;
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
import java.util.List;

@Controller
@RequestMapping("admin/cinemas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CinemasController {

    private final CinemaService cinemaService;
    private final CinemaHallService cinemaHallService;
    private final ImageService imageService;

    // Show all Cinemas
    @GetMapping({"/", ""})
    public ModelAndView showAllCinemas() {
        return new ModelAndView("admin/cinemas/index", "cinemas", cinemaService.getAllCinemas());
    }

    // Cinema part
    // Add cinema
    @GetMapping("add")
    public String addCinema() {
        Cinema cinema = new Cinema();
        cinema.setImages(imageService.initImageList(4));
        cinema.setCinemaHalls(new ArrayList<>());
        cinema.setSeo(new SEO());
        cinemaService.createCinema(cinema);
        return "redirect:/admin/cinemas/edit/" + cinemaService.getLastId();
    }

    // Update cinema
    @GetMapping("edit/{cinemaId}")
    public ModelAndView showCinemaEditPage(@PathVariable("cinemaId") Long cinemaId) {
        return new ModelAndView("admin/cinemas/update", "cinema", cinemaService.getCinemaById(cinemaId));
    }

    @PutMapping("update/{cinemaId}")
    public String updateCinema(@PathVariable("cinemaId") Long cinemaId,
                               @ModelAttribute("cinema") Cinema cinema,
                               @RequestParam("image") MultipartFile file,
                               @RequestParam("image2") MultipartFile file2,
                               @RequestParam("galleryImages") MultipartFile[] galleryImages) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        String uploadDir = "cinemas/" + cinemaId;
        String galleryImagesUploadDir = "/gallery-images/";
        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            cinema.setMainImage(fileName);
        } else cinema.setMainImage(cinemaService.getCinemaById(cinemaId).getMainImage());
        if (!fileName2.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName2, file2);
            cinema.setTopBanner(fileName2);
        } else cinema.setMainImage(cinemaService.getCinemaById(cinemaId).getTopBanner());
        List<Image> images = cinemaService.getCinemaById(cinemaId).getImages();
        if (galleryImages.length != 0) {
            for (int i = 0; i < galleryImages.length; i++) {
                String galleryImageName = StringUtils.cleanPath(galleryImages[i].getOriginalFilename());
                if (!galleryImageName.equals("")) {
                    FileUploadUtil.saveFile(galleryImagesUploadDir, galleryImageName, galleryImages[i]);
                    images.get(i).setName(galleryImageName);
                } else {
                    images.get(i).setName(cinemaService.getCinemaById(cinemaId).getImages().get(i).getName());
                }

            }
        }
        cinema.setImages(images);
        cinemaService.updateCinema(cinema);
        return "redirect:/admin/cinemas/edit/{cinemaId}";
    }

    // Delete cinema
    @GetMapping("delete/{cinemaId}")
    public String deleteCinema(@PathVariable("cinemaId") Long cinemaId) {
        cinemaService.deleteCinemaById(cinemaId);
        return "redirect:/admin/cinemas";
    }

    // Cinema Hall part
    // Create cinema hall
    @GetMapping("edit/{cinemaId}/halls/add")
    public String addCinemaHall(@PathVariable("cinemaId") Long cinemaId) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setImages(imageService.initImageList(4));
        cinemaHall.setSeo(new SEO());
        cinemaHall.setCinemaId(cinemaId);
        cinemaHallService.createCinemaHall(cinemaHall);
        return "redirect:/admin/cinemas/edit/" + cinemaId + "/halls/edit/" + cinemaHallService.getLastId();
    }

    // Update cinema hall
    @GetMapping("edit/{cinemaId}/halls/edit/{hallId}")
    public ModelAndView showCinemaHallEditPage(@PathVariable("cinemaId") Long cinemaId,
                                               @PathVariable("hallId") Long hallId) {
        return new ModelAndView("admin/cinemas/halls/update",
                "cinemaHall", cinemaHallService.getCinemaHallById(hallId));
    }

    @PutMapping("edit/{cinemaId}/halls/update/{hallId}")
    public String updateCinemaHall(@PathVariable("cinemaId") Long cinemaId,
                                   @PathVariable("hallId") Long hallId,
                                   @ModelAttribute("cinema") Cinema cinema,
                                   @ModelAttribute("cinemaHall") CinemaHall cinemaHall,
                                   @RequestParam("image") MultipartFile file,
                                   @RequestParam("image2") MultipartFile file2,
                                   @RequestParam("galleryImages") MultipartFile[] galleryImages) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        String uploadDir = "cinema-halls/" + hallId;
        String galleryImagesUploadDir = "/gallery-images/";
        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            cinemaHall.setMainImage(fileName);
        } else cinema.setMainImage(cinemaHallService.getCinemaHallById(hallId).getMainImage());
        if (!fileName2.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName2, file2);
            cinemaHall.setTopBanner(fileName2);
        } else cinema.setMainImage(cinemaHallService.getCinemaHallById(hallId).getTopBanner());
        List<Image> images = cinemaHallService.getCinemaHallById(hallId).getImages();
        if (galleryImages.length != 0) {
            for (int i = 0; i < galleryImages.length; i++) {
                String galleryImageName = StringUtils.cleanPath(galleryImages[i].getOriginalFilename());
                if (!galleryImageName.equals("")) {
                    FileUploadUtil.saveFile(galleryImagesUploadDir, galleryImageName, galleryImages[i]);
                    images.get(i).setName(galleryImageName);
                } else {
                    images.get(i).setName(cinemaHallService.getCinemaHallById(hallId).getImages().get(i).getName());
                }
            }
        }
        cinemaHall.setImages(images);
        cinemaHallService.updateCinemaHall(cinemaHall);
        return "redirect:/admin/cinemas/edit/" + cinemaId;
    }

    // Delete cinema hall
    @GetMapping("edit/{cinemaId}/halls/delete/{hallId}")
    public String deleteCinemaHall(@PathVariable("cinemaId") Long cinemaId,
                                   @PathVariable("hallId") Long hallId) {
        cinemaHallService.deleteCinemaHallById(hallId);
        return "redirect:/admin/cinemas/edit/" + cinemaId;
    }

}
