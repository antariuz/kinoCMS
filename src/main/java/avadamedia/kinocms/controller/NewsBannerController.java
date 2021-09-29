package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.assist.FileUploadUtil;
import avadamedia.kinocms.model.banners.NewsBanner;
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
@RequestMapping("admin/banners/news/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsBannerController {

    private final NewsBannerService newsBannerService;

//    //   Show all Banners
//    @GetMapping({"/", ""})
//    public ModelAndView banners() {
//        return new ModelAndView("/admin/banners/index", "newsBanners", newsBannerService.getAllNewsBanners());
//    }

    //    Add part mapping of NewsBanner
    @GetMapping("/news/add")
    public String addNewsBanner(Model model) {
        model.addAttribute("newsBanner", new NewsBanner());
        return "/admin/banners/news/add";
    }

    @PostMapping("/news/add")
    public String addNewsBanner(NewsBanner newsBanner,
                                @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        newsBanner.setImageUrl(fileName);
        newsBannerService.createNewsBanner(newsBanner);
        String uploadDir = "news-banners/" + newsBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

    //    Delete part
    @GetMapping("/news/delete/{id}")
    public String deleteNewsBanner(@PathVariable("id") Long id) {
        newsBannerService.deleteNewsBannerById(id);
        return "redirect:/admin/banners";
    }

    //    Update part
    @GetMapping("/news/update/{id}")
    public ModelAndView updateNewsBanner(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/banners/news/update", "newsBanner", newsBannerService.getNewsBannerById(id));
    }

    @PutMapping("/news/update")
    public String updateNewsBanner(NewsBanner newsBanner, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        newsBanner.setImageUrl(fileName);
        newsBannerService.updateNewsBanner(newsBanner);
        String uploadDir = "news-banners/" + newsBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

}