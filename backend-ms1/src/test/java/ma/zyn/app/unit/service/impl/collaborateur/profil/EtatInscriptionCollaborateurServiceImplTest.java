package ma.zyn.app.unit.service.impl.collaborateur.profil;

import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.dao.facade.core.profil.EtatInscriptionDao;
import ma.zyn.app.service.impl.collaborateur.profil.EtatInscriptionCollaborateurServiceImpl;

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
class EtatInscriptionCollaborateurServiceImplTest {

    @Mock
    private EtatInscriptionDao repository;
    private AutoCloseable autoCloseable;
    private EtatInscriptionCollaborateurServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EtatInscriptionCollaborateurServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllEtatInscription() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveEtatInscription() {
        // Given
        EtatInscription toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteEtatInscription() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetEtatInscriptionById() {
        // Given
        Long idToRetrieve = 1L; // Example EtatInscription ID to retrieve
        EtatInscription expected = new EtatInscription(); // You need to replace EtatInscription with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        EtatInscription result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private EtatInscription constructSample(int i) {
		EtatInscription given = new EtatInscription();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
