//package com.mylabs.myfuel.api.resource;
//
//import com.mylabs.myfuel.builds.AbastecimentoBuild;
//import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoInput;
//import com.mylabs.myfuel.domain.dto.mapper.AbastecimentoMapper;
//import com.mylabs.myfuel.domain.entity.Abastecimento;
//import com.mylabs.myfuel.domain.service.AbastecimentoService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@WebMvcTest(controllers = AbastecimentoController.class)
//@ContextConfiguration(classes = {AbastecimentoController.class, AbastecimentoService.class, AbastecimentoMapper.class})
//@AutoConfigureMockMvc
//public class AbastecimentoControllerTest {
//
//    static String ABASTECIMENTO_URL = "/abastecimento";
//
//    @Autowired
//    MockMvc mvc;
//
//    @MockBean
//    AbastecimentoService abastecimentoService;
//
//    @MockBean
//    AbastecimentoMapper abastecimentoMapper;
//
//    @BeforeEach
//    private void setup() {
//        BDDMockito.given(abastecimentoMapper.toEntity(Mockito.any(AbastecimentoInput.class)))
//                .willReturn(AbastecimentoBuild.createNewAbastecimeto());
//        BDDMockito.given(abastecimentoMapper.toModel(Mockito.any(Abastecimento.class)))
//                .willReturn(AbastecimentoBuild.createNewAbastecimentoModel());
//    }
//
//    @Test
//    @DisplayName("Deve salvar um abastecimento")
//    public void createAbastecimentoTest() throws Exception {
//        // Cenário
//        String json = AbastecimentoBuild.createJSONNewAbastecimentoInput();
//
//        Abastecimento abs = AbastecimentoBuild.createNewAbastecimeto();
//
//        BDDMockito.given(abastecimentoService.save(Mockito.any(Abastecimento.class)))
//                .willReturn(abs);
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .post(ABASTECIMENTO_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(json);
//
//        mvc
//                .perform(request)
//                .andExpect(status().isCreated());
////                .andExpect(jsonPath("id").value(AbastecimentoBuild.createNewAbastecimentoModel().getId()));
//
//    }
//
//}