package com.zup.ecommerce.commons;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Profile("prod")
@Service
public class ProdImageUploaderService implements ImageUploaderService{

    @Override
    public Set<String> envia(List<MultipartFile> imagens) {
        System.out.println("ENVIANDO IMAGEM PRA PROD!");
        return imagens.stream()
                .map(imagem -> "https://bucket.io/"
                        + imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }

}
