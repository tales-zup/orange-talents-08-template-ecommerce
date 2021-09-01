package com.zup.ecommerce.categoria;

import com.zup.ecommerce.commons.utils.JsonUtil;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaControllerTest {

//    @Autowired
//    private CategoriaRepository categoriaRepository;

    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        categoriaRepository.deleteAll();
//    }
    // tentei usar essa abordagem pra limpar a base pra cada teste, mas não estava dando certo
    // consegui apenas usando a anotação @DirtiesContext

    private void performarRequest(CategoriaRequest categoriaRequest, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(categoriaRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(matcher);
    }

    @Test
    public void deveRetornarSucessoAoCriarNovaCategoria() throws Exception {
        CategoriaRequest categoriaRequest = new CategoriaRequest("Tecnologia", null);
        performarRequest(categoriaRequest, status().isOk());
    }

    @Test
    public void deveRetornarSucessoAoCriarNovaCategoriaComCategoriaMae() throws Exception {
        CategoriaRequest catTecnologia = new CategoriaRequest("Tecnologia", null);
        performarRequest(catTecnologia, status().isOk());
        CategoriaRequest catSmartphone = new CategoriaRequest("Smartphone", 1L);
        performarRequest(catSmartphone, status().isOk());
    }

    @Test
    public void deveRetornar400BadRequestAoCriarCategoriaComIdCategoriaMaeInexistente() throws Exception {
        CategoriaRequest catTecnologia = new CategoriaRequest("Tecnologia", null);
        performarRequest(catTecnologia, status().isOk());
        CategoriaRequest catSmartphone = new CategoriaRequest("Smartphone", 2L);
        performarRequest(catSmartphone, status().isBadRequest());
    }

    @Test
    public void deveRetornar400BadRequestAoCriarCategoriaComNomeVazio() throws Exception {
        CategoriaRequest categoriaRequest = new CategoriaRequest("", null);
        performarRequest(categoriaRequest, status().isBadRequest());
    }
}
