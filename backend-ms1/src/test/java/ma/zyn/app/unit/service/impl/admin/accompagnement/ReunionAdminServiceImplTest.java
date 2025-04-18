package ma.zyn.app.unit.service.impl.admin.accompagnement;

import ma.zyn.app.bean.core.accompagnement.Reunion;
import ma.zyn.app.dao.facade.core.accompagnement.ReunionDao;
import ma.zyn.app.service.impl.admin.accompagnement.ReunionAdminServiceImpl;

import ma.zyn.app.bean.core.accompagnement.EtatReunion ;
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
class ReunionAdminServiceImplTest {

    @Mock
    private ReunionDao repository;
    private AutoCloseable autoCloseable;
    private ReunionAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ReunionAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllReunion() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveReunion() {
        // Given
        Reunion toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteReunion() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetReunionById() {
        // Given
        Long idToRetrieve = 1L; // Example Reunion ID to retrieve
        Reunion expected = new Reunion(); // You need to replace Reunion with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Reunion result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Reunion constructSample(int i) {
		Reunion given = new Reunion();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setCollaborateur(new Collaborateur(1L));
        given.setEtatReunion(new EtatReunion(1L));
        return given;
    }

}
