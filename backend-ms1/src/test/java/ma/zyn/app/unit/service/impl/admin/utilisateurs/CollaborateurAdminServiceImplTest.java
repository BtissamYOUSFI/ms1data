package ma.zyn.app.unit.service.impl.admin.utilisateurs;

import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.dao.facade.core.utilisateurs.CollaborateurDao;
import ma.zyn.app.service.impl.admin.utilisateurs.CollaborateurAdminServiceImpl;

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
class CollaborateurAdminServiceImplTest {

    @Mock
    private CollaborateurDao repository;
    private AutoCloseable autoCloseable;
    private CollaborateurAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CollaborateurAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCollaborateur() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCollaborateur() {
        // Given
        Collaborateur toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCollaborateur() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCollaborateurById() {
        // Given
        Long idToRetrieve = 1L; // Example Collaborateur ID to retrieve
        Collaborateur expected = new Collaborateur(); // You need to replace Collaborateur with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Collaborateur result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Collaborateur constructSample(int i) {
		Collaborateur given = new Collaborateur();
        given.setDescription("description-"+i);
        given.setEmail("email-"+i);
        given.setEnabled(false);
        given.setCredentialsNonExpired(false);
        given.setAccountNonExpired(false);
        given.setUsername("username-"+i);
        given.setPasswordChanged(false);
        given.setAccountNonLocked(false);
        given.setPassword("password-"+i);
        return given;
    }

}
