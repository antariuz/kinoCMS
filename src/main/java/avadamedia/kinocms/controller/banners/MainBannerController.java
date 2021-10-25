package avadamedia.kinocms.controller.banners;


import avadamedia.kinocms.model.banners.MainBanner;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.service.banner.MainBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("admin/banners/main/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainBannerController {

    private final MainBannerService service;

    //    Add part
    @GetMapping("add")
    public String addMainBanner(Model model) {
        model.addAttribute("mainBanner", new MainBanner());
        return "/admin/banners/main/add";
    }

    @PostMapping("add")
    public RedirectView addMainBanner(MainBanner mainBanner,
                                      @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        mainBanner.setMainImage(fileName);
        service.createMainBanner(mainBanner);
        String uploadDir = "main-banners/" + mainBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return new RedirectView("/admin/banners", true);
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteMainBanner(@PathVariable("id") Long id) {
        service.deleteMainBannerById(id);
        return "redirect:/admin/banners";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateMainBanner(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/banners/main/update", "mainBanner", service.getMainBannerById(id));
    }

    @PutMapping("update")
    public String updateMainBanner(MainBanner mainBanner, @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        mainBanner.setMainImage(fileName);
        service.updateMainBanner(mainBanner);
        String uploadDir = "main-banners/" + mainBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

}