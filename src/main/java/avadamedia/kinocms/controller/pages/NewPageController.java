package avadamedia.kinocms.controller.pages;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.pages.NewPage;
import avadamedia.kinocms.service.common.ImageService;
import avadamedia.kinocms.service.page.NewPageService;
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
@RequestMapping("admin/pages/new")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewPageController {

    private final NewPageService newPageService;
    private final ImageService imageService;

    // Add
    @GetMapping("add")
    public String addNewPage() {
        NewPage newPage = new NewPage();
        newPage.setSeo(new SEO());
        newPage.setImages(imageService.initImageList(4));
        newPageService.createNewPage(newPage);
        return "redirect:/admin/pages/new/edit/" + newPage.getId();
    }

    // Update
    @GetMapping("edit/{id}")
    public ModelAndView showEditNewPagePage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/pages/new/update");
        mav.addObject("newPage", newPageService.getNewPageById(id));
        return mav;
    }

    @PutMapping("update/{id}")
    public String updateNewPage(@PathVariable("id") Long id,
                                @ModelAttribute("newPage") NewPage newPage,
                                @RequestParam("image") MultipartFile file,
                                @RequestParam("galleryImages") MultipartFile[] galleryImages) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "new-pages/" + id;
        String galleryImagesUploadDir = "/gallery-images/";

        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            newPage.setMainImage(fileName);
        } else newPage.setMainImage(newPageService.getNewPageById(id).getMainImage());

        List<Image> images = newPageService.getNewPageById(id).getImages();
        if (galleryImages.length != 0) {
            for (int i = 0; i < galleryImages.length; i++) {
                String galleryImageName = StringUtils.cleanPath(galleryImages[i].getOriginalFilename());
                if (!galleryImageName.equals("")) {
                    FileUploadUtil.saveFile(galleryImagesUploadDir, galleryImageName, galleryImages[i]);
                    images.get(i).setName(galleryImageName);
                } else {
                    images.get(i).setName(newPageService.getNewPageById(id).getImages().get(i).getName());
                }
            }
        }
        newPage.setImages(images);
        newPageService.updateNewPage(newPage);
        return "redirect:/admin/pages/new/edit/" + id;
    }

    // Delete
    @GetMapping("delete/{id}")
    public String deleteNewPage(@PathVariable("id") Long id) {
        newPageService.deleteNewPageById(id);
        return "redirect:/admin/pages";
    }

}