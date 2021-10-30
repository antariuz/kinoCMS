package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.mailing.assist.Template;
import avadamedia.kinocms.model.news.News;
import avadamedia.kinocms.service.UserService;
import avadamedia.kinocms.service.mailing.assist.TemplateService;
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
@RequestMapping("admin/mailings")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MailingsController {

    private final TemplateService templateService;
    private final UserService userService;

    // Show all
    @GetMapping({"/", ""})
    public ModelAndView showMailingPage() {
        ModelAndView mav = new ModelAndView("admin/mailings/index");
        mav.addObject("templates", templateService.getLastFiveId());
        if (templateService.getAllTemplates().iterator().hasNext()) {
            mav.addObject("lastTemplate", templateService.getTemplateById(templateService.getLastId()));
        }
        return mav;
    }

    // Show choose users page
    @GetMapping({"choose"})
    public ModelAndView showChoosePage() {
        return new ModelAndView("admin/mailings/choose", "users", userService.getAllUsers());
    }

    // Add template
    @PutMapping("template/add")
    public String addTemplate(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Template template = new Template();
        template.setName(fileName);
        template.setEmailMailingId(1L);
        templateService.createTemplate(template);
        String uploadDir = "mailings/template/" + template.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin/mailings";
    }

    // Delete template
    @GetMapping("delete/{id}")
    public String deleteCurrentMovie(@PathVariable("id") Long id) {
        templateService.deleteTemplateById(id);
        return "redirect:/admin/mailings";
    }

}
