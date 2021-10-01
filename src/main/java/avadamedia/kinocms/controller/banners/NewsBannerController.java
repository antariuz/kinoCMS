package avadamedia.kinocms.controller.banners;

import avadamedia.kinocms.model.common.FileUploadUtil;
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

    private final NewsBannerService service;

    //    Add part
    @GetMapping("add")
    public String addNewsBanner(Model model) {
        model.addAttribute("newsBanner", new NewsBanner());
        return "/admin/banners/news/add";
    }

    @PostMapping("add")
    public String addNewsBanner(NewsBanner newsBanner,
                                @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        newsBanner.setImageName(fileName);
        service.createNewsBanner(newsBanner);
        String uploadDir = "news-banners/" + newsBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteNewsBanner(@PathVariable("id") Long id) {
        service.deleteNewsBannerById(id);
        return "redirect:/admin/banners";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateNewsBanner(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/banners/news/update", "newsBanner", service.getNewsBannerById(id));
    }

    @PutMapping("update")
    public String updateNewsBanner(NewsBanner newsBanner, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        newsBanner.setImageName(fileName);
        service.updateNewsBanner(newsBanner);
        String uploadDir = "news-banners/" + newsBanner.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

}