package avadamedia.kinocms.service;

import avadamedia.kinocms.model.common.Image;

public interface ImageService {

    Image createImage(Image image);

    void updateImage(Image image);

    void deleteImageById(Long id);

    Iterable<Image> getAllImages();

    Image getImageById(Long id);

}
