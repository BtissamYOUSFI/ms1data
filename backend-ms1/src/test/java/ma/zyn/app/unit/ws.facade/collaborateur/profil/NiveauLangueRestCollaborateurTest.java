package ma.zyn.app.unit.ws.facade.collaborateur.profil;

import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.service.impl.collaborateur.profil.NiveauLangueCollaborateurServiceImpl;
import ma.zyn.app.ws.facade.collaborateur.profil.NiveauLangueRestCollaborateur;
import ma.zyn.app.ws.converter.profil.NiveauLangueConverter;
import ma.zyn.app.ws.dto.profil.NiveauLangueDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NiveauLangueRestCollaborateurTest {

    private MockMvc mockMvc;

    @Mock
    private NiveauLangueCollaborateurServiceImpl service;
    @Mock
    private NiveauLangueConverter converter;

    @InjectMocks
    private NiveauLangueRestCollaborateur controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllNiveauLangueTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<NiveauLangueDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<NiveauLangueDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveNiveauLangueTest() throws Exception {
        // Mock data
        NiveauLangueDto requestDto = new NiveauLangueDto();
        NiveauLangue entity = new NiveauLangue();
        NiveauLangue saved = new NiveauLangue();
        NiveauLangueDto savedDto = new NiveauLangueDto();

        // Mock the converter to return the niveauLangue object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved niveauLangue DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<NiveauLangueDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        NiveauLangueDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved niveauLangue DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
