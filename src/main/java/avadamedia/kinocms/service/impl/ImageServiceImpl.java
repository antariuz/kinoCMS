package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.repository.ImageRepository;
import avadamedia.kinocms.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    public Image createImage(Image image) {
        return repository.save(image);
    }

    @Override
    public void updateImage(Image image) {
        repository.save(image);
    }

    @Override
    public void deleteImageById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Image> getAllImages() {
        return repository.findAll();
    }

    @Override
    public Image getImageById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

}
