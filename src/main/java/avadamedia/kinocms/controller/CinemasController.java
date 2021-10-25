package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.service.cinema.CinemaHallService;
import avadamedia.kinocms.service.cinema.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("admin/cinemas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CinemasController {

    private final CinemaService cinemaService;
    private final CinemaHallService cinemaHallService;

    // Show all Cinemas
    @GetMapping({"/", ""})
    public ModelAndView showAllCinemas() {
        return new ModelAndView("/admin/cinemas/index", "cinemas", cinemaService.getAllCinemas());
    }

    // Cinema part
    // Add cinema
    @GetMapping("add")
    public String addCinema() {
        Cinema cinema = new Cinema();
        cinema.setImages(new ArrayList<>());
        cinema.setCinemaHalls(new ArrayList<>());
        cinema.setSeo(new SEO());
        cinemaService.createCinema(cinema);
        return "redirect:/admin/cinemas/edit/" + cinemaService.getLastId();
    }

    // Update cinema
    @GetMapping("edit/{cinemaId}")
    public ModelAndView showCinemaEditPage(@PathVariable("cinemaId") Long cinemaId) {
        ModelAndView mav = new ModelAndView("/admin/cinemas/update");
        mav.addObject("cinema", cinemaService.getCinemaById(cinemaId));
        return mav;
    }

    @PutMapping("update/{cinemaId}")
    public String updateCinema(@PathVariable("cinemaId") Long cinemaId,
                               @RequestParam("image") MultipartFile file,
                               @RequestParam("image2") MultipartFile file2,
                               @ModelAttribute("cinema") Cinema cinema) throws IOException {
        String uploadDir = "cinemas/" + cinemaId;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        FileUploadUtil.saveFile(uploadDir, fileName2, file2);
        cinema.setMainImage(fileName);
        cinema.setTopBanner(fileName2);
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
        cinemaHall.setImages(new ArrayList<>());
        cinemaHall.setSeo(new SEO());
        cinemaHall.setCinemaId(cinemaId);
        cinemaHallService.createCinemaHall(cinemaHall);
        return "redirect:/admin/cinemas/edit/" + cinemaId + "/halls/edit/" + cinemaHallService.getLastId();
    }

    // Update cinema hall
    @GetMapping("edit/{cinemaId}/halls/edit/{hallId}")
    public ModelAndView showCinemaHallEditPage(@PathVariable("cinemaId") Long cinemaId, @PathVariable("hallId") Long hallId) {
        ModelAndView mav = new ModelAndView("/admin/cinemas/halls/update");
        mav.addObject("cinema", cinemaService.getCinemaById(cinemaId));
        mav.addObject("cinemaHall", cinemaHallService.getCinemaHallById(hallId));
        return mav;
    }

    @PutMapping("edit/{cinemaId}/halls/update/{hallId}")
    public String updateCinemaHall(@PathVariable("cinemaId") Long cinemaId,
                                   @PathVariable("hallId") Long hallId,
                                   @ModelAttribute("cinema") Cinema cinema,
                                   @ModelAttribute("cinemaHall") CinemaHall cinemaHall,
                                   @RequestParam("image") MultipartFile file,
                                   @RequestParam("image2") MultipartFile file2) throws IOException {
        String uploadDir = "cinema-halls/" + cinemaHall.getId() + "/";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        FileUploadUtil.saveFile(uploadDir, fileName2, file2);
        cinemaHall.setMainImage(fileName);
        cinemaHall.setTopBanner(fileName2);
        cinemaHallService.updateCinemaHall(cinemaHall);
        return "redirect:/admin/cinemas/edit/" + cinemaId;
    }

    // Delete cinema hall
    @GetMapping("edit/{cinemaId}/halls/delete/{hallId}")
    public String deleteCinemaHall(@PathVariable("cinemaId") Long cinemaId, @PathVariable("hallId") Long hallId) {
        cinemaHallService.deleteCinemaHallById(hallId);
        return "redirect:/admin/cinemas/edit/" + cinemaId;
    }

}
