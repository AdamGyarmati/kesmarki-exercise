package hu.gyarmati.kemarkiexercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdatePersonDto;
import hu.gyarmati.kemarkiexercise.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @DisplayName("Test for savePerson method")
    @Test
    public void CanSaveANewPerson() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/api/persons").contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PersonInfoDto(1L, "John Doe")))
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(CREATED.value());
    }

    @DisplayName("Test for getPersonById method")
    @Test
    public void canGetPersonById() throws Exception {
        PersonDetailsDto johnDoe = new PersonDetailsDto(1L, "John Doe", Collections.emptyList());

        given(personService.getPersonById(1L))
                .willReturn(johnDoe);

        MockHttpServletResponse response = mvc.perform(
                get("/api/persons/1").accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                objectMapper.writeValueAsString(johnDoe)
        );
    }

    @DisplayName("Test for getAllPerson method")
    @Test
    public void canGetAllPerson() throws Exception {
        PersonDetailsDto johnDoe = new PersonDetailsDto(1L, "John Doe", Collections.emptyList());
        PersonDetailsDto janeDoe = new PersonDetailsDto(2L, "Jane Doe", Collections.emptyList());

        given(personService.getAllPerson())
                .willReturn(List.of(johnDoe, janeDoe));

        MockHttpServletResponse response = mvc.perform(
                get("/api/persons/all").accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(new PersonDetailsDto[]{johnDoe, janeDoe}));
    }

    @DisplayName("Test for updatePerson method")
    @Test
    public void canUpdatePerson() throws Exception {
        SaveAndUpdatePersonDto dto = new SaveAndUpdatePersonDto("Jane Doe");
        PersonInfoDto janeDoe = new PersonInfoDto(1L, "Jane Doe");

        given(personService.updatePerson(1L, dto)).willReturn(janeDoe);

        MockHttpServletResponse response = mvc.perform(
                put("/api/persons/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(janeDoe));
    }

    @DisplayName("Test for deletePerson method")
    @Test
    public void canDeletePerson() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                delete("/api/persons/1")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(NO_CONTENT.value());
    }
}
