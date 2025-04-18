package ma.zyn.app.unit.service.impl.collaborateur.profil;

import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.dao.facade.core.profil.InscriptionDao;
import ma.zyn.app.service.impl.collaborateur.profil.InscriptionCollaborateurServiceImpl;

import ma.zyn.app.bean.core.profil.Metier ;
import ma.zyn.app.bean.core.profil.NiveauLangue ;
import ma.zyn.app.bean.core.profil.EtatInscription ;
import ma.zyn.app.bean.core.profil.Langue ;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur ;
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
class InscriptionCollaborateurServiceImplTest {

    @Mock
    private InscriptionDao repository;
    private AutoCloseable autoCloseable;
    private InscriptionCollaborateurServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new InscriptionCollaborateurServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllInscription() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveInscription() {
        // Given
        Inscription toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteInscription() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetInscriptionById() {
        // Given
        Long idToRetrieve = 1L; // Example Inscription ID to retrieve
        Inscription expected = new Inscription(); // You need to replace Inscription with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Inscription result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Inscription constructSample(int i) {
		Inscription given = new Inscription();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setNom("nom-"+i);
        given.setPrenom("prenom-"+i);
        given.setEmail("email-"+i);
        given.setPassword("password-"+i);
        given.setLangue(new Langue(1L));
        given.setNiveauLangue(new NiveauLangue(1L));
        given.setMetier(new Metier(1L));
        given.setNombreHeureExperience(i);
        given.setEtatInscription(new EtatInscription(1L));
        given.setCollaborateur(new Collaborateur(1L));
        return given;
    }

}
