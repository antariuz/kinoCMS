package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.cinemas.Cinema;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
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
@RequestMapping("admin/cinemas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CinemasController {

    private final CinemaService service;

    //    Show all Cinemas
    @GetMapping({"/", ""})
    public ModelAndView showAllCinemas() {
        return new ModelAndView("/admin/cinemas/index", "cinemas", service.getAllCinemas());
    }

    //    Add part

    @GetMapping("add")
    public String addCinema(Model model) {
        model.addAttribute("cinema", new Cinema());
        model.addAttribute("seo", new SEO());
        return "/admin/cinemas/add";
    }

    @PostMapping("add")
    public String addCinema(Cinema cinema,
                            @RequestParam("logoImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        cinema.setMainImage(fileName);
        service.createCinema(cinema);
        String uploadDir = "cinemas/" + cinema.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/cinemas";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteCinema(@PathVariable("id") Long id) {
        service.deleteCinemaById(id);
        return "redirect:/admin/cinemas";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateCinema(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/cinemas/update");
        mav.addObject("cinema", service.getCinemaById(id));
        mav.addObject("seo", new SEO());
        return mav;
    }

    @PutMapping("update")
    public String updateCinema(Cinema cinema,
                               @RequestParam("mainImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        cinema.setMainImage(fileName);
        service.updateCinema(cinema);
        String uploadDir = "cinemas/" + cinema.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/cinemas";
    }

}