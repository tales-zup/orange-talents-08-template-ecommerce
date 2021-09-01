package com.zup.ecommerce.usuario;


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
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private void performarRequest(UsuarioRequest usuarioRequest, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(usuarioRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(matcher);
    }

    @Test
    public void deveRetornarSucessoAoCriarNovoUsuario() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("tales.araujo@zup.com.br", "123456");
        performarRequest(usuarioRequest, status().isOk());
    }

    @Test
    public void deveRetornar400BadRequestPorLoginComFormatoInvalidoAoCriarUsuario() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("email invalido", "123456");
        performarRequest(usuarioRequest, status().isBadRequest());
    }

    @Test
    public void deveRetornar400BadRequestPorSenhaPequenaAoCriarNovoUsuario() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("tales.araujo@zup.com.br", "12345");
        performarRequest(usuarioRequest, status().isBadRequest());
    }

    @Test
    public void deveRetornar400BadRequestPorLoginVazioAoCriarNovoUsuario() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("", "12345");
        performarRequest(usuarioRequest, status().isBadRequest());
    }

    @Test
    public void deveRetornar400BadRequestPorSenhaVaziaAoCriarNovoUsuario() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("tales.araujo@zup.com.br", "");
        performarRequest(usuarioRequest, status().isBadRequest());
    }

    @Test
    public void deveRetornar400BadRequestPorUsuarioComEmailRepetido() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("tales.araujo@zup.com.br", "123456");
        performarRequest(usuarioRequest, status().isOk());
        performarRequest(usuarioRequest, status().isBadRequest());
    }

}
