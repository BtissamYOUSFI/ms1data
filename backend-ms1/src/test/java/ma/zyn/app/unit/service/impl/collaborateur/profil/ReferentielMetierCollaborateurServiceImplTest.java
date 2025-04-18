package ma.zyn.app.unit.service.impl.collaborateur.profil;

import ma.zyn.app.bean.core.profil.ReferentielMetier;
import ma.zyn.app.dao.facade.core.profil.ReferentielMetierDao;
import ma.zyn.app.service.impl.collaborateur.profil.ReferentielMetierCollaborateurServiceImpl;

import ma.zyn.app.bean.core.profil.Metier ;
import ma.zyn.app.bean.core.profil.NiveauLangue ;
import ma.zyn.app.bean.core.profil.Langue ;
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
class ReferentielMetierCollaborateurServiceImplTest {

    @Mock
    private ReferentielMetierDao repository;
    private AutoCloseable autoCloseable;
    private ReferentielMetierCollaborateurServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ReferentielMetierCollaborateurServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllReferentielMetier() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveReferentielMetier() {
        // Given
        ReferentielMetier toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteReferentielMetier() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetReferentielMetierById() {
        // Given
        Long idToRetrieve = 1L; // Example ReferentielMetier ID to retrieve
        ReferentielMetier expected = new ReferentielMetier(); // You need to replace ReferentielMetier with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ReferentielMetier result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ReferentielMetier constructSample(int i) {
		ReferentielMetier given = new ReferentielMetier();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setDescription("description-"+i);
        given.setMetier(new Metier(1L));
        given.setNombreHeuresExperienceMin(i);
        given.setLangue(new Langue(1L));
        given.setNiveauLangue(new NiveauLangue(1L));
        given.setScelleRouge(false);
        return given;
    }

}
