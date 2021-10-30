package avadamedia.kinocms.controller.banners;

import avadamedia.kinocms.model.banners.MainBanner;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.service.banner.MainBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    // Add
    @GetMapping("add")
    public String addMainBanner() {
        mainBannerService.createMainBanner(new MainBanner());
        return "redirect:/admin/banners/main/edit/" + mainBannerService.getLastId();
    }

    // Update
    @GetMapping("edit/{id}")
    public ModelAndView showEditMainBannerPage(@PathVariable("id") Long id) {
        return new ModelAndView("admin/banners/main/update",
                "mainBanner", mainBannerService.getMainBannerById(id));
    }

    @PutMapping("update/{id}")
    public String updateMainBanner(@PathVariable("id") Long id,
                                   @ModelAttribute("mainBanner") MainBanner mainBanner,
                                   @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "main-banners/" + mainBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        mainBanner.setMainImage(fileName);
        mainBannerService.updateMainBanner(mainBanner);
        return "redirect:/admin/banners/main/edit/" + id;
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteMainBanner(@PathVariable("id") Long id) {
        mainBannerService.deleteMainBannerById(id);
        return "redirect:/admin/banners";
    }

}