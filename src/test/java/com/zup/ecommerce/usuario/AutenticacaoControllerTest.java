package com.zup.ecommerce.usuario;

import com.zup.ecommerce.categoria.CategoriaRequest;
import com.zup.ecommerce.commons.utils.JsonUtil;
import com.zup.ecommerce.usuario.login.LoginRequest;
import org.junit.Test;
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
public class AutenticacaoControllerTest {

    private final String PATH_USUARIOS = "/usuarios";
    private final String PATH_CATEGORIAS = "/categorias";
    private final String PATH_AUTH = "/auth";

    @Autowired
    private MockMvc mockMvc;

    private void criarUsuario(UsuarioRequest usuarioRequest, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_USUARIOS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(usuarioRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(matcher);
    }

    private void autenticar(LoginRequest loginRequest, ResultMatcher matcher) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(loginRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(matcher);
    }

    private void criarCategoria(CategoriaRequest categoriaRequest, ResultMatcher matcher, String token) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_CATEGORIAS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(JsonUtil.toJson(categoriaRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(matcher);
    }

    @Test
    public void deveriaCriarUmUsuarioEGerarUmTokenComSucesso() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("tales.araujo@zup.com.br", "123456");
        criarUsuario(usuarioRequest, status().isOk());
        LoginRequest loginRequest = new LoginRequest("tales.araujo@zup.com.br", "123456");
        autenticar(loginRequest, status().isOk());
    }

    @Test
    public void deveriaCriarUmUsuarioEDarErroAoAutenticar() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("tales.araujo@zup.com.br", "123456");
        criarUsuario(usuarioRequest, status().isOk());
        LoginRequest loginRequest = new LoginRequest("tales.araujo@zup.com.br", "abcdef");
        autenticar(loginRequest, status().isBadRequest());
    }

    @Test
    public void deveriaDar403ForbiddenAoUsarEndpointDeCadastroDeCategoriaSemToken() throws Exception {
        CategoriaRequest categoriaRequest = new CategoriaRequest("Tecnologia", null);
        criarCategoria(categoriaRequest, status().isForbidden(), "");
    }

}
