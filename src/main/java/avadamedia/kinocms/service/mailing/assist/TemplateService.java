package avadamedia.kinocms.service.mailing.assist;

import avadamedia.kinocms.model.mailing.assist.Template;

import java.util.List;

public interface TemplateService {

    void createTemplate(Template template);

    void updateTemplate(Template template);

    void deleteTemplateById(Long id);

    Iterable<Template> getAllTemplates();

    Template getTemplateById(Long id);

    Long getLastId();

    List<Template> getLastFiveId();

}
