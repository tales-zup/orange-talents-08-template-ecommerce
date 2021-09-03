package com.zup.ecommerce.commons;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ImageUploaderService {

    /**
     *
     * @param imagens
     * @return links para imagens que foram uplodeadas
     */
    Set<String> envia(List<MultipartFile> imagens);

}
