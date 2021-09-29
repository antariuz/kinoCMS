package avadamedia.kinocms.controller.banners;

import avadamedia.kinocms.model.assist.FileUploadUtil;
import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.model.banners.MainBanner;
import avadamedia.kinocms.model.banners.NewsBanner;
import avadamedia.kinocms.service.BackgroundBannerService;
import avadamedia.kinocms.service.MainBannerService;
import avadamedia.kinocms.service.NewsBannerService;
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
@RequestMapping("admin/banners/main/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainBannerController {

    private final MainBannerService mainBannerService;

    //    Add part mapping of MainBanner
    @GetMapping("add")
    public String addMainBanner(Model model) {
        model.addAttribute("mainBanner", new MainBanner());
        return "/admin/banners/main/add";
    }

    @PostMapping("add")
    public String addMainBanner(MainBanner mainBanner,
                                @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        mainBanner.setImageUrl(fileName);
        mainBannerService.createMainBanner(mainBanner);
        String uploadDir = "main-banners/" + mainBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteMainBanner(@PathVariable("id") Long id) {
        mainBannerService.deleteMainBannerById(id);
        return "redirect:/admin/banners";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateMainBanner(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/banners/main/update", "mainBanner", mainBannerService.getMainBannerById(id));
    }

    @PutMapping("update")
    public String updateMainBanner(MainBanner mainBanner, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        mainBanner.setImageUrl(fileName);
        mainBannerService.updateMainBanner(mainBanner);
        String uploadDir = "main-banners/" + mainBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

}