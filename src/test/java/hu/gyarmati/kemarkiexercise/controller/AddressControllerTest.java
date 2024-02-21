package hu.gyarmati.kemarkiexercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.gyarmati.kemarkiexercise.dto.AddressDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.service.AddressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @DisplayName("Test for getAddressById method")
    @Test
    public void canGetAddressById() throws Exception {
        AddressDetailsDto addressDetailsDto = new AddressDetailsDto(1L, "PERMANENT", Collections.emptyList());

        given(addressService.getAddressById(1L))
                .willReturn(addressDetailsDto);

        MockHttpServletResponse response = mvc.perform(
                get("/api/addresses/1").accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                objectMapper.writeValueAsString(addressDetailsDto)
        );
    }

    @DisplayName("Test for getAllAddress method")
    @Test
    public void canGetAllAddress() throws Exception {
        AddressDetailsDto address1 = new AddressDetailsDto(1L, "PERMANENT", Collections.emptyList());
        AddressDetailsDto address2 = new AddressDetailsDto(2L, "TEMPORARY", Collections.emptyList());

        given(addressService.getAllAddress())
                .willReturn(List.of(address1, address2));

        MockHttpServletResponse response = mvc.perform(
                get("/api/addresses/all").accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(new AddressDetailsDto[]{address1, address2}));
    }
}
