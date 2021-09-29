package avadamedia.kinocms.controller.banners;

import avadamedia.kinocms.model.assist.FileUploadUtil;
import avadamedia.kinocms.model.banners.BackgroundBanner;
import avadamedia.kinocms.service.BackgroundBannerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("admin/banners/background/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BackgroundBannerController {

    private final BackgroundBannerService backgroundBannerService;

    @PutMapping("update")
    public String updateBackgroundBanner(BackgroundBanner backgroundBanner, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        backgroundBanner.setImageUrl(fileName);
        backgroundBannerService.updateBackgroundBanner(backgroundBanner);
        String uploadDir = "background-banners/";
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/banners";
    }

}
