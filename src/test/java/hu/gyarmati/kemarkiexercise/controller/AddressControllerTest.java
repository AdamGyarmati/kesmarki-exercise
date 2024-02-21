package hu.gyarmati.kemarkiexercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.gyarmati.kemarkiexercise.dto.*;
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
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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

    @DisplayName("Test for updatePerson method")
    @Test
    public void canUpdatePerson() throws Exception {
        SaveAndUpdateAddressDto dto = new SaveAndUpdateAddressDto("PERMANENT", 2L);
        AddressInfoDto addressInfo = new AddressInfoDto(1L, "PERMANENT");

        given(addressService.updateAddress(1L, dto)).willReturn(addressInfo);

        MockHttpServletResponse response = mvc.perform(
                put("/api/addresses/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(addressInfo));
    }

    @DisplayName("Test for deleteAddress method")
    @Test
    public void canDeleteAddress() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                delete("/api/addresses/1")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(NO_CONTENT.value());
    }
}
