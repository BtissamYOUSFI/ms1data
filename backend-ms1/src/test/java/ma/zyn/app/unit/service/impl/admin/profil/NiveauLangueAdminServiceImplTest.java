package ma.zyn.app.unit.service.impl.admin.profil;

import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.dao.facade.core.profil.NiveauLangueDao;
import ma.zyn.app.service.impl.admin.profil.NiveauLangueAdminServiceImpl;

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
class NiveauLangueAdminServiceImplTest {

    @Mock
    private NiveauLangueDao repository;
    private AutoCloseable autoCloseable;
    private NiveauLangueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new NiveauLangueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllNiveauLangue() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveNiveauLangue() {
        // Given
        NiveauLangue toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteNiveauLangue() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetNiveauLangueById() {
        // Given
        Long idToRetrieve = 1L; // Example NiveauLangue ID to retrieve
        NiveauLangue expected = new NiveauLangue(); // You need to replace NiveauLangue with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        NiveauLangue result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private NiveauLangue constructSample(int i) {
		NiveauLangue given = new NiveauLangue();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
