package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.news.News;
import avadamedia.kinocms.service.NewsService;
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
@RequestMapping("admin/news")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsController {

    private final NewsService service;

    //    Show all News
    @GetMapping({"/", ""})
    public ModelAndView showAllNews() {
        return new ModelAndView("/admin/news/index", "newsList", service.getAllNews());
    }

    //    Add part
    @GetMapping("add")
    public String addNews(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("seo", new SEO());
        return "/admin/news/add";
    }

    @PostMapping("add")
    public String addNews(News news,
                          @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        news.setMainImage(fileName);
        service.createNews(news);
        String uploadDir = "news/" + news.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/news";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        service.deleteNewsById(id);
        return "redirect:/admin/news";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateNews(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/news/update");
        mav.addObject("news", service.getNewsById(id));
        mav.addObject("seo", new SEO());
        return mav;
    }

    @PutMapping("update")
    public String updateNews(News news,
                             @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        news.setMainImage(fileName);
        service.updateNews(news);
        String uploadDir = "news/" + news.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/news";
    }

}