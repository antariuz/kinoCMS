package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.assist.FileUploadUtil;
import avadamedia.kinocms.model.banners.MainBanner;
import avadamedia.kinocms.service.MainBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("admin/banners")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainBannerController {

    private final MainBannerService service;

    //    Show all Banners
    @GetMapping({"/", ""})
    public ModelAndView banners() {
        return new ModelAndView("/admin/banners/list", "mainBanners", service.getAllMainBanners());
    }

    //    Add part mapping of MainBanner
    @GetMapping("/main/add")
    public String addMainBanner(Model model) {
        model.addAttribute("mainBanner", new MainBanner());
        return "/admin/banners/main/add";
    }

    @PostMapping("/main/add")
    public String addMainBanner(MainBanner mainBanner,
                                @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        mainBanner.setImageUrl(fileName);
        service.createMainBanner(mainBanner);
        String uploadDir = "/main-banners/" + mainBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

    //    Delete part mapping of Banners
    @GetMapping("/main/delete/{id}")
    public String deleteMainBanner(@PathVariable("id") Long id) {
        service.deleteMainBannerById(id);
        return "redirect:/admin/banners";
    }

    //    Update part mapping of Main Banner
    @GetMapping("/main/update/{id}")
    public ModelAndView updateMainBanner(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/banners/main/update", "mainBanner", service.getMainBannerById(id));
    }

    //    @PostMapping("/update")
    @PutMapping("/main/update")
    public String updateMainBanner(MainBanner mainBanner) {
        service.updateMainBanner(mainBanner);
        return "redirect:/admin/banners";
    }

}