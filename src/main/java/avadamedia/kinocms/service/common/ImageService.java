package avadamedia.kinocms.service.common;

import avadamedia.kinocms.model.common.Image;

import java.util.List;

public interface ImageService {

    Image createImage(Image image);

    void updateImage(Image image);

    void deleteImageById(Long id);

    Iterable<Image> getAllImages();

    Image getImageById(Long id);

    List<Image> initImageList(int qty);

    Long getFirstCurrentMovieId(Long currentMovieId);

}
