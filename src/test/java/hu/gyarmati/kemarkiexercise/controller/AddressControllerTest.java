package hu.gyarmati.kemarkiexercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.service.AddressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AddressController.class)
public class AddressControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    @DisplayName("Test for saveAddress method")
    @Test
    public void canSaveANewAddress() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/api/addresses").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AddressInfoDto(1L, "PERMANENT")))
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(CREATED.value());
    }
}
