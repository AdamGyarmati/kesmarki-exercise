package hu.gyarmati.kemarkiexercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.gyarmati.kemarkiexercise.domain.ContactInformationType;
import hu.gyarmati.kemarkiexercise.dto.AddressDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.ContactInformationInfoDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.service.ContactInformationService;
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

@WebMvcTest(ContactInformationController.class)
public class ContactInformationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContactInformationService contactInformationService;

    @DisplayName("Test for saveContactInformation method")
    @Test
    public void canSaveANewContactInformation() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/api/contact-informations").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ContactInformationInfoDto(1L, "Info", "PHONE")))
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(CREATED.value());
    }

    @DisplayName("Test for getContactInformationById method")
    @Test
    public void canGetContactInformationById() throws Exception {
        ContactInformationInfoDto contactInformationInfoDto = new ContactInformationInfoDto(1L, "Info", "PHONE");

        given(contactInformationService.getContactInformationById(1L))
                .willReturn(contactInformationInfoDto);

        MockHttpServletResponse response = mvc.perform(
                get("/api/contact-informations/1").accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                objectMapper.writeValueAsString(contactInformationInfoDto)
        );
    }

    @DisplayName("Test for getAllContactInformation method")
    @Test
    public void canGetAllContactInformation() throws Exception {
        ContactInformationInfoDto contactInformationInfoDto1 = new ContactInformationInfoDto(1L, "Info", "PHONE");
        ContactInformationInfoDto contactInformationInfoDto2 = new ContactInformationInfoDto(2L, "Info2", "EMAIL");

        given(contactInformationService.getAllContactInformation())
                .willReturn(List.of(contactInformationInfoDto1, contactInformationInfoDto2));

        MockHttpServletResponse response = mvc.perform(
                get("/api/contact-informations/all").accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(new ContactInformationInfoDto[]{contactInformationInfoDto1, contactInformationInfoDto2}));
    }
}
