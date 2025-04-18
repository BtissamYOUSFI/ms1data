package ma.zyn.app.unit.service.impl.collaborateur.transaction;

import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.dao.facade.core.transaction.StatusPaiementDao;
import ma.zyn.app.service.impl.collaborateur.transaction.StatusPaiementCollaborateurServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class StatusPaiementCollaborateurServiceImplTest {

    @Mock
    private StatusPaiementDao repository;
    private AutoCloseable autoCloseable;
    private StatusPaiementCollaborateurServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StatusPaiementCollaborateurServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStatusPaiement() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveStatusPaiement() {
        // Given
        StatusPaiement toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteStatusPaiement() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetStatusPaiementById() {
        // Given
        Long idToRetrieve = 1L; // Example StatusPaiement ID to retrieve
        StatusPaiement expected = new StatusPaiement(); // You need to replace StatusPaiement with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        StatusPaiement result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private StatusPaiement constructSample(int i) {
		StatusPaiement given = new StatusPaiement();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
