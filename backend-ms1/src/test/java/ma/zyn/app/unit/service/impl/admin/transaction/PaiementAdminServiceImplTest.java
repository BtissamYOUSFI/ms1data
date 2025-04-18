package ma.zyn.app.unit.service.impl.admin.transaction;

import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.dao.facade.core.transaction.PaiementDao;
import ma.zyn.app.service.impl.admin.transaction.PaiementAdminServiceImpl;

import ma.zyn.app.bean.core.transaction.MoyenPaiement ;
import ma.zyn.app.bean.core.transaction.StatusPaiement ;
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
class PaiementAdminServiceImplTest {

    @Mock
    private PaiementDao repository;
    private AutoCloseable autoCloseable;
    private PaiementAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PaiementAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPaiement() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePaiement() {
        // Given
        Paiement toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePaiement() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPaiementById() {
        // Given
        Long idToRetrieve = 1L; // Example Paiement ID to retrieve
        Paiement expected = new Paiement(); // You need to replace Paiement with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Paiement result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Paiement constructSample(int i) {
		Paiement given = new Paiement();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setDescription("description-"+i);
        given.setMontant(BigDecimal.TEN);
        given.setDatePaiement(LocalDateTime.now());
        given.setMoyenPaiement(new MoyenPaiement(1L));
        given.setStatusPaiement(new StatusPaiement(1L));
        return given;
    }

}
