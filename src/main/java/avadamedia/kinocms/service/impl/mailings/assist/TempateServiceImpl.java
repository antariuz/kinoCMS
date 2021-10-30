package avadamedia.kinocms.service.impl.mailings.assist;

import avadamedia.kinocms.model.mailing.assist.Template;
import avadamedia.kinocms.repository.mailings.assist.TemplateRepository;
import avadamedia.kinocms.service.mailing.assist.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TempateServiceImpl implements TemplateService {

    private final TemplateRepository repository;

    @Override
    public void createTemplate(Template template) {
        repository.save(template);
    }

    @Override
    public void updateTemplate(Template template) {
        repository.save(template);
    }

    @Override
    public void deleteTemplateById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Template> getAllTemplates() {
        return repository.findAll();
    }

    @Override
    public Template getTemplateById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Long getLastId() {
        return repository.getLastId();
    }

    @Override
    public List<Template> getLastFiveId() {
        return repository.getLastFiveId();
    }

}
