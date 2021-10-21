package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.service.CinemaHallService;
import avadamedia.kinocms.service.CinemaService;
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

    // Add cinema
    @GetMapping("add")
    public String addCinema() {
        Cinema cinema = new Cinema();
        cinema.setImageList(new ArrayList<>());
        cinema.setCinemaHallList(new ArrayList<>());
        cinema.setSeo(new SEO());
        cinemaService.createCinema(cinema);
        return "redirect:/admin/cinemas/update/" + cinemaService.getLastId();
    }

    // Update cinema
    @GetMapping("update/{id}")
    public ModelAndView updateCinema(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/cinemas/update");
        mav.addObject("cinema", cinemaService.getCinemaById(id));
        mav.addObject("imageList", cinemaService.getCinemaById(id).getImageList());
        mav.addObject("cinemaHalls", cinemaService.getCinemaById(id).getCinemaHallList());
        mav.addObject("seo", cinemaService.getCinemaById(id).getSeo());
        System.out.println("Images: " + cinemaService.getCinemaById(id).getImageList());
        System.out.println("Cinema Halls: " + cinemaService.getCinemaById(id).getCinemaHallList());
        System.out.println("SEO : " + cinemaService.getCinemaById(id).getSeo());
        return mav;
    }

    @PutMapping("update")
    public String updateCinema(Cinema cinema, SEO seo,
                               @RequestParam("image") MultipartFile file,
                               @RequestParam("image2") MultipartFile file2) throws IOException {
        String uploadDir = "cinemas/" + cinema.getId();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        FileUploadUtil.saveFile(uploadDir, fileName2, file2);
        cinema.setMainImage(fileName);
        cinema.setTopBanner(fileName2);
        cinema.setSeo(seo);
        cinemaService.updateCinema(cinema);
        return "redirect:/admin/cinemas";
    }

    // Delete cinema
    @GetMapping("delete/{id}")
    public String deleteCinema(@PathVariable("id") Long id) {
        cinemaService.deleteCinemaById(id);
        return "redirect:/admin/cinemas";
    }

    // Create cinema hall
    @GetMapping("update/{id}/halls/add")
    public String addCinemaHall(@PathVariable("id") Long id) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setImageList(new ArrayList<>());
        cinemaHall.setSeo(new SEO());
        cinemaHallService.createCinemaHall(cinemaHall);
        cinemaService.getCinemaById(id).getCinemaHallList().add(cinemaHall);
        cinemaService.getCinemaById(id).getCinemaHallList().forEach(System.out::println);
        return "redirect:/admin/cinemas/update/" + id + "/halls/" + cinemaHallService.getLastId();
    }

    // Update cinema hall
    @GetMapping("update/{id}/halls/{idHall}")
    public ModelAndView updateCinemaHall(@PathVariable("id") Long id, @PathVariable("idHall") Long idHall) {
        ModelAndView mav = new ModelAndView("/admin/cinemas/halls/update");
        mav.addObject("cinema", cinemaService.getCinemaById(id));
        mav.addObject("cinemaHall", cinemaHallService.getCinemaHallById(idHall));
//        mav.addObject("imageList", cinemaHallService.getCinemaHallById(id).getImageList());
        mav.addObject("seo", cinemaHallService.getCinemaHallById(idHall).getSeo());
        return mav;
    }

    @PutMapping("halls/update")
    public String updateCinemaHall(Cinema cinema, CinemaHall cinemaHall, SEO seo,
                                   @RequestParam("image") MultipartFile file,
                                   @RequestParam("image2") MultipartFile file2) throws IOException {
        String uploadDir = "cinema-halls/" + cinemaHall.getId() + "/";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        FileUploadUtil.saveFile(uploadDir, fileName2, file2);
        cinemaHall.setMainImage(fileName);
        cinemaHall.setTopBanner(fileName2);
        cinema.setSeo(seo);
        cinemaHallService.updateCinemaHall(cinemaHall);
        return "redirect:/admin/cinemas/update/" + cinema.getId();
    }

    // Delete cinema hall
    @GetMapping("delete/{id}/halls/{idHall}")
    public String deleteCinemaHall(@PathVariable("id") Long id, @PathVariable("idHall") Long idHall) {
        cinemaHallService.deleteCinemaHallById(idHall);
        return "redirect:/admin/cinemas/update/" + id;
    }

}