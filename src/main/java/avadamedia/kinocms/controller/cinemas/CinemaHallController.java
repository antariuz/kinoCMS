package avadamedia.kinocms.controller.cinemas;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.cinemas.assist.CinemaHall;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.service.CinemaHallService;
import avadamedia.kinocms.service.CinemaService;
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
@RequestMapping("admin/cinemas/halls")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CinemaHallController {

    private final CinemaHallService service;

    //    Show all CinemasHall
    @GetMapping({"/", ""})
    public ModelAndView showAllCinemaHall() {
        return new ModelAndView("/admin/cinemas/halls/index", "cinemaHalls", service.getAllCinemaHalls());
    }

    //    Add part

    @GetMapping("add")
    public String addCinemaHall(Model model) {
        model.addAttribute("cinemaHall", new CinemaHall());
        model.addAttribute("seo", new SEO());
        return "/admin/cinemas/halls/add";
    }

    @PostMapping("add")
    public String addCinemaHall(CinemaHall cinemaHall,
                            @RequestParam("logoImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        cinemaHall.setMainImage(fileName);
        service.createCinemaHall(cinemaHall);
        String uploadDir = "cinemas/halls/" + cinemaHall.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/cinemas";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteCinemaHall(@PathVariable("id") Long id) {
        service.deleteCinemaHallById(id);
        return "redirect:/admin/cinemas";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateCinemaHall(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/cinemas/halls/update");
        mav.addObject("cinemaHall", service.getCinemaHallById(id));
        mav.addObject("seo", new SEO());
        return mav;
    }

    @PutMapping("update")
    public String updateCinemaHall(CinemaHall cinemaHall,
                                   @RequestParam("mainImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        cinemaHall.setMainImage(fileName);
        service.updateCinemaHall(cinemaHall);
        String uploadDir = "cinemas/halls/" + cinemaHall.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/cinemas";
    }

}