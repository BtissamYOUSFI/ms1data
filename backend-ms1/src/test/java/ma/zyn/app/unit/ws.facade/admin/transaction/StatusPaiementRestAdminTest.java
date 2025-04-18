package ma.zyn.app.unit.ws.facade.admin.transaction;

import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.service.impl.admin.transaction.StatusPaiementAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.transaction.StatusPaiementRestAdmin;
import ma.zyn.app.ws.converter.transaction.StatusPaiementConverter;
import ma.zyn.app.ws.dto.transaction.StatusPaiementDto;
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
public class StatusPaiementRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private StatusPaiementAdminServiceImpl service;
    @Mock
    private StatusPaiementConverter converter;

    @InjectMocks
    private StatusPaiementRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllStatusPaiementTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<StatusPaiementDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<StatusPaiementDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveStatusPaiementTest() throws Exception {
        // Mock data
        StatusPaiementDto requestDto = new StatusPaiementDto();
        StatusPaiement entity = new StatusPaiement();
        StatusPaiement saved = new StatusPaiement();
        StatusPaiementDto savedDto = new StatusPaiementDto();

        // Mock the converter to return the statusPaiement object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved statusPaiement DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<StatusPaiementDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        StatusPaiementDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved statusPaiement DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
