package ma.zyn.app.unit.service.impl.admin.transaction;

import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.dao.facade.core.transaction.MoyenPaiementDao;
import ma.zyn.app.service.impl.admin.transaction.MoyenPaiementAdminServiceImpl;

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
class MoyenPaiementAdminServiceImplTest {

    @Mock
    private MoyenPaiementDao repository;
    private AutoCloseable autoCloseable;
    private MoyenPaiementAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MoyenPaiementAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllMoyenPaiement() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveMoyenPaiement() {
        // Given
        MoyenPaiement toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteMoyenPaiement() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetMoyenPaiementById() {
        // Given
        Long idToRetrieve = 1L; // Example MoyenPaiement ID to retrieve
        MoyenPaiement expected = new MoyenPaiement(); // You need to replace MoyenPaiement with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        MoyenPaiement result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private MoyenPaiement constructSample(int i) {
		MoyenPaiement given = new MoyenPaiement();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
