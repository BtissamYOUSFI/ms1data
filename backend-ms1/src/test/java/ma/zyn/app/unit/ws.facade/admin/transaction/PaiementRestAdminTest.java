package ma.zyn.app.unit.ws.facade.admin.transaction;

import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.service.impl.admin.transaction.PaiementAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.transaction.PaiementRestAdmin;
import ma.zyn.app.ws.converter.transaction.PaiementConverter;
import ma.zyn.app.ws.dto.transaction.PaiementDto;
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
public class PaiementRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private PaiementAdminServiceImpl service;
    @Mock
    private PaiementConverter converter;

    @InjectMocks
    private PaiementRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllPaiementTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<PaiementDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<PaiementDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSavePaiementTest() throws Exception {
        // Mock data
        PaiementDto requestDto = new PaiementDto();
        Paiement entity = new Paiement();
        Paiement saved = new Paiement();
        PaiementDto savedDto = new PaiementDto();

        // Mock the converter to return the paiement object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved paiement DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<PaiementDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        PaiementDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved paiement DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getMontant(), responseBody.getMontant());
        assertEquals(savedDto.getDatePaiement(), responseBody.getDatePaiement());
    }

}
