package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.news.News;
import avadamedia.kinocms.service.NewsService;
import avadamedia.kinocms.service.common.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/news")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsController {

    private final NewsService newsService;
    private final ImageService imageService;

    //    Show all News
    @GetMapping({"/", ""})
    public ModelAndView showAllNews() {
        return new ModelAndView("admin/news/index", "newsList", newsService.getAllNews());
    }

    //    Add part
    @GetMapping("add")
    public String addNews() {
        News news = new News();
        news.setImages(imageService.initImageList(4));
        news.setSeo(new SEO());
        newsService.createNews(news);
        return "redirect:/admin/news/edit/" + news.getId();
    }

    // Update
    @GetMapping("edit/{id}")
    public ModelAndView showEditNewsPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/news/update");
        mav.addObject("news", newsService.getNewsById(id));
        return mav;
    }

    @PutMapping("update/{id}")
    public String updateNews(@PathVariable("id") Long id,
                             @ModelAttribute("news") News news,
                             @RequestParam("image") MultipartFile file,
                             @RequestParam("galleryImages") MultipartFile[] galleryImages) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "news/" + news.getId();
        String galleryImagesUploadDir = "/gallery-images/";
        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            news.setMainImage(fileName);
        } else news.setMainImage(newsService.getNewsById(id).getMainImage());
        List<Image> images = newsService.getNewsById(id).getImages();
        if (galleryImages.length != 0) {
            for (int i = 0; i < galleryImages.length; i++) {
                String galleryImageName = StringUtils.cleanPath(galleryImages[i].getOriginalFilename());
                if (!galleryImageName.equals("")) {
                    FileUploadUtil.saveFile(galleryImagesUploadDir, galleryImageName, galleryImages[i]);
                    images.get(i).setName(galleryImageName);
                } else {
                    images.get(i).setName(newsService.getNewsById(id).getImages().get(i).getName());
                }
            }
        }
        news.setImages(images);
        newsService.updateNews(news);
        return "redirect:/admin/news/edit/" + id;
    }

    // Delete
    @GetMapping("delete/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNewsById(id);
        return "redirect:/admin/news";
    }

}