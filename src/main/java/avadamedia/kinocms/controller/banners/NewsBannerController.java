package avadamedia.kinocms.controller.banners;

import avadamedia.kinocms.model.banners.NewsBanner;
import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.service.banner.NewsBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("admin/banners/news/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsBannerController {

    private final NewsBannerService newsBannerService;

    // Add
    @GetMapping("add")
    public String addNewsBanner() {
        newsBannerService.createNewsBanner(new NewsBanner());
        return "redirect:/admin/banners/news/edit/" + newsBannerService.getLastId();
    }

    // Update
    @GetMapping("edit/{id}")
    public ModelAndView showEditNewsBannerPage(@PathVariable("id") Long id) {
        return new ModelAndView("admin/banners/news/update",
                "newsBanner", newsBannerService.getNewsBannerById(id));
    }

    @PutMapping("update/{id}")
    public String updateNewsBanner(@PathVariable("id") Long id,
                                   @ModelAttribute("newsBanner") NewsBanner newsBanner,
                                   @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "news-banners/" + newsBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        newsBanner.setMainImage(fileName);
        newsBannerService.updateNewsBanner(newsBanner);
        return "redirect:/admin/banners/news/edit/" + id;
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteNewsBanner(@PathVariable("id") Long id) {
        newsBannerService.deleteNewsBannerById(id);
        return "redirect:/admin/banners";
    }

}